package com.es.esspringstu.canal.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.es.esspringstu.canal.es.UserEsDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@ConditionalOnProperty(value = "app.rabbitmq.enable", havingValue = "true", matchIfMissing = true)
public class RabbitMQCustomerListener {


    @Autowired
    private ElasticsearchRestTemplate template;

//    //监听email队列
//    @RabbitListener(queues = {RabbitMQConfig.QUEUE_INFORM_CANAL})
//    public void receive_email(Object msg, Message message, Channel channel) {
//
//        String body = new String(message.getBody());
//        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
//        if (receivedRoutingKey.equals(MQRoutingKeyEnum.SEND_FENPEI_CANAL.getRoutingKey())) {
//            //数据同步
//            UserEsDocument userEsDocument = JSON.parseObject(body, UserEsDocument.class);
//            template.save(userEsDocument);
//        }
//    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = RabbitMQConfig.QUEUE_CANAL, durable = "true"),
                    exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_CANAL, type = ExchangeTypes.FANOUT),
                    key = RabbitMQConfig.ROUTINGKEY_CANAL
            )
    }, concurrency = "10")
    public void test(String message) {
//        log.info("test接收到消息。message:{}", message);
        JSONObject jsonObject = JSON.parseObject(message);
        JSONArray data = jsonObject.getJSONArray("data");
        data.forEach(l->{
            UserEsDocument userEsDocument = JSON.parseObject(l.toString(), UserEsDocument.class);
            template.save(userEsDocument);
        });
    }
}