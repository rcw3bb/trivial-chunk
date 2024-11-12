package xyz.ronella.trivial.functional;

import java.util.function.Predicate;

/**
 * A functional interface that will test an argument and can throw an exception.
 *
 * @param <T> The type of the argument.
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedPredicate<T, X extends Exception> {

    /**
     * Tests the argument.
     *
     * @param arg The argument.
     * @return The result of the test.
     * @throws X The exception.
     */
    boolean checkedTest(T arg) throws X;

    /**
     * Return as a Predicate.
     *
     * @return The Predicate.
     */
    default Predicate<T> asPredicate() {
        return arg -> {
            try {
                return checkedTest(arg);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedPredicate.
     *
     * @param logic The logic to build the CheckedPredicate.
     * @param <T> The type of the argument.
     * @param <X> The type of the exception.
     * @return The CheckedPredicate.
     */
    static <T, X extends Exception> CheckedPredicate<T, X> of(
            final CheckedPredicate<T, X> logic) {
        return logic;
    }

}
