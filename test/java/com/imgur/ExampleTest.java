package com.imgur;

import com.imgur.builder.Response;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.ResponseSpecification;
import lesson4.dto.ShoppingListRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class ExampleTest extends AbstractTest {

    @Test
    void getExampleTest() {
        given()
                .when()
                .get("https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .spec(responseSpecification);

        given()
                .when()
                .request(Method.GET,"https://api.spoonacular.com/mealplanner/dsky/shopping-list/items", false, getApiKey())
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getSpecifyingRequestDataTest() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("aisle","Baking")
                .request(Method.DELETE,"https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);


    given()
                .when()
                .get(getBaseUrl()+"mealplanner/{username}/shopping-list"
                        + "hath={baking powder}&apiKey={apiKey}",115388, true, getApiKey())
                .then()
                .spec(responseSpecification);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/json")
                .formParam("hash","The private hash for the username.")
                .when()
                .post(getBaseUrl()+"mealplanner/dsky/shopping-list/hath")
                .then()
                .spec(responseSpecification);

        Cookie someCookie = new Cookie
                .Builder("some_cookie", "some_value")
                .setSecured(true)
                .setComment("some comment")
                .build();


        given().cookie("username","max")
                .cookie(someCookie)
                .when()
                .get(getBaseUrl()+"mealplanner/{username}/shopping-list"
                        + "hath={baking powder}&apiKey={apiKey}" +getApiKey())
                .then()
                .spec(responseSpecification);

        given().headers("username","max")
                .when()
                .get(getBaseUrl()+"mealplanner/{username}/shopping-list"
                        + "hath={baking powder}&apiKey={apiKey}" +getApiKey())
                .then()
                .spec(responseSpecification);

        given()
                .queryParam("hash")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"baking powder\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post(getBaseUrl()+"mealplanner/dsky/shopping-list/hath")
                .then()
                .spec(responseSpecification);

    }



    @Test
    void getRecipeWithQueryParametersPositiveTest() {
        given()
                .queryParam("apiKey", "62e8122bf46941569505d78f6d632a72")
                .queryParam("hash", "True")
                .when()
                .get("https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void getResponseData(){
        Response response = given()
                .pathParam("apiKey", "62e8122bf46941569505d78f6d632a72")
                .when()
                .get("https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .extract()
                .body()
                .as(Response.class);
        assertThat(response.getAisle(), equalTo("Baking"));
        assertThat(response.getId(), equalTo("115388"));
        assertThat(response.getName(), equalTo("baking powder"));
        assertThat(response.getAmount(), equalTo("1.0"));
        assertThat(response.getUnit(), equalTo("package"));
        assertThat(response.getPantryItem(), equalTo("false"));
        assertThat(response.getCost(), equalTo("0.71"));
        assertThat(response.getIngredientId(), equalTo("18369"));
        assertThat(response.getStartDate(), equalTo("1588291200"));
        assertThat(response.getEndDate(), equalTo("1588896000"));

        // Get all headers
        Headers allHeaders = response.getHeaders();
        // Get a single header value:
        System.out.println("Content-Encoding: " + response.getHeader("Content-Encoding"));

        // Get all cookies as simple name-value pairs
        Map<String, String> allCookies = response.getCookies();
        // Get a single cookie value:
        System.out.println("CookieName: " + response.getCookie("cookieName"));

        // Get status line
        System.out.println("StatusLine: " + response.getStatusLine());
        // Get status code
        System.out.println("Code: " + response.getStatusCode());


        String hath = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"dsky/shopping-list/items")
                .path("hash");

        System.out.println("Baking " + hath);

        response = (Response) given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"dsky/shopping-list/items")
                .then().extract().response();

        String confidence = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"dsky/shopping-list/items")
                .then().extract()
                .jsonPath()
                .get("hash")
                .toString();

        System.out.println("Baking " + hath);

    }

}
