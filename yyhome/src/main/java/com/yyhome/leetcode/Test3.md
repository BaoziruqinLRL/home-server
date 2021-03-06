## 锁和并发工具
### Synchronized
- java关键字，由jvm实现
- 是一个可重入锁，但是是非公平锁
- 锁对象:
    1. 锁代码块时，可指定对象
    2. 锁普通方法时，锁的是方法对应的实例(即this)
    3. 锁静态方法时，锁的是静态类
- 每个对象都有一个Monitor监视器锁，Synchronized通过MonitorEnter和MonitorExit两个原语，当Monitor进入数为0时，
  表示当前没有线程占用，则通过MonitorEnter让线程进入，并对进入数+1，之后必须由同一个线程执行MonitorExit使进入数
  为0，这个执行代码块才释放，随后才能被其它线程竞争
    
### Synchronized锁竞争过程
    JVM中维护了ContentionList，这个list是一个后进先出(LIFO)队列，同时还有一个EntryList；
    Synchronized的锁队列是阻塞队列；
- 首先线程一开始进行锁竞争，当竞争资源处于锁住状态，线程进行自旋，在自旋期间仍会一直尝试获取锁；
- 当自旋了一段时间后仍未获得锁，则进入ContentionList中阻塞，等待唤醒
- 当资源被释放时，jvm发起通知，ContentionList迁移线程到EntryList中，并指定EntryList中一个线程为就绪状态，由该
  线程自己去竞争锁
- 若竞争成功，则成为执行线程，开始执行代码；否则继续留在EntryList中等待唤醒

### Synchronized锁升级
    从轻到重为：无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁
- 偏向锁:
    1. 竞争：对象头的Mark Word有标记记录锁是否是偏向锁，当锁是偏向锁时，线程判断锁对象的线程id是否是自身，是的话
    则直接进入代码块执行而无需进行锁竞争，如果不是则使用CAS操作尝试获取锁，获取到则得到锁，获取不到，则升级为轻量
    级锁
    2. 释放：偏向锁不会主动释放，只有线程来竞争才会释放，当有线程竞争时，等待全局安全点后挂起偏向锁线程，检查该线程
    是否还存活，若否则恢复回无锁状态，若是则允许其它线程竞争该锁，此时升级为轻量级锁
- 轻量级锁：
    1. 竞争：基于对象头的Mark Word配置CAS操作来实现，当线程竞争锁时，线程会在自己的栈上创建一个对象的Mark Word
    拷贝，然后通过CAS操作将锁对象头的Mark Word指向自己，如果成功则获取锁，如果不成功则进行自旋获取锁，一段时间后
    升级为重量级锁
    2. 释放：使用CAS操作将对象头的Mark Word恢复，若恢复成功则释放锁回到无锁状态，否则锁已经膨胀
- 重量级锁：
    1. 竞争：当轻量级锁自旋达到一定次数仍未获取锁时，锁将升级为重量级锁，此时重量级锁依赖操作系统的原语进行锁竞争，
    而不再依赖于Mark Word对象头，使用Monitor锁对象依赖MonitorEnter和MonitorExit原语进行竞争，此时未竞争到锁
    的线程将会被阻塞挂起，等待锁释放
    
### ReentrantLock
- 继承自AQS(AbstractQueuedSynchronizer)
- 可以声明公平锁或非公平锁，内部有FairSync和NonfairSync
- 需要自己unlock
- 无法指定锁定的对象

### countDownLatch
    JDK提供的一个控制线程并发顺序的并发工具，内部维护一个初始值，调用wait()方法使线程阻塞等待，当调用countDown()
    时，对计数器减1，直到计数器减为0后，会唤醒阻塞线程继续执行。也就是靠阻塞+计数器的形式来实现控制并发顺序

### volatile
- 保证内存可见性，即保证最新读。即被volatile修饰的变量，在本身被修改后会立即刷新到主存，其它线程在读取时会直接读取
  主存的该值。而普通变量是不保证立即刷新的。
- 禁止指令重排序，保证执行有序。即被volatile修饰的变量，在jvm指令重排序时，会绝对保证该变量操作的顺序，简单地说，
  volatile之前的语句不会被重排序到它后面，之后的语句不会被重排序到它前面
- 不保证原子性。volatile只保证最新读，当多个线程同时读到最新值后对变量做修改此时并不保证读取和修改的原子性。原子性
  仍需要加锁处理
  
### Atomic包
    内部使用CAS+volatile实现线程安全的操作

### LongAddr
    采用分段锁机制，内部使用Cell数组维护每个线程自己的变量槽，当数值修改不存在竞争时，直接使用CAS操作修改base属性，
    否则计算线程hash值得到Cell数组槽位，线程只对该槽位使用CAS修改值，最后获得的值是所有槽位和base的累加和。减小锁
    的粒度，在高并发的情况下比Atomic类更优秀