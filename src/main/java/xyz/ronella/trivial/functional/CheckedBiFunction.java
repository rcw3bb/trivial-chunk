package xyz.ronella.trivial.functional;

import java.util.function.BiFunction;

/**
 * A functional interface that will accept two arguments and can throw an exception.
 *
 * @param <T1> The type of the first argument.
 * @param <T2> The type of the second argument.
 * @param <R> The type of the return.
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes", "PMD.ShortMethodName"})
@FunctionalInterface
public interface CheckedBiFunction<T1, T2, R, X extends Exception> {

    /**
     * Processes the arguments.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @return The return.
     * @throws X The exception.
     */
    R checkedApply(T1 arg1, T2 arg2) throws X;

    /**
     * Return as a BiFunction.
     *
     * @return The BiFunction.
     */
    default BiFunction<T1, T2, R> asBiFunction() {
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
     * @param <T1> The type of the first argument.
     * @param <T2> The type of the second argument.
     * @param <R> The type of the return.
     * @param <X> The type of the exception.
     * @return The CheckedBiFunction.
     */
    static <T1, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> of(
            final CheckedBiFunction<T1, T2, R, X> logic) {
        return logic;
    }

}
