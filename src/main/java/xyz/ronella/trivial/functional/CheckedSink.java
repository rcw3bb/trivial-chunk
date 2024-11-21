package xyz.ronella.trivial.functional;

/**
 * A functional interface that will accept no argument and can throw an exception.
 *
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes", "PMD.ShortMethodName"})
@FunctionalInterface
public interface CheckedSink<X extends Exception> {

    /**
     * Process the argument.
     *
     * @throws X The exception.
     */
    void checkedPlummet() throws X;

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
     * @param <X> The type of the exception.
     * @return The CheckedSink.
     */
    static <X extends Exception> CheckedSink<X> of(
            final CheckedSink<X> logic) {
        return logic;
    }
}
