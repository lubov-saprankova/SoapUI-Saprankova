package com.imgur;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class ComplexSearchTest extends AbstractTest {


    @Test
    void getComplexSearchPositiveTest() {
        given()
                .spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/mealplanner/hath/shopping-list/items?" + "false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .spec(responseSpecification);

    }
        @Test
        void getComplexSearchQueryParameterPositiveTest() {
        given()
                .spec(requestSpecification)
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("hath" , "false")
                .when()
                .get("https://api.spoonacular.com/mealplanner/hath/shopping-list/items?")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void getComplexPositiveTest() {
        given()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?" +
                        "query=pasta&maxFat=25&number=2=false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .statusCode(200);
    }

    @Test
    void getComplexQueryParameterPositiveTest() {
        given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("maxFat=25&number=2" , "false")
                .when()
                .get(" https://api.spoonacular.com/recipes/complexSearch/query=pasta&maxFat=25&number=2=false?")
                .then()
                .statusCode(200);
    }

    @Test
    void getComplexWithBodyChecksAfterRequestPositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information/quary/pasta?")
                .body()
                .jsonPath();
        assertThat(response.get("Garlic"), equalTo(false));
        assertThat(response.get("Scallions"), is(false));
        assertThat(response.get("Cauliflower & Breadcrumbs"), equalTo(false));

    }

    @Test
    void getComplexSearchWithBodyChecksInGivenPositiveTest() {
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("query=pasta&maxFat=25&number=2", "false")
                .expect()
                .body("Garlic", equalTo(false))
                .body("Scallions", is(false))
                .body("Cauliflower & Breadcrumbs", equalTo(false))
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information/quary/pasta?")
                .then()
                .statusCode(200);
    }

}

