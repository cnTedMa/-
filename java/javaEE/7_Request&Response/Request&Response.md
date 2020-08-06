# Request
1. Request对象和Response对象的原理
   1. Request和Response对象由服务器创建
   2. Request对象用来获取请求消息, Reponse对象设置响应消息
2. Request功能:
   1. 获取请求行, 请求头, 请求体
   2. 请求转发: 一种在服务器内部的资源跳转方式
      1. 步骤:
         1. 通过Request对象获取请求转发器对象getRequestDispatcher
         2. 使用RequestDispatcher对象调用farward方法进行转发
      2. 特点:
         1. 浏览器地址栏路径不发生变化
         2. 只能转发到当前服务器内部的资源
         3. 转法是一次请求
      3. 共享数据;
         * 域对象: 一个有作用范围的对象, 可以在范围内共享数据
         * request域: 代表一次请求的范围, 一般用于请求转发的多个资源中共享数据
      4. 获取ServletContext

## BeanUtils工具类, 简化数据封装
1. JavaBean: 标准的Java类
   1. 要求
      1. 类必须被public修饰
      2. 必须提供空参的构造器
      3. 成员变量必须使用private修饰
      4. 提供公共的getter和setter方法
   2. 功能: 封装数据
2. 概念:
   1. 成员变量
   2. 属性: setter和getter方法截取后的产物
3. 方法:
   1. setProperty
   2. getProperty
   3. populate