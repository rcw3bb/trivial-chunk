package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.OSType;
import xyz.ronella.trivial.handy.PathFinder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFileTest {

    @Test
    public void testTextWindowsContent() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var pathFinder = PathFinder.getBuilder("textfile-windows.txt")
                .addPaths(List.of("src/test/resources", "src/test/resources/pathfinder"))
                .build();
        final var file = pathFinder.getFile().get();
        final var textFile = new TextFile(file);
        final var text = textFile.getText();
        assertEquals(expectation, text);
    }

    @Test
    public void testTextWindowsContentFromString() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var pathFinder = PathFinder.getBuilder("textfile-windows.txt")
                .addPaths(List.of("src/test/resources", "src/test/resources/pathfinder"))
                .build();
        final var fileString = pathFinder.getFile().get().getAbsolutePath();
        final var textFile = new TextFile(fileString);
        final var text = textFile.getText();
        assertEquals(expectation, text);
    }

    @Test
    public void testTextUnixContent() throws IOException {
        final var expectation = "Line 1.\n" +
                "Line 2.\n" +
                "Line 3.";
        final var pathFinder = PathFinder.getBuilder("textfile-unix.txt")
                .addPaths(List.of("src/test/resources", "src/test/resources/pathfinder"))
                .build();
        final var file = pathFinder.getFile().get();
        final var textFile = new TextFile(file);
        final var text = textFile.getText(OSType.Linux.getEOL());
        assertEquals(expectation, text);
    }


}
