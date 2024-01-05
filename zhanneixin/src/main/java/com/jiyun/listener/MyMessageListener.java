package com.jiyun.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class MyMessageListener implements MessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @RabbitListener(queues = "d16_queue")
    public void onMessage(Message message) {
        String body = new String(message.getBody());
        System.out.println("----------"+body);
        redisTemplate.boundValueOps(body).set(body);
        redisTemplate.boundHashOps("MESSAGEHASH").put(body,body);
        redisTemplate.boundListOps("MESSAGELIST").rightPush(body);
    }
}
