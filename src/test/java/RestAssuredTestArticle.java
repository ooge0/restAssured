import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.get;

public class RestAssuredTestArticle {

    @Test
    public void getRequestFindCapital() throws JSONException {

        // выполняем запрос get для доступа ко всем параметрам ответа
        Response resp = get("http://restcountries.eu/rest/v1/name/ukraine");

        JSONArray jsonResponse = new JSONArray(resp.asString());

        // получение параметра capital (столицы Норвегии)
        String capital = jsonResponse.getJSONObject(0).getString("capital");
        System.out.println(capital);
        // проверка, что столицей является Осло/Kiev
        AssertJUnit.assertEquals(capital, "Oslo");
    }

    @Test(description = "GET")
    public void getRequestExampleTest() throws JSONException {
        Response response = get("http://restcountries.eu/rest/v1/name/ukraine");
        JSONArray jsonResponse = new JSONArray(response.asString());
        String capital = jsonResponse.getJSONObject(0).getString("capital");
        String country_name = jsonResponse.getJSONObject(0).getString("name");
        int country_population = jsonResponse.getJSONObject(0).getInt("population");
        String country_nativeName = jsonResponse.getJSONObject(0).getString("nativeName");

        System.out.println("Capital city of "+country_name+ " is :"+capital+
        "\n Population of "+ country_name + " is " +country_population+
        "\n Native name of "+country_name+ " is : "+country_nativeName);
        Assert.assertEquals(capital, "Kiev");
    }

    @Test(description = "POST")
    public void postRequestExampleTest() {
        String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());

        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandomString);
        requestBody.put("LastName", someRandomString);
        requestBody.put("UserName", someRandomString);
        requestBody.put("Password", someRandomString);
        requestBody.put("Email", someRandomString + "@gmail.com");
        System.out.println("someRandomString is :" + someRandomString);

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://restapi.demoqa.com/customer/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");
        System.out.println(response.getBody().asString());
    }

}
