package xyz.ronella.trivial.functional;

import java.util.function.Predicate;

/**
 * A functional interface that will test an argument and can throw an exception.
 *
 * @param <TYPE> The type of the argument.
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedPredicate<TYPE, EXCEPTION extends Exception> {

    /**
     * Tests the argument.
     *
     * @param arg The argument.
     * @return The result of the test.
     * @throws EXCEPTION The exception.
     */
    boolean checkedTest(TYPE arg) throws EXCEPTION;

    /**
     * Return as a Predicate.
     *
     * @return The Predicate.
     */
    default Predicate<TYPE> asPredicate() {
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
     * @param <TYPE> The type of the argument.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedPredicate.
     */
    static <TYPE, EXCEPTION extends Exception> CheckedPredicate<TYPE, EXCEPTION> of(
            final CheckedPredicate<TYPE, EXCEPTION> logic) {
        return logic;
    }

}
