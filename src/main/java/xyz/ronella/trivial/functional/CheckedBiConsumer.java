package xyz.ronella.trivial.functional;

import java.util.function.BiConsumer;

/**
 * A functional interface that will accept two arguments and can throw an exception.
 *
 * @param <T1> The type of the first argument.
 * @param <T2> The type of the second argument.
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedBiConsumer<T1, T2, X extends Exception> {

    /**
     * Accepts the arguments.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @throws X The exception.
     */
    void checkedAccept(T1 arg1, T2 arg2) throws X;

    /**
     * Return as a BiConsumer.
     *
     * @return The BiConsumer.
     */
    default BiConsumer<T1, T2> asBiConsumer() {
        return (arg1, arg2) -> {
            try {
                checkedAccept(arg1, arg2);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedBiConsumer.
     *
     * @param logic The logic to build the CheckedBiConsumer.
     *
     * @param <T1> The type of the first argument.
     * @param <T2> The type of the second argument.
     * @param <X> The type of the exception.
     * @return The CheckedBiConsumer.
     */
    static <T1, T2, X extends Exception> CheckedBiConsumer<T1, T2, X> of(
            final CheckedBiConsumer<T1, T2, X> logic) {
        return logic;
    }
}
