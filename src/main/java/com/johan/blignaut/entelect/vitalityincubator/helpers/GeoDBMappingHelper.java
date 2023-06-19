package com.johan.blignaut.entelect.vitalityincubator.helpers;

import com.johan.blignaut.entelect.vitalityincubator.exception.CustomInternalServerException;
import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.service.GeoDBMappingService;
import kotlin.io.TextStreamsKt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Component
public class GeoDBMappingHelper {

    private final GeodbApi geodbApi;
    private final GeoDBMappingService geoDBMappingService;

    @Value("${itemsperpage}")
    private int itemsPerPage = 7;

    private static final String PLACES = "places";

    public GeoDBMappingHelper(GeodbApi geodbApi, GeoDBMappingService geoDBMappingService) {
        this.geodbApi = geodbApi;
        this.geoDBMappingService = geoDBMappingService;
    }

    @Cacheable(PLACES)
    public GeoDBServer mapClientToServerGeoDB(String filterByName) {
        try {
            Call<GeoDBClient> call = geodbApi.getPlaces(itemsPerPage);
            Response<GeoDBClient> response = call.execute();
            if (response.isSuccessful()) {
                GeoDBClient geodbClient = response.body();
                return geoDBMappingService.mapClientToServerGeoDB(geodbClient, filterByName);
            }
            throw new CustomInternalServerException(
                    response.errorBody() != null && !response.errorBody().string().isEmpty()
                            ? new JSONObject(TextStreamsKt.readText(response.errorBody().charStream())).getString("msg")
                            : response.message()
            );
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new CustomInternalServerException(e);
        }
    }
}