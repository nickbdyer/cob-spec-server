package uk.nickbdyer.cobspecserver.testdoubles;

import uk.nickbdyer.cobspecserver.middleware.Logger;

public class DummyLogger extends Logger {

    @Override
    public void log(String request) {
    }
}
