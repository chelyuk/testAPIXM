package api;

import base.BaseTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class XMTest extends BaseTest {
    String FILM_NAME = "A New Hope";
    String CHARACTER_NAME = "Biggs Darklighter";
    String STARSHIP_CLASS = "Starfighter";
    String PILOT = "Luke Skywalker";

    @Test
    public void MyTest() {
        Response getFilm = request.getFilmByName(FILM_NAME);

        assertThat(getFilm.getStatusCode(), equalTo(HttpStatus.SC_OK));

        getFilm.path("results.characters[0]");
        Response getLuke = request.searchCharacter(PILOT);

        assertThat(getLuke.getStatusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(getFilm.path("results.characters[0]"), hasItem((String)getLuke.path("results.url[0]")));

        Response getBiggs = request.searchCharacter(CHARACTER_NAME);

        assertThat(getBiggs.getStatusCode(), equalTo(HttpStatus.SC_OK));

        Response starship = request.getAPIURL(getBiggs.path("results.starships[0][0]"));

        assertThat(starship.getStatusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(starship.path("starship_class"), equalTo(STARSHIP_CLASS));
        assertThat(starship.path("pilots"), hasItem((String)getLuke.path("results.url[0]")));
    }
}
