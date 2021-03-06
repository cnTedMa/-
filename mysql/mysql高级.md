# MySQL高级

## MySQL逻辑架构

1. **连接层**

   最上层是一些客户端和连接服务

2. **服务层**

   第二层架构主要完成核心服务功能, 如SQL接口, 优化器等

3. **引擎层**

   存储引擎层

4. **存储层**

   数据存储层, 主要是将数据存储在运行于裸设备的文件系统之上, 并完成与存储引擎的交互

## 存储引擎

**InnoDB和MyISAM的对比**

![image-20200806203509121](C:\Users\tatema\AppData\Roaming\Typora\typora-user-images\image-20200806203509121.png)

## 索引

1. **索引是什么**

   MySQL官方对索引的定义为: 索引(Index)是帮助MySQL高效获取数据的数据结构. 索引的本质: 索引是数据结构. 可以理解为"**排好序的快速查找数据结构**", 索引会影响where和order by的查询和排序速率.

   在数据之外, **数据库系统还维护着满足特定查找算法的数据结构**, 这些数据结构以某种方式引用数据, 这样就可以在这些数据结构上实现高级查找算法. 这种数据结构, 就是索引.

   一般来说索引本身也很大, 不可能全部存储在内存中, 因此索引往往以索引文件的形式存储在磁盘上

2. **优势与劣势**

   1. 优势
      - 提高数据检索的效率, 降低数据库的IO成本
      - 降低数据排序的成本, 降低了CPU的消耗
   2. 劣势
      - 占用空间
      - 虽然大大提高了查询速度, 同时却会降低更新表的速度
      - 花时间研究建立最优秀的索引, 即优化索引

3. **索引分类**

   1. 单值索引: 一个索引只包含单个列, 一个表可以有多个单列索引
   2. 唯一索引: 索引列的值必须唯一, 但允许有空值  

4. **B+Tree索引**

5. **哪些情况需要创建索引**

   1. 主键自动建立唯一索引
   2. 频繁作为查询条件的字段应该创建索引
   3. 查询中与其他表关联的字段, 外键关系建立索引
   4. 频繁更新的字段不适合创建索引
   5. where条件里用不到的字段不创建索引
   6. 单键/组合索引的选择问题(在高并发下倾向创建组合索引)
   7. 查询中排序的字段, 排序字段若通过索引去访问将大大提高排序速度
   8. 查询中统计或者分组字段

6. **哪些情况不需要创建索引**

   1. 表记录太少
   2. 经常增删改的表
   3. 数据重复且分布平均的表字段, 如果某个数据列包含许多重复的内容, 为它建立索引就没有太大的实际效果

## 性能分析

1. **常见瓶颈**

   1. CPU: CPU饱和的时候一般发生在数据装入内存或从磁盘上读取数据时侯
   2. IO: 磁盘I/O瓶颈发生在装入数据远大于内存容量的时候
   3. 服务器硬件的性能瓶颈: top, free, iostat和vmstat来查看系统的性能状态

