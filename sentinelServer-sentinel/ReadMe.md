# 该项目模拟了sentinel，使用控制台去对接口或方法进行流控，
sentinel 是面向分布式、多语言异构化服务架构的流量治理组件

# 注： 只编写了代码，但未实操

# ncoas中的配置信息：

```json
[
    {
        "resource": "/hello/world", 
        "limitApp": "default",
        "grade": 1,
        "count": 3, 
        "strategy": 0, 
        "controlBehavior": 0, 
        "clusterMode": false
    }
]
```
