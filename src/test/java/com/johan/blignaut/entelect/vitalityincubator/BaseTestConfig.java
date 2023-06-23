package com.johan.blignaut.entelect.vitalityincubator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import wiremock.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@Import({ApplicationConfig.class})
public class BaseTestConfig {

    public static String fromFile(String file) throws IOException {
        return FileUtils.readFileToString(new File("src/test/resources/" + file));
    }

}
