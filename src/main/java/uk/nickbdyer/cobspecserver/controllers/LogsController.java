package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.httpserver.controllers.Controller;
import uk.nickbdyer.httpserver.middleware.Logger;
import uk.nickbdyer.httpserver.requests.Request;
import uk.nickbdyer.httpserver.responses.Response;

import java.util.HashMap;
import java.util.stream.Collectors;

public class LogsController extends Controller {

    private final Logger logger;

    public LogsController(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Response get(Request request) {
        return new Response(200, new HashMap<>(), getLogs());
    }

    private byte[] getLogs() {
        return logger.logs().stream()
                .collect(Collectors.joining("\n")).getBytes();
    }

}
