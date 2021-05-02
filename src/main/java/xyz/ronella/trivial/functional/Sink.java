package xyz.ronella.trivial.functional;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Must hold a logic that doesn't expects an argument and a return value.
 *
 * @author Ron Webb
 * @since 2019-11-28
 */
@FunctionalInterface
public interface Sink {

    /**
     * Must a logic that doesn't expects an argument and a return value.
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
    default Sink drainsTo(Sink logic) {
        Objects.requireNonNull(logic);
        return () -> {
            plummet();
            logic.plummet();
        };
    }
}
