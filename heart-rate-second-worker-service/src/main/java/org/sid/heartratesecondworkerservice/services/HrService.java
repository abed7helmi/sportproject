package org.sid.heartratesecondworkerservice.services;


import org.sid.heartratesecondworkerservice.event.HrSensorDTO;
import org.sid.heartratesecondworkerservice.event.HrEmergencyEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service

public class HrService {

    Logger logger = LoggerFactory.getLogger(HrService.class);


    @Autowired
    private HrEmergencyEventRepository hrEmergencyEventRepository;

    @Autowired
    private StreamBridge streamBridge;



    public void hrEventCommmandHandler(HrSensorDTO hrSensorDTO) {

        if(this.checkEmergency(hrSensorDTO)){
            this.hrEventEvenSourcingHandler(hrSensorDTO);


        }
    }

    private void hrEventEvenSourcingHandler(HrSensorDTO hrSensorDTO) {
        logger.warn("CONTACT EMERGENCY SERVICE FOR USER  "+ hrSensorDTO.getIdmember());
        hrSensorDTO.setState("Urgence");
        hrEmergencyEventRepository.save(hrSensorDTO);
        streamBridge.send("event_hr_emergency",hrSensorDTO);
        logger.info("event Hr stocke dans l'event store et envoyé vers le topic event_hr_emergency: "+  hrSensorDTO);

    }


    public boolean checkEmergency (HrSensorDTO hrSensorDTO){


        // La méthode la plus fiable que nous vous conseillons : FC max = 207 – 0,7 x âge
        // https://www.irbms.com/calculer-sa-frequence-cardiaque-pour-un-effort/#:~:text=La%20formule%20d%27Haskell%20et,une%20FC%20max%20de%20160.

        Double CardiacMax = 207 - 0.7*hrSensorDTO.getAge();
        if (hrSensorDTO.getCardiacFrequency()>CardiacMax){
            return true;
        }else {
            return false;
        }

    }



}
