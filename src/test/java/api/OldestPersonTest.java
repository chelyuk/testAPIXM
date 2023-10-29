package api;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OldestPersonTest extends BaseTest {

    @Test
    public void oldestPersonTest() {
        String url = "https://swapi.dev/api/people/";
        String oldestPerson = "";
        double oldestAge = Double.MIN_VALUE;

        while (url != null) {
            Response peoplePage = request.getAPIURL(url);
            assertThat(peoplePage.getStatusCode(), equalTo(HttpStatus.SC_OK));
            JsonPath jsonPath = peoplePage.jsonPath();

            List<String> names = jsonPath.getList("results.name");
            List<String> birthYears = jsonPath.getList("results.birth_year");

            for (int i = 0; i < names.size(); i++) {
                String birthYearStr = birthYears.get(i);
                if (!birthYearStr.equals("unknown")) {
                    double age = Double.parseDouble(birthYearStr.replace("BBY", ""));
                    if (age > oldestAge) {
                        oldestAge = age;
                        oldestPerson = names.get(i);
                    }
                }
            }

            url = jsonPath.getString("next");
        }

        System.out.println("The oldest person who ever played in all Star Wars films is: " + oldestPerson);
    }
}
