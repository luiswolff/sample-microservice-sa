package de.wolff.sample;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendServer {
    private static final Logger LOGGER = Logger.getLogger(FrontendServer.class.getName());
    private final HttpServer webServer;

    private FrontendServer(HttpServer webServer) {
        this.webServer = webServer;
    }

    private static URI getBaseURI() {
        final String schema = System.getProperty("frontend.SCHEMA", "http");
        final String host = System.getProperty("frontend.HOST", "localhost");
        final Integer port = Integer.getInteger("frontend.PORT", 8080);

        return UriBuilder.fromUri(schema + "://" + host)
                .port(port)
                .build();
    }

    private static FrontendServer start() throws IOException {

        final URI BASE_URI = getBaseURI();

        ResourceConfig rc = new ResourceConfig()
                .registerClasses(PatientController.class)
                .register(FreemarkerMvcFeature.class);

        final HttpServer grizzlyServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc, true);

        LOGGER.info("Jersey app started. Try out " + BASE_URI + "\nHit CTRL + C to stop it ...");
        grizzlyServer.start();

        return new FrontendServer(grizzlyServer);
    }

    private void stop() {
        webServer.shutdown();
    }

    public static void main(String[] args) throws IOException {
        Logger rootLogger = Logger.getGlobal();
        rootLogger.setLevel(Level.FINEST);
        FrontendServer server = start();

        System.in.read();
        server.stop();
    }
}
