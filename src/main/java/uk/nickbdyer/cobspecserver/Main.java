package uk.nickbdyer.cobspecserver;

import uk.nickbdyer.cobspecserver.controllers.*;
import uk.nickbdyer.cobspecserver.filemanager.FileFinder;
import uk.nickbdyer.cobspecserver.middleware.BasicAuth;
import uk.nickbdyer.cobspecserver.middleware.Logger;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        File publicFolder = new File(arguments.getDirectoryPath());
        FileFinder fileFinder = new FileFinder(publicFolder);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        BasicAuth basicAuth = new BasicAuth();
        basicAuth.addAuthorisedUser("admin", "hunter2");

        Logger logger = new Logger();

        FormData form = new FormData("");
        Router router = new Router(fileFinder);
        router.addController("/", new RootController(publicFolder));
        router.addController("/redirect", new RedirectController());
        router.addController("/form", new FormController(form));
        router.addController("/parameters", new ParameterController());
        router.addController("/method_options", new MethodOptionsController());
        router.addController("/method_options2", new MethodOptions2Controller());
        router.addController("/coffee", new CoffeeController());
        router.addController("/tea", new TeaController());
        router.addController("/logs", new LogsController(basicAuth, logger));
        router.addController("/elmttt", new ElmTTTController(fileFinder));

        new HttpServer(executorService, new ServerSocket(arguments.getPort()), router, logger).listen();
    }

}
