package com.johan.blignaut.entelect.vitalityincubator.task2.service;

import com.johan.blignaut.entelect.vitalityincubator.restclient.model.CityClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.LinkClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.MetaDataClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.CityServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.LinkServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.MetaDataServer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoDBMappingServiceImpl implements GeoDBMappingService {
    @Override
    public GeoDBServer mapClientToServerGeoDB(GeoDBClient geoDBClient) {
        GeoDBServer geoDBServer = new GeoDBServer();
        List<CityServer> citiesList = geoDBClient.getData()
                .stream()
                .map(this::mapClientToServerCity)
                .collect(Collectors.toList());
        geoDBServer.setData(citiesList);
        List<LinkServer> linksList = geoDBClient.getLinks()
                .stream()
                .map(this::mapClientToServerLink)
                .collect(Collectors.toList());
        geoDBServer.setLinks(linksList);
        geoDBServer.setMetadata(
                this.mapClientToServerMetaData(
                        geoDBClient.getMetadata()
                )
        );
        return geoDBServer;
    }

    private CityServer mapClientToServerCity(CityClient cityClient) {
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
        return cityServer;
    }

    private LinkServer mapClientToServerLink(LinkClient linkClient) {
        LinkServer linkServer = new LinkServer();
        linkServer.setRel(linkServer.getRel());
        linkServer.setHref(linkClient.getHref());
        return linkServer;
    }

    private MetaDataServer mapClientToServerMetaData(MetaDataClient metaDataClient) {
        MetaDataServer metaDataServer = new MetaDataServer();
        metaDataServer.setCurrentOffset(metaDataClient.getCurrentOffset());
        metaDataServer.setTotalCount(metaDataClient.getTotalCount());
        return metaDataServer;
    }
}
