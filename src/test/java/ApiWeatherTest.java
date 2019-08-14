import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

public class ApiWeatherTest {

    @Test
    public void testApiWeather() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/Everett");

        String responseBody = response.getBody().asString();

        System.out.println(responseBody);

        int statusCode = response.getStatusCode();

        System.out.println(statusCode);








    }



}
