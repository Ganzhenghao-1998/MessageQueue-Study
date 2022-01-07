package com.example.consumer.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2022/1/6 13:45
 */
@RestController
public class ConsumerController {


    @GetMapping("/consumer")
    public ResponseEntity<String> methodName() {
        return ResponseEntity.ok("消费了消息!");
    }

}
