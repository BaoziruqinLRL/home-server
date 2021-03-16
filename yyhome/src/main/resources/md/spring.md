[toc]

## Spring

### 核心原理
- IOC
    称为控制反转。将原本由程序自己控制的对象的创建和生命周期交由spring控制，通常实例化一个对象时需要我们new一个对象，
    但是IOC把这个工作交给了Spring框架；
- IOC容器
    具有依赖注入（DI）功能的容器，是创建对象的容器。IOC负责实例化程序中的对象并建立对象之间的联系
- DI
    称为依赖注入，是IOC的实现方式。组件之间的依赖关系由容器运行时决定，是IOC中决定对象关联关系的一种实现方式，程序中
    所用到的外部资源会由IOC容器动态的注入到程序中（外部资源通常就是一些加了@Resource或@Autowired的类)
- AOP
    面向切面编程。将程序的公共方法抽出来，进行横向扩展，无需在程序中置入代码片段的编程方式成为AOP。通常进行方法抽象的方式是编写一段公共方法，然后由程序主动调用公共方法，而AOP则是通过切入点让程序在运行时插入代码块，而不用将抽象的方法在程序中调用。通常用于做一些日志记录、权限控制等统一的功能
    1. Aspect。切面，AOP中公共功能的实现类，通常是一个类，使用@Aspect修饰
    2. Advice。通知，切面的具体实现，也就是切入后具体要实现的功能
    3. JoinPoint。连接点，程序运行过程中的一个插入地点，此地点可能包含方法名，方法参数等等信息
    4. Pointcut。切入点，定义了切入的位置
    5. 实现原理。动态代理分为JDK动态代理和CGLIB动态代理。基本实现流程是，在调用Bean时，通过Spring的代理工厂创建代理对象，Spring会为代理对象创建外层拦截器，在执行的时候，代理对象会先执行外层拦截器，外层拦截器根据一些配置信息创建内层拦截器（也就是用户自己编写的拦截器，例如我们的AOP切面），组成一个拦截器链，使用递归的方式执行整个拦截器链
### BeanFactory和FactoryBean
    区别在于BeanFactory是一个Bean的管理工厂。FactroyBean是一个Bean的生成器。
- BeanFactory是IOC中的核心工厂接口，也就是IOC容器，负责实例化和管理配置各种对象。但它只是一个接口定义，实际的实现
  由很多个实现类在实现
- FactoryBean是一个生产和修饰对象生成的Bean工厂接口，用户可以通过实现该接口定制bean的实例化逻辑。其中包含三个方法，
  getObject(获取对象实例), booleanSingleton(是否单例，若是单例则会加入到单例缓存池)，getObjectType(返回对象类型)
## spring boot

### 启动原理
- SpringBootApplication注解
    1. EnableAutoConfiguration。内部结合了@Import(EnableAutoConfigurationImportSelector.class)，借助
    EnableAutoConfigurationImportSelector类，以及SpringFactoriesLoader工具的支持，找到
    META-INF/spring.factories文件定义的配置文件进行加载，并转化成JavaConfig形式的配置
    2. Configuration。定义了javaConfig形式的配置
    3. ComponentScan。定义了类扫描路径
### 执行过程
- 创建SpringApplicationRunListener，并开始监听
- 加载SpringBoot配置环境
- 将配置环境加入监听器中
- 创建应用上下文
- prepareContext将监听器，配置环境等跟上下文绑定
- refreshContext初始化自动配置，包括spring.factories里面的配置加载，bean的实例化等

### 分布式事务
- CAP定理
	1. C（Consistency一致性）。在分布式系统中的所有数据备份，在同一时刻是否同样的值。
	2. A（Availability可用性）。在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。
	3. P（Partition Tolerance分区容错性）。以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择。
