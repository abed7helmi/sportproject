package org.sid.emergencynotificationagentservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    /*@Autowired
    private HrService hrService;*/

    @RabbitListener(queues = "EmergencyQueue")
    private void receive(HrSensorDTO message){
        System.out.println(message);
        log.info("Message urgence recu ",message);
        //hrService.checkHr(message);
    }

}
