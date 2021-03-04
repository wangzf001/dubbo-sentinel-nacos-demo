Jvm参数：
-Ddubbo.protocol.port=20881 -Dcsp.sentinel.dashboard.server=localhost:7777 -Dcsp.sentinel.log.use.pid=true  -Dproject.name=App-Wzf

Nacosp配置：
    Data ID: App-Wzf-flow-rules
    Group: SENTINEL_GROUP

[{
    "resource":"com.wzf.study.ISentinelService:sayHello()",
    "grade":1,
    "count":10,
    "clusterMode":"true",
    "clusterConfig":{
        "flowId":1111,
        "thresholdType":1,
        "fallbackToLocalWhenFaile":true
    }
}]