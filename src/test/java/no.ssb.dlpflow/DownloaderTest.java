package no.ssb.dlpflow;


import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class DownloaderTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    private EmbeddedServer embeddedServer;


    @BeforeEach
    void setUp() {
        RestAssured.port = embeddedServer.getPort();

    }
    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

}
