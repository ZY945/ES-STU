package com.es.esspringstu.canal.controller;

import com.alibaba.fastjson.JSON;
import com.es.esspringstu.canal.empty.User;
import com.es.esspringstu.canal.mq.MQRoutingKeyEnum;
import com.es.esspringstu.canal.mq.RabbitMQConfig;
import com.es.esspringstu.canal.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/1")
    public boolean data(){
        // 业务逻辑
        ArrayList<User> list = new ArrayList<>();
        Random random = new Random();
        for(int i = 0 ; i <=100_000; i++){
            int i1 = random.nextInt(100);
            String str = String.valueOf(i);
            User user = new User();
            user.setUserId("name"+i+i1);
            user.setEmail("name"+i+i1);
            user.setPassword(str);
            user.setFirstName(str);
            user.setLastName(str);
            user.setUsername(str);
            list.add(user);
            // TODO 移到canal里
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS_INFORM, MQRoutingKeyEnum.SEND_FENPEI_CANAL.getRoutingKey(), JSON.toJSONString(user));
        }

        userService.saveBatch(list);
        return true;
    }
}
