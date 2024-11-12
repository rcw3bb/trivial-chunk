package xyz.ronella.trivial.functional;

import java.util.function.BiPredicate;

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
public interface CheckedBiPredicate<TYPE1, TYPE2, EXCEPTION extends Exception> {

    /**
    * Tests the arguments.
    *
    * @param arg1 The first argument.
    * @param arg2 The second argument.
    * @return The result.
    * @throws EXCEPTION The exception.
    */
    boolean checkedTest(TYPE1 arg1, TYPE2 arg2) throws EXCEPTION;

    /**
     * Return as a BiPredicate.
     *
     * @return The BiPredicate.
     */
    default BiPredicate<TYPE1, TYPE2> asBiPredicate() {
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
     * @param <TYPE1> The type of the first argument.
     * @param <TYPE2> The type of the second argument.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedBiPredicate.
     */
    static <TYPE1, TYPE2, EXCEPTION extends Exception> CheckedBiPredicate<TYPE1, TYPE2, EXCEPTION> of(
            final CheckedBiPredicate<TYPE1, TYPE2, EXCEPTION> logic) {
        return logic;
    }

}
