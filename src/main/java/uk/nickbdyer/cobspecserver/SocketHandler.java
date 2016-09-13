package uk.nickbdyer.cobspecserver;

import uk.nickbdyer.cobspecserver.middleware.Logger;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.requests.RequestParser;
import uk.nickbdyer.cobspecserver.responses.Response;
import uk.nickbdyer.cobspecserver.responses.ResponseFormatter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.Socket;

public class SocketHandler {
    private final Socket socket;
    private final Logger logger;
    private final Router router;

    public SocketHandler(Socket socket, Logger logger, Router router) {
        this.socket = socket;
        this.logger = logger;
        this.router = router;
    }

    public void processRequestAndRespond() {
        try {
            RequestParser parser = new RequestParser(socket, logger);
            Request request = parser.parse();
            Response response = router.route(request);
            new ResponseFormatter(socket, response).sendResponse();
        } catch (Exception e) {
            returnA500Error();
        }
    }

    private void returnA500Error() {
        try {
            new ResponseFormatter(socket, Response.ServerError()).sendResponse();
        } catch (IOException e1) {
            throw new UncheckedIOException(e1);
        }
    }
}
