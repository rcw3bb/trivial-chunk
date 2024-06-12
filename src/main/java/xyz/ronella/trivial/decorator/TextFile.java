package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.handy.EndOfLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * A class that wraps a File class that must be holding a reference to a text file.
 *
 * @since 2.16.0
 * @author Ron Webb
 */
public class TextFile {

    private final File file;

    /**
     * Creates an instance of a text file.
     * @param file An instance of File.
     */
    public TextFile(final File file) {
        this.file = file;
    }

    /**
     * Creates an instance of a text file.
     * @param filename The filename.
     */
    public TextFile(final String filename) {
        this(new File(filename));
    }

    /**
     * Return the text content of the file.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     */
    public String getText() throws IOException {
        return getText(StandardCharsets.UTF_8, EndOfLine.SYSTEM);
    }

    /**
     * Return the text content of the file.
     * @param endOfLine An instance of EndOfLine.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     */
    public String getText(final EndOfLine endOfLine) throws IOException {
        return getText(StandardCharsets.UTF_8, endOfLine);
    }

    /**
     * Return the text content of the file.
     * @param charset The character set to use.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     */
    public String getText(final Charset charset) throws IOException {
        return getText(charset, EndOfLine.SYSTEM);
    }

    /**
     * Return the text content of the file.
     * @param charset The character set to use.
     * @param endOfLine An instance of EndOfLine.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     */
    public String getText(final Charset charset, final EndOfLine endOfLine) throws IOException {
        try(var scanner = new Scanner(file, charset)) {
            final var sbText = new StringBuilderAppender(___sb -> new StringBuilderAppender(___sb)
                    .appendWhen(endOfLine.eol())
                    .when(Predicate.not(StringBuilder::isEmpty)));

            while (scanner.hasNextLine()) {
                final var line = scanner.nextLine();
                sbText.append(line);
            }
            return sbText.toString();
        }
    }

}
