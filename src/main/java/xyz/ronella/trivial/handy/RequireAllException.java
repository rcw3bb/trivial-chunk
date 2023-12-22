package xyz.ronella.trivial.handy;

import java.io.Serial;

/**
 * Thrown with one of argument in Require.objects is null.
 *
 * @author Ron Webb
 */
public class RequireAllException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8886147418746226040L;

    /**
     * Creates an instance of RequireAllException.
     * @param throwable an instance of Throwable.
     * @since 2.17.0
     */
    public RequireAllException(final Throwable throwable) {
        super(throwable);
    }

}
