package world.inetum.realdolmen;

import io.restassured.RestAssured;

// Caution: app needs to be deployed and running before this test can be executed!
// System properties that may be passed in:
// - host (defaults to localhost)
// - port (defaults to 8080)
public abstract class HttpIntegrationTestBase {

    public HttpIntegrationTestBase() {
        String rawPort = System.getProperty("port", "8080");
        RestAssured.port = Integer.parseInt(rawPort);
        String rawHost = System.getProperty("host", "localhost");
        RestAssured.baseURI = "http://" + rawHost;
    }

}
