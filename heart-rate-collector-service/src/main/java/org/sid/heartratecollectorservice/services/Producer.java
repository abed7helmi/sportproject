package org.sid.heartratecollectorservice.services;

import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.sid.heartratecollectorservice.models.HrSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
public class Producer {

    Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;


    public void send(HrSensorDTO message){

        rabbitTemplate.convertAndSend(directExchange.getName(),"routing.HeartRate",message);
        logger.info("Heart Rate :  "+message + "send TO RABBITMQ BROKER (HeartRateQueue) BY THE HR COLLECTOR SERVICE");
    }
}
