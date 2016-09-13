package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.cobspecserver.middleware.BasicAuth;
import uk.nickbdyer.cobspecserver.middleware.Logger;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import java.util.HashMap;
import java.util.stream.Collectors;

public class LogsController extends Controller {

    private final BasicAuth basicAuthorisor;
    private final Logger logger;

    public LogsController(BasicAuth basicAuthorisor, Logger logger) {
        this.basicAuthorisor = basicAuthorisor;
        this.logger = logger;
    }

    @Override
    public Response get(Request request) {
        String user = request.getHeaders().getOrDefault("Authorization", "");
        if (basicAuthorisor.userIsAuthorised(user)) {
            return new Response(200, new HashMap<>(), getLogs());
        }
        return new Response(401, basicAuthorisor.getUnAuthorisedHeader(), "");
    }

    private byte[] getLogs() {
        return logger.logs().stream()
                .collect(Collectors.joining("\n")).getBytes();
    }

}
