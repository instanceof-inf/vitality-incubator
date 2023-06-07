package com.johan.blignaut.entelect.vitalityincubator.task2;
import com.johan.blignaut.entelect.vitalityincubator.restclient.ApiException;
import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import com.johan.blignaut.entelect.vitalityincubator.restserver.api.GeoApi;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.task2.helpers.GeoDBMappingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeoDBController implements GeoApi {

    private GeoDBMappingHelper geoDBMappingHelper;

    @Autowired
    public GeoDBController(GeoDBMappingHelper geoDBMappingHelper) {
        this.geoDBMappingHelper = geoDBMappingHelper;
    }

    @Override
    public ResponseEntity<GeoDBServer> getPlaces() throws ApiException {
        GeoDBServer geoDBServer = this.geoDBMappingHelper.mapGeoDB();
        return ResponseEntity.ok(geoDBServer);
    }
}
