# Tomcat
## web相关概念回顾
1. 软件架构
   1. C/S: 客户端/服务器端
   2. B/S: 浏览器/服务器端
2. 资源分类
   1. 静态资源: 所有用户访问后, 得到的结果都是一样的, 称为静态资源. 静态资源可以直接被浏览器解析
      * 如: html, css, JavaScript
   2. 动态资源: 每个用户访问相同资源后, 得到的结果可能不一样, 称为动态资源. 动态资源被访问后, 需要先转换为静态资源, 再返回给浏览器
      * 如: servlet/jsp, php, asp
3. 通信三要素
   1. IP
   2. 端口
   3. 传输协议

## web服务器软件:
* 服务器: 安装了服务器软件的计算机
* 服务器软件: 接受用户的请求, 处理请求, 做出响应
* web服务器软件: 
  * 再web服务器软件中, 可以部署web项目, 让用户通过浏览器来访问这些项目
  * 有时候被称为web容器
* 常见的java相关的web服务器软件:
  * webLocig: Oracle公司, 大型的JavaEE服务器, 支持所有的JavaEE规范, 收费
  * webSphere: IBM公司, 大型的JavaEE服务器, 支持所有的JavaEE规范, 收费
  * JBOSS: JBOSS公司的, 大型的JavaEE服务器, 支持所有的JavaEE规范, 收费
  * Tomcat: Apache基金组织, 中小型的JavaEE服务器, 仅仅支持少量的JavaEE规范servlet/jsp. 开源
* JavaEE: Java语言再企业级开发中使用的技术规范的总和, 一共规定了13项大的规范
* Tomcat: web服务器软件
   1. 下载: tomcat.apache.org
   2. 安装: 解压即可
   3. 卸载: 删除目录即可
   4. 启动:
      1. 双击运行bin/startup.bat
      2. 访问: localhost:8080
   5. 关闭:
      1.  双击运行bin/shutdown.bat
      2.  Ctrl + C
   6. 部署
      * 部署项目的方式:
        1. 直接将项目放到webapps目录下即可
          * 简化部署: 将项目打成一个war包, 再将war包放置到webapps目录下, war包会自动解压缩
        2. 配置conf/server.xml文件
          * 在``<Host>``标签体中配置``<Context docBase="项目路径" path="/虚拟目录" />``
        3. 在conf/Catalina/localhost创建以虚拟目录命名的xml文件, 在文件中编写``<Context docBase="项目路径" />``
      * 静态项目和同台项目:
        * 目录结构
          * java动态项目的目录结构:
            ```
            -- 项目根目录
               -- WEB-INF目录
                  -- web.xml: web项目的核心配置文件
                  -- classes: 放置字节码文件的目录
                  -- lib: 防止依赖的jar包
            ```