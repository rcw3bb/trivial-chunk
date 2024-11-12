package xyz.ronella.trivial.functional;

/**
 * A functional interface that will accept no argument and can throw an exception.
 *
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedSink<EXCEPTION extends Exception> {

    /**
     * Process the argument.
     *
     * @throws EXCEPTION The exception.
     */
    void checkedPlummet() throws EXCEPTION;

    /**
     * Return as a Sink.
     *
     * @return The Sink.
     */
    default Sink asSink() {
        return () -> {
            try {
                checkedPlummet();
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedSink.
     *
     * @param logic The logic to build the CheckedSink.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedSink.
     */
    static <EXCEPTION extends Exception> CheckedSink<EXCEPTION> of(
            final CheckedSink<EXCEPTION> logic) {
        return logic;
    }
}
