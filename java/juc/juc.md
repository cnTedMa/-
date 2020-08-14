# JUC
## JMM Java内存模型规范
1. 可见性 (主内存的值被修改, 工作内存可以拿到最新的值)
2. 原子性 (操作不可打断)
3. 有序性
## volatile
1. 保证可见性 
2. 不保证原子性 (多个线程操纵资源类加1操作, 会出现写丢失)
   * 解决方法用原子类AtomicInteger...
3. 禁止指令重排
* 通过内存栅栏保证可见性和禁止指令重排: 在写时先读取一次; 在volatile变量操作前后设置栅栏
* 单例模式在多线程环境下: 
  1. 在整个getInstance方法前加synchronized太重了 -> 所以采用DCL double check lock双端检锁机制在进入synchronized同步代码块之前, 先判断一次单例对象是否为空, 进入同步代码块之后, 再判断一次, 如果为空, 则调用构造方法.
  2. 由于多线程环境下, 指令重排, 在极端情况下, 上述仍会出错, 所以要在单例对象前添加volatile关键字, 禁止指令重排
        ```java
        private static volatile SingletonDemo instance = null;

        public static SingletonDemo getInstance() {
            if (instance == null) {
                synchronized (SingletonDemo.class) {
                    if (instance == null) {
                        instance = new SingletonDemo();
                    }
                }
            }
            return instance;
        }
        ```
## CAS
1. compare and swap 比较并交换, 与预期值相同时才更改, 否则重新从主内存获得变量, 是原子类采用的思想
2. CAS的核心时unsafe类, 是rt包中的类, 其中都是native方法, 可以调用系统资源, 通过地址修改变量.
    ```java
    do {
        获得变量值
    } while (!与预期值相同则设置成功)
    ```
3. 缺点
   1. 虽然没有加锁提高了并发性, 但是循环时间长开销很大
   2. 会有ABA问题
4. 如何解决ABA问题
   * 使用AtomicStampedReference, 通过比较版本号
## 容器复习
1. ArrayList底层是一个数组, 初始大小为10, 每次扩容增加原大小的一半, 扩容使用的方法是Arrays.copyOf
2. HashSet底层结构是HashMap, 初始值为16, 负载因子0.75. put的时候(value, new Object一个常量)
3. 用过哪些集合类? 
   * Collection -> List -> ArrayList, LinkedList, CopyOnWriteList
   * Collection -> Queue -> BlockingQueue
## 集合类不安全
ArrayList不安全因为add方法不是synchronized方法, 多线程同时调用时会出现 *java.util.ConcurrentModificationException* 异常

HashMap, HashSet也不安全
* 解决方法
  1. Vector的add方法是synchronized的
  2. Collections工具类的方法
        ```java
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        ```
  3. JUC的写时复制类CopyOnWriteArrayList
* set版本:
  1. Collections工具类的synchronizedSet方法
  2. JUC的CopyOnWriteArraySet底层也是CopyOnWriteArrayList
* map版本:
  1. Collections工具类的synchronizedMap方法
  2. JUC的ConcurrentHashMap
## java锁
1. **公平和非公平锁**
   * 是什么
     1. 公平锁: 是指多个线程按照申请锁的顺序来获取锁, 类似排队打饭, 先来后到.
     2. 非公平锁: 是指多个线程获取锁的顺序并不是按照申请锁的顺序, 有可能后申请的线程比先申请的线程优先获取锁. 在高并发的情况下, 有可能会造成优先级反转或者饥饿现象.
   * 两者区别: juc中**ReentrantLock**的创建可以指定是否公平, **默认为非公平锁**, synchronized也是一种非公平锁
     1. 公平锁: 在并发环境中, 每个线程在获取锁时会先查看此锁维护的等待队列, 如果为空, 或者当前线程是等待队列的第一个, 就占有锁, 否则就会加入到等待队列中, 以后会按照FIFO的规则从队列中取到自己
     2. 非公平锁: 比较粗鲁, 上来就直接尝试占有锁, 如果尝试失败, 就再采用类似公平锁那种方式.
     3. 非公平锁的优点在于吞吐量比公平锁大.
2. **可重入锁(又名递归锁)**
   * 是什么: 指的是同一线程外层函数获得锁之后, 内层递归函数仍然能获取该锁的代码, 在同一个线程在外层方法获取锁的时候, 在进入内层方法会自动获取锁, 也就是说, 线程可以进入任何一个它已经拥有的锁所同步着的代码块
   * ReentrantLock/synchronized就是典型的可重入锁
   * 可避免死锁
