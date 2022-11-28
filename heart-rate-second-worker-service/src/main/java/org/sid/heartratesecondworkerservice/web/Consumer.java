package org.sid.heartratesecondworkerservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.heartratesecondworkerservice.dto.HrSensorDTO;

import org.sid.heartratesecondworkerservice.services.HrService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private HrService hrService;

    @RabbitListener(queues = "queue.A")
    private void receive(HrSensorDTO message){
        System.out.println(message);
        log.info("Message recu ",message);
        hrService.checkHr(message);
    }


}
