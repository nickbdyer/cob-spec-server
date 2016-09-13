package uk.nickbdyer.cobspecserver.testdoubles;

import java.io.InputStream;
import java.net.Socket;

public class UnreadableSocketStub extends Socket {

    @Override
    public InputStream getInputStream() {
        return new UnreadbleInputStreamStub();

    }
}
