package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class RootController extends Controller {

    private final File publicfolder;

    public RootController(File publicfolder) {
        this.publicfolder = publicfolder;
    }

    @Override
    public Response get(Request request) {
        File[] files = publicfolder.listFiles();
        String body = null;
        if (files != null) {
            body = Arrays.stream(files)
                .map(File::getName)
                .map(this::makeLink)
                .collect(Collectors.joining());
        }
        return new Response(200, new HashMap<>(), body);
    }

    private String makeLink(String fileName) {
        return "<a href=\"/" + fileName + "\">" + fileName + "</a>\n";
    }

    @Override
    public Response head(Request request) {
        return new Response(200, new HashMap<>(), "");
    }

}
