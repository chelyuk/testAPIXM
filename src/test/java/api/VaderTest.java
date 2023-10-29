package api;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class VaderTest extends BaseTest {
    String CHARACTER_NAME = "Vader";
    int minPlanetAmount = Integer.MAX_VALUE;
    String filmName = "";

    @Test
    public void vaderTest() {
        Response vader = request.searchCharacter(CHARACTER_NAME);
        assertThat(vader.getStatusCode(), equalTo(HttpStatus.SC_OK));
        List<String> filmsWithVader = vader.path("results.films[0]");
        for (String filmURL : filmsWithVader) {
            Response film = request.getAPIURL(filmURL);
            assertThat(film.getStatusCode(), equalTo(HttpStatus.SC_OK));
            List<String> planets = film.path("planets");
            int planetAmount = planets.size();
            if (planetAmount < minPlanetAmount) {
                minPlanetAmount = planetAmount;
                filmName = film.path("title");
            }
        }
        System.out.println(filmName);
        Response filmWithMinimumPlanets = request.getFilmByName(filmName);
        assertThat(filmWithMinimumPlanets.getStatusCode(), equalTo(HttpStatus.SC_OK));
        String vaderStarship = vader.path("results.starships[0][0]");
        assertThat(filmWithMinimumPlanets.path("results.starships[0]"), hasItem(vaderStarship));
    }
}
