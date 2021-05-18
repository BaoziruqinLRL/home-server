### BIO、NIO和AIO的模型与区别
- 同步/异步 针对客户端而言，客户端发起请求后阻塞等待最终的响应则是同步；客户端发起请求后结束线程，等待服务端主动回调结果则是异步
- 阻塞/非阻塞 针对服务端而言，线程必须等待计算出返回结果并返回才能进行下一个计算则为阻塞；服务端提交任务之后，被动等待计算结果，此时可以继续处理并提交其他任务则为非阻塞
- BIO（Blocking IO）：同步阻塞模型，常见的web模型，一个请求即是一个线程，并发量等于线程数量
- NIO（Non-Blocking IO）：同步非阻塞模型，基于Reactor模型（基于事件驱动模式），使用多路复用器的模型，让所有的连接注册到多路复用器上，由一个Selector去轮询所有的连
接，当某个连接产生了IO请求时，将请求分配给后端的处理器线程池，由线程池分配线程处理具体的逻辑
- AIO（Asynchronous IO）：异步非阻塞模型

### select、poll和epoll
- https://www.jianshu.com/p/397449cadc9a
- select接收传入的fd_set集合，并将其从用户态拷贝到内核态，在内核态遍历轮询fd_set集合的文件句柄，找出有IO请求的连接交由服务端处理，fd_set数量限制为1024；select由
用户主动调用
- poll与select一样，区别在于poll的fd_set没有数量限制
- epoll采用回调通知的方式，与select和poll一样，都有一个多路复用注册器，不同的是当注册上来的连接有IO请求时会加入到Ready队列，并主动通知epoll函数，此时epoll才会主动
去获取该连接上的IO请求，交由后端处理

### Netty与Java NIO的关系


### Netty的线程模型，与Redis的区别
- https://zhuanlan.zhihu.com/p/92193290
- 使用Reactor模型。 Reactor模型分为单线程模型、多线程模型和主从模型。单线程Reactor模型由单个线程完成所有请求的连接、握手以及IO独写；多线程模型中由一个Acceptor线程
完成请求的连接和握手，而后将请求注册到一个NIO线程池中，由该线程池中的线程对连接的IO请求进行独写；主从模型中，Acceptor线程仅接收请求，之后将请求分配到一个NIO线程池，由
该线程池中的线程来完成握手认证的步骤，之后再注册给另一个NIO线程池，由该线程池去做最终的IO独写操作。几个模型的进阶类似于对线程做了更明细的分工
- Netty采用Reactor的模型，在初始化时，使用BossGroup线程池进行连接的握手认证，使用WorkGroup线程进行IO独写，更类似于主从Reactor模型；然而，当bossGroup和workGroup
设定为同一个线程池时，Netty的Reactor模型就变为了多线程模型，且进一步当线程池中线程数量为单个时，就变成了单线程Reactor模型。因此，netty其实可以支持单线程、多线程和主从
的Reactor模型，取决与启动服务时的配置设置，但是平时使用大多数还是使用主从Reactor模型
- 与Redis的区别是，Redis固定为单线程Reactor模型，因为Redis的设计决定了它的操作都不是耗时操作，处理效率大大提示，因此使用单线程模型足够

### Netty的粘包、拆包怎么处理，有哪些实现
- https://www.cnblogs.com/rickiyang/p/12904552.html
- 通过自定义的解码器处理。Netty提供定长解码器（FixedDecoder）、换行符解码器（LineBasedDecoder）、自定义符号解码器（DelimiterBasedDecoder）以及变长解码器（
LengthFieldDecoder）。
- 粘包拆包主要是由三点原因产生：
    1. socket窗口大小。读取和写入的速度大小不一样，缓冲区同时可能存在不同大小的可用空间，导致读取或写入的大小是不固定的
    2. MSS/MTU大小限制。即数据链路层定义的以太网传输帧大小限制
    3. Nagle算法。此算法会保证发送时要将缓冲区写满才一次性发送，而不是每次都发送比较小的不足缓冲区大小的数据块
    
### Netty的protobuf编解码机制
- protobuf协议相比json格式，减少了一次编解码的过程，并且通过预先定义好的.proto文件协助进行编解码，在数据量比较大的时候比json优势更大；且protobuf协议是二进制协议
- netty提供了ProtobufDecoder和Encoder来使用该协议

### Netty如何实现断线自动重连


### Netty如何支持单机百万连接

### 零拷贝原理

### 长连接心跳保活机制

### Netty的内存池实现

### Netty如何解决NIO底层epoll空转导致CPU 100%的bug

### Netty的高并发高性能体现在哪