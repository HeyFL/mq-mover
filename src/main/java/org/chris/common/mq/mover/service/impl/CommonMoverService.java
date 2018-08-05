
package org.chris.common.mq.mover.service.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.chris.common.mq.mover.domain.MoveInfo;
import org.chris.common.mq.mover.service.MoveService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CommonMoverService implements MoveService {
    @Resource
    @Qualifier("commonMQConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "pfsPickupSyncTemplate")
    private RabbitTemplate rabbitTemplate;

    /**
     * 转移消息
     *
     * @param moveInfo
     * @throws Exception
     */
    @Override
    public void moveMsg(MoveInfo moveInfo) throws Exception {

        //是否成功消费
        boolean isConsumerSuccess = false;
        //是否获取到消息标识,没获取到时为0L
        long deliveryTag = 0L;

        //连接rabbit
        Channel channel = getChannel() ;
        for (int i = 0; i < moveInfo.getMoveNum(); i++) {
            try {
                //获取消息
                GetResponse response = channel.basicGet(moveInfo.getQueue(), false);
                deliveryTag = response.getEnvelope().getDeliveryTag();
                String resStr = new String(response.getBody(), "UTF-8");

                //转发消息
                send(resStr, moveInfo.getToExchange(), moveInfo.getToRoutingKey());
                isConsumerSuccess = true;

                //确认消息
                channel.basicAck(deliveryTag, false);

                //回滚消费失败消息
            } catch (Exception e) {
                log.error("error:{}", e);
                throw e;
            } finally {
                //获取到消息 并且 没有成功消费
                if (isConsumerSuccess == false && deliveryTag != 0L) {
                    channel.basicReject(deliveryTag, false);
                }
            }
        }
    }

    private Channel getChannel() {
        Channel channel;
        try{
            //连接rabbit
            channel = connectionFactory.createConnection().createChannel(false);
        }catch (Exception e){
            log.error("获取rabbit连接失败:{}",e);
            throw e;
        }
        return channel;
    }

    /**
     * 发送消息
     *
     * @param msg
     * @param toExchange
     * @param toRoutingKey
     */
    private void send(String msg, String toExchange, String toRoutingKey) {
        rabbitTemplate.convertAndSend(toExchange, toRoutingKey, msg);
    }
}
