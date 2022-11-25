package org.sid.heartratecollectorservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class CollectorConfigController {
    @Value("${global.params.GP1}")
    private String p1;
    @Value("${global.params.GP2}")
    private String p2;
    @Value("${heart-collector.params.a}")
    private String x;
    @Value("${heart-collector.params.b}")
    private String y;

    @GetMapping("/params")
    public Map<String,String> params(){
        return Map.of("P1",p1,"P2",p2,"X",x,"Y",y);
    }
}


