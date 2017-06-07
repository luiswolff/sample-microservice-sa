package de.wolff.sample;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackendServer {

    private static final Logger LOGGER = Logger.getLogger(BackendServer.class.getName());
    private final HttpServer webServer;

    private BackendServer(HttpServer webServer) {
        this.webServer = webServer;
    }

    private static URI getBaseURI() {
        final String schema = System.getProperty("backend.SCHEMA", "http");
        final String host = System.getProperty("backend.HOST", "localhost");
        final Integer port = Integer.getInteger("backend.PORT", 8080);

        return UriBuilder.fromUri(schema + "://" + host)
                .port(port)
                .build();
    }

    private static BackendServer start() throws IOException {

        final URI BASE_URI = getBaseURI();

        ResourceConfig rc = new ResourceConfig()
                .registerClasses(PatientService.class);

        final HttpServer grizzlyServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc, true);

        LOGGER.info("Jersey app started. Try out " + BASE_URI + "\nHit CTRL + C to stop it ...");
        grizzlyServer.start();

        return new BackendServer(grizzlyServer);
    }

    private void stop() {
        webServer.shutdown();
    }

    public static void main(String[] args) throws IOException {
        Logger rootLogger = Logger.getGlobal();
        rootLogger.setLevel(Level.FINEST);
        BackendServer server = start();

        System.in.read();
        server.stop();
    }

}
