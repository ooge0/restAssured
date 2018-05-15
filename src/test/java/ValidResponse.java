import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class ValidResponse {
    @BeforeClass
    public void setup () {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.basePath = "/maps/api/";
        //RestAssured.basePath = "/maps/api/place/details/json?";
    }


    @Test(enabled=true)
    public void statusCodeVerification () {
        given ()
                .param("unit", "imperial")
                .param ("origins","Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c") //AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c   /
                //AIzaSyDaH4HZ_rHSGrNeiLJetnFyAd2vxtDxOpY
                .when()
                .get("/distancematrix/json")
                .then()
                .statusCode(200)
                .and ()
                .body("rows[0].elements[0].distance.text", equalTo("362 km"))
                .contentType(ContentType.JSON);


    }

}

