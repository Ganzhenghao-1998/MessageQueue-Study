package com.example.rocketmqconsumertwo.service;

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
@RocketMQMessageListener(topic = "topic-one", consumerGroup = "consumer-group-two", selectorType = SelectorType.TAG, selectorExpression = "B")
public class ConsumerServiceFour implements RocketMQListener<String> {
    // 收到消息执行的方法
    @Override
    public void onMessage(String message) {
        System.out.println("receiveB-Two = " + message);
    }
}
