package com.johan.blignaut.entelect.vitalityincubator.task2.helpers;

import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.MetaDataServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.CityServer;
import com.johan.blignaut.entelect.vitalityincubator.restclient.ApiException;
import com.johan.blignaut.entelect.vitalityincubator.restclient.api.GeodbApi;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.CityClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.LinkServer;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.LinkClient;

import java.util.ArrayList;
import java.util.List;

public class GeoDBMappingHelper {

    private final GeodbApi geodbApi;

    public GeoDBMappingHelper(GeodbApi geodbApi) {
        this.geodbApi = geodbApi;
    }

    public GeoDBServer mapGeoDB() throws ApiException {
        GeoDBServer geoDBServer = new GeoDBServer();
        GeoDBClient geodbClient = this.geodbApi.getPlaces();
        List<CityServer> citiesList = new ArrayList<>();
        for (CityClient cityClient : geodbClient.getData()) {
            CityServer cityServer = new CityServer();
            cityServer.setId(cityClient.getId());
            cityServer.setWikiDataId(cityClient.getWikiDataId());
            cityServer.setType(cityClient.getType());
            cityServer.setName(cityClient.getName());
            cityServer.setCountry(cityClient.getCountry());
            cityServer.setCountryCode(cityClient.getCountryCode());
            cityServer.setRegion(cityClient.getRegion());
            cityServer.setRegionCode(cityClient.getRegionCode());
            cityServer.setRegionWdId(cityClient.getRegionWdId());
            cityServer.setLatitude(cityClient.getLatitude());
            cityServer.setLongitude(cityClient.getLongitude());
            cityServer.setPopulation(cityClient.getPopulation());
            citiesList.add(cityServer);
        }
        geoDBServer.setData(citiesList);
        List<LinkServer> linksList = new ArrayList<>();
        for (LinkClient linkClient : geodbClient.getLinks()) {
            LinkServer linkServer = new LinkServer();
            linkServer.setRel(linkClient.getRel());
            linkServer.setHref(linkClient.getHref());
            linksList.add(linkServer);
        }
        geoDBServer.setLinks(linksList);
        MetaDataServer metaDataServer = new MetaDataServer();
        metaDataServer.setCurrentOffset(geodbClient.getMetadata().getCurrentOffset());
        metaDataServer.setTotalCount(geodbClient.getMetadata().getTotalCount());
        geoDBServer.setMetadata(metaDataServer);
        return geoDBServer;
    }
}
