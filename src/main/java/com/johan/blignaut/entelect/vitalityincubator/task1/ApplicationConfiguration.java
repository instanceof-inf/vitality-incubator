package com.johan.blignaut.entelect.vitalityincubator.task1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
