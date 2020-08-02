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
## ECMAScript: 客户端脚本语言的标准
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
        1. 一元运算符: 只有一个运算数的运算符 ++, --, +(正号) ...
           * 注意: 在JS中, 如果运算数不是运算符所要求的类型, 那么JS引擎会自动地将运算数进行类型转换
             * 其他类型转number:
               * string转number: 按照字面值转换. 如果字面值不是数字, 则转为NaN
               * boolean转number: true转为1, false转为0
        2. 算术运算符: + - * / % ...
        3. 赋值运算符: = += -= ...
        4. 比较运算符: > < >= <= == ===(全等于)
           * 比较方式
             1. 类型相同: 直接比较
                * 字符串: 按照字典顺序比较, 按位逐一比较, 直到得出大小位置
             2. 类型不同: 先进行类型转换, 再比较
                * ===: 全等于. 再比较之前,先判断类型, 如果类型不一样, 则直接返回false 
        5. 逻辑运算符: && || !
           * 其他类型转boolean:
             1. number: 0或NaN为假, 其他为真
             2. string: 除了空字符串(""), 其他为真
             3. null&undefined: 都是false
             4. 对象: 所有对象都是true
        6. 三元运算符: ? :
     6. 流程控制语句: 
        1. if...else...
        2. switch
           * 在java中, switch语句可以接受的数据类型: byte int short char 枚举(1.5) String(1.7)
               ```java
               swich(变量)
               case 值:
               ```
           * 在JS中, switch语句可以接受任意的原始数据类型
        3. while
        4. do...while
        5. for
     7. JS特殊语法: 
        1. 语句以;结尾, 如果一行只有一条语句则;可以省略(不建议)
        2. 变量的定义使用var关键字, 也可以不使用
           * 用: 定义的变量是局部变量
           * 不用: 定义的变量是全局变量(不建议)
  2. 基本对象
     1. Function: 函数(方法)对象
       1. 创建:
          1. ``var fun = new Function(形式参数列表, 方法体); // 了解即可``
          2. ``function 方法名称(形式参数列表) {方法体}``
          3. ``var 方法名 = function(形式参数列表) {方法体}``
       2. 方法:
       3. 属性: 
          1. length: 代表形参的个数
       4. 特点
          1. 方法定义时, 形参的类型不用写, 返回值类型也不写
          2. 方法是一个对象, 如果定义名称相同的方法, 会覆盖
          3. 在JS中, 方法的调用只与方法的名称有关, 和参数列表无关
          4. 在方法声明中有一个隐藏的内置对象(数组), arguments, 封装所有的实际参数
       5. 调用
          
            方法名称(实际参数列表);
     2. Array: 数组对象
       6. 创建
          1. ``var arr = new Array(元素列表)``
          2. ``var arr = new Array(默认长度)``
          3. ``var arr = [元素列表]``
       7. 方法
          1. join(参数): 将数组中的元素按照指定的分隔符拼接成字符串 
          2. push(): 向数组的末尾添加一个或更多元素, 并返回新的长度.
       8. 属性
          
          length: 数组的长度
       9. 特点
          1. JS中, 数组元素的类型是可变的.
          2. JS中, 数组的长度是可变的.
     3. Boolean
     4. Date: 日期对象
        1. 创建: var date = new Date();
        2. 方法
           1. toLocaleString(): 返回当前date对象对应的时间本地字符串格式
           2. getTime(): 获取毫秒值
     5. Math: 数学对象
        1. 创建:
           * 特点: Math对象不用创建, 直接使用.
        2. 方法:
           1. random(): 返回0-1之间的随机数. 含0不含1
           2. ceil(): 向上取整
           3. floor(): 向下取整
           4. round(): 四舍五入
        3. 属性: PI等
     6. Number
     7. String
     8. RegExp: 正则表达式对象
        1. 正则表达式: 定义字符串的组成规则
           1. 单个字符: []
              * 如[a] [ab] [a-zA-Z0-9_]
              * 特殊符号代表特殊含义的单个字符:
                * \d: 单个数字字符[0-9]
                * \w: 单个单词字符[a-zA-Z0-9_]
           2. 量词符号:
              * ?: 表示出现0次或1次
              * *: 表示出现0次或多次
              * +: 表示出现1次或多次
              * {m, n}: 表示m <= 数量 <= n
                * m缺省: {, n}最多n次
                * n缺省: {m, }最少m次
           3. 开始结束符号
              * ^: 开始
              * $: 结束
        2. 正则对象:
           1. 创建
              1. ``var reg = new RegExp("正则表达式");``
              2. ``var reg = /正则表达式/;``
           2. 方法
              1. test(参数): 验证指定的字符串是否符合正则定义的规范
     9. Global
        1. 特点: 全局对象, Global中封装的方法不需要对象就可以直接调用
        2. 方法: 
           1. encodeURI()
           2. decodeURI()
           3. encodeURIComponent()
           4. decodeURIComponent()
           5. parseInt(): 逐一判断每一个字符是否为数字, 直到不是数字为止, 将前边数字部分转为number
           6. isNaN(): 判断一个值是否为NaN
           7. eval(): 将JavaScript字符串转为脚本执行
