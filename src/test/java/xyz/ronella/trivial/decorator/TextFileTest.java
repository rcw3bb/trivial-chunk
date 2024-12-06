package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import xyz.ronella.trivial.handy.EndOfLine;
import xyz.ronella.trivial.handy.OSType;
import xyz.ronella.trivial.handy.ObjectRequiredException;
import xyz.ronella.trivial.handy.PathFinder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextFileTest {

    @Test
    @EnabledOnOs({OS.WINDOWS})
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
    public void nullFile() {
        assertThrows(ObjectRequiredException.class, () -> new TextFile((File) null));
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
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
    @EnabledOnOs({OS.WINDOWS})
    public void setTextContentOnly() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var file = new File("src/test/resources/dummy.txt");
        final var textFile = new TextFile(file);
        textFile.setText(expectation);
        final var text = textFile.getText();
        assertEquals(expectation, text);
        file.delete();
        assertFalse(file.exists());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void setTextWithCharset() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var file = new File("src/test/resources/dummy.txt");
        final var textFile = new TextFile(file, StandardCharsets.UTF_16);
        textFile.setText(expectation);
        final var text = textFile.getText();
        assertEquals(expectation, text);
        file.delete();
        assertFalse(file.exists());
    }

    @Test
    public void setTextWithLineEnding() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var file = new File("src/test/resources/dummy.txt");
        final var textFile = new TextFile(file, EndOfLine.CRLF);
        textFile.setText(expectation);
        final var text = textFile.getText();
        assertEquals(expectation, text);
        file.delete();
        assertFalse(file.exists());
    }

    @Test
    public void setTextWithCharsetString() throws IOException {
        final var expectation = new String("""
                Line 1.\r
                Line 2.\r
                Line 3.""".getBytes(StandardCharsets.UTF_16), StandardCharsets.UTF_16);
        final var file = new File("src/test/resources/dummy.txt");
        final var textFile = new TextFile(file.getAbsolutePath(), StandardCharsets.UTF_16);
        textFile.setText(expectation);
        final var text = textFile.getText();
        assertEquals(expectation, text);
        file.delete();
        assertFalse(file.exists());
    }

    @Test
    public void setTextWithLineEndingString() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var file = new File("src/test/resources/dummy.txt");
        final var textFile = new TextFile(file.getAbsolutePath(), EndOfLine.CRLF);
        textFile.setText(expectation);
        final var text = textFile.getText();
        assertEquals(expectation, text);
        file.delete();
        assertFalse(file.exists());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void testWindowEndOfLine() throws IOException {
        final var file = new File("src/test/resources/textfile-windows.txt");
        final var textFile = new TextFile(file);
        assertEquals(OSType.WINDOWS.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testWindowEOLUTF16LE() throws IOException {
        final var file = new File("src/test/resources/textfile-windows-utf16-le.txt");
        final var textFile = new TextFile(file, StandardCharsets.UTF_16LE);
        assertEquals(OSType.WINDOWS.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testWindowEOLUTF16LEBOM() throws IOException {
        final var file = new File("src/test/resources/textfile-windows-utf16-le-bom.txt");
        final var textFile = new TextFile(file, StandardCharsets.UTF_16LE);
        assertEquals(OSType.WINDOWS.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testWindowEOLUTF16BE() throws IOException {
        final var file = new File("src/test/resources/textfile-windows-utf16-be.txt");
        final var textFile = new TextFile(file, StandardCharsets.UTF_16BE);
        assertEquals(OSType.WINDOWS.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testWindowEmpty() throws IOException {
        final var file = new File("src/test/resources/textfile-empty.txt");
        final var textFile = new TextFile(file);
        assertEquals(OSType.UNKNOWN.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testNonExistentFile() throws IOException {
        final var file = new File("src/test/resources/non-existent.txt");
        final var textFile = new TextFile(file);
        assertDoesNotThrow(textFile::getEndOfLine);
        assertEquals(EndOfLine.SYSTEM, textFile.getEndOfLine());
    }

    @Test
    public void testWindowEOLUTF16BEBOM() throws IOException {
        final var file = new File("src/test/resources/textfile-windows-utf16-be-bom.txt");
        final var textFile = new TextFile(file, StandardCharsets.UTF_16BE);
        assertEquals(OSType.WINDOWS.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testLinuxEndOfLine() throws IOException {
        final var file = new File("src/test/resources/textfile-unix.txt");
        final var textFile = new TextFile(file);
        assertEquals(OSType.LINUX.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testMacEndOfLine() throws IOException {
        final var file = new File("src/test/resources/textfile-mac.txt");
        final var textFile = new TextFile(file);
        assertEquals(OSType.MAC.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testMacSingleLineEndOfLine() throws IOException {
        final var file = new File("src/test/resources/textfile-mac-oneline.txt");
        final var textFile = new TextFile(file);
        assertEquals(OSType.MAC.getEOL(), textFile.getEndOfLine());
    }

    @Test
    public void testEmptyEndOfLine() throws IOException {
        final var file = new File("src/test/resources/textfile-empty.txt");
        final var textFile = new TextFile(file);
        assertEquals(EndOfLine.SYSTEM, textFile.getEndOfLine());
    }

    @Test
    public void testNoNextLineEndOfLine() throws IOException {
        final var file = new File("src/test/resources/textfile-no-nextline.txt");
        final var textFile = new TextFile(file);
        assertEquals(EndOfLine.SYSTEM, textFile.getEndOfLine());
    }

    @Test
    public void callingSetTextTwice() throws IOException {
        final var expectation = "Line 1.\r\n" +
                "Line 2.\r\n" +
                "Line 3.";
        final var file = new File("src/test/resources/textfile-nonempty.txt");
        final var textFile = new TextFile(file.getAbsolutePath(), EndOfLine.CRLF);
        textFile.setText(expectation);
        textFile.setText(expectation);
        final var text = textFile.getText();
        assertEquals(expectation, text);
        file.delete();
        assertFalse(file.exists());
    }

}
