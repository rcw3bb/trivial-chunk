package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.handy.EndOfLine;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
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

    /**
     * Return the line ending of the file.
     * @return An instance of EndOfLine.
     * @throws IOException Can throw this exception.
     *
     * @since 2.19.0
     */
    @SuppressWarnings({"PMD.AvoidFileStream", "PMD.EmptyCatchBlock"})
    public Optional<EndOfLine> getEndOfLine() throws IOException {
        Optional<EndOfLine> output = Optional.empty();

        try (var raf = new RandomAccessFile(file, "r")) {
            byte byteRead = raf.readByte();
            final var sbFirstLine = new StringBuilder();
            while (byteRead != -1) {
                sbFirstLine.append((char) byteRead);
                if (sbFirstLine.toString().endsWith("\r")) {
                    output = /*Assume that it is already CR */ Optional.of(EndOfLine.CR);

                    byteRead = /* Read the next byte to check if LF is the next character. */ raf.readByte();
                    sbFirstLine.append((char) byteRead);
                    if (byteRead != -1 && sbFirstLine.toString().endsWith("\r\n")) {
                        output = Optional.of(EndOfLine.CRLF);
                        break;
                    }
                    break;
                } else if (sbFirstLine.toString().endsWith("\n")) {
                    output = Optional.of(EndOfLine.LF);
                    break;
                }
                byteRead = raf.readByte();
            }
        } catch (EOFException e) {
            //If end of file was reached without detecting any line ending. The output will be empty as expected.
        }
        return output;
    }

}
