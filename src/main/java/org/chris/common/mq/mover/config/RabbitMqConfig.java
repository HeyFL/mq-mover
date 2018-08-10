
package org.chris.common.mq.mover.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author caizq
 * @date 2018/4/8
 * @since v1.0.0
 */
@Slf4j
@Configuration
public class RabbitMqConfig {


    /**
     * 注解 @Scope("prototype") 可以令你你动态修改的配置生效
     * the @Scope is important that allows you change collection config dynamically when you request
     * @param addresses
     * @param username
     * @param password
     * @param virtualHost
     * @return
     */
    @Bean
    @Scope("prototype")
    public ConnectionFactory commonMQConnectionFactory(
            @Value("${spring.rabbitmq.default.addresses}") String addresses,
            @Value("${spring.rabbitmq.default.username}") String username,
            @Value("${spring.rabbitmq.default.password}") String password,
            @Value("${spring.rabbitmq.PFS_PDS.virtual-host}") String virtualHost
    ) {
        CachingConnectionFactory connectionFactory = getConnectionFactory(addresses, username, password, virtualHost);
        connectionFactory.setRequestedHeartBeat(12);
        return connectionFactory;
    }

    @Bean
    @Scope("prototype")
    public RabbitTemplate pfsPickupSyncTemplate(@Qualifier("commonMQConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }

    /**
     * 初始化RabbitMQ 连接池
     *
     * @param addresses
     * @param username
     * @param password
     * @param virtualHost
     * @return
     */
    private CachingConnectionFactory getConnectionFactory(String addresses, String username, String password, String virtualHost) {
        //这里指定localhost是因为linux下取不到默认配置
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

}
