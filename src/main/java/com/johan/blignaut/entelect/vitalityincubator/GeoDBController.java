package com.johan.blignaut.entelect.vitalityincubator;

import com.johan.blignaut.entelect.vitalityincubator.helpers.GeoDBMappingHelper;
import com.johan.blignaut.entelect.vitalityincubator.restserver.api.GeoApi;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoDBController implements GeoApi {
    private final GeoDBMappingHelper geoDBMappingHelper;

    public GeoDBController(GeoDBMappingHelper geoDBMappingHelper) {
        this.geoDBMappingHelper = geoDBMappingHelper;
    }

    @Override
    public ResponseEntity<GeoDBServer> getPlaces(String filterByName) {
        return ResponseEntity.ok(geoDBMappingHelper.mapClientToServerGeoDB(filterByName));
    }
}
