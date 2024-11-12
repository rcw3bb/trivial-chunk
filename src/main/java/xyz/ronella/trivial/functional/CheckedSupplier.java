package xyz.ronella.trivial.functional;

import java.util.function.Supplier;

/**
 * A functional interface that will supply a value and can throw an exception.
 *
 * @param <RETURN> The type of the return.
 * @param <EXCEPTION> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedSupplier<RETURN, EXCEPTION extends Exception> {

    /**
     * Supplies the value.
     *
     * @return The value.
     * @throws EXCEPTION The exception.
     */
    RETURN checkedGet() throws EXCEPTION;

    /**
     * Return as a Supplier.
     *
     * @return The Supplier.
     */
    default Supplier<RETURN> asSupplier() {
        return () -> {
            try {
                return checkedGet();
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Builder of CheckedSupplier.
     *
     * @param logic The logic to build the CheckedSupplier.
     * @param <RETURN> The type of the return.
     * @param <EXCEPTION> The type of the exception.
     * @return The CheckedSupplier.
     */
    static <RETURN, EXCEPTION extends Exception> CheckedSupplier<RETURN, EXCEPTION> of(
            final CheckedSupplier<RETURN, EXCEPTION> logic) {
        return logic;
    }

}
