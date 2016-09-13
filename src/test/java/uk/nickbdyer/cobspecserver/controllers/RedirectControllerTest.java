package uk.nickbdyer.cobspecserver.controllers;

import org.junit.Test;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import static org.junit.Assert.assertEquals;
import static uk.nickbdyer.cobspecserver.requests.Method.GET;

public class RedirectControllerTest {

    @Test
    public void willRespondToAGetRequest() {
        RedirectController controller = new RedirectController();
        Request request = new Request(GET, "/redirect");

        Response response = controller.execute(request);

        assertEquals(302, response.getStatusCode());
        assertEquals(0, response.getBody().length);
    }

}
