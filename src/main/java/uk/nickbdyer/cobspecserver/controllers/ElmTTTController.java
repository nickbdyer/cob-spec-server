package uk.nickbdyer.cobspecserver.controllers;

import uk.nickbdyer.cobspecserver.filemanager.FileFinder;
import uk.nickbdyer.cobspecserver.filemanager.FileManager;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.responses.Response;

import java.io.File;
import java.util.HashMap;

public class ElmTTTController extends Controller {

    private final FileManager fileMan;
    private final File file;

    public ElmTTTController(FileFinder fileFinder) {
        this.file = fileFinder.getFile("index.html");
        this.fileMan = new FileManager(file);

    }

    @Override
    public Response get(Request request) {
        if (file != null) {
            byte[] html = fileMan.getFileBytes();
            return new Response(200, new HashMap<>(), html);
        }
        return Response.NotFound();
    }

}
