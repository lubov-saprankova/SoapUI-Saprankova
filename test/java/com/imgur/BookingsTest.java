package com.imgur;


import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class BookingsTest {

    @Test
    void getBookingTest () {
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/" +
                        "booking=4=false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .statusCode(200);

    }
    @Test
    void getBookingDataTest() {
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("booking=4", "false")
                .pathParam( "id", "1334")
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}&apiKey={8283d76201b4453387373dad1009474f}",1334, false, "apiKey", "8283d76201b4453387373dad1009474f")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .contentType("application/json")
                .formParam("title","calculated when request is sent")
                .when()
                .post("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?")
                .then()
                .statusCode(200);

        Cookie someCookie = new Cookie
                .Builder("some_cookie", "some_value")
                .setSecured(true)
                .setComment("some comment")
                .build();


        given().cookie("firstname","Dmitriy Smirnov")
                .cookie(someCookie)
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}&apiKey={8283d76201b4453387373dad1009474f}")
                .then()
                .statusCode(200);

        given().headers("firstname","Dmitriy Smirnov")
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}&apiKey={8283d76201b4453387373dad1009474f}")
                .then()
                .statusCode(200);

    }
    @Test
    void getBookingDataTestPositiveTest() {
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("booking=4", "false")
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?")
                .then()
                .statusCode(200);
    }
    @Test
    void getBookingDataBodyChecksAfterRequestPositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("booking=4", "false")
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?")
                .body()
                .jsonPath();
        assertThat(response.get("totalprice"), equalTo(true));
        assertThat(response.get("total"), equalTo(false));
    }



    @Test
    void getBookingResponseData(){
        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}&apiKey={8283d76201b4453387373dad1009474f}");
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


        String cuisine = given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .when()
                .post("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}")
                .path("booking");

        System.out.println("booking" + "getBookingDataTest");

        response = given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .when()
                .post("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}")
                .then().extract().response();

        String confidence = given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .when()
                .post("https://restful-booker.herokuapp.com/"+"booking=4/{1334}?" +
                        "booking=4={1334}")
                .then().extract()
                .jsonPath()
                .get("booking")
                .toString();

        System.out.println("booking ");

    }
}


