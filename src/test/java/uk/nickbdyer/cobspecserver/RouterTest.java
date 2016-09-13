package uk.nickbdyer.cobspecserver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.nickbdyer.cobspecserver.filemanager.FileFinder;
import uk.nickbdyer.cobspecserver.requests.Request;
import uk.nickbdyer.cobspecserver.requests.RequestLine;
import uk.nickbdyer.cobspecserver.responses.Response;
import uk.nickbdyer.cobspecserver.testdoubles.ControllerSpy;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static uk.nickbdyer.cobspecserver.requests.Method.GET;

public class RouterTest {

    private Router router;
    private Request request;
    private FileFinder fileFinder;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        fileFinder = new FileFinder(tempFolder.getRoot());
        router = new Router(fileFinder);
        request = new Request(new RequestLine(GET, "/test", new HashMap<>()), new HashMap<>(), "");
    }

    @Test
    public void routerWillMatchARequestPathToInTheRouteTableAndTriggerTheController() {
        ControllerSpy controller = new ControllerSpy();
        router.addController("/test", controller);

        router.route(request);

        assertEquals("get", controller.methodTriggered);
    }

    @Test
    public void routerWillReturnNotFoundIfPathCanNotBeMatched() {
        Response response = router.route(request);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void routerWillRespondToAFileRoute() throws IOException {
        tempFolder.newFile("test");

        Response response = router.route(request);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void routerWillReturnNotFoundForPreloadRequestsWithoutHeaders() throws IOException {
        Request request = new Request(null, null);

        Response response = router.route(request);

        assertEquals(404, response.getStatusCode());
    }

}