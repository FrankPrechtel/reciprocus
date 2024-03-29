package eu.prechtel.reciprocus;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;

import static org.hamcrest.Matchers.*;

//@WebFluxTest(
//        controllers = GingerbreadController.class,
//        excludeAutoConfiguration = { ReactiveSecurityAutoConfiguration.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GingerbreadControllerTest {

    final WebTestClient webTestClient;

    public GingerbreadControllerTest(@Autowired WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    void exchangeGingerbread() {
        final String token = "Basic " + Base64.getEncoder().encodeToString("myuser:Password1234".getBytes());
        webTestClient.get()
                .uri("/gingerbread/1")
                .header("Authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Gingerbread.class)
                .value(Gingerbread::getFlavor, is(not(emptyOrNullString())));
    }

    @Test
    // FIXME: remove @Disabled to activate the test
    @Disabled
    void feast() {
        // FIXME: delete all gingerbread and create and get a list of 2 of them
        ////////////////////////////////////////////////////////////////
        // webTestClient...
        // webTestClient...
        // webTestClient...
        ////////////////////////////////////////////////////////////////

        webTestClient.get()
                .uri("/gingerbreads")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Gingerbread.class)
        // FIXME: enable
        ////////////////////////////////////////////////////////////////
        //        .hasSize(2)
        ////////////////////////////////////////////////////////////////
        ;
    }
}