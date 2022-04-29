review:
1.Servlet生命周期中的初始化方法 : init() , init(config)
    public void init(ServletConfig config){
        this.config = config;
        init();
   }
   因此, 如果我们需要在初始化时执行一些自定义操作, 那么我们可以重写无参的init方法
   我们可以通过getConfig()获取ServletConfig对象
   可以通过config.getInitParameter()获取初始化参数

2.通过ServletContext获取配置的上下文参数

3.MVC : V:view 视图 ; C:Controller 控制器 ; M:Model 模型
  模型有很多种: 数据访问模型(DAO) ; 业务逻辑模型(BO) ; 值对象模型(POJO) ; 数据传输对象(DTO)

4.IOC
    IOC - 控制反转 / ID - 依赖注入
    控制反转 :
    1) 之前在Servlet中, 创建service对象 , FruitService fruitService = new FruitServiceImpl();
       这句话如果出现在servlet中的某个方法内部 , 那么这个fruitServlet的作用域应该就是这个方法级别
       如果这句话出现在servlet的类中, 也就是说fruitService是一个成员变量, 那么这个fruitService的作用域应该就是这个servlet实例级别
    2) 之后我们在applicationContext.xml中定义了fruitService, 然后通过XML, 产生fruitService实例, 存放在beanMap中, 这个beanMap在一个BeanFactory中
       因此, 我们改变了之前的service实例, dao实例等等他们的声明周期, 控制权从程序员转移到BeanFactory。这个现象我们称之为控制反转

    依赖注入 :
    1) 之前我们在控制层出现代码: FruitService fruitService = new FruitServiceImpl();
        那么, 控制层和service层存在耦合
    2) 之后, 我们将代码修改成FruitService fruitService = null;
        然后, 在配置文件中配置:
        <bean id="fruit" class="com.study.fruit.controllers.FruitController">
            <property name="fruitService" ref="fruitService"/>
        </bean>

今日内容:
    1.过滤器Filter
    2.事务管理(TransactionManager, ThreadLocal, OpenSessionInViewFilter)
    3.监听器(Listener, ContextLoaderListener)


1.过滤器Filter
1) Filter也属于Servlet规范
2) Filter开发步骤: 新建类实现Filter接口, 然后实现其中的三个方法: init、doFilter、destroy
   配置Filter, 可以用注解的@WebFilter, 也可以用<filter><filter-mapping>
3) Filter在配置时, 和servlet一样, 也可以配置通配符, 例如@WebFilter("*.do")表示拦截所有以.do结尾的请求
4) 过滤器链
    1) 执行的顺序依次是 : A B C demo03 C2 B2 A2
    2) 如果采取的是注解的方式进行配置 , 那么过滤器链的拦截顺序时按照全类名的先后顺序排序
    3） 如果采取的是xml的方式进行配置, 那么按照配置的先后顺序怕羞

2.事务管理