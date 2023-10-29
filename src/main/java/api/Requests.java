package api;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Urls;

import static io.restassured.RestAssured.given;

public class Requests {

    private RequestSpecification requestSpec;

    private final Urls url = new Urls();

    public void requestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(url.BASE)
                .setContentType("application/json")
                .build();
    }

    public Response getFilms(String filmNumber) {
        requestSpec();
        return given()
                .spec(requestSpec)
        .when()
                .get(url.FILM + filmNumber)
                .prettyPeek()
        .then()
                .extract()
                .response();
    }

    public Response getFilmByName(String filmName) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .queryParam("search", filmName)
        .when()
                .get(url.FILM)
                .prettyPeek()
        .then()
                .extract()
                .response();
    }

    public Response getPlanets(String planetNumber) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .when()
                .get(url.PLANETS + planetNumber)
                .then()
                .extract()
                .response();
    }

    public Response getPeople(String peopleNumber) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .when()
                .get(url.PEOPLE + peopleNumber)
                .then()
                .extract()
                .response();
    }

    public Response getSpecies(String speciesNumber) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .when()
                .get(url.SPECIES + speciesNumber)
                .then()
                .extract()
                .response();
    }

    public Response getStarships(String shipsNumber) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .when()
                .get(url.STARSHIPS + shipsNumber)
                .then()
                .extract()
                .response();
    }

    public Response searchFilm(String filmName) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .queryParam("search", filmName)
                .when()
                .get(url.FILM)
                .then()
                .extract()
                .response();
    }

    public Response searchCharacter(String characterName) {
        requestSpec();
        return given()
                .spec(requestSpec)
                .queryParam("search", characterName)
                .when()
                .get(url.PEOPLE)
                .then()
                .extract()
                .response();
    }

    public Response getAPIURL(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }
}
