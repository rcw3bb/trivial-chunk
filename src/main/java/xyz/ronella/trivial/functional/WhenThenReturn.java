package xyz.ronella.trivial.functional;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Must have the implementation to generate the output when the condition was met.
 * This generates output.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
@FunctionalInterface
public interface WhenThenReturn<T, R> extends Function<Predicate<T>, R> {

    /**
     * Must hold the logic to generate the output when the condition was met.
     * The Then part of WhenThen is the actual implementation of when.
     *
     * @param condition The condition that must be true to generate the output.
     * @return An instance of the TYPE_OUTPUT.
     */
    R when(Predicate<T> condition);

    /**
     * Redirect the call to when implementation.
     * @param supplier the function argument
     * @return The output of when implementation.
     */
    @Override
    default R apply(final Predicate<T> supplier) {
        return when(supplier);
    }

}
