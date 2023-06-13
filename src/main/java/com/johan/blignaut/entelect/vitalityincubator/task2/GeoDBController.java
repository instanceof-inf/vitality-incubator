package com.johan.blignaut.entelect.vitalityincubator.task2;

import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.api.GeoApi;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.task2.exception.CustomInternalServerException;
import com.johan.blignaut.entelect.vitalityincubator.task2.service.GeoDBMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@RestController
public class GeoDBController implements GeoApi {

    private final GeodbApi geodbApi;
    private final GeoDBMappingService geoDBMappingService;

    public GeoDBController(GeodbApi geodbApi, GeoDBMappingService geoDBMappingService) {
        this.geodbApi = geodbApi;
        this.geoDBMappingService = geoDBMappingService;
    }

    @Override
    public ResponseEntity<GeoDBServer> getPlaces(String filterByName) {
        try {
            Call<GeoDBClient> call = geodbApi.getPlaces();
            Response<GeoDBClient> response = call.execute();
            if (response.isSuccessful()) {
                GeoDBClient geodbClient = response.body();
                GeoDBServer geoDBServer = geoDBMappingService.mapClientToServerGeoDB(geodbClient);
                return ResponseEntity.ok(geoDBServer);
            }
            throw new CustomInternalServerException();
        } catch (IOException e) {
            throw new CustomInternalServerException(e);
        }
    }
}
