package com.imgur;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class ComplexSearchTest {


    @Test
    void getComplexSearchPositiveTest() {
        given()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch/query/pasta?" +
                        "cuisine=false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .statusCode(200);
    }
        @Test
        void getComplexSearchQueryParameterPositiveTest() {
        given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("cuisine" , "false")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch/quary/pasta?")
                .then()
                .statusCode(200);
    }


    String id;
    @Test
    void addComplexSearchMealTest() {
        id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\":\"Uno Pizza\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
    }
    @AfterEach
    void tearComplexSearchDown() {
        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
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

