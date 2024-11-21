package xyz.ronella.trivial.functional;

import java.util.function.BiPredicate;

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
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes", "PMD.ShortMethodName"})
@FunctionalInterface
public interface CheckedBiPredicate<T1, T2, X extends Exception> {

    /**
    * Tests the arguments.
    *
    * @param arg1 The first argument.
    * @param arg2 The second argument.
    * @return The result.
    * @throws X The exception.
    */
    boolean checkedTest(T1 arg1, T2 arg2) throws X;

    /**
     * Return as a BiPredicate.
     *
     * @return The BiPredicate.
     */
    default BiPredicate<T1, T2> asBiPredicate() {
        return (___arg1, ___arg2) -> {
            try {
                return checkedTest(___arg1, ___arg2);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedBiPredicate.
     *
     * @param logic The logic to build the CheckedBiPredicate.
     *
     * @param <T1> The type of the first argument.
     * @param <T2> The type of the second argument.
     * @param <X> The type of the exception.
     * @return The CheckedBiPredicate.
     */
    static <T1, T2, X extends Exception> CheckedBiPredicate<T1, T2, X> of(
            final CheckedBiPredicate<T1, T2, X> logic) {
        return logic;
    }

}
