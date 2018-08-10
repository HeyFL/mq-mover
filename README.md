# mq-mover

通用【队列消息】转移【另外一个队列】   可用作死信手动重新消费

请求报文:
```
{
	"queue": "PFS_PICKUP_Q_PREDICT_DEAD",//原队列
	"toExchange": "PFS_PICKUP_X_PREDICT",//目的地队列Exchange
	"toRoutingKey": "PFS_PICKUP_R_PREDICT",//目的地队地RoutingKey
	"moveNum": 10
}
```

支持动态修改MQ用户名、密码、VH等信息
