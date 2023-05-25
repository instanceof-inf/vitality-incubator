package com.johan.blignaut.entelect.vitalityincubator.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorld helloWorld;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return helloWorld.getMessage();
    }

    @PostMapping("/message")
    public String showMessage(@RequestBody String message) {
        return message;
    }
}
