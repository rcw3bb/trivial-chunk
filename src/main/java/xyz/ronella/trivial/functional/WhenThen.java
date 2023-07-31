package xyz.ronella.trivial.functional;

import java.util.function.BooleanSupplier;

/**
 * Must have the implementation to process a task when the condition was met.
 * This doesn't generate any output.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
@FunctionalInterface
public interface WhenThen {

    /**
     * Must hold the logic to process a task when the condition was met.
     * @param condition The condition that must be true to do the task.
     */
    void when(BooleanSupplier condition);
}
