[toc]


### 什么是死锁？

### 死锁产生的条件？

### 怎样避免死锁？

（1）破坏“不可剥夺”条件：一个进程不能获得所需要的全部资源时便处于等待状态，等待期间他占有的资源将被隐式的释放重新加入到 系统的资源列表中，可以被其他的进程使用，而等待的进程只有重新获得自己原有的资源以及新申请的资源才可以重新启动，执行。

（2）破坏”请求与保持条件”：第一种方法静态分配即每个进程在开始执行时就申请他所需要的全部资源。第二种是动态分配即每个进程在申请所需要的资源时他本身不占用系统资源。

（3）破坏“循环等待”条件：采用资源有序分配其基本思想是将系统中的所有资源顺序编号，将紧缺的，稀少的采用较大的编号，在申请资源时必须按照编号的顺序进行，一个进程只有获得较小编号的进程才能申请较大编号的进程。


### synchronized和Reentrantlock的区别？
        A.synchronized 是jvm的关键字，由jvm实现的锁，是非公平锁且不可中断，
        加锁解锁以及中间的线程唤醒阻塞等，都由jvm自己实现，使用简单但是不够灵活；
        Reentrantlock 是jdk提供的API，由用户自己调用，加锁解锁以及唤醒可由
        用户程序自定义，并且支持公平和非公平锁，可以被中断。
### ReentrantLock实现原理？
        ReentrantLock
答：简单来说，ReenTrantLock的实现是一种自旋锁，通过循环调用CAS操作来实现加锁。它的性能比较好也是因为避免了使线程进入内核态的阻塞状态。想尽办法避免线程进入内核的阻塞状态是我们去分析和理解锁设计的关键钥匙。


### 可重入锁是什么？

答：可重入锁，从字面来理解，就是可以重复进入的锁。它也叫做递归锁，指的是同一线程外层函数获得锁之后，内层递归函数仍然有获取该锁的代码，但不受影响。在JAVA环境下ReentrantLock和synchronized都是可重入锁。


### springboot自动配置原理？

### 事务的隔离级别？解释

### volatile关键字

### 单例模式实现

### cas原理

### ABA问题

### HTTP状态码

### 谈谈JMM

### linux修改文件权限

### linux查看文件的某一行

### redis数据结构

### redis线程模型

### redis为什么快？除了基于内存还有什么原因？

### 垃圾回收算法？新生代用什么？

### wait（）和sleep（）的区别？

### HTTP短连接和长连接

### 手撕代码：

给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:

输入: [3, 2, 1]

输出: 1

解释: 第三大的数是 1.


示例 2:

输入: [1, 2]

输出: 2

解释: 第三大的数不存在, 所以返回最大的数 2 .


示例 3:

输入: [2, 2, 3, 1]

输出: 1

解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。

存在两个值为2的数，它们都排第二