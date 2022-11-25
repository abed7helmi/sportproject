package org.sid.mock.controllers;

import lombok.AllArgsConstructor;
import org.sid.mock.task.SportJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mock")
@AllArgsConstructor
public class MockRestController {

    private SportJob sportJob;

    @GetMapping("/startmock")
    public void startmock() {
        sportJob.startmock=true;

    }

    @GetMapping("/stopmock")
    public void stopmock() {
        sportJob.startmock=false;
    }

}
