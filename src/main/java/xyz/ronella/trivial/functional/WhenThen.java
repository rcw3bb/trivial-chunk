package xyz.ronella.trivial.functional;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Must have the implementation to process a task when the condition was met.
 * This doesn't generate any output.
 *
 * @param <T> The type of the input argument.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
@FunctionalInterface
public interface WhenThen<T> extends Consumer<Predicate<T>> {

    /**
     * Must hold the logic to process a task when the condition was met.
     * The Then part of WhenThen is the actual implementation of when.
     *
     * @param condition The condition that must be true to do the task.
     */
    void when(Predicate<T> condition);

    /**
     * Accepts just redirect the call to implementation.
     * @param condition the input argument
     */
    @Override
    default void accept(final Predicate<T> condition) {
        when(condition);
    }
}
