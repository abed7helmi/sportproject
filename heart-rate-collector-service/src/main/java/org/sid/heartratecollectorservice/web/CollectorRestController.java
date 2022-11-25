package org.sid.heartratecollectorservice.web;


import org.sid.heartratecollectorservice.models.HrSensor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectorRestController {

    @PostMapping(value ="/hr",consumes="application/json")
    public void sendhr(@RequestBody HrSensor hrSensor) {
        System.out.println("zebi");
        System.out.println(hrSensor);
    }
}
