package xyz.ronella.trivial.functional;

import java.util.function.BiConsumer;

/**
 * A functional interface that will accept two arguments and can throw an exception.
 *
 * @param <TYPE1> The type of the first argument.
 * @param <TYPE2> The type of the second argument.
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedBiConsumer<TYPE1, TYPE2, EXCEPTION extends Exception> {

    /**
     * Accepts the arguments.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @throws EXCEPTION The exception.
     */
    void checkedAccept(TYPE1 arg1, TYPE2 arg2) throws EXCEPTION;

    /**
     * Return as a BiConsumer.
     *
     * @return The BiConsumer.
     */
    default BiConsumer<TYPE1, TYPE2> asBiConsumer() {
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
     * @param <TYPE1> The type of the first argument.
     * @param <TYPE2> The type of the second argument.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedBiConsumer.
     */
    static <TYPE1, TYPE2, EXCEPTION extends Exception> CheckedBiConsumer<TYPE1, TYPE2, EXCEPTION> of(
            final CheckedBiConsumer<TYPE1, TYPE2, EXCEPTION> logic) {
        return logic;
    }
}
