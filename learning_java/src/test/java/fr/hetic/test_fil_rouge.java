package fr.hetic;

import org.junit.jupiter.api.Test;
import java.io.File;

public class test_fil_rouge {

    @Test
    public void testProcessFile() {
        File tempFile = createTempFile("test", ".op");

        tempFile.delete();
    }

    private File createTempFile(String string, String string2) {
        throw new UnsupportedOperationException("Unimplemented method 'createTempFile'");
    }
}