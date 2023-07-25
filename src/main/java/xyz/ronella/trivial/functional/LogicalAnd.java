package xyz.ronella.trivial.functional;

/**
 * The implementation must be used for concatenating boolean logic.
 *
 * @param <T> The type of object.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
@FunctionalInterface
public interface LogicalAnd<T> {

    /**
     * Must have the logic to return boolean output.
     * @param object An instance to check.
     * @return Returns boolean.
     */
    Boolean and(T object);
}
