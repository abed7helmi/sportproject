package org.sid.notificationchannelmanagerservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.notificationchannelmanagerservice.dto.CoachDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {



    @RabbitListener(queues = "NotifQueue")
    private void receive(CoachDTO coachDTO){
        System.out.println(coachDTO);
        log.info("Message notif recu ",coachDTO);

    }

}
