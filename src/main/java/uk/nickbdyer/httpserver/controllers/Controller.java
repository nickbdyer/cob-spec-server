package uk.nickbdyer.httpserver.controllers;

import uk.nickbdyer.httpserver.requests.Request;
import uk.nickbdyer.httpserver.responses.Response;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static uk.nickbdyer.httpserver.responses.StatusLine.METHOD_NOT_ALLOWED;

public abstract class Controller {

    public Response execute(Request request) {
        switch (request.getMethod()) {
            case GET:
                return get(request);
            case POST:
                return post(request);
            case PUT:
                return put(request);
            case DELETE:
                return delete(request);
            case HEAD:
                return head(request);
            case OPTIONS:
                return options(request);
            case TRACE:
                return trace(request);
            case CONNECT:
                return connect(request);
            default:
                return MethodNotAllowed();
        }

    }

    protected Response get(Request request) {
        return MethodNotAllowed();
    }

    protected Response post(Request request) {
        return MethodNotAllowed();
    }

    protected Response put(Request request) {
        return MethodNotAllowed();
    }

    protected Response delete(Request request) {
        return MethodNotAllowed();
    }

    protected Response head(Request request) {
        return MethodNotAllowed();
    }

    protected Response options(Request request) {
        return MethodNotAllowed();
    }

    protected Response trace(Request request) {
        return MethodNotAllowed();
    }

    protected Response connect(Request request) {
        return MethodNotAllowed();
    }

    private Response MethodNotAllowed() {
        return new Response(METHOD_NOT_ALLOWED, allowedMethods(), "");
    }

    protected String allowedMethods() {
        List<String> methods = Arrays.stream(this.getClass().getDeclaredMethods())
                .map(method -> method.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        return "Allow: " + String.join(",", methods) + "\n";
    }

}
