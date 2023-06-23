package com.johan.blignaut.entelect.vitalityincubator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ApplicationConfig {

    public static final String BASE_URL = "http://geodb-free-service.wirefreethought.com/v1/";
    @Bean
    public GeodbApi geodbApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        return new Retrofit.Builder().client(httpClientBuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .build().create(GeodbApi.class);
    }

    @Bean
    public ThreadPoolExecutor executor() {
        return (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }
}
