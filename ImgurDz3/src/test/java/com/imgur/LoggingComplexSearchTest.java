package com.imgur;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoggingComplexSearchTest {

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void getComplexSearchLogTest() {
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .log().method()
                .log().params()
                .when()
                .get(" https://api.spoonacular.com/recipes/complexSearch/query=pasta");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");

        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .log().all()
                .when()
                .get(" https://api.spoonacular.com/recipes/complexSearch/query=pasta");
    }

    @Test
    void getComplexLogTest(){
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .log().all()
                .when()
                .get(" https://api.spoonacular.com/recipes/complexSearch/query=pasta")
                .prettyPeek();
    }

    @Test
    void getComplexSearchErrorTest(){
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .when()
                .get(" https://api.spoonacular.com/recipes/complexSearch/query=pasta")
                .then().statusCode(201);
    }
}
