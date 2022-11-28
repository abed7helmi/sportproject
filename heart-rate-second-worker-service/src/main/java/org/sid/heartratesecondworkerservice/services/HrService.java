package org.sid.heartratesecondworkerservice.services;


import org.sid.heartratesecondworkerservice.dto.HrSensorDTO;
import org.sid.heartratesecondworkerservice.models.Member;
import org.sid.heartratesecondworkerservice.repo.UserRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class HrService {
    @Autowired
    private UserRestClientService userRestClientService;

    public void checkHr (HrSensorDTO hrSensorDTO){
        if(this.checkSubscrib(hrSensorDTO)){
            System.out.println("user bien inscrit");
            if(this.checkEmergency(hrSensorDTO)){
                System.out.println("appel urgence " + hrSensorDTO.getCardiacFrequency());
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
