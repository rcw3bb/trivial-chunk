package xyz.ronella.trivial.handy;

/**
 * Text file line ending.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
public enum EndOfLine {

    /**
     * Combination of carriage return and line feed.
     */
    CRLF("\r\n"),
    /**
     * Carriage return only.
     */
    CR("\r"),
    /**
     * Line feed only.
     */
    LF("\n"),
    /**
     * System detected EOL.
     */
    SYSTEM(System.lineSeparator());

    private final String lineEnding;
    EndOfLine(final String lineEnding) {
        this.lineEnding = lineEnding;
    }

    /**
     * The String representation of EndOfLine instance.
     * @return The String representation.
     */
    public String eol() {
        return lineEnding;
    }
}
