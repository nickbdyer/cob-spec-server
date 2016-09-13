package uk.nickbdyer.cobspecserver.controllers;

import org.junit.Test;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static uk.nickbdyer.cobspecserver.requests.Method.GET;

public class CoffeeControllerTest {

    @Test
    public void willRespondToAGetRequest() {
        CoffeeController controller = new CoffeeController();
        Request request = new Request(GET, "/");

        Response response = controller.execute(request);

        assertEquals(418, response.getStatusCode());
    }

    @Test
    public void bodyWillContainImATeaPot() {
        CoffeeController controller = new CoffeeController();
        Request request = new Request(GET, "/");

        Response response = controller.execute(request);

        assertThat(new String(response.getBody()), containsString("I'm a teapot"));
    }
}
