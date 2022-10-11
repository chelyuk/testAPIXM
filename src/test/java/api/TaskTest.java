package api;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TaskTest extends BaseTest {
    String FILM_NAME = "A New Hope";
    String CHARACTER_NAME = "Biggs Darklighter";
    String STARSHIP_CLASS = "Starfighter";
    String PILOT = "Luke Skywalker";


    @Test
    public void filmNameTest() {
        String endpoint = "https://swapi.dev/api/films";

        given()
                .queryParam("search", FILM_NAME)
        .when()
                .get(endpoint)
        .then()
        .assertThat()
                .body("results.title[0]", equalTo(FILM_NAME));
    }


    @Test
    public void characterNameTest() {
        String endpoint = "https://swapi.dev/api/people";

        given()
                .queryParam("search", CHARACTER_NAME)
        .when()
                .get(endpoint)
        .then()
        .assertThat()
                .body("results.name[0]", equalTo(CHARACTER_NAME));
    }
}
