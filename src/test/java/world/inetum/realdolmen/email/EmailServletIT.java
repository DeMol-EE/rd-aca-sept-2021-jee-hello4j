package world.inetum.realdolmen.email;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import world.inetum.realdolmen.HttpIntegrationTestBase;

// Extra system properties needed to pass authentication:
// - username (defaults to admin)
// - password (required, there is no default!)
public class EmailServletIT extends HttpIntegrationTestBase {

    @Test
    public void postSendsEmail() {
        String username = System.getProperty("username", "admin");
        String password = System.getProperty("password");
        RequestSpecification requestSpecification = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .and()
                .body("This is a test email")
                .contentType("text/plain")
                .and()
                .queryParam("to", "test@recipient.com");
        // act
        Response response = requestSpecification.when()
                .post("/email");
        // assert
        response.then()
                .statusCode(200);
        // verify that mail was sent... how?
    }

    @Test
    public void postWithCredentialsFails() {
        RequestSpecification requestSpecification = RestAssured.given()
                .body("This is a test email")
                .contentType("text/plain")
                .and()
                .queryParam("to", "test@recipient.com");
        // act
        Response response = requestSpecification.when()
                .post("/email");
        // assert
        response.then()
                .statusCode(401);
        // verify that no email was sent... how?
    }
}