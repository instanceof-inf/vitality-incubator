package com.johan.blignaut.entelect.vitalityincubator.service;


import com.johan.blignaut.entelect.vitalityincubator.restclient.model.GeoDBClient;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;

public interface GeoDBMappingService {

    GeoDBServer mapClientToServerGeoDB(GeoDBClient geoDBClient, String filterByName);
}
