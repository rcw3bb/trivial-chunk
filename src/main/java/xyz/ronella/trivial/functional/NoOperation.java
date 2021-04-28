package xyz.ronella.trivial.functional;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

}