2. **Explain**

   1. **作用**: 使用Explain关键字可以模拟优化器执行sql查询语句, 从而知道MySQL是如何处理你的SQL语句的, 分析你的查询语句或是表结构的性能瓶颈

      - 表的读取顺序
      - 数据读取操作的操作类型
      - 哪些索引可以使用
      - 那些索引被实际使用
      - 表之间的引用
      - 每张表有多少行被优化器查询

   2. **表头:**

      id, select_type, table, type, possible_keys, key, key_len, ref, rows, Extra

      - **<u>id:</u>** select查询的序列号, 包含一组数字, 表示查询中执行select子句或操作表的顺序 

        id相同, 执行顺序从上至下; 如果是子查询, id的序号会递增, id值越大优先级越高, 越先被执行

      - **select_type**: 

        - simple: 简单的select查询, 查询中不包含子查询或者union
        - primary: 查询中若包含任何复杂的子查询, 最外层查询则被标记为primary
        - subquery: 在select或where列表中包含的子查询
        - derived: 在from列表中包含的子查询被标记为DERIVED(衍生), MySQL会递归执行这些子查询, 把结果放在临时表里
        - union: 若第二个select出现在union之后, 则被标记为union; 若union包含在from子句的子查询中, 外层select则被标记为derived
        - union result: 从union表获取结果的select

      - <u>**type**</u>: 访问类型排列

        从最好到最差依次是: system>const>eq_ref>ref>range>index>ALL

        - system: 表只有一行记录(等于系统表), 这是const类型的特例, 平时不会出现, 这个也可以忽略不计
        - const: 表示通过索引一次就找到了, const用于比较primary key或者unique索引. 因为只匹配一行数据, 所以很快. 如将主键至于where列表中, MySQL就能将该查询转换为一个常量
        - eq_ref: 唯一性索引扫描, 对于每个索引键, 表中只有一条记录与之匹配. 常见于主键或唯一索引扫描.
        - ref: 非唯一性索引扫描, 返回匹配某个单独值的所有行. 可能会找到多个符合条件的行, 所以应该属于扫描和查找的混合.
        - range: 只检索给定范围的行, 使用一个索引来选择行. 比全表扫描要好.
        - index: 全索引扫描, 通常比ALL快, 因为索引文件通常比数据文件小.
        - all: 全表扫描
        - 备注: 一般来说, 得保证查询至少达到range级别, 最好能达到ref.

      - **possible_keys**: 显示可能应用在这张表中的索引, 一个或多个. 查询涉及到的字段上若存在索引, 则该索引将被列出, 但不一定被查询实际使用.

      - <u>**key:**</u> 实际使用的索引. 如果为NULL, 则没有使用索引. 查询中若使用了覆盖索引, 则该索引仅出现再key列表里.

      - **key_len:** 表示索引中使用的字节数, 可通过该列计算查询中使用的索引的长度. 在不损失精确性的情况下, 长度越短越好. key_len显示的值为索引字段的最大可能长度, 并非实际使用长度, 即key_len是根据表定义计算而得, 不是通过表内检索出的.

      - **ref:** 显示索引的哪一列被引用了, 如果可能的话, 是一个常数. 哪些列或常量被用于查找索引列上的值.

      - <u>**rows:**</u> 根据表统计信息及索引选用情况, 大致估算出找到所需的记录所需要读取的行数.

      - **Extra:** 包含不适合在其他列显示但十分重要的信息

        - **using filesort:** 说明mysql会对数据使用一个外部的索引排序, 而不是按照表内的索引顺序进行排序. mysql中无法利用索引完成的排序操作称为"文件排序"

        - **using temporary:** 使用了临时表保存中间结果, mysql在对查询结果排序时使用临时表. 常见于排序order by和分组查询group by

        - **using index:** 表示相应的select操作中使用了覆盖索引, 避免访问了表的数据行, 效率不错! 弱国同时出现using where, 表明索引被用来执行索引键值的查找; 如果没有同时出现using where, 表明索引用来读取数据而非执行查找动作.

          **覆盖索引**: select的数据列只用从索引中就能够取得, 不必读取数据行, mysql可以利用索引返回select列表中的字段, 而不必根据索引再次读取数据文件, 换句话说<u>查询列要被所建的索引覆盖</u>.

        - using where: 表明使用了where过滤

        - using join buffer: 使用了连接缓存

        - impossible where: where子句的值总是false, 不能用来获取任何元组

        - select tables optimized away: 在没有group by子句的情况下, 基于索引优化MIN/MAX操作或者对于MyISAM存储引擎优化COUNT(*)操作,  不必等到执行阶段再进行计算, 查询执行计划生成的阶段即完成优化.

        - distinct: 优化distinct操作, 在找到第一匹配的元组后即停止找同样值的动作.

