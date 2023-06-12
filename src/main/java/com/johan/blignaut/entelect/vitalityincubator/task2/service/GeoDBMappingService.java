package com.johan.blignaut.entelect.vitalityincubator.task2.service;


import com.johan.blignaut.entelect.vitalityincubator.restclient.model.CityClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.LinkClient;
import com.johan.blignaut.entelect.vitalityincubator.restclient.model.MetaDataClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.CityServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.LinkServer;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.MetaDataServer;

public interface GeoDBMappingService {

    CityServer mapClientToServerCity(CityClient cityClient);
    LinkServer mapClientToServerLink(LinkClient linkClient);
    MetaDataServer mapClientToServerMetaData(MetaDataClient metaDataClient);
}
