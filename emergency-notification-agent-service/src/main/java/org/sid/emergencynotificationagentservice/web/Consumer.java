package org.sid.emergencynotificationagentservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.services.EmergencyNotifService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private EmergencyNotifService emergencyNotifService;

    @RabbitListener(queues = "EmergencyQueue")
    private void receive(HrSensorDTO hrSensorDTO){
        System.out.println(hrSensorDTO);
        log.info("Message urgence recu ",hrSensorDTO);
        emergencyNotifService.notifVerif(hrSensorDTO);
    }

}
