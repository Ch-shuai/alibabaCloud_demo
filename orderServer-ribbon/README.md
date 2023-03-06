# 将订单服务注册到nacos中

order-serer是订单服务，订单服务会消耗库存，所以order-server也为客户端（消费者）,
    该服务【客户端】配置了ribbon【负载均衡】设置，通过所选的负载均衡策略去对生产者服务进行负载均衡访问。

```
stockServer-nacos:  # 生产者服务名
  ribbon:
    NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule   # 负载均衡策略，【其他策略在，com.alibaba.cloud.nacos.ribbon中选择】
```