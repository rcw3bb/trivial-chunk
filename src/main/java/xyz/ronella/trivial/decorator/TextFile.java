package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.handy.EndOfLine;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
    private final Charset charset;
    private final EndOfLine endOfLine;

    /**
     * Creates an instance of a text file.
     * @param file An instance of File.
     */
    public TextFile(final File file) {
        this(file, StandardCharsets.UTF_8, EndOfLine.SYSTEM);
    }

    /**
     * Creates an instance of a text file.
     * @param filename The filename.
     */
    public TextFile(final String filename) {
        this(filename, StandardCharsets.UTF_8, EndOfLine.SYSTEM);
    }

    /**
     * Creates an instance of a text file.
     * @param filename The filename.
     * @param endOfLine The character set to use.
     *
     * @since 2.20.0
     */
    public TextFile(final String filename, final EndOfLine endOfLine) {
        this(filename, StandardCharsets.UTF_8, endOfLine);
    }

    /**
     * Creates an instance of a text file.
     * @param filename The filename.
     * @param charset The character set to use.
     *
     * @since 2.20.0
     */
    public TextFile(final String filename, final Charset charset) {
        this(filename, charset, EndOfLine.SYSTEM);
    }

    /**
     * Creates an instance of a text file.
     * @param filename The filename.
     * @param charset The character set to use.
     * @param endOfLine The character set to use.
     *
     * @since 2.20.0
     */
    public TextFile(final String filename, final Charset charset, final EndOfLine endOfLine) {
        this(new File(filename), charset, endOfLine);
    }

    /**
     * Creates an instance of a text file.
     * @param file The filename.
     * @param charset The character set to use.
     *
     * @since 2.20.0
     */
    public TextFile(final File file, final Charset charset) {
        this(file, charset, EndOfLine.SYSTEM);
    }

    /**
     * Creates an instance of a text file.
     * @param file The filename.
     * @param endOfLine The character set to use.
     *
     * @since 2.20.0
     */
    public TextFile(final File file, final EndOfLine endOfLine) {
        this(file, StandardCharsets.UTF_8, endOfLine);
    }

    /**
     * Creates an instance of a text file.
     * @param file The filename.
     * @param charset The character set to use.
     * @param endOfLine The character set to use.
     *
     * @since 2.20.0
     */
    public TextFile(final File file, final Charset charset, final EndOfLine endOfLine) {
        this.file = file;
        this.charset = charset;
        this.endOfLine = endOfLine;
    }

    /**
     * Return the text content of the file.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     */
    public String getText() throws IOException {
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

    /**
     * Return the text content of the file.
     * @param endOfLine An instance of EndOfLine.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     * @deprecated Use the constructor to set the endOfLine instead.
     */
    @Deprecated
    public String getText(final EndOfLine endOfLine) throws IOException {
        return getText(StandardCharsets.UTF_8, endOfLine);
    }

    /**
     * Return the text content of the file.
     * @param charset The character set to use.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     * @deprecated Use the constructor to set the charset instead.
     */
    @Deprecated
    public String getText(final Charset charset) throws IOException {
        return getText(charset, EndOfLine.SYSTEM);
    }

    /**
     * Return the text content of the file.
     * @param charset The character set to use.
     * @param endOfLine An instance of EndOfLine.
     * @return The text content.
     * @throws FileNotFoundException Can throw this exception.
     * @deprecated Use the constructor to set the charset and endOfLine instead.
     */
    @Deprecated
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

    /**
     * Set the text content of the file.
     * @param text The text content.
     * @throws IOException Can throw this exception.
     *
     * @since 2.20.0
     */
    public void setText(final String text) throws IOException {
        try (var writer = Files.newBufferedWriter(file.toPath(), charset);
             var scanner = new Scanner(text)) {

            while (scanner.hasNextLine()) {
                writer.write(scanner.nextLine());
                writer.write(endOfLine.eol());
            }
        }
    }

    /**
     * Return the line ending of the file.
     * @return An instance of EndOfLine.
     * @throws IOException Can throw this exception.
     *
     * @since 2.19.0
     */
     public EndOfLine getEndOfLine() throws IOException {
        EndOfLine output = EndOfLine.SYSTEM;

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            final var bytes = new byte[(int) raf.length()];
            raf.readFully(bytes);
            final var content = new String(bytes, charset);

            if (content.contains("\r\n")) {
                output = EndOfLine.CRLF;
            } else if (content.contains("\n")) {
                output = EndOfLine.LF;
            } else if (content.contains("\r")) {
                output = EndOfLine.CR;
            }
        }

        return output;
    }

}
