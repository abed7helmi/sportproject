package org.sid.mock.task;

import com.sun.jdi.InternalException;
import org.sid.mock.models.HrSensor;
import org.sid.mock.models.Member;
import org.sid.mock.repositories.HeartRateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Configuration
@EnableScheduling
public class SportJob {
    public static boolean startmock=true;

    @Autowired
    private HeartRateProxy heartRateProxy;


    @Scheduled(fixedRate = 1500L)
    public void productionJob() throws InternalException {
        long j=0;

        if (startmock){

            Random random = new Random();
            for (int i=1 ; i<2 ; i++){
                long l= i;
                HrSensor hr = HrSensor.builder().idHrSensor(j+1).memberId(l).cardiacFrequency(60+Math.random()*10).build();

                System.out.println(hr);
                heartRateProxy.sendHr(hr);





            }


        }
    }
}
