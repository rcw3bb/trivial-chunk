package xyz.ronella.trivial.functional;

import java.util.function.Supplier;

/**
 * A functional interface that will supply a value and can throw an exception.
 *
 * @param <R> The type of the return.
 * @param <X> The type of the exception.
 *
 * @author Ron Webb
 * @since 2.21.0
 */
@FunctionalInterface
public interface CheckedSupplier<R, X extends Exception> {

    /**
     * Supplies the value.
     *
     * @return The value.
     * @throws X The exception.
     */
    R checkedGet() throws X;

    /**
     * Return as a Supplier.
     *
     * @return The Supplier.
     */
    default Supplier<R> asSupplier() {
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
     * @param <R> The type of the return.
     * @param <X> The type of the exception.
     * @return The CheckedSupplier.
     */
    static <R, X extends Exception> CheckedSupplier<R, X> of(
            final CheckedSupplier<R, X> logic) {
        return logic;
    }

}
