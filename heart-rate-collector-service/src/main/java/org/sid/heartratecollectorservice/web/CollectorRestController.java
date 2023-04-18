//package org.sid.heartratecollectorservice.web;
//
//
//import org.sid.heartratecollectorservice.dto.HrSensorDTO;
//import org.sid.heartratecollectorservice.services.Producer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CollectorRestController {
//
//    Logger logger = LoggerFactory.getLogger(CollectorRestController.class);
//    //@Autowired
//    private Producer producer;
//    @PostMapping(value ="/hr",consumes="application/json")
//    public void sendhr(@RequestBody HrSensorDTO hrSensor) {
//        logger.info("HR "+hrSensor + "RECEIVED FROM THE GATEWAY ");
//        producer.send(hrSensor);
//
//
//    }
//}
