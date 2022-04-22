1.设置编码
    post方式下，设置编码，防止中文乱码
    get方式目前不需要设置编码(基于tomcat8)
    注意：设置编码的这一句代码必须在所有的获取参数动作之前
    request.setCharacterEncoding("UTF-8");


2.Servlet的继承关系
    1.继承关系
    javax.servlet.Servlet接口
        javax.servlet.GenericServlet抽象类
            javax.servlet.http.HttpServlet抽象子类

    2.相关方法
        javax.servlet.Servlet接口：
          void init(config) - 初始化方法
          void service(request,response) - 服务方法
          void destroy() - 销毁方法

        javax.servlet.GenericServlet抽象类：
          void service(request,response) - 仍然是抽象的

        javax.servlet.http.HttpServlet 抽象子类：
          void service(request,response) - 不是抽象的

    3.小结
    1) 继承关系：HttpServlet -> GenericServlet -> Servlet
    2) Servlet中的核心方法：init(),service(),destroy()
    3) 服务方法：当有请求过来，service方法会自动相应(其实是tomcat容器调用的)
            在HttpServlet中我们会去分析请求的方式：到底是get、post、head还是delete等等
            然后再决定调用的是哪个do开头的方法
            那么在HttpServlet中这些do方法默认都是405的实现风格
    4) 因此，我们在新建Servlet时，我们才会去考虑请求方法，从而决定重写哪个do方法


3.Servlet的生命周期
    1) 生命周期：从出生到死亡的过程就是生命周期。对应Servlet中的三个方法：init(),service(),destroy()
    2) 默认情况下：
        第一次接收请求时，这个Servlet会进行实例化(调用构造方法)、初始化(调用init())、然后服务(调用service())
        从第二次请求开始，每一次都是服务
        当容器关闭时，其中的所有servlet实例都会被销毁，调用销毁方法
    3) 通过案例我们发现
        - Servlet实例只会创建一个，所有请求都是这个实例去响应
        - 第一次请求时，tomcat才会去实例化，初始化，然后再服务
        - 因此
    4) Servlet的初始化时机
        - 默认是第一次接收请求时，实例化，初始化
        - 我们可以通过<load-on-startup>来设置servlet启动的先后顺序，数字越小，启动越靠前，最小值0
    5) Servlet在容器中是：单例的、线程不安全的

4.Http协议
    1) Http称之为 超文本传输协议
    2) Http是无状态的
    3) Http请求响应包含两个部分：请求和响应
      - 请求:
        请求包含三个部分： 1.请求行 ; 2.请求消息头 ; 3.请求主体
        1) 请求行包含是三个信息:  1.请求的方式 ; 2.请求的URL ; 3.请求的协议(一般都是HTTP1.1)
        2) 请求消息头中包含了很多客户端需要告诉服务器的信息
        3) 请求体，三种情况
            get方法，没有请求体，但是有一个queryString
            post方法，有请求体，form data
            json格式，有请求体，request payload
      - 响应
        响应也包含三本: 1.响应行 ; 2.响应头 ; 响应体
        1) 响应行包含三个信息：1.协议 2.响应状态码(200) 3.响应状态(OK)
        2) 响应头：包含了服务器的信息; 服务器发送给浏览器的信息(内容的媒体类型、编码、内容长度等)
        3) 响应体：响应的实际内容(比如请求add.html页面时，响应的内容就是<html><head><body>...)


5.会话
    1) Http是无状态的
       - HTTP 无状态

    2) 会话跟踪技术
       - 客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后返回给客户端
       - 下次客户端给服务器发请求时，会把sessionID带给服务器，那么服务器就能获取到了
       - 常用API：
         request.getSession() -> 获取当前的会话，没有则创建一个新的会话
         request.getSession(true) -> 效果和不带参数相同
         request.getSession(false) -> 获取当前会话，没有则返回null，不会创建新的

         session.getId() -> 获取sessionID
         session.isNew() -> 判断当前session是否是新的
         session.getMaxInactiveInterval() -> session的非激活间隔时间，默认1800秒
         session.setMaxInactiveInterval()
         session.invalidate() -> 强制让会话立即失效


6.Thymeleaf