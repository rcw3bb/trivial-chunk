package xyz.ronella.trivial.handy;

import java.io.Serial;

/**
 * Thrown when the CommandProcessor encountered an exception.
 *
 * @author Ron Webb
 * @since 2.18.0
 */
public class CommandProcessorException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3239334852013230629L;

    /**
     * Creates an instance of CommandRunnerException.
     * @param throwable The original error.
     */
    public CommandProcessorException(final Throwable throwable) {
        super(throwable);
    }
}
