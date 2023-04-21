package org.sid.heartratesecondworkerservice.web;

import org.sid.heartratesecondworkerservice.event.HrSensorDTO;
import org.sid.heartratesecondworkerservice.services.HrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EventConsumer {

    Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    @Autowired
    private HrService hrService;

    @Bean
    public Consumer<HrSensorDTO> hrEventConsumer(){ // spring fait le subscribe automatiqyement
        return (input)->{
            logger.info("event recu de event_hr_collector " +input.toString());

            hrService.hrEventCommmandHandler(input);

        };
    }


}
