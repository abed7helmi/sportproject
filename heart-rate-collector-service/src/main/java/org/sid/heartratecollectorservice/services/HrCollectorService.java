package org.sid.heartratecollectorservice.services;

import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.sid.heartratecollectorservice.models.Member;
import org.sid.heartratecollectorservice.repo.UserRestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrCollectorService {
    Logger logger = LoggerFactory.getLogger(HrCollectorService.class);

    @Autowired
    private UserRestClientService userRestClientService;

    public void hrCollectorCommandHandler(HrSensorDTO hrSensorDTO){
        if (hrSensorDTO.getCardiacFrequency()<60 && hrSensorDTO.getCardiacFrequency()>220 ){
            Member member=userRestClientService.mumberById(hrSensorDTO.getIdmember());
            if (member != null) {
                this.hrCollectorEvenSourcingHandler(hrSensorDTO);
            }
        }


    }


    public void hrCollectorEvenSourcingHandler(HrSensorDTO hrSensorDTO){

    }
}
