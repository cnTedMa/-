# Servlet
1. 概念: server applet运行在服务器的小程序
  * Servlet就是一个接口, 定义了Java类被浏览器访问到的规则
  * 自定义类, 实现servlet接口.
2. 快速入门: 
  1. 创建JavaEE项目
  2. 定义一个类, 实现Servlet接口
  3. 实现接口中的抽象方法
  4. 配置Servlet
    ```xml
    <!--在web.xml中配置Servlet-->
      <servlet>
          <servlet-name>demo1</servlet-name>
          <servlet-class>com.tate.web.servlet.ServletDemo1</servlet-class>
      </servlet>

      <servlet-mapping>
          <servlet-name>demo1</servlet-name>
          <url-pattern>/demo1</url-pattern>
      </servlet-mapping>
      ```
3. 执行原理:
  1. 当服务器接收到客户端浏览器的请求后, 会解析URL路径, 获取访问的Servlet的资源路径
  2. 查找web.xml文件, 是否有对应的``<url-pattern>``标签体内容
  3. 如果有, 则再找到对应的`<servelt-class>`全类名
  4. tomcat会将字节码文件加载进内存, 并且创建其对象
  5. 调用其方法
4. Servlet中的生命周期:
  1. 被创建: 执行init方法, 只执行一次
     * Servlet什么时候被创建
       * 默认情况下, 第一次被访问时, 创建
       * 可以配置Servlet的创建时机
         * 在Servlet标签下``<load-on-startup>``的值为0或正整数时 再服务器启动时创建; 负数时第一次被访问时创建
     * Servlet的init方法, 只执行一次, 说明一个Servlet在内存中只存在一个对象, Servlet是单例的
       * 多个用户同时访问时, 可能存在线程安全问题
       * 解决: 尽量不要在Servlet中定义成员变量, 即使定义了成员变量, 也不要对其修改
  2. 提供服务: 执行service方法, 执行多次
  3. 被销毁: 执行destroy方法, 只执行一次
5. Servlet3.0:
  * 好处: 
    * 支持注解配置, 可以不需要web.xml
  * 步骤:
    1. 创建JavaEE项目, 选择Servlet的版本3.0以上, 可以不创建web.xml
    2. 定义一个类, 实现Servlet接口
    3. 复写方法
    4. 在类上使用@WebServlet注解进行配置 ``@WebServlet("资源路径")``
6. 体系结构:
  * Servlet 接口 -> GenericServlet 抽象类 -> HttpServlet 抽象类
  * GenericServlet: 将Servlet接口中其他的方法做了默认空实现, 只讲service()方法作为抽象方法
  * HttpServlet: 对http协议的一种封装, 简化操作. 需要复写doGet()和doPost()方法
7. Servlet相关配置(@WebServlet注解)
   1. urlpattern: Servlet访问路径
      1. 一个Servlet可以定义多个访问路径: @WebServlet({"/1", "/2", "/3"})
      2. 路径定义规则:
         1. /xxx
         2. /xxx/xxx: 多层
         3. *.do: 通配符加自定义后缀