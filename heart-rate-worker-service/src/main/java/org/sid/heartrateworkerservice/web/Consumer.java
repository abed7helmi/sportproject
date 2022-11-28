package org.sid.heartrateworkerservice.web;

import lombok.extern.slf4j.Slf4j;
import org.sid.heartrateworkerservice.dto.HrSensorDTO;
import org.sid.heartrateworkerservice.models.HrSensor;
import org.sid.heartrateworkerservice.services.HrService;
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
