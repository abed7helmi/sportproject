package org.sid.emergencynotificationagentservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.services.EmergencyNotifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    private EmergencyNotifService emergencyNotifService;

    @RabbitListener(queues = "EmergencyQueue")
    private void receive(HrSensorDTO hrSensorDTO){
        logger.info(" message"+ hrSensorDTO +" obtain from EmergencyQueue");
        emergencyNotifService.notifVerif(hrSensorDTO);
    }

}
