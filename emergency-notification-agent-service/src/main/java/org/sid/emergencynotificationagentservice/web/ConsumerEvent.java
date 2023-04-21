package org.sid.emergencynotificationagentservice.web;

import org.sid.emergencynotificationagentservice.event.EventHrSensor;
import org.sid.emergencynotificationagentservice.event.CoachConnectedEvent;
import org.sid.emergencynotificationagentservice.services.CoachEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ConsumerEvent {

    Logger logger = LoggerFactory.getLogger(ConsumerEvent.class);


    @Autowired
    private CoachEventService coachEventService ;


    @Bean
    public Consumer<CoachConnectedEvent> coachEventCnxConsumer(){
        return (input)->{
            logger.info("message coachEventCnx recu ");
            System.out.println(input.toString());
            coachEventService.coachCnxEvenSourcingHandler(input);

        };
    }

    @Bean
    public Consumer<EventHrSensor> hrEmergencyConsumer(){
        return (input)->{
            logger.info("message hrEmergency recu ");
            System.out.println(input.toString());
            coachEventService.hrEmergencyEvenSourcingHandler(input);

        };
    }


}
