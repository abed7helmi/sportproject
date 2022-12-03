package org.sid.heartratesecondworkerservice.services;


import org.sid.heartratesecondworkerservice.dto.HrSensorDTO;
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


    public void send(HrSensorDTO message){
        rabbitTemplate.convertAndSend(directExchange.getName(),"routing.Emergency",message);

    }
}
