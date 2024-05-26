package com.es.esspringstu.canal.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_INFORM_CANAL = "queue_canal_mysql_es";
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_canal";
    public static final String ROUTINGKEY_CANAL = "canal.#";


    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    //声明QUEUE_INFORM_CANAL队列
    @Bean(QUEUE_INFORM_CANAL)
    public Queue QUEUE_INFORM_CANAL(){
        return new Queue(QUEUE_INFORM_CANAL);
    }


    //ROUTINGKEY_CANAL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_QUEUE_INFORM_CANAL(@Qualifier(QUEUE_INFORM_CANAL) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_CANAL).noargs();
    }
    /** 通过canal **/

    public static final String QUEUE_CANAL = "queue_canal_mysql_es";
    public static final String EXCHANGE_CANAL="exchange_exchange_canal";
    public static final String ROUTINGKEY_TEST_CANAL = "canal.#";


    //声明交换机
    @Bean(EXCHANGE_CANAL)
    public Exchange EXCHANGE_INFORM(){
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.fanoutExchange(EXCHANGE_CANAL).durable(true).build();
    }

    //声明QUEUE_INFORM_CANAL队列
    @Bean(QUEUE_CANAL)
    public Queue QUEUE_CANAL(){
        return new Queue(QUEUE_CANAL);
    }


    //ROUTINGKEY_CANAL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_QUEUE_CANAL_INFORM_CANAL(@Qualifier(QUEUE_CANAL) Queue queue,
                                              @Qualifier(EXCHANGE_CANAL) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_TEST_CANAL).noargs();
    }
}