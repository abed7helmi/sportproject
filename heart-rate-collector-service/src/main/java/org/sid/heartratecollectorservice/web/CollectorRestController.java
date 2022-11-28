package org.sid.heartratecollectorservice.web;


import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.sid.heartratecollectorservice.models.HrSensor;
import org.sid.heartratecollectorservice.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectorRestController {

    @Autowired
    private Producer producer;
    @PostMapping(value ="/hr",consumes="application/json")
    public void sendhr(@RequestBody HrSensorDTO hrSensor) {
        System.out.println("zebi");
        System.out.println(hrSensor);
        producer.send(hrSensor);

    }
}
