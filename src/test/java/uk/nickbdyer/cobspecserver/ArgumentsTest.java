package uk.nickbdyer.cobspecserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {

    @Test
    public void argumentsCanParseAPortNumber() {
        String[] arguments = new String[]{"-p", "5000"};

        int port = new Arguments(arguments).getPort();

        assertEquals(5000, port);
    }

    @Test
    public void argumentsCanParseDifferentPortNumber() {
        String[] arguments = new String[]{"-p", "7777"};

        int port = new Arguments(arguments).getPort();

        assertEquals(7777, port);
    }

    @Test
    public void argumentsWillReturnTheDefaultPortOf5000() {
        String[] arguments = new String[]{};

        int port = new Arguments(arguments).getPort();

        assertEquals(5000, port);
    }

    @Test
    public void argumentsCanParseADirectoryPath() {
        String[] arguments = new String[]{"-d", "/path/to/somewhere/"};

        String directoryPath = new Arguments(arguments).getDirectoryPath();

        assertEquals("/path/to/somewhere/", directoryPath);
    }

    @Test
    public void argumentsCanParseADifferentDirectoryPath() {
        String[] arguments = new String[]{"-d", "/path/to/somewhere/else/"};

        String directoryPath = new Arguments(arguments).getDirectoryPath();

        assertEquals("/path/to/somewhere/else/", directoryPath);
    }

    @Test
    public void argumentsWillReturnTheDefaultDirectoryToPublicCobSpec() {
        String[] arguments = new String[]{};

        String directoryPath = new Arguments(arguments).getDirectoryPath();

        assertEquals("/Users/nickdyer/projects/cob_spec/public", directoryPath);
    }
}
