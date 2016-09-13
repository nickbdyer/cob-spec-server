package uk.nickbdyer.cobspecserver.controllers;

import org.junit.Test;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import static org.junit.Assert.assertEquals;
import static uk.nickbdyer.cobspecserver.requests.Method.GET;

public class TeaControllerTest {

    @Test
    public void willRespondToAGetRequest() {
        TeaController controller = new TeaController();
        Request request = new Request(GET, "/");

        Response response = controller.execute(request);

        assertEquals(200, response.getStatusCode());
    }

}
