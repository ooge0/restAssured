
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



public class POSTRequestDemo {
    @BeforeClass
    public void setup () {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.basePath = "/maps/api/";
        //RestAssured.basePath = "/maps/api/place/details/json?";
    }


    @Test(enabled=true)
    public void statusCodeVerification () {
        given ()
                .param("key", "AIzaSyDaH4HZ_rHSGrNeiLJetnFyAd2vxtDxOpY") //AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c
                .body ("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -33.8669710,\n" +
                        "    \"lng\": 151.1958750\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"new place Shoes!\",\n" +
                        "  \"phone_number\": \"(02) 9374 4000\",\n" +
                        "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                        "  \"types\": [\"shoe_store\"],\n" +
                        "  \"website\": \"http://www.google.com.au/\",\n" +
                        "  \"language\": \"en-AU\"\n" +
                        "}")
                // .param("key", "AIzaSyDaH4HZ_rHSGrNeiLJetnFyAd2vxtDxOpY")

                .when()
                    .get("//place/add/json")
                .then()
                    .statusCode(200);

    }
}
