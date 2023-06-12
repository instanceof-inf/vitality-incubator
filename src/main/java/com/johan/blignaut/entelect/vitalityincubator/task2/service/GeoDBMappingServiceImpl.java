package com.johan.blignaut.entelect.vitalityincubator.task2.service;

import com.johan.blignaut.entelect.vitalityincubator.restclient.model.CityClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.LinkClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.MetaDataClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.CityServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.LinkServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.MetaDataServer;
import org.springframework.stereotype.Service;

@Service
public class GeoDBMappingServiceImpl implements GeoDBMappingService {
    @Override
    public CityServer mapClientToServerCity(CityClient cityClient) {
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

    @Override
    public LinkServer mapClientToServerLink(LinkClient linkClient) {
        LinkServer linkServer = new LinkServer();
        linkServer.setRel(linkServer.getRel());
        linkServer.setHref(linkClient.getHref());
        return linkServer;
    }

    @Override
    public MetaDataServer mapClientToServerMetaData(MetaDataClient metaDataClient) {
        MetaDataServer metaDataServer = new MetaDataServer();
        metaDataServer.setCurrentOffset(metaDataClient.getCurrentOffset());
        metaDataServer.setTotalCount(metaDataClient.getTotalCount());
        return metaDataServer;
    }
}
