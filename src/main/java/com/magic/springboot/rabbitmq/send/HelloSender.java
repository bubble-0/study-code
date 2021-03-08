package com.magic.springboot.rabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
