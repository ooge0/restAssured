
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
        //RestAssured.basePath = "/maps/api/place/details/json?";
    }

    @Test (enabled=true)
    public void statusCodeVerification () {
        given ()
                .param("unit", "imperial")
                .param ("origins","Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyDaH4HZ_rHSGrNeiLJetnFyAd2vxtDxOpY")
                .when()
                .get("/distancematrix/json")
                .then()
                .statusCode(200);


    }


    @Test
    public void getResponseBody () {
        Response res  =
                given ()
                        .param("unit", "imperial")
                        .param ("origins","Washington,DC")
                        .param("destinations", "New+York+City,NY")
                        .param("key", "AIzaSyDaH4HZ_rHSGrNeiLJetnFyAd2vxtDxOpY")
                        .when()
                        .get("/distancematrix/json");
        System.out.println(res.body().prettyPrint());


    }
}