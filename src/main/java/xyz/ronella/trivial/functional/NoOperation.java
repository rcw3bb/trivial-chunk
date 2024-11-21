package xyz.ronella.trivial.functional;

import java.util.function.*;

/**
 * A do nothing implementation of some functional interfaces.
 * 
 * @author Ron Webb
 * @since 2.2.0
 */
@SuppressWarnings({"PMD.CouplingBetweenObjects", "PMD.TooManyMethods"})
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
     * An implementation of CheckedConsumer that does nothing.
     *
     * @param <T> The type of the argument of the consumer.
     * @param <X> The type of the exception.
     *
     * @return A do nothing implementation of CheckedConsumer.
     * @since 2.21.0
     */
    public static <T, X extends Exception> CheckedConsumer<T, X> checkedConsumer() {
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
     * An implementation of CheckedBiConsumer that does nothing.
     *
     * @param <T1> The type of the first argument of the BiConsumer.
     * @param <T2> The type of the second argument of the BiConsumer.
     * @param <X> The type of the exception.
     *
     * @return A do nothing implementation of CheckedBiConsumer.
     * @since 2.21.0
     */
    public static <T1, T2, X extends Exception> CheckedBiConsumer<T1, T2, X> checkedBiConsumer() {
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
     * An implementation of CheckedSink that does nothing.
     *
     * @param <X> The type of the exception.
     *
     * @return A do nothing implementation of CheckedSink.
     * @since 2.21.0
     */
    public static <X extends Exception> CheckedSink<X> checkedSink() {
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
     * An implementation of CheckedSupplier that does nothing.
     *
     * @param <T> The type of output that the supplier will generate.
     * @param <X> The type of the exception.
     *
     * @return Always return null.
     * @since 2.21.0
     */
    public static <T, X extends Exception> CheckedSupplier<T, X> checkedSupplier() {
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
     * An implementation of CheckedPredicate that does nothing but must have a specific boolean output.
     *
     * @param output The desired output of the do nothing.
     * @param <T> The type of argument that the predicate will work on.
     * @param <X> The type of the exception.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T, X extends Exception> CheckedPredicate<T, X> checkedPredicate(final Boolean output) {
        return (arg) -> output;
    }

    /**
     * An implementation of BiPredicate that does nothing but must have a specific boolean output.
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
     * An implementation of CheckedBiPredicate that does nothing but must have a specific boolean output.
     *
     * @param output The desired output of the do nothing.
     * @param <T1> The type of first argument that the predicate will work on.
     * @param <T2> The type of second argument that the predicate will work on.
     * @param <X> The type of the exception.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T1, T2, X extends Exception> CheckedBiPredicate<T1, T2, X> checkedBiPredicate(final Boolean output) {
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
     * An implementation of CheckedFunction that does nothing.
     * @param <T> The type of the argument to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     *
     * @return Always return null.
     * @since 2.21.0
     */
    public static <T,R, X extends Exception> CheckedFunction<T,R, X> checkedFunction() {
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
    public static <T,R> Function<T,R> function(final R output) {
        return (arg) -> output;
    }

    /**
     * An implementation of CheckedFunction that does nothing but produce default output.
     * @param <T> The type of the argument to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     * @param output The desired output of type R.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T,R, X extends Exception> CheckedFunction<T,R, X> checkedFunction(final R output) {
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
     * An implementation of CheckedFunction that does nothing and just return what passed.
     * @param <T> The type of the argument of instance of R to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T extends R,R, X extends Exception> CheckedFunction<T,R, X> checkedFunctionPassThru() {
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
     * An implementation of CheckedBiFunction that does nothing.
     * @param <T1> The type of the first argument to work on.
     * @param <T2> The type of the second argument to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     *
     * @return Always return null.
     * @since 2.21.0
     */
    public static <T1, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunction() {
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
    public static <T1, T2, R> BiFunction<T1, T2, R> biFunction(final R output) {
        return (arg1, arg2) -> output;
    }

    /**
     * An implementation of CheckedBiFunction that does nothing but produce default output.
     * @param <T1> The type of the first argument to work on.
     * @param <T2> The type of the second argument to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     * @param output The desired output of type R.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T1, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunction(final R output) {
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
     * An implementation of CheckedBiFunction that does nothing and just return argument 1.
     * @param <T1> The type of the first argument of instance of R to work on.
     * @param <T2> The type of the second argument to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T1 extends R, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunctionArg1PassThru() {
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

    /**
     * An implementation of CheckedBiFunction that does nothing and just return argument 2.
     * @param <T1> The type of the first argument to work on.
     * @param <T2> The type of the second argument of instance of R to work on.
     * @param <R> The return type.
     * @param <X> The type of the exception.
     *
     * @return The value of the output parameter.
     * @since 2.21.0
     */
    public static <T1, T2 extends R, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunctionArg2PassThru() {
        return (arg1, arg2) -> arg2;
    }
}