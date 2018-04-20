# mq-mover

通用死信重新消费

请求报文:
```
{
	"queue": "PFS_PICKUP_Q_PREDICT_DEAD",
	"toExchange": "PFS_PICKUP_X_PREDICT",
	"toRoutingKey": "PFS_PICKUP_R_PREDICT",
	"moveNum": 10
}
```
