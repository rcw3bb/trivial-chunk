package xyz.ronella.trivial.handy;

/**
 * This is thrown when no command to execute.
 *
 * @author Ron Webb
 * @since 2.13.0
 */
public class MissingCommandException extends Exception {

    private static final long serialVersionUID = 120261769391531909L;

    /**
     * Creates an instance of MissingCommandException.
     */
    public MissingCommandException() {
        super();
    }
}