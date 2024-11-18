package xyz.ronella.trivial.functional;

import xyz.ronella.trivial.handy.Require;
import xyz.ronella.trivial.handy.RequireObject;

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
        Require.objects(new RequireObject(logic, "logic cannot be null"));
        return () -> {
            plummet();
            logic.plummet();
        };
    }
}
