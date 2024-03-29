# 该模块完成了seata分布式事务架构

## 在 Seata 框架中，TM、RM 和 TC 是分布式事务中的三个重要角色：
    TM (Transaction Manager)：事务管理器，负责全局事务的协调和控制。在 Seata 框架中，TM 是事务的发起者，它会协调所有的 RM 完成事务的提交或回滚。
    RM (Resource Manager)：资源管理器，负责管理分支事务的提交和回滚。在 Seata 框架中，每个参与者服务都需要有一个对应的 RM，它可以是数据库、消息队列等资源的管理器。
    TC (Transaction Coordinator)：事务协调器，负责协调 TM 和 RM 完成分布式事务。在 Seata 框架中，TC 是全局事务的协调者，它会收集 TM 发起的全局事务信息，向所有的 RM 发起分支事务请求，并根据 RM 的响应结果决定最终的事务提交或回滚。
    通过 TM、RM 和 TC 的协作，Seata 框架实现了分布式事务的一致性和可靠性。其中，TM 负责全局事务的控制，RM 负责分支事务的提交和回滚，TC 负责全局事务的协调和决策，三者共同完成了分布式事务的一致性处理。