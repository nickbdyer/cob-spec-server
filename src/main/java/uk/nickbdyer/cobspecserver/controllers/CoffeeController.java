package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.httpserver.controllers.Controller;
import uk.nickbdyer.httpserver.requests.Request;
import uk.nickbdyer.httpserver.responses.Response;

import java.util.HashMap;


public class CoffeeController extends Controller {

    @Override
    public Response get(Request request) {
        return new Response(418, new HashMap<>(), "I'm a teapot");
    }

}
