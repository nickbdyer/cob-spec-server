package uk.nickbdyer.cobspecserver;

import org.junit.Test;
import uk.nickbdyer.cobspecserver.testdoubles.*;

import java.io.ByteArrayOutputStream;
import java.io.UncheckedIOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class SocketHandlerTest {

    @Test
    public void socketHandlerWillRespondToARequest() {
        ByteArrayOutputStream recievedResponse = new ByteArrayOutputStream();
        SocketStub socketStub = new SocketStub("GET / HTTP/1.1", recievedResponse);
        SocketHandler socketHandler = new SocketHandler(socketStub, new DummyLogger(), new Router(new DummyFileFinder()));

        socketHandler.processRequestAndRespond();

        assertThat(recievedResponse.toString(), containsString("HTTP/1.1 404 Not Found"));
    }

    @Test(expected = UncheckedIOException.class)
    public void socketHandlerWillThrowUncheckIOExceptionIfStreamsCannotBeRead() {
        SocketHandler socketHandler = new SocketHandler(new UnreadableSocketStub(), new DummyLogger(), new Router(new DummyFileFinder()));

        socketHandler.processRequestAndRespond();
    }

    @Test
    public void socketHandlerWillReturnA500IfPossible() {
        ByteArrayOutputStream receivedContent = new ByteArrayOutputStream();
        SocketHandler socketHandler = new SocketHandler(new BrokenInputStreamSocket(receivedContent), new DummyLogger(), new Router(new DummyFileFinder()));

        socketHandler.processRequestAndRespond();

        assertThat(receivedContent.toString(), containsString("HTTP/1.1 500 Internal Server Error"));
    }
}