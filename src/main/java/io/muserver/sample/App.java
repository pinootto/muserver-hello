package io.muserver.sample;

import io.muserver.*;
import io.muserver.handlers.ResourceHandlerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Starting mu-server-sample app");
        MuServer server = MuServerBuilder.muServer()
            .withHttpPort(18080)
            .addHandler(Method.GET, "/", (request, response, pathParams) -> {
                response.status(200);
                response.contentType(ContentTypes.TEXT_PLAIN);
                response.write("Hello, world!");
            })
            .start();

        log.info("Server started at " + server.httpUri());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down...");
            server.stop();
            log.info("Shut down complete.");
        }));

    }

}