3. **自旋锁**
   * 是什么: 是指尝试获取锁的线程不会立即阻塞, 而是采用循环的方式去尝试获取锁, 这样的好处是减少线程上下文切换的消耗, 缺点是循环会消耗CPU
4. **独占锁(写锁)/共享锁(读锁)/互斥锁**
   * 独占锁: 指该锁一次只能被一个线程所持有. ReentrantLock和Synchronized都是独占锁.
   * 共享锁: 指该锁可被多个线程所持有.
   * ReentrantReadWriteLock: 读锁时共享锁, 写锁时独占锁.
5. **synchronized和Lock有什么区别**
   1. **原始构成**
      * synchronized是关键字属于jvm层面, synchronized代码块通过monitorenter和monitorexit控制, 底层是通过monitor对象来完成
      * Lock是juc的具体类, 是api层面的锁
   2. **使用方法**
      * synchronized不需要用户去手动释放锁
      * ReentrantLock则需要用户手动释放锁, 若没有主动释放锁, 就有可能导致死锁
   3. **等待是否可中断**
      * synchronized不可中断, 除非抛出异常或者正常运行完成
      * ReentrantLock可中断: 
        1. 设置超时方法trylock
        2. lockInterruptibly()放代码中, 调用interrupt方法可中断
   4. **加锁是否公平**
      * synchronized是非公平锁
      * ReentrantLock默认非公平锁, 可以设置为公平锁
   5. **锁绑定多个条件**
      * synchronized不支持
      * ReentrantLock可以实现分组精准唤醒
## CountDownLatch, CyclicBarrier, Semaphore
1. **CountDownLatch:** 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒.
   * CountDownLatch主要有两个方法, 当一个或多个线程调用await方法时, 调用线程会被阻塞. 其他线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞), 当计数器的值变为0时, 因调用await方法被阻塞的线程会被唤醒, 继续执行.
2. **CyclicBarrier:** 让一组线程到达屏障时被阻塞, 直到最后一个线程到达屏障时, 所有被屏障拦截的线程才会继续干活, 线程进入屏障通过await方法
3. **Semaphore:** 信号量主要用于两个目的, 一个是用于多个共享资源的互斥使用, 另一个用于并发线程数的控制.
## 阻塞队列
1. **什么是?**
   * 当阻塞队列是空时, 从队列中获取元素的操作将会被阻塞.
   * 当阻塞队列是满时, 往队列里添加元素的操作将会被阻塞.
2. **为什么用? 有什么好处?**
   * 不需要关心什么时候需要阻塞线程, 什么时候需要唤醒线程, 因为这一切BlockingQueue都一手包办了. 在juc包发布以前, 在多线程环境下, 我们每个程序员都必须去自己控制这些细节, 尤其还有兼顾效率和线程安全, 而这会给我们的程序带来不小的复杂度.
3. **种类分析:** BlockingQueue是接口, 有以下实现类
   1. ***ArrayBlockingQueue:*** 由数组结构组成的**有界**阻塞队列
   2. ***LinkedBlockingQueue:*** 由链表结构组成的**有界**阻塞队列(**但大小默认值为Integer.MAX_VALUE**)
   3. *PriorityBlockingQueue:* 支持优先级排序的无界阻塞队列
   4. *DelayQueue:* 使用优先级队列实现的延迟无界阻塞队列
   5. ***SynchronousQueue:*** 不存储元素的阻塞队列, 也即单个元素的队列
   6. *LinkedTransferQueue:* 由链表结构组成的无界阻塞队列
   7. *LinkedBlockingDeque:* 由链表结构组成的双向阻塞队列
4. **核心方法:**
   1. 抛出异常组: add, remove, element. 只要失败就抛异常
   2. 返回布尔值组: offer, poll, peek. 失败返回false, 取元素失败返回null
   3. 一直阻塞组: put, take
   4. 超时控制: offer, poll. 参数设置阻塞多久, 超时则退出
5. **同步阻塞队列SynchronousQueue:** 每一个put操作必须要等待一个take操作, 否则不能继续添加元素, 反之亦然.
6. **用在哪里**
   1. 生产者消费者
   2. 线程池
   3. 消息中间件
## 线程池
1. 为什么用线程池, 优势
   * 线程池做的工作主要是