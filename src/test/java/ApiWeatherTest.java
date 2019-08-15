import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class ApiWeatherTest {

    @Test
    public void testApiWeather() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/Everett");

        String responseBody = response.getBody().asString();

        Reporter.log("Response " + responseBody);

        int statusCode = response.getStatusCode();
        Reporter.log("We get status code " + statusCode);

//        log.info("Status code " + statusCode);

        Assert.assertEquals(200, statusCode);
//        log.info("Status code is verified");

        String statusLine = response.statusLine();
//        log.info(statusLine);

        Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
//        log.info("Status line is verified");

        Headers headers = response.headers();

        for (Header header: headers) {

            System.out.println("Key " + header.getName() + " Value " + header.getValue());

        }

        String contentType = response.header("Content-Type");

        Assert.assertEquals("application/json", contentType);

        String server = response.header("Server");

        Assert.assertEquals("nginx", server);

        JsonPath jsonObject = response.jsonPath();

        String city = jsonObject.get("City");
//        log.info(city);
        Assert.assertEquals("Everett", city);
//        log.info("City is verified");

        String temperature = jsonObject.get("Temperature");
//        log.info(temperature);
        Assert.assertTrue(temperature.contains("Degree celsius"));
//        log.info("Temperature is verified");

        String humidity = jsonObject.get("Humidity");
//        log.info(humidity);
        Assert.assertTrue(humidity.contains("Percent"));
//        log.info("Humidity is verified");

        String windSpeed = jsonObject.get("WindSpeed");
//        log.info(windSpeed);
        Assert.assertTrue(windSpeed.contains("per hour"));
//        log.info("WindSpeed is verified");

        String windDirectionDegree = jsonObject.get("WindDirectionDegree");
//        log.info(windDirectionDegree);
        Assert.assertTrue(windDirectionDegree.contains("Degree"));
//        log.info("WindDirectionDegree is verified");


    }



}
