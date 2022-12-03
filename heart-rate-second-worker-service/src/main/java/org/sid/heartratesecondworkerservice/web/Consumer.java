package org.sid.heartratesecondworkerservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.heartratesecondworkerservice.dto.HrSensorDTO;
import org.sid.heartratesecondworkerservice.services.HrService;
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
    private HrService hrService;

    @RabbitListener(queues = "HeartRateQueue")
    private void receive(HrSensorDTO message){

        logger.info("Second worker : message"+ message +" obtain from HeartRateQueue");
        hrService.checkHr(message);

    }


}
