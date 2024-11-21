package xyz.ronella.trivial.functional;

import java.util.function.Function;

/**
 * A functional interface that will accept an argument and can throw an exception.
 *
 * @param <T> The type of the argument.
 * @param <R> The type of the return.
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes", "PMD.ShortMethodName"})
@FunctionalInterface
public interface CheckedFunction<T, R, X extends Exception> {

    /**
     * Processes the argument.
     *
     * @param arg The argument.
     * @return The return.
     * @throws X The exception.
     */
    R checkedApply(T arg) throws X;

    /**
     * Return as a Function.
     *
     * @return The Function.
     */
    default Function<T, R> asFunction() {
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
     * @param <T> The type of the argument.
     * @param <R> The type of the return.
     * @param <X> The type of the exception.
     * @return The CheckedFunction.
     */
    static <T, R, X extends Exception> CheckedFunction<T, R, X> of(
            final CheckedFunction<T, R, X> logic) {
        return logic;
    }

}
