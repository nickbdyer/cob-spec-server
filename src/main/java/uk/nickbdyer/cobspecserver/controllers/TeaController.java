package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import java.util.HashMap;

public class TeaController extends Controller {

    @Override
    public Response get(Request request) {
        return new Response(200, new HashMap<>(), "Short and stout");
    }

}
