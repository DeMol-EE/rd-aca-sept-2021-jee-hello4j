package world.inetum.realdolmen.greeting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import world.inetum.realdolmen.HttpIntegrationTestBase;

public class GreeterServletIT extends HttpIntegrationTestBase {

    @Test
    public void greetWithNoParametersReturnsHelloWorld() {
        // act
        Response response = RestAssured.when()
                .get("/greet");
        // assert
        response.then()
                .statusCode(200)
                .and()
                .body(Matchers.equalTo("Hello, world!"));
        // assert that jms was used... how?
    }
}