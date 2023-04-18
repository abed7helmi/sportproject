package org.sid.emergencynotificationagentservice.services;



import org.sid.emergencynotificationagentservice.dto.CoachDTO;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;


    public void send(CoachDTO message){
        rabbitTemplate.convertAndSend(directExchange.getName(),"routing.Notif",message);
    }
}