- 由于网络的不可靠性，CAP三者不能共有
  1. **CA without P**。如果不要求P（不允许分区），则C（强一致性）和A（可用性）是可以保证的。但放弃P的同时也就意味着放弃了系统的扩展性，也就是分布式节点受限，没办法部署子节点，这是违背分布式系统设计的初衷的。
  2. **CP without A**。如果不要求A（可用），相当于每个请求都需要在服务器之间保持强一致，而P（分区）会导致同步时间无限延长(也就是等待数据同步完才能正常访问服务)，一旦发生网络故障或者消息丢失等情况，就要牺牲用户的体验，等待所有数据全部一致了之后再让用户访问系统。
  3. **AP wihtout C**。要高可用并允许分区，则需放弃一致性。一旦分区发生，节点之间可能会失去联系，为了高可用，每个节点只能用本地数据提供服务，而这样会导致全局数据的不一致性。

### 分布式事务两种方案
- XA Transaction。分为两个阶段
	1. 事务管理器要求分布式事务的每个业务节点预提交事务操作，并反映是否可以提交
	2. 事务协调器综合每个节点的数据，向所有节点发起提交数据或者回滚数据的要求
	这种方式实现简单，但是很容易存在不一致性，因为每一步都是多点调用，都有可能因为网络问题而导致部分节点收不到请求，而导致部分提交或部分回滚
- TCC事务补偿。分为三个步骤
	1. Try阶段。尝试执行，所有业务进行业务检查，确保业务是可以执行
	2. Confirm阶段。Try阶段全部成功后，进行Confirm，所有业务进行Confirm，当有任何一个失败时进行重试，所以Confirm业务要保证幂等性，因为可能重复尝试
	3. Cancel。取消执行，当Try阶段有任何失败时则进行Cancel，全部不执行。

### Bean加载过程
#### 1
	获取bean的定义->实例化生成对象->依赖注入->初始化拦截器和AOP等方法->类型转换
- 获取beanName。对传入的beanName做解析，转化为一个可以从BeanDefinition中获取bean的名称。传入可能是bean name、alias name或者factoryBean name（带&前缀）
- 合并BeanDifinition的定义。根据类的继承关系递归的合并所有涉及到的信息
- 开始实例化。使用构造函数或者BeanFactory创建Bean实例。拿到上一步的BeanDifinition开始创建实例，返回Bean的包装类
- 属性填充。填充一些由Spring控制的属性，例如依赖注入属性，这里要解决循环依赖问题，只能解决设值的循环依赖（例如@Resource，@Autowire），通过三级缓存解决
	1. singletonObjects
	2. earlySingletonObjects
	3. singletonFactories
- 初始化。调用拦截器或者用户自定义的初始化方法，触发BeanPostProcessor
- 获取最终的Bean。类型转换
#### 2
- 解析Bean，获取BeanDefinition，注册到BeanDefinitionRegistry（实际就是map缓存）
- DefaultListableBeanFactory调用getBean方法，首先检查缓存是否存在bean，若有则取出返回，若取出的是FactoryBean，则调用工厂的getBean获取具体的bean，否则就自己创建bean
- 创建之前调用BeanPostProcessor处理，可以允许返回一个bean的代理对象，而不是直接返回bean

### 类加载过程
- 加载。根据类的限定名，找到类文件，将其转成二进制字节流装载到方法区中，并在堆中生成一个Class对象。这里使用了各个类加载器去加载，如BootstrapLoader，extendLoader和ApplicationLoader
- 验证。验证文件的内容是否符合JVM的规范
- 准备。为类的静态变量进行分配内存和赋初始值，这里只是赋予0值，而不是用户定义的值；若是常量则赋代码中定义的值
- 解析。将符号引用转成直接引用
- 初始化。执行构造方法

###　MVC流程
- 用户请求到前端控制dispatcherServlet（前端控制器）
- dispatcherServlet调用HandlerMapping（处理器映射器）
- HandlerMapping根据url找到具体的Controller返回，并返回执行链
- DispatcherServlet调用HandlerAdapter（处理器适配器）按照一定的规则去执行处理器，例如加了拦截器的处理器，则先执行拦截器，再执行具体的Controller
- Adapter返回结果给控制器
- 控制器将ModelAndView传给ViewReslover（视图解析器）
- 解析后具体返回一个视图
- 最后控制器响应用户
