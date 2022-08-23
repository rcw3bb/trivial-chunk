package xyz.ronella.trivial.handy;

/**
 * Thrown when the CommandRunner encountered an exception.
 *
 * @author Ron Webb
 * @since 2.14.0
 */
public class CommandRunnerException extends Exception {

    private static final long serialVersionUID = -1570448112945466784L;

    /**
     * Creates an instance of CommandRunnerException.
     * @param message The error message.
     */
    public CommandRunnerException(final String message) {
        super(message);
    }
}