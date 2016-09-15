package uk.nickbdyer.cobspecserver.controllers;

import org.junit.Before;
import org.junit.Test;
import uk.nickbdyer.httpserver.middleware.Logger;
import uk.nickbdyer.httpserver.requests.Request;
import uk.nickbdyer.httpserver.requests.RequestLine;
import uk.nickbdyer.httpserver.responses.Response;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static uk.nickbdyer.httpserver.requests.Method.GET;

public class LogsControllerTest {

    private LogsController controller;
    private Logger logger;

    @Before
    public void setUp() {
        logger = new Logger();
        logger.log("GET /route HTTP/1.1");
        controller = new LogsController(logger);
    }

    @Test
    public void willRespondToAGetRequest() {
        RequestLine requestLine = new RequestLine(GET, "/logs", new HashMap<>());
        Request request = new Request(requestLine, new HashMap<>(), "");

        Response response = controller.execute(request);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void willReturnLogsWithAnAuthorisedRequest() {
        RequestLine requestLine = new RequestLine(GET, "/logs", new HashMap<>());
        Request request = new Request(requestLine, new HashMap<>(), "");

        Response response = controller.execute(request);

        assertThat(new String(response.getBody()), containsString("GET /route HTTP/1.1"));
    }

}