## DOM简单学习
* 功能: 控制html文档的内容
* 代码: 获取页面标签(元素)对象 Element
   ```javascript
   document.getElementById("id值") // 通过元素id获取元素对象
   ```
* 操作Element对象:
  1. 修改属性值
     1. 明确获取的对象是哪一个
     2. 查看API文档, 找其中有哪些属性可以设置
  2. 修改标签体内容
## 事件简单学习
* 功能: 某些组件被执行了某些操作后, 出发某些代码的执行.
* 如何绑定事件
  1. 直接在html标签上, 指定事件的属性, 属性值为js代码
     1. onclick --单击事件
  2. 通过js获取元素对象, 指定事件属性, 设置一个函数
## BOM:
1. 概念：Browser Object Model浏览器对象模型
   * 将浏览器的各个组成部分封装成对象。
2. 组成：
   * Window: 窗口对象
   * Navigator: 浏览器对象
   * Screen: 显示器屏幕对象
   * History: 历史记录对象
   * Location: 地址栏对象
3. Window: 窗口对象
   1. 创建
   2. 方法
      1. 与弹出框有关的方法：
         1. alert() 显示待有一段消息和一个确认按钮的警告框
         2. confirm() 显示带有一段消息以及确认按钮和取消按钮的对话框
            * 如果用户点击确定按钮，则方法返回true
            * 如果用户点击取消按钮，则方法返回false
         3. prompt() 显示可提示用户输入的对话框
            * 返回值是用户输入的值
      2. 与打开关闭有关的方法
         1. close() 关闭浏览器窗口
         2. open() 打开一个新的浏览器窗口
      3. 与定时器有关的方法
         1. setTimeout() 在指定的毫秒数后调用函数或计算表达式
         2. clearTimeout() 取消由setTimeout()方法设置的timeout
         3. setIntervar() 按照指定的周期（以毫秒计）来调用函数或计算表达式
         4. clearInterval() 取消由setInterval()设置的timeout
   3. 属性
      1. 获取其他BOM对象：History, Location, Navigator, Screen
      2. 获取DOM对象: document
   4. 特点
      * Window对象不需要创建可以直接使用  window.方法名();
      * Window引用可以省略 方法名();
4. Location: 地址栏对象
   1. 创建（获取）：
      1. window.location
      2. location
   2. 方法
      * reload() 重新加载当前窗口
   3. 属性
      * href 设置或返回完整的URL
5. History: 历史记录对象
   1. 创建（获取）：
      1. window.history
      2. history
   2. 方法
      * back() 加载history列表中的前一个URL
      * forward() 加载history列表中的下一个URL
      * go(参数) 加载history列表中的某个具体页面
        * 参数：
          * 正数：前进几个历史记录
          * 负数：后退几个历史记录
   3. 属性
      * length 返回当前窗口历史列表中的URL书香
## DOM:
* 概念：Document Object Model文档对象模型
  * 将标记语言文档的各个组成部分，封装为对象。可以使用这些对象，对标记语言文档进行CRUD的动态操作
* W3C DOM标准被分为3个不同的部分：
  * 核心 DOM - 针对任何结构化文档的标准模型
    * Document：文档对象
    * Element：元素对象
    * Attribute：属性对象
    * Text：文本对象
    * Comment：注释对象
    * Node：节点对象，其他5个的父对象
  * XML DOM - 针对XML文档的标准模型
  * HTML DOM - 针对HTML文档的标准模型
* 核心DOM模型：
  * Document：文档对象
    1. 创建（获取）：在html dom模型中可以使用window对象来获取
       1. window.document
       2. document
    2. 方法
       1. 获取Element对象：
          1. getElementById(): 根据id属性值获取元素对象，id属性值一般唯一
          2. getElementsByTagName(): 根据元素名称获取元素对象们，返回值是一个数组
          3. getElementsByClassName(): 根据Class属性值获取元素对象们，返回值是一个数组
          4. getElementsByName(): 根据name属性值获取元素对象们，返回值是一个数组
       2. 创建其他DOM对象：
          1. createAttribute()
          2. createComment()
          3. createElement()
          4. createTextNode()
    3. 属性
  * Element：元素对象
    1. 获取/创建：通过document来获取和创建
    2. 方法
       1. removeAttribute(): 删除属性
       2. setAttribute(): 设置属性
  * Node：节点对象，其他5个的父对象
    * 特点：所有dom对象都可以被认为是一个节点
    * 方法：
      * CRUD dom树：
        * appendChild(): 向节点的子节点列表的结尾添加新的子节点
        * removeChild(): 删除（并返回）当前节点的指定子节点
        * replaceChild(): 用新节点替换一个子结点
    * 属性
      * parentNode 返回节点的父节点
* HTML DOM