package org.sid.heartratecollectorservice.services;

import org.sid.heartratecollectorservice.event.HrSensorDTO;
import org.sid.heartratecollectorservice.models.HrSensor;
import org.sid.heartratecollectorservice.models.Member;
import org.sid.heartratecollectorservice.event.HrSensorEventRepository;
import org.sid.heartratecollectorservice.repo.UserRestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class HrCollectorService {
    Logger logger = LoggerFactory.getLogger(HrCollectorService.class);

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private HrSensorEventRepository hrSensorDTORepository;

    @Autowired
    private UserRestClientService userRestClientService;

    public void hrCollectorCommandHandler(HrSensorDTO hrSensorDTO){

        if (hrSensorDTO.getCardiacFrequency()>60 && hrSensorDTO.getCardiacFrequency()<220 ){
            Member member=userRestClientService.mumberById(hrSensorDTO.getIdmember());
            if (member != null) {
                this.hrCollectorEvenSourcingHandler(hrSensorDTO,member.getAge());
            }
        }


    }


    public void hrCollectorEvenSourcingHandler(HrSensorDTO hrSensorDTO,int age){
        HrSensor hrSensorevent = new HrSensor(null,"verifié",age,hrSensorDTO.getCardiacFrequency(),hrSensorDTO.getDate(),hrSensorDTO.getIdmember());
        hrSensorDTORepository.save(hrSensorevent);
        streamBridge.send("event_hr_collector",hrSensorevent);
        logger.info("event Hr stocke dans l'event store et envoyé vers le topic event_hr_collector: "+  hrSensorevent);
    }
}
