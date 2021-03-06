package uk.nickbdyer.cobspecserver.controllers;

import org.junit.Before;
import org.junit.Test;
import uk.nickbdyer.httpserver.requests.Request;
import uk.nickbdyer.httpserver.requests.RequestLine;
import uk.nickbdyer.httpserver.responses.Response;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static uk.nickbdyer.httpserver.requests.Method.*;

public class FormControllerTest {

    private FormData data;

    @Before
    public void setUp() throws Exception {
        data = new FormData("");
    }

    @Test
    public void willRespondToAGetRequest() {
        FormController controller = new FormController(data);
        Request request = new Request(GET, "/form");

        Response response = controller.execute(request);

        assertEquals(200, response.getStatusCode());
        assertEquals(0, response.getBody().length);
    }

    @Test
    public void willRespondToAPostRequest() {
        FormController controller = new FormController(data);
        RequestLine requestLine = new RequestLine(POST, "/form", new HashMap<>());
        Request request = new Request(requestLine, new HashMap<>(), "data=fatcat");

        Response response = controller.execute(request);

        assertEquals(200, response.getStatusCode());
        assertEquals("data=fatcat", new String(response.getBody()));
    }

    @Test
    public void willRespondToAPutRequest() {
        FormController controller = new FormController(data);
        RequestLine requestLine = new RequestLine(PUT, "/form", new HashMap<>());
        Request request = new Request(requestLine, new HashMap<>(), "data=heathcliff");

        Response response = controller.execute(request);

        assertEquals(200, response.getStatusCode());
        assertEquals("data=heathcliff", new String(response.getBody()));
    }

    @Test
    public void willRespondToADeleteRequest() {
        FormData data = new FormData("data=asdl;vhaw;ejkrasdf");
        FormController controller = new FormController(data);
        Request request = new Request(DELETE, "/form");

        Response response = controller.execute(request);

        assertEquals(200, response.getStatusCode());
        assertEquals(0, response.getBody().length);
    }
}
