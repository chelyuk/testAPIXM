package api;


import base.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;


public class ApiTests extends BaseTest {

    String FILM_NAME = "A New Hope";
    String CHARACTER_NAME = "Biggs Darklighter";
    String STARSHIP_CLASS = "Starfighter";
    String PILOT = "Luke Skywalker";

    @Test(groups = "people")
    public void lukeSkywalkerTest() {
        Response getLuke = request.searchCharacter(PILOT);
        assertEquals(getLuke.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(getLuke.path("results.name[0]"), PILOT);
        Response getHomePlanet = request.getAPIURL(getLuke.path("results.homeworld[0]"));
        assertEquals(getHomePlanet.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(getHomePlanet.path("name"), "Tatooine");
    }

    @Test(groups = "people")
    public void bobaFettTest() {
        Response getBoba = request.searchCharacter("Boba Fett");
        assertEquals(getBoba.getStatusCode(), HttpStatus.SC_OK);
        Response getHomePlanet = request.getAPIURL(getBoba.path("results.homeworld[0]"));
        assertEquals(getHomePlanet.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(getHomePlanet.path("name"), "Kamino");
    }

    @Test(groups = "film")
    public void jubbaTheHuttTest() {
        Response getJabba = request.searchCharacter("Jabba Desilijic Tiure");
        assertEquals(getJabba.getStatusCode(), HttpStatus.SC_OK);
        Response getHomePlanet = request.getAPIURL(getJabba.path("results.homeworld[0]"));
        assertEquals(getHomePlanet.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(getHomePlanet.path("name"), "Nal Hutta");
        ArrayList films = getJabba.path("results.films[0]");
//        TODO: find the film in which Jabba appears at first, based on release_date
//        for (Object film: films) {
//            Response release_date = request.getAPIURL((String) film);
//            Date date = release_date.path("release_date[0]");
//        }
    }
}
