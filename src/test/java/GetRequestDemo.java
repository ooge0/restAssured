
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestDemo {
    @BeforeClass
    public void setup () {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.basePath = "/maps/api/";
    }

    @Test (enabled=true)
    public void statusCodeVerification () {
        given ()
                .param("units", "imperial")
                .param ("origins","Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c")
                .when()
                .get("/distancematrix/json")
                .then()
                .statusCode(200);


    }


    @Test
    public void getResponseBody () {
        Response res  =
                given ()
                        .param("units", "imperial")
                        .param ("origins","Washington,DC")
                        .param("destinations", "New+York+City,NY")
                        .param("key", "AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c")
                        .when()
                        .get("/distancematrix/json");
        System.out.println(res.body().prettyPrint());


    }
}