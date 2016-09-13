package uk.nickbdyer.cobspecserver.testdoubles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketStubWithRequest extends Socket {

    private final String request;

    public SocketStubWithRequest(String request) {
        this.request = request;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(request.getBytes());
    }

    @Override
    public OutputStream getOutputStream() {
        return new ByteArrayOutputStream();
    }

}
