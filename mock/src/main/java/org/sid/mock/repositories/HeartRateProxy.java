package org.sid.mock.repositories;

import org.sid.mock.dto.HrSensorDTO;
import org.sid.mock.models.HrSensor;
import org.sid.mock.task.SportJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class HeartRateProxy {

    Logger logger = LoggerFactory.getLogger(HeartRateProxy.class);

    @Value("${GATEWAY_HOST}")
    String GATEWAY_HOST;

    public void sendHr(HrSensorDTO hrSensor){

        String url="http://"+GATEWAY_HOST+"/heart-rate-collector-service/hr";



        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<HrSensorDTO> request = new HttpEntity<HrSensorDTO>(hrSensor);
        ResponseEntity<HrSensor> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<HrSensor>() {}

        );

        logger.info("HR value calculated "+hrSensor.getCardiacFrequency() + " sent to the gateway for the user id : " + hrSensor.getIdmember());
        logger.info("HR value calculate2d "+hrSensor.toString());

    }

}
