package uk.nickbdyer.cobspecserver.testdoubles;

import uk.nickbdyer.cobspecserver.filemanager.FileFinder;

import java.io.File;

public class DummyFileFinder extends FileFinder {

    public DummyFileFinder() {
        super(new File(""));
    }

}
