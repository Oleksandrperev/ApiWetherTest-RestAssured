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
        Reporter.log("Status code " + statusCode);

        Assert.assertEquals(200, statusCode);
        Reporter.log("Status code " + statusCode + " is verified");

        String statusLine = response.statusLine();
        Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
        Reporter.log("Status line " + statusLine + " is verified");

        Headers headers = response.headers();

        for (Header header: headers) {

            System.out.println("Key " + header.getName() + " Value " + header.getValue());

        }

        String contentType = response.header("Content-Type");
        Assert.assertEquals("application/json", contentType);
        Reporter.log("Content Type " + contentType + " is verified");

        String server = response.header("Server");
        Assert.assertEquals("nginx", server);
        Reporter.log("Server " + server + " is verified");

        JsonPath jsonObject = response.jsonPath();

        String city = jsonObject.get("City");
        Assert.assertEquals("Everett", city);
        Reporter.log("City " + city + " is verified");

        String temperature = jsonObject.get("Temperature");
        Assert.assertTrue(temperature.contains("Degree celsius"));
        Reporter.log("Temperature " + temperature + " is verified");

        String humidity = jsonObject.get("Humidity");
        Assert.assertTrue(humidity.contains("Percent"));
        Reporter.log("Humidity " + humidity + " is verified");

        String windSpeed = jsonObject.get("WindSpeed");
        Assert.assertTrue(windSpeed.contains("per hour"));
        Reporter.log("WindSpeed " + windSpeed + " is verified");

        String windDirectionDegree = jsonObject.get("WindDirectionDegree");
        Assert.assertTrue(windDirectionDegree.contains("Degree"));
        Reporter.log("WindDirectionDegree " + windDirectionDegree + " is verified");

    }



}
