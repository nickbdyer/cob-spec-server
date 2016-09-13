package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import java.util.HashMap;
import java.util.Map;


public class RedirectController extends Controller {

    @Override
    public Response get(Request request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", "http://localhost:5000/");
        return new Response(302, headers, "");
    }

}
