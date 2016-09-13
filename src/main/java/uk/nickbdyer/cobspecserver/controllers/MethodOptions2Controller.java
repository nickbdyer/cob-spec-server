package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import java.util.HashMap;

public class MethodOptions2Controller extends Controller {

    @Override
    public Response get(Request request) {
        return new Response(200, new HashMap<>(),"");
    }

    @Override
    public Response options(Request request) {
        return new Response(200, allowedMethods(), "");
    }

}
