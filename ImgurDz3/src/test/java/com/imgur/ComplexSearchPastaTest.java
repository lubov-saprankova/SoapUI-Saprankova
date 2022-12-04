package com.imgur;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComplexSearchPastaTest {


    @Test
    void PostComplexSearchPositiveTest() {
        given()
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch?query=pasta&maxFat=25&number=2" +
                        "cuisine=false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .statusCode(200);
    }
    @Test
    void PostComplexSearchQueryParameterPositiveTest() {
        given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("cuisine" , "false")
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch/quary/pasta?")
                .then()
                .statusCode(200);
    }



    @Test
    void PostComplexPositiveTest() {
        given()
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch?" +
                        "query=pasta&maxFat=25&number=2=false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .statusCode(200);
    }

    @Test
    void PostComplexQueryParameterPositiveTest() {
        given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2" , "false")
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch/716429/pasta?")
                .then()
                .statusCode(200);
    }

    @Test
    void PostComplexWithBodyChecksAfterRequestPositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch/716429/pasta?")
                .body()
                .jsonPath();
        assertThat(response.get("Garlic"), equalTo(false));
        assertThat(response.get("Scallions"), is(false));
        assertThat(response.get("Cauliflower & Breadcrumbs"), equalTo(false));

    }

    @Test
    void PostComplexSearchWithBodyChecksInGivenPositiveTest() {
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .expect()
                .body("Garlic", equalTo(false))
                .body("Scallions", is(false))
                .body("Cauliflower & Breadcrumbs", equalTo(false))
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch/716429/pasta?")
                .then()
                .statusCode(200);
    }

    @Test
    void PostComplexSearchWithLoggingPositiveTest () {
        given()
                .log()
                .all()
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch/716429/pasta?")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}



