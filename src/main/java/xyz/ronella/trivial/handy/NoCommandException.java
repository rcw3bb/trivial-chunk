package xyz.ronella.trivial.handy;

/**
 * Thrown when no command to execute.
 *
 * @author Ron Webb
 * @since 2.5.0
 *
 * @deprecated Use MissingCommandException instead.
 */
@Deprecated
public class NoCommandException extends MissingCommandException {

    private static final long serialVersionUID = -6887968132858882534L;

    /**
     * Creates an instance of NoCommandException.
     */
    public NoCommandException() {
        super();
    }
}
