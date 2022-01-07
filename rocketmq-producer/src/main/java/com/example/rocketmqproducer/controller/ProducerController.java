package com.example.rocketmqproducer.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2022/1/6 11:51
 */
@RestController
public class ProducerController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/produce/{num}")
    public ResponseEntity<String> methodName(@PathVariable Integer num) {

        for (int i = 1; i <= num; i++) {

            Message<String> message = MessageBuilder.withPayload(StrUtil.format("订单号:{} \t 流程 : 创建", i)).build();
            Message<String> message2 = MessageBuilder.withPayload(StrUtil.format("订单号:{} \t 流程 : 付款", i)).build();
            Message<String> message3 = MessageBuilder.withPayload(StrUtil.format("订单号:{} \t 流程 : 完成", i)).build();

            rocketMQTemplate.syncSendOrderly("topic-one:A", message, String.valueOf(i));
            rocketMQTemplate.syncSendOrderly("topic-one:A", message2, String.valueOf(i));
            rocketMQTemplate.syncSendOrderly("topic-one:A", message3, String.valueOf(i));

            rocketMQTemplate.syncSendOrderly("topic-one:B", message, String.valueOf(i));
            rocketMQTemplate.syncSendOrderly("topic-one:B", message2, String.valueOf(i));
            rocketMQTemplate.syncSendOrderly("topic-one:B", message3, String.valueOf(i));


        }

        return ResponseEntity.ok("生产了" + num + "条消息");
    }


    @GetMapping("/transac/{arg}")
    public ResponseEntity<String> transac(@PathVariable String arg) {


        if (StrUtil.isEmpty(arg) || arg.equals("1")){
            arg = "传递给事务处理的参数";
        }

        // 这里的arg参数是用于事务处理器的
        rocketMQTemplate.sendMessageInTransaction("topic-one:A", MessageBuilder.withPayload("事务消息!!!").build(),arg);

        return ResponseEntity.ok("发送了事务消息");
    }

}
