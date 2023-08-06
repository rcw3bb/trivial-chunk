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
public interface WhenThenReturn<TYPE_ARG, TYPE_OUTPUT> extends Function<Predicate<TYPE_ARG>, TYPE_OUTPUT> {

    /**
     * Must hold the logic to generate the output when the condition was met.
     * The Then part of WhenThen is the actual implementation of when.
     *
     * @param condition The condition that must be true to generate the output.
     */
    TYPE_OUTPUT when(Predicate<TYPE_ARG> condition);

    /**
     * Redirect the call to when implementation.
     * @param supplier the function argument
     * @return The output of when implementation.
     */
    @Override
    default TYPE_OUTPUT apply(final Predicate<TYPE_ARG> supplier) {
        return when(supplier);
    }

}
