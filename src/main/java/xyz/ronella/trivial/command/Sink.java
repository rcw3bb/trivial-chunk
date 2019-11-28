package xyz.ronella.trivial.command;

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
}
