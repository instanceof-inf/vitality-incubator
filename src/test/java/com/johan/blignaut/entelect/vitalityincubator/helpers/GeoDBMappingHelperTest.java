package com.johan.blignaut.entelect.vitalityincubator.helpers;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.johan.blignaut.entelect.vitalityincubator.BaseTestConfig;
import com.johan.blignaut.entelect.vitalityincubator.VitalityIncubatorApplication;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {VitalityIncubatorApplication.class, BaseTestConfig.class})
@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
public class GeoDBMappingHelperTest {

    @Mock
    private GeoDBMappingHelper geoDBMappingHelper;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void testUnfilteredMapClientToServerGeoDB() {
        configureFor("geodb-free-service.wirefreethought.com/v1/", 8080);
        try {
            stubFor(get("/geo/places")
                    .willReturn(ok()
                            .withBody(BaseTestConfig.fromFile("__files/geodb-successful-response.json"))
                    )
            );
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail("Failed to read from file");
        }
        GeoDBServer geoDBServer = geoDBMappingHelper.mapClientToServerGeoDB("");
        Assert.assertEquals(10, geoDBServer.getData().size());
    }

    @Test
    public void testFilteredMapClientToServerGeoDB() {
        configureFor("geodb-free-service.wirefreethought.com/v1/", 8080);
        try {
            stubFor(get("/geo/places")
                    .willReturn(ok()
                            .withBody(BaseTestConfig.fromFile("__files/geodb-successful-filtered-response.json"))
                    )
            );
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail("Failed to read from file");
        }
        GeoDBServer geoDBServer = geoDBMappingHelper.mapClientToServerGeoDB("Al");
        Assert.assertEquals(2, geoDBServer.getData().size());
    }
}
