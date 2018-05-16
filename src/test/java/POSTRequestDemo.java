import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class POSTRequestDemo {
    @BeforeClass
    public void setup () {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.basePath = "/maps/api/";
     /*
        API_key1 = AIzaSyDaH4HZ_rHSGrNeiLJetnFyAd2vxtDxOpY
        API_key2 = AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c
*/
    }


    @Test(enabled=true)
    public void statusCodeVerification () {
        given ()
                .queryParam("key", "AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c")
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


                .when()
                    .post("/place/add/json")
                .then()
                    .statusCode(200).and()
                    .contentType(ContentType.JSON).and ()
                    .body("scope", equalTo ("APP")).and()
                    //.body("id", equalTo ("066d3edac51ba138cf621572884805fb56c98460")).and()
                    .body("status", equalTo("OK"));

    }
    @Test(enabled=true)
    public void printResponseBody () {
        Map<String, Double> locationMap= new HashMap<String, Double>();
        locationMap.put("lat", -33.8669710);
        locationMap.put("lng", 151.1958750);

        ArrayList<String > types = new ArrayList<String>();
        types.add("shoe_store");
        PlacesAndModel places = new PlacesAndModel();
        places.setLocation(locationMap);
        places.getAccuracy(50);
        places.setPhone_number("(02) 9374 4000");
        places.getAddresss("48 Pirrama Road, Pyrmont, NSW 2009, Australia");
        places.setTypes(types);
        places.setWebsite("http://www.google.com.au");
        places.setLanguage("en-AU");


       Response resp =  given ()
                .queryParam("key", "AIzaSyCPWu4gt6sxGQl1aJGjGZaPzp8Gjm9Im3c")
                .body (places)
                .contentType(ContentType.JSON)
                .when()
                .post("/place/add/json");
        System.out.println(resp.body().asString());



    }
}
