package xyz.ronella.trivial.functional;

import java.util.function.Function;

/**
 * A functional interface that will accept an argument and can throw an exception.
 *
 * @param <TYPE> The type of the argument.
 * @param <RETURN> The type of the return.
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedFunction<TYPE, RETURN, EXCEPTION extends Exception> {

    /**
     * Processes the argument.
     *
     * @param arg The argument.
     * @return The return.
     * @throws EXCEPTION The exception.
     */
    RETURN checkedApply(TYPE arg) throws EXCEPTION;

    /**
     * Return as a Function.
     *
     * @return The Function.
     */
    default Function<TYPE, RETURN> asFunction() {
        return arg -> {
            try {
                return checkedApply(arg);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedFunction.
     *
     * @param logic The logic to build the CheckedFunction.
     * @param <TYPE> The type of the argument.
     * @param <RETURN> The type of the return.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedFunction.
     */
    static <TYPE, RETURN, EXCEPTION extends Exception> CheckedFunction<TYPE, RETURN, EXCEPTION> of(
            final CheckedFunction<TYPE, RETURN, EXCEPTION> logic) {
        return logic;
    }

}
