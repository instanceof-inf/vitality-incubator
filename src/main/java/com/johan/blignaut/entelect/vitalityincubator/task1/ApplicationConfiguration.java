package com.johan.blignaut.entelect.vitalityincubator.task1;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfiguration {

    private HelloWorldBean helloWorldBean;

    public ApplicationConfiguration(HelloWorldBean helloWorldBean) {
        this.helloWorldBean = helloWorldBean;
    }

    public String getHelloWorldBean() {
        return this.helloWorldBean.getMessage();
    }
}
