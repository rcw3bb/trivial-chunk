package xyz.ronella.trivial.functional;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A do nothing implementation of some functional interfaces.
 *
 * @author Ron Webb
 *
 * @since 2.2.0
 */
public class NoOperation {

    private NoOperation() {}

    /**
     * An implementation of Consumer that does nothing.
     *
     * @param <T> The type of the argument of the consumer.
     *
     * @return A do nothing implementation of Consumer.
     */
    public static <T> Consumer<T> consumer() {
        return arg1 -> {};
    }

    /**
     * An implementation of BiConsumer that does nothing.
     *
     * @param <T1> The type of the first argument of the BiConsumer.
     * @param <T2> The type of the second argument of the BiConsumer.
     *
     * @return A do nothing implementation of BiConsumer.
     */
    public static <T1, T2> BiConsumer<T1, T2> biConsumer() {
        return (arg1, arg2) -> {};
    }

    /**
     * An implementation of Sink that does nothing.
     *
     * @return A do nothing implementation of Sink.
     */
    public static Sink sink() {
        return () -> {};
    }

    /**
     * An implementation of Supplier that does nothing.
     *
     * @param <T> The type of output that the supplier will generate.
     *
     * @return Always return null.
     */
    public static <T> Supplier<T> supplier() {
        return () -> null;
    }

    /**
     * An implementation of Predicate that does nothing but must have a specific boolean output.
     *
     * @param output The desired output of the do nothing.
     * @param <T> The type if argument that the predicate will work on.
     *
     * @return The value of the output parameter.
     */
    public static <T> Predicate<T> predicate(Boolean output) {
        return (T arg) -> output;
    }

}
