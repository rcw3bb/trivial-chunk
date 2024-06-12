package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class NomenTest {

    @Test
    public void nameFromFileWithExt() {
        final var file = new File("TestFile.txt");
        final var nomen = new Nomen(file);
        assertEquals("TestFile", nomen.getFilename().get());
    }

    @Test
    public void nameFromFileWithoutExt() {
        final var file = new File("TestFile");
        final var nomen = new Nomen(file);
        assertEquals("TestFile", nomen.getFilename().get());
    }

    @Test
    public void nameFromFileExtOnly() {
        final var file = new File(".txt");
        final var nomen = new Nomen(file);
        assertTrue(nomen.getFilename().isEmpty());
    }

    @Test
    public void extFromFileWithExt() {
        final var file = new File("TestFile.txt");
        final var nomen = new Nomen(file);
        assertEquals("txt", nomen.getExtension().get());
    }

    @Test
    public void extFromFileWithoutExt() {
        final var file = new File("TestFile");
        final var nomen = new Nomen(file);
        assertTrue(nomen.getExtension().isEmpty());
    }

    @Test
    public void extFromFileExtOnly() {
        final var file = new File(".txt");
        final var nomen = new Nomen(file);
        assertEquals("txt", nomen.getExtension().get());
    }

}
