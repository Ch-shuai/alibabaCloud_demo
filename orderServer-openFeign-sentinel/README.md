# 该模块完成了openFein-sentinel的基本操作

openFeign是要声明式的web服务客户端，或叫做声明式REST客户端，它让编写web服务客户端变得简单。feign主要是为我们提供了远程调用的服务[RPC(Remote Procedure Call)]
sentinel 是面向分布式、多语言异构化服务架构的流量治理组件

## openFeign远程调用其他服务，使用sentinel-dashboard对远程调用接口进行监控，熔断降级监听

## 慢调用比例 (SLOW_REQUEST_RATIO)：
    选择以慢调用比例作为阈值，需要设置允许的慢调用 RT（即最大的响应时间），请求的响应时间大于该值则统计为慢调用。
    当单位统计时长（statIntervalMs）内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
    经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，若大于设置的慢调用 RT 则会再次被熔断。
## 异常比例 (ERROR_RATIO)：
    当单位统计时长（statIntervalMs）内请求数目大于设置的最小请求数目，并且异常的比例大于阈值， 则接下来的熔断时长内请求会自动被熔断。
    经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%。
## 异常数 (ERROR_COUNT)：
    当单位统计时长内的异常数目超过阈值之后会自动进行熔断。
    经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。


## 文档地址：
    https://sentinelguard.io/zh-cn/docs/quick-start.html