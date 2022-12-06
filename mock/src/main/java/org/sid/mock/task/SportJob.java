package org.sid.mock.task;

import com.sun.jdi.InternalException;
import org.sid.mock.dto.HrSensorDTO;
import org.sid.mock.models.HrSensor;
import org.sid.mock.models.Member;
import org.sid.mock.repositories.HeartRateProxy;
import org.sid.mock.repositories.HrSensorRepository;
import org.sid.mock.repositories.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
@EnableScheduling
public class SportJob {

    Logger logger = LoggerFactory.getLogger(SportJob.class);

    public static boolean startmock=true;

    @Autowired
    private HeartRateProxy heartRateProxy;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HrSensorRepository hrSensorRepository;




    @Scheduled(fixedRate = 15000L)
    public void productionJob() throws InternalException {
        long j=0;

        if (startmock){

            Random random = new Random();
            List<Member> members = memberRepository.findAll();


           for (Member m: members ){

                HrSensor hr = HrSensor.builder().state("").cardiacFrequency(160+Math.random()*20).date(null).member(m).build();


                //hrSensorRepository.save(hr);
                HrSensorDTO hrSensorDTO = new HrSensorDTO(hr.getIdHrSensor(),hr.getState(),hr.getCardiacFrequency(),hr.getDate(),hr.getMember().getIdMember());
                heartRateProxy.sendHr(hrSensorDTO);






            }


        }
    }
}
