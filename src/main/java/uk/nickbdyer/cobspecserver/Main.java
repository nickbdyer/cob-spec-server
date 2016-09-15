package uk.nickbdyer.cobspecserver;

import uk.nickbdyer.cobspecserver.controllers.*;
import uk.nickbdyer.httpserver.HttpServer;
import uk.nickbdyer.httpserver.filemanager.FileFinder;
import uk.nickbdyer.httpserver.middleware.BasicAuth;
import uk.nickbdyer.httpserver.middleware.Logger;
import uk.nickbdyer.httpserver.middleware.MiddlewareStack;
import uk.nickbdyer.httpserver.middleware.Router;

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

        BasicAuth basicAuth = new BasicAuth();
        basicAuth.addAuthorisedUser("/logs", "admin", "hunter2");

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
        router.addController("/logs", new LogsController(logger));
        router.addController("/elmttt", new ElmTTTController(fileFinder));

        MiddlewareStack middlewareStack = new MiddlewareStack();
        middlewareStack.add(logger);
        middlewareStack.add(basicAuth);
        middlewareStack.add(router);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        new HttpServer(executorService, new ServerSocket(arguments.getPort()), middlewareStack, logger).listen();
    }

}
