package xyz.ronella.trivial.functional;

import java.util.function.*;

/**
 * A do nothing implementation of some functional interfaces.
 *
 * @author Ron Webb
 *
 * @since 2.2.0
 */
public final class NoOperation {

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
     * @param <T> The type of argument that the predicate will work on.
     *
     * @return The value of the output parameter.
     */
    public static <T> Predicate<T> predicate(final Boolean output) {
        return (arg) -> output;
    }

    /**
     * An implementation of Predicate that does nothing but must have a specific boolean output.
     *
     * @param output The desired output of the do nothing.
     * @param <T1> The type of first argument that the predicate will work on.
     * @param <T2> The type of second argument that the predicate will work on.
     *
     * @return The value of the output parameter.
     */
    public static <T1, T2> BiPredicate<T1, T2> biPredicate(final Boolean output) {
        return (arg1, arg2) -> output;
    }

    /**
     * An implementation of Function that does nothing.
     * @param <T> The type of the argument to work on.
     * @param <R> The return type.
     *
     * @return Always return null.
     */
    public static <T,R> Function<T,R> function() {
        return (arg) -> null;
    }

    /**
     * An implementation of Function that does nothing but produce default output.
     * @param <T> The type of the argument to work on.
     * @param <R> The return type.
     * @param output The desired output of type R.
     *
     * @return The value of the output parameter.
     *
     * @since 2.11.0
     */
    public static <T,R> Function<T,R> function(R output) {
        return (arg) -> output;
    }

    /**
     * An implementation of Function that does nothing and just return what passed.
     * @param <T> The type of the argument of instance of R to work on.
     * @param <R> The return type.
     *
     * @return The value of the output parameter.
     *
     * @since 2.11.0
     */
    public static <T extends R,R> Function<T,R> functionPassThru() {
        return (arg) -> arg;
    }

    /**
     * An implementation of BiFunction that does nothing.
     * @param <T1> The type of the first argument to work on.
     * @param <T2> The type of the second argument to work on.
     *
     * @param <R> The return type.
     *
     * @return Always return null.
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> biFunction() {
        return (arg1, arg2) -> null;
    }

    /**
     * An implementation of BiFunction that does nothing but produce default output.
     * @param <T1> The type of the first argument to work on.
     * @param <T2> The type of the second argument to work on.
     * @param <R> The return type.*
     * @param output The desired output of type R.
     *
     * @return The value of the output parameter.
     *
     * @since 2.11.0
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> biFunction(R output) {
        return (arg1, arg2) -> output;
    }

    /**
     * An implementation of BiFunction that does nothing and just return argument 1.
     * @param <T1> The type of the first argument of instance of R to work on.
     * @param <T2> The type of the second argument to work on.
     * @param <R> The return type.*
     *
     * @return The value of the output parameter.
     *
     * @since 2.11.0
     */
    public static <T1 extends R, T2, R> BiFunction<T1, T2, R> biFunctionArg1PassThru() {
        return (arg1, arg2) -> arg1;
    }

    /**
     * An implementation of BiFunction that does nothing and just return argument 2.
     * @param <T1> The type of the first argument to work on.
     * @param <T2> The type of the second argument of instance of R to work on.
     * @param <R> The return type.*
     *
     * @return The value of the output parameter.
     *
     * @since 2.11.0
     */
    public static <T1, T2 extends R, R> BiFunction<T1, T2, R> biFunctionArg2PassThru() {
        return (arg1, arg2) -> arg2;
    }
}
