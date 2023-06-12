package com.johan.blignaut.entelect.vitalityincubator.task2.helpers;

import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.CityServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.LinkServer;
import com.johan.blignaut.entelect.vitalityincubator.task2.service.GeoDBMappingService;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeoDBMappingHelper {

    private final GeodbApi geodbApi;
    private final GeoDBMappingService geoDBMappingService;

    public GeoDBMappingHelper(GeodbApi geodbApi, GeoDBMappingService geoDBMappingService) {
        this.geodbApi = geodbApi;
        this.geoDBMappingService = geoDBMappingService;
    }

    public GeoDBServer mapClientToServerGeoDB() throws IOException {
        GeoDBServer geoDBServer = new GeoDBServer();
        Call<GeoDBClient> call = geodbApi.getPlaces();
        Response<GeoDBClient> response = call.execute();
        GeoDBClient geodbClient = response.body();
        List<CityServer> citiesList = geodbClient.getData()
                .stream()
                .map(geoDBMappingService::mapClientToServerCity)
                .collect(Collectors.toList());
        geoDBServer.setData(citiesList);
        List<LinkServer> linksList = geodbClient.getLinks()
                .stream()
                .map(geoDBMappingService::mapClientToServerLink)
                .collect(Collectors.toList());
        geoDBServer.setLinks(linksList);
        geoDBServer.setMetadata(
                geoDBMappingService.mapClientToServerMetaData(
                        geodbClient.getMetadata()
                )
        );
        return geoDBServer;
    }
}
