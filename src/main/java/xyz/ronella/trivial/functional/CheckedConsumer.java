package xyz.ronella.trivial.functional;

import java.util.function.Consumer;

/**
 * A functional interface that will accept an argument and can throw an exception.
 *
 * @param <T> The type of the argument.
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedConsumer<T, X extends Exception> {

    /**
     * Accepts the argument.
     *
     * @param arg The argument.
     * @throws X The exception.
     */
    void checkedAccept(T arg) throws X;

    /**
     * Return as a Consumer.
     *
     * @return The Consumer.
     */
    default Consumer<T> asConsumer() {
        return arg -> {
            try {
                checkedAccept(arg);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedConsumer.
     *
     * @param logic The logic to build the CheckedConsumer.
     * @param <T> The type of the argument.
     * @param <X> The type of the exception.
     * @return The CheckedConsumer.
     */
    static <T, X extends Exception> CheckedConsumer<T, X> of(
            final CheckedConsumer<T, X> logic) {
        return logic;
    }
}
