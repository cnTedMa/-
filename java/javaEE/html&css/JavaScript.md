# JavaScript
* 概念: 一门客户端脚本语言
  * 运行在客户端浏览器中, 每一个浏览器都有JavaScript的解析引擎
  * 脚本语言: 不需要编译, 直接就可以被浏览器解析执行
* 功能:
  * 可以增强用户和html页面的交互过程, 可以控制html元素, 让页面有一些动态效果, 增强用户的体验
* JavaScript发展史:
  1. 1992年, Nombase公司, 开发出第一门客户端脚本语言, 专门用于表单的检验. 命名为: C--, 后来更名为: ScriptEase
  2. 1995年, Netscape(网景)公司, 开发了一门客户端脚本语言: LiveScript. 后来, 请来SUN公司的专家, 修改LiveScript, 命名为JavaScript
  3. 1996年, 微软抄袭JavaScript开发出Jscript语言
  4. 1997年, ECMA(欧洲计算机制造商协会), ECMAScript, 就是所有客户端脚本语言的标准
  * JavaScript = ECMAScript + JavaScript自己特有的东西(BOM + DOM)
* ECMAScript: 客户端脚本语言的标准
  1. 基本语法:
     1. 与html结合方式
        1. 内部JS
           * 定义script, 标签体内容就是js代码
        2. 外部JS
           * 定义script, 通过src属性引入外部的js文件
        * 注意:
          1. script可以定义在html页面的任何地方, 但是定义的位置会影响执行顺序
          2. script可以定义多个
     2. 注释
        1. 单行注释 // ...
        2. 多行注释 /* ... */
     3. 数据类型
        1. 原始数据类型(基本数据类型):
           1. number: 数字. 整数/小数/NaN(not a number一个不是数字的数字类型)
           2. string: 字符串. 字符串
           3. boolean: true和false
           4. null: 一个对象为空的占位符
           5. undefined: 未定义. 如果一个变量没有给初始化值, 则会被默认赋值为undefined
        2. 引用数据类型: 对象
     4. 变量
        * 变量: 一小块存储数据的内存空间
        * Java语言是强类型语言, 而JavaScript是弱类型语言.
          * 强类型: 在开辟变量存储空间是, 定义了空间将来存储数据的类型, 只能存储固定类型的数据
          * 弱类型: 在开辟变量存储空间时, 不定义空间将来存储数据的类型, 可以存放任意类型的数据
        * 语法:
          * var 变量名 = 初始值
     5. 运算符
     6. 流程控制语句
  2. 基本对象