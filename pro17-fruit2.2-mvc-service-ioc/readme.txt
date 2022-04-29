review:
1.最初的做法是 一个请求对应一个servlet 这样存在的问题太多了
2.把一些列的请求都对应一个servlet, IndexServlet/AddServlet/EditServlet/DelServlet/UpdateServlet
  通过一个operate的值来决定调用FruitServlet中的哪一个方法
  使用的是switch-case
3.在上一个版本中,Servlet中充斥着大量的switch-case,如果项目业务规模扩大,那么会有很多Servlet
  因此,Servlet中使用了反射技术,我们规定operate的值和方法名一致,那么接受到operate的值是什么就表明我们需要调用的方法进行响应,如果找不到则抛出异常
4.在上一个版本中我们使用了反射技术,但是其实还是存在一定的问题:每一个servlet中都有类似的反射技术的代码.因此继续抽取,设计了中央控制器类:DispatcherServlet
  DispatcherServlet这个类的工作分为两大部分 :
  1.根据url定位到能够处理这个请求的controller组件：
    1)从url中提取servletPath : /fruit.do -> fruit
    2)根据fruit找到对应的组件: FruitController , 这个对应的依据我们存储在applicationContext.xml中
        通过DOM技术我们去解析XML文件, 在中央控制器中形成一个beanMap容器 , 用来存放所有的Controller组件
    3) 根据获取到的operate的值定位到我们FruitController中需要调用的方法

  2.调用Controller组件中的方法:
  1) 获取参数
     获取即将要调用的方法的参数签名信息 : Parameter[] parameters = method.getParameters();
     通过parameter.getName()获取参数的名称;
     准备了Object[] parameterValues 这个数组用来存放对应参数的参数值
     另外, 我们需要考虑参数的类型问题, 需要做类型转化的工作 通过parameter.getType()获取参数的类型
  2) 执行方法
     Object returnObj = method.invoke(controllerBean,parameterValues);
  3) 视图处理
     String returnStr = (String) returnObj;
     if (returnStr.startWith("redirect:")){
        ...
     } else if ...

今日内容：
1.再次学习servlet的初始化方法
    1).Servlet生命周期：实例化、初始化、服务、销毁
    2).Servlet中的初始化方法有两个：init() , init(config)
       其中带参数的方法代码如下:
       public void init(ServletConfig config){
        this.config = config;
        init();
       }
       另一个无参的init方法如下:
       public void init() throws ServletException{

       }
       如果我们想要在Servlet初始化做一些准备工作，我们就要重写init
       我们可以通过如下步骤去获取初始化设置的数据
       - 获取config对象 : ServletConfig config = getServletConfig();
       - 获取初始化参数值 : config.getInitParameter(key);
    3) 在web.xml文件中配置servlet

    4) 也可以通过注解的方式进行配置

2.学习Servlet中的ServletContext和<context-param>
    1) 获取ServletContext, 有很多方法
        在初始化方法中 : ServletContext ServletContext = getServletContext();
        在服务方法中也可以通过request对象获取 , 也可以通过session获取
        req.getServletContext(); req.getSession().getServletContext();
    2) 获取初始化值:
        servletContext.getInitParameter();

3.什么是业务层
    1) Model1和Model2
    MVC : Model(模型)、View(视图)、Controller(控制器)
    视图层 : 用于做数据展示以及和用户交互的一个界面
    控制层 : 能够接受客户端的请求,具体的业务功能还是要借助模型组件来完成
    模型层 : 模型分为很多种 : 有比较简单的pojo/vo(value object) , 有业务模型组件 , 有数据访问层组价
        1) pojo/vo: 值对象
        2) DAO: 数据访问对象
        3) BO: 业务对象

    区分业务对象和数据访问对象 :
    1) DAO中的方法都是单精度方法 一个方法只考虑一个操作 , 比如添加就是insert操作、查询就是select操作
    2) BO中的方法属于业务方法，也实际的业务是比较复杂的，因此业务方法的粒度还是比较粗的
    注册这个功能属于业务功能，也就是说注册这个方法属于业务方法
    那么这个业务方法中包含了多个DAO方法。也就是说注册这个业务功能需要通过多个DAO方法的组合调用，从而完成注册功能的开发
    注册：
        1. 检查用户名是否已被注册 - DAO中的查询操作
        2. 向用户表新增一条新用户记录 - DAO中的insert操作
        3. 向用户积分表中新增一条记录 - DAO中的insert操作
        4. 向系统消息表新增一条记录 - DAO中的insert操作
        5. 向系统日志表新增一条记录(某用户在某时某分) - DAO中的insert操作
        6. ...
    3) 在库存系统中添加业务组件

4.IOC
    1) 耦合/依赖
      依赖指的是XXX离不开XXX
      在软件系统中, 层与层之间是存在依赖的.我们也称之为耦合
      我们系统架构或者是设计的一个原则是: 高内聚低耦合
      层内部的组成应该是高度聚合的, 而层与层之间的关系应该是低耦合的, 最理想的情况0耦合
    2) IOC - 控制反转 / DI - 依赖注入
