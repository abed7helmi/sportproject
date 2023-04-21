package org.sid.eventscollectorservice.web;

import org.sid.eventscollectorservice.entities.GlobalEvent;
import org.sid.eventscollectorservice.events.CoachConnectedEvent;
import org.sid.eventscollectorservice.events.CoachDTO;
import org.sid.eventscollectorservice.events.EventHrSensor;
import org.sid.eventscollectorservice.events.HrSensorDTO;
import org.sid.eventscollectorservice.repo.GlobalEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class EventsConsumer {

    Logger logger = LoggerFactory.getLogger(EventsConsumer.class);

    @Autowired
    private GlobalEventRepository globalEventRepository;
    @Bean
    public Consumer<CoachConnectedEvent> coachEventCnxConsumer(){
        return (input)->{
            logger.info("message coachEventCnx recu "+ input.toString());
            System.out.println(input.toString());
            //coachEventService.subscribeCoachCache(input);
            GlobalEvent globalEvent = new GlobalEvent("coachEventCnx",input.toString());
            globalEventRepository.save(globalEvent);
            logger.info("message stocke dans l'event global event store");

        };
    }

    @Bean
    public Consumer<EventHrSensor> hrEmergencyConsumer(){
        return (input)->{
            logger.info("message hrEmergency recu ");
            System.out.println(input.toString());
            GlobalEvent globalEvent = new GlobalEvent("hrEmergencyEvent",input.toString());
            globalEventRepository.save(globalEvent);
            logger.info("message stocke dans l'event global event store");

        };
    }


    @Bean
    public Consumer<HrSensorDTO> hrEventConsumer(){ // spring fait le subscribe automatiqyement
        return (input)->{
            logger.info("event recu de event_hr_collector " +input.toString());

            GlobalEvent globalEvent = new GlobalEvent("hrEvent",input.toString());
            globalEventRepository.save(globalEvent);
            logger.info("message stocke dans l'event global event store");

            //hrService.hrEventCommmandHandler(input);

        };
    }


    @Bean
    public Consumer<CoachDTO> notificationCoachConsumer(){ // spring fait le subscribe automatiqyement
        return (input)->{
            logger.info("event recu de event_notification_coach " +input.toString());

            GlobalEvent globalEvent = new GlobalEvent("notification",input.toString());
            globalEventRepository.save(globalEvent);
            logger.info("message stocke dans l'event global event store");

            //hrService.hrEventCommmandHandler(input);

        };
    }


}
