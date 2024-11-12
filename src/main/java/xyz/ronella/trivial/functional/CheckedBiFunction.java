package xyz.ronella.trivial.functional;

import java.util.function.BiFunction;

/**
 * A functional interface that will accept two arguments and can throw an exception.
 *
 * @param <TYPE1> The type of the first argument.
 * @param <TYPE2> The type of the second argument.
 * @param <RETURN> The type of the return.
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedBiFunction<TYPE1, TYPE2, RETURN, EXCEPTION extends Exception> {

    /**
     * Processes the arguments.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @return The return.
     * @throws EXCEPTION The exception.
     */
    RETURN checkedApply(TYPE1 arg1, TYPE2 arg2) throws EXCEPTION;

    /**
     * Return as a BiFunction.
     *
     * @return The BiFunction.
     */
    default BiFunction<TYPE1, TYPE2, RETURN> asBiFunction() {
        return (arg1, arg2) -> {
            try {
                return checkedApply(arg1, arg2);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedBiFunction.
     *
     * @param logic The logic to build the CheckedBiFunction.
     *
     * @param <TYPE1> The type of the first argument.
     * @param <TYPE2> The type of the second argument.
     * @param <RETURN> The type of the return.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedBiFunction.
     */
    static <TYPE1, TYPE2, RETURN, EXCEPTION extends Exception> CheckedBiFunction<TYPE1, TYPE2, RETURN, EXCEPTION> of(
            final CheckedBiFunction<TYPE1, TYPE2, RETURN, EXCEPTION> logic) {
        return logic;
    }

}
