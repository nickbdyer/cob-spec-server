package uk.nickbdyer.cobspecserver.testdoubles;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketSpy extends ServerSocket {

    private boolean acceptWasCalled;

    public ServerSocketSpy() throws IOException {
        acceptWasCalled = false;
    }

    public boolean acceptWasCalled() {
       return acceptWasCalled;
    }

    @Override
    public Socket accept() {
        acceptWasCalled = true;
        return null;
    }

}
