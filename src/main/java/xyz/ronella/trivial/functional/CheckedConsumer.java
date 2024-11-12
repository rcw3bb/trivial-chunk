package xyz.ronella.trivial.functional;

import java.util.function.Consumer;

/**
 * A functional interface that will accept an argument and can throw an exception.
 *
 * @param <TYPE> The type of the argument.
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedConsumer<TYPE, EXCEPTION extends Exception> {

    /**
     * Accepts the argument.
     *
     * @param arg The argument.
     * @throws EXCEPTION The exception.
     */
    void checkedAccept(TYPE arg) throws EXCEPTION;

    /**
     * Return as a Consumer.
     *
     * @return The Consumer.
     */
    default Consumer<TYPE> asConsumer() {
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
     * @param <TYPE> The type of the argument.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedConsumer.
     */
    static <TYPE, EXCEPTION extends Exception> CheckedConsumer<TYPE, EXCEPTION> of(
            final CheckedConsumer<TYPE, EXCEPTION> logic) {
        return logic;
    }
}
