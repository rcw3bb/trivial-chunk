package xyz.ronella.trivial.functional;

import java.util.Objects;

/**
 * Must hold a logic that doesn't expect an argument and a return value.
 *
 * @author Ron Webb
 * @since 2019-11-28
 */
@FunctionalInterface
public interface Sink {

    /**
     * Must a logic that doesn't expect an argument and a return value.
     */
    void plummet();

    /**
     * Return a composed Sink that executes the logic after the main Sink logic.
     *
     * @param logic The logic that will be performed after the main Sink logic.
     * @return A Sink implementation.
     *
     * @since 2.2.0
     */
    default Sink drainsTo(final Sink logic) {
        Objects.requireNonNull(logic);
        return () -> {
            plummet();
            logic.plummet();
        };
    }
}
