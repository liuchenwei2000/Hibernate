## Hibernate 简介 ##

Hibernate 是一个开放源代码的对象关系映射框架，它对 JDBC 进行了非常轻量级的封装，使得 Java 程序员可以随心所欲的使用对象编程思维来操纵数据库。

Hibernate 可以应用在任何使用 JDBC 的场合，既可以在 Java 的客户端程序使用，也可以在 Servlet/JSP 的 Web 应用中使用，最具革命意义的是，Hibernate 可以在应用 EJB 的 JavaEE 架构中取代 CMP，完成数据持久化的重任。


### 核心接口 ###

Hibernate 的核心接口一共有6个，分别为:`Session`、`SessionFactory`、`Transaction`、`Query`、`Criteria` 和 `Configuration`。

这6个核心接口在任何开发中都会用到。通过这些接口，不仅可以对持久化对象进行存取，还能够进行事务控制。


#### Session ####

Session 接口负责执行被持久化对象的 CRUD 操作，但需要注意的是 Session 对象是非线程安全的。同时，Hibernate 的 session 不同于 JSP 应用中的 HttpSession。

这里当使用 session 这个术语时，其实指的是 Hibernate 中的 session，而以后会将 HttpSession 对象称为用户 session。

#### SessionFactory ####

SessionFactory 接口负责初始化 Hibernate。它充当数据存储源的代理，并负责创建 Session 对象。这里用到了工厂模式。

需要注意的是 SessionFactory 并不是轻量级的，因为一般情况下，一个项目通常只需要一个 SessionFactory 就够，当需要操作多个数据库时，可以为每个数据库指定一个 SessionFactory。

#### Transaction ####

Transaction 接口是一个可选的 API，可以选择不使用这个接口，取而代之的是 Hibernate 的设计者自己写的底层事务处理代码。

Transaction 接口是对实际事务实现的一个抽象，这些实现包括 JDBC 的事务、JTA 中的 UserTransaction、甚至可以是 CORBA 事务。

之所以这样设计是能让开发者能够使用一个统一事务的操作界面，使得自己的项目可以在不同的环境和容器之间方便地移植。

#### Query ####

Query 接口让你方便地对数据库及持久对象进行查询，它可以有两种表达方式：HQL 语言或本地数据库的 SQL 语句。

Query 经常被用来绑定查询参数、限制查询记录数量，并最终执行查询操作。

#### Criteria ####

Criteria 接口与 Query 接口非常类似，允许创建并执行面向对象的标准化查询。

值得注意的是 Criteria 接口也是轻量级的，它不能在 Session 之外使用。

#### Configuration ####

Configuration 接口的作用是对 Hibernate 进行配置，以及对它进行启动。

在 Hibernate 的启动过程中，Configuration 类的实例首先定位映射文档的位置，读取这些配置，然后创建一个 SessionFactory 对象。

虽然 Configuration 接口在整个 Hibernate 项目中只扮演着一个很小的角色，但它是启动 Hibernate 时所遇到的第一个对象。
