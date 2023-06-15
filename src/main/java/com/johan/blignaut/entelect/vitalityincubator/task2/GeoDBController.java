package com.johan.blignaut.entelect.vitalityincubator.task2;

import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.api.GeoApi;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.task2.exception.CustomInternalServerException;
import com.johan.blignaut.entelect.vitalityincubator.task2.helpers.GeoDBMappingHelper;
import com.johan.blignaut.entelect.vitalityincubator.task2.service.GeoDBMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@RestController
public class GeoDBController implements GeoApi {
    private final GeoDBMappingHelper geoDBMappingHelper;

    public GeoDBController(GeoDBMappingHelper geoDBMappingHelper) {
        this.geoDBMappingHelper = geoDBMappingHelper;
    }

    @Override
    public ResponseEntity<GeoDBServer> getPlaces(String filterByName) {
        return ResponseEntity.ok(geoDBMappingHelper.mapClientToServerGeoDB());
    }
}
