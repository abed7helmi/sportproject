package org.sid.emergencynotificationagentservice.web;

import org.sid.emergencynotificationagentservice.event.CoachConnectedEvent;
import org.sid.emergencynotificationagentservice.services.EmergencyNotifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ConsumerEvent {

    Logger logger = LoggerFactory.getLogger(ConsumerEvent.class);
    @Autowired
    private EmergencyNotifService emergencyNotifService;


    @Bean
    public Consumer<CoachConnectedEvent> coachEventCnxConsumer(){ // spring fait le subscribe automatiqyement
        return (input)->{
            logger.info("message recu ");
            System.out.println(input.toString());

        };
    }

}
