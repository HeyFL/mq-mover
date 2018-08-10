# mq-mover

## 通用【队列消息】转移【另外一个队列】   可用作死信手动重新消费

支持动态修改MQ用户名、密码、VH等信息

### step1:配置src/main/resources/application.properties
```
#MQ Setting
spring.rabbitmq.default.addresses=127.0.0.1:5672
spring.rabbitmq.default.username=MQ_userName
spring.rabbitmq.default.password=MQ_password
spring.rabbitmq.default.publisher-confirms=true
spring.rabbitmq.PFS_PDS.virtual-host=MQ_virtual-host
```

### step2:请求:http://localhost:8080/move
请求JSON报文:
```
{
	"queue": "PFS_PICKUP_Q_PREDICT_DEAD",//原队列
	"toExchange": "PFS_PICKUP_X_PREDICT",//目的地队列Exchange
	"toRoutingKey": "PFS_PICKUP_R_PREDICT",//目的地队地RoutingKey
	"moveNum": 10 //转移多少条消息
}
```

