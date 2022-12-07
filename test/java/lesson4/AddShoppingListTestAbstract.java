package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddShoppingListTestAbstract extends TestAbstract {


    @Test
    void PostShoppingListPositiveTest  () {
        given()
                .when()
                .request(Method.POST,"https://api.spoonacular.com/mealplanner/dsky/shopping-list/items" +
                        "hash=false&apiKey=8283d76201b4453387373dad1009474f")
                .then()
                .spec(responseSpecification);

    }
    @Test
    void PostComplexSearchQueryParameterPositiveTest() {
        RestAssured.responseSpecification = responseSpecification;
        given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("hash" , "false")
                .when()
                .request(Method.POST,"https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void PostComplexQueryParameterPositiveTest() {
        given()
                .queryParam("apiKey" , "8283d76201b4453387373dad1009474f")
                .queryParam("hash&Baking" , "true")
                .when()
                .request(Method.POST,"https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .spec(responseSpecification);
    }

    RequestSpecification requestSpecification = null;

    @Test
    void beforeTest() {
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .addQueryParam("hash&Baking" , "true")
                .log(LogDetail.ALL)
                .build();

    }




    @Test
    void PostComplexWithBodyChecksAfterRequestPositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("hash&Baking" , "true")
                .when()
                .request(Method.POST,"https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .body()
                .jsonPath();
        assertThat(response.get("hash"), equalTo(true));
        assertThat(response.get("Baking"), is(true));
        assertThat(response.get("baking powder"), equalTo(true));

    }

    @Test
    void PostComplexSearchWithBodyChecksInGivenPositiveTest() {
        given()
                .queryParam("apiKey", "8283d76201b4453387373dad1009474f")
                .queryParam("hash&Baking" , "true")
                .expect()
                .when()
                .request(Method.POST,"https://api.spoonacular.com/recipes/complexSearch/716429/pasta?")
                .then()
                .spec(responseSpecification);
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
                .spec(responseSpecification);
    }
}



