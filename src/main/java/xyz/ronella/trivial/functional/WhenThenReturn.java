package xyz.ronella.trivial.functional;

import java.util.function.BooleanSupplier;

/**
 * Must have the implementation to generate the output when the condition was met.
 * This generates output.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
@FunctionalInterface
public interface WhenThenReturn<TYPE_OUTPUT> {

    /**
     * Must hold the logic to generate the output when the condition was met.
     * @param condition The condition that must be true to generate the output.
     */
    TYPE_OUTPUT when(BooleanSupplier condition);

}
