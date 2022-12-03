package org.sid.heartratesecondworkerservice.services;


import org.sid.heartratesecondworkerservice.dto.HrSensorDTO;
import org.sid.heartratesecondworkerservice.models.Member;
import org.sid.heartratesecondworkerservice.repo.UserRestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class HrService {

    Logger logger = LoggerFactory.getLogger(HrService.class);
    @Autowired
    private UserRestClientService userRestClientService;

    @Autowired
    private Producer producer;

    public void checkHr (HrSensorDTO hrSensorDTO){
        Member member= userRestClientService.mumberById(hrSensorDTO.getIdmember());
        if(this.checkSubscrib(member)){
            logger.info("USER "+ member.getMemberFirstName()+" SUBSCRIBED");
            if(this.checkEmergency(member,hrSensorDTO)){

                producer.send(hrSensorDTO);
                logger.warn("CONTACT EMERGENCY SERVICE FOR USER  "+ member);

            }
        }else {
            logger.warn("USER "+ member.getMemberFirstName()+" NOT SUBSCRIBED");
        }

    }

    public boolean checkSubscrib (Member m){

        if (m.getMemberSubscription()==true){
            return true;
        }else {
            return false;
        }

    }

    public boolean checkEmergency (Member member,HrSensorDTO hrSensorDTO){


        // La méthode la plus fiable que nous vous conseillons : FC max = 207 – 0,7 x âge
        // https://www.irbms.com/calculer-sa-frequence-cardiaque-pour-un-effort/#:~:text=La%20formule%20d%27Haskell%20et,une%20FC%20max%20de%20160.

        Double CardiacMax = 207 - 0.7*member.getAge();
        if (hrSensorDTO.getCardiacFrequency()>CardiacMax){
            return true;
        }else {
            return false;
        }

    }
}
