package com.example.consumer.service;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2022/1/6 13:49
 */
@Service
@RocketMQMessageListener(topic = "topic-one", consumerGroup = "consumer-group-one", selectorType = SelectorType.TAG, selectorExpression = "A", messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class ConsumerService implements RocketMQListener<String> {
    // 收到消息执行的方法
    @Override
    public void onMessage(String message) {
        System.out.println("receiveA-One = " + message);
    }
}
