package org.sid.heartrateworkerservice.services;

import org.sid.heartrateworkerservice.dto.HrSensorDTO;
import org.sid.heartrateworkerservice.models.Member;
import org.sid.heartrateworkerservice.repo.UserRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HrService {
    @Autowired
    private UserRestClientService userRestClientService;

    @Autowired
    private Producer producer;

    public void checkHr (HrSensorDTO hrSensorDTO){
        if(this.checkSubscrib(hrSensorDTO)){
            System.out.println("user bien inscrit");
            if(this.checkEmergency(hrSensorDTO)){
                System.out.println("appel urgence " + hrSensorDTO.getCardiacFrequency());
                producer.send(hrSensorDTO);

            }
        }

    }

    public boolean checkSubscrib (HrSensorDTO hrSensorDTO){
        Member m= userRestClientService.mumberById(hrSensorDTO.getIdmember());
        if (m.getMemberSubscription()==true){
            return true;
        }else {
            return false;
        }

    }

    public boolean checkEmergency (HrSensorDTO hrSensorDTO){

        if (hrSensorDTO.getCardiacFrequency()>65){
            return true;
        }else {
            return false;
        }

    }
}
