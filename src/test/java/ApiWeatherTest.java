import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class ApiWeatherTest {

    private static Logger log = Logger.getLogger(ApiWeatherTest.class.getName());

    @Test
    public void testApiWeather() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/Everett");

        String responseBody = response.getBody().asString();

        log.info(responseBody);

        int statusCode = response.getStatusCode();

        log.info("Status code " + statusCode);

        Assert.assertEquals(200, statusCode);
        log.info("Status code is verified");

        String statusLine = response.statusLine();
        log.info(statusLine);

        Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
        log.info("Status line is verified");

        Headers headers = response.headers();

        for (Header header: headers) {

            System.out.println("Key " + header.getName() + " Value " + header.getValue());

        }

        String contentType = response.header("Content-Type");

        Assert.assertEquals("application/json", contentType);

        String server = response.header("Server");

        Assert.assertEquals("nginx", server);








    }



}
