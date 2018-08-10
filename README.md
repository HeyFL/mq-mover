# mq-mover

通用【队列消息】转移【另外一个队列】   可用作死信重新消费

请求报文:
```
{
	"queue": "PFS_PICKUP_Q_PREDICT_DEAD",
	"toExchange": "PFS_PICKUP_X_PREDICT",
	"toRoutingKey": "PFS_PICKUP_R_PREDICT",
	"moveNum": 10
}
```

程序限制：2个队列需要在同一个Virtual Host下
