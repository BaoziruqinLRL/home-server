[toc]

### 组成结构
- nameserver. 动态列表，节点列表，维护集群所有的broker信息，以及topic和broker的对应关系。在nameserver
和broker的心跳中会有信息交换，从而获得它所要维护的信息，可以理解为一个broker的索引。
- producer. 消息生产者。与nameserver建立通信，获取能发送消息的broker
- broker. MQ服务器，负责接收消息，持久化消息和发送消息。定时与nameserver进行心跳，同步集群信息。
- consumer. 消息消费者，从broker上获取消息。与nameserver建立通信，获取能拉取消息的broker

### 消费模式
- 集群消费。同一个group下的多个消费者，一条消息只会被其中一个消费者消费
- 广播模式。同一个group下的多个消费者，一条消息会被所有消费者消费

### 消息拉取方式
- RocketMQ只有pull模式，push模式实际也是采用pull长轮询的方式拉取消息。原因是RocketMQ不希望为了提高broker的消息推送效率，而造成consumer的消息堆积，因为全部采取pull的方式来获取消息

### 负载均衡模式
- producer端：连接到nameServer，获取topic和broker的关系，轮询将topic的消息平均的发送到不同的broker中，由此实现消息的负载均衡
- consumer端：连接到nameServer，通过负载均衡策略，决定消费者的消费方式，如平均模式，则所有消费者平均分配所有的消息队列。策略有如下几种：
    1. AllocateMessageQueueAveragely - 平均分配
        - 根据MessageQueue的数量除以consumer数量，平均给consumer分配queue，例如 C1-Q1, C1-Q2, C1-Q3, C2-Q4, C2-Q5, C3-Q6
    2. AllocateMessageQueueAveragelyByCircle - 环形分配
        - 每个consumer依次获取一个queue，直到结束，最终结果是交替的，例如 C1-Q1, C2-Q2, C3-Q3, C1-Q4, C2-Q5, C2-Q6
    3. AllocateMessageQueueByConfig - 手动配置（自定义配置）
        - 自定义配置，例如指定consumer指定queue
    4. AllocateMessageQueueByMachineRoom - 机房分配
        - 根据brokerName的结构如[机房A@b1]的方式划分机房，在相同机房的broker中采取平均分配策略。给consumer指定机房，如[机房A@C1]、[机房A@C2]、[机房B@C3],
        queue如下[机房A@b1]、[机房A@b2]、[机房A@b3]、[机房B@b4]、[机房B@b5]，则C1 C2平均分配b1 b2 b3，C3平均分配b4 b5
    5. AllocateMessageQueueConsistentHash - 一致性hash分配
        - 通过consumerID进行hash，得到consumer在0~2^31-1的环形上的分布，而后对每个queue进行hash计算，queue落在hash环上的地点顺时针遇到的第一个consumer就是要消费该queue的consumer
    6. AllocateMachineRoomNearby - 靠近机房分配
        - 第一次分配同机房分配策略，剩下的未被匹配上的queue则平均分配给所有consumer

### 消息重复消费
- ACK机制。消费完成后会发送ACK给broker，如果因为网络原因ACK发送失败，则broker会重新投递消息，引起重复消费

### 消息的消费顺序
- RocketMQ的消息队列是天然的FIFO（先进先出）队列，具有天然的顺序性，只要保证一些顺序消息在同一个queue中，则对于consumer来说一定是有顺序的消费
- 可能通过自己实现MessageQueueSelector接口，实现自己的消息发送逻辑，保证某些消息只发送到某个queue。例如根据业务的id进行hash取得某一个queue进行发送，保证一个业务的顺序性

### MQ如何保证消息的有序性消费
- 三把锁
    1. 在拉取消息的时候在broker上对队列加锁。如果加锁成功才拉取，失败则等待重试
    2. 在开始消费时在broker上对队列加锁。保证该队列全局只被一个消费者消费
    3. 消费过程中对本地的处理队列加锁（处理队列为从broker上拉取到的消息存放的队列，使用队列也是为了保证该队列下消息的有序性）。保证在本地消费者线程池消费中只会有一个消费者对其进行消费，同时在消费者负载均衡后也能保证队列消费完成再进行处理

### 保证消息不丢失
- producer端开启重试机制。更完善的可以在失败后让业务进行重试发送
- broker端。开启同步刷盘模式进行持久化，默认是异步的，但是开启后会有效率问题，这就是选择题
- consumer端。保证消息消费处理完成才提交ack，同时业务处理要保证幂等性，防止网络问题ack提交失败重复消费

### 消息堆积怎么处理
- 定位问题原因
    1. Producer太多，consumer太少消费不及时
    2. consumer出现了问题，代码不合理，死锁，http请求慢，宕机等问题
- 针对问题1，可以考虑是否是消费者设置不合理，consumer端可以设置并发消费者数量以及一次消费消息数量，以此可以控制消费速率。
其次考虑的是扩容，因为问题很明显是因为生产者速率远大于消费者速率导致，因为可以考虑consumer扩容，增加消费速率。
最后可以考虑producer熔断，针对的是一些没那么重要的消息，比如通知、实时日志等等，可以考虑在一定情况下限流，而改用业务主动拉取的方式解决。
- 针对问题2，优先考虑代码bug或服务器异常。如果无法及时处理，可以采取临时方案，将消息转移到其它临时topic，上线临时consumer进行处理，不阻塞主要链路，而后再着重解决当然问题。

### 死信队列
- 消费重试了一定次数之后（默认16次）进入死信队列
- 死信队列对应的是GroupId，不区分topic，同一个GroupId下消费失败的消息都会归到同一个死信队列中
- 重新将队列的消息发送到topic才可重新消费

### 分布式事务
- 步骤
    1. 预提交阶段。事务发起者预提交一个消息，此时不会真正提交给消费者
    2. 发起者执行业务逻辑。
    3. 发起者执行成功，向MQ发送commit消息，发送成功则消息真正提交，消费者可以消费；否则提交rollback消息，MQ将消息删除，不再发送
    4. 消费者消费成功，则返回成功，整个事务成功；否则就需要重试；重试还是失败则需要其它手段处理生产者已经提交的事务
- 事务启动时MQ会启动定时任务回查生产者业务状态，如果生产者迟迟无法发送commit或rollback消息，则由生产者主动去获取生产者的业务状态，如若还是获取不到，则主动rollback


