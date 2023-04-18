package org.sid.emergencynotificationagentservice.web;

import lombok.extern.slf4j.Slf4j;

import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;
import org.sid.emergencynotificationagentservice.event.CoachConnectedEvent;
import org.sid.emergencynotificationagentservice.services.EmergencyNotifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    private EmergencyNotifService emergencyNotifService;


    @Bean
    public Consumer<CoachConnectedEvent> CoachConnectedEventConsumer(){ // spring fait le subscribe automatiqyement
        return (input)->{
            System.out.println("**********************");
            System.out.println(input.toString());
            System.out.println("**********************");
        };
    }

}
