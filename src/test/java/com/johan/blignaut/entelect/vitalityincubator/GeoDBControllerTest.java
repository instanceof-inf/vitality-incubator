package com.johan.blignaut.entelect.vitalityincubator;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.johan.blignaut.entelect.vitalityincubator.restserver.model.GeoDBServer;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {VitalityIncubatorApplication.class, BaseTestConfig.class})
@WebAppConfiguration
public class GeoDBControllerTest {

    @Autowired
    private GeoDBController geoDBController;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void testControllerSuccessfulResponse() {
//        WireMockServer wireMockServer = new WireMockServer();
//        configureFor("localhost", 8089);
        stubFor(get("/geo/places")
                .willReturn(ok()
                        .withBodyFile("/__files/geodb-successful-response.json")
                )
        );
        ResponseEntity<GeoDBServer> places = geoDBController.getPlaces("");

        Assertions.assertThat(places.getStatusCode().is2xxSuccessful()).isTrue();
//        GeoDBServer geoDBServer = null;
//        try {
//            InputStream is = GeoDBServer.class.getResourceAsStream("/__files/geodb-successful-response.json");
//            geoDBServer = new ObjectMapper().readValue(is, GeoDBServer.class);
//            System.out.println(geoDBServer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertThat(places.getBody()).isEqualTo(geoDBServer);
    }

    @Test
    public void testControllerFailedResponse() {
//        WireMockServer wireMockServer = new WireMockServer();
//        wireMockServer.start();
//        configureFor("localhost", 8089);
        stubFor(get("/geo/places")
                .willReturn(aResponse()
                        .withStatus(500)
                )
        );

        ResponseEntity<GeoDBServer> places = geoDBController.getPlaces("");

        System.out.println("STATUSS" + places.getStatusCode());
        Assertions.assertThat(places.getStatusCode().is5xxServerError()).isTrue();
    }
}
