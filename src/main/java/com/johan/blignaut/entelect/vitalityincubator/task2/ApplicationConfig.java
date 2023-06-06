package com.johan.blignaut.entelect.vitalityincubator.task2;

import com.johan.blignaut.entelect.vitalityincubator.restclient.ApiClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }

    @Bean
    public GeodbApi geodbApi() {
        return new GeodbApi(apiClient());
    }
}
