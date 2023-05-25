package com.johan.blignaut.entelect.vitalityincubator.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class HelloWorldController {

    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    public HelloWorldController(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return applicationConfiguration.getHelloWorldBean();
    }

    @PostMapping("/message")
    public String showMessage(@RequestBody String message) {
        return message;
    }
}
