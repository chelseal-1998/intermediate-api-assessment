package app.restfulbooker.tests.restfulbooker.healthcheck;

import app.restfulbooker.tests.BaseTest;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestfulBookerHealthCheck extends BaseTest {

    @Test
    @Description("Ping the Restful Booker health check endpoint")
    public void pingRestfulBookerHealthEndpoint() {
        given().
                contentType(ContentType.JSON).
                when().
                get("ping").
                then().
                assertThat().
                statusCode(HttpStatus.SC_CREATED);
    }
}