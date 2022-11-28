package org.sid.heartratecollectorservice.services;

import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.sid.heartratecollectorservice.models.HrSensor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;


    public void send(HrSensorDTO message){
        System.out.println("wiiiw");
        rabbitTemplate.convertAndSend(directExchange.getName(),"routing.A",message);
        System.out.println("message send seccess");
    }
}
