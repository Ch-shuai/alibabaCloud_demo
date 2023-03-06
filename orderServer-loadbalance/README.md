# 将订单服务注册到nacos中

order-serer是订单服务，订单服务会消耗库存，所以order-server也为客户端（消费者）,
    该服务【客户端】配置了springCloudAlibaba自带的loadBalance【负载均衡】设置，loadBalance目前只有线性轮询的机制去负载均衡
