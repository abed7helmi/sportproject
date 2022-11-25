package org.sid.mock.repositories;

import org.sid.mock.models.HrSensor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class HeartRateProxy {

    public void sendHr(HrSensor hrSensor){

        String url="http://localhost:9999/heart-rate-collector-service/hr";
        System.out.print("wwaaawwww");
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<HrSensor> request = new HttpEntity<HrSensor>(hrSensor);
        ResponseEntity<HrSensor> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<HrSensor>() {}

        );

    }

    public void sendHrtest (HrSensor hrSensor){



    }
}
