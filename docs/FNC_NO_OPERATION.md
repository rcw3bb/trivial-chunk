# NoOperation Class

The class that supplies a do nothing implementation of some of the functional interfaces. This is created so that you don't have to create it yourself.

##### Available Methods

| Method Signature                                             | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public static <T1, T2> BiConsumer<T1, T2> biConsumer()       | Provides a do nothing implementation of BiConsumer.          |
| public static <T1, T2, R> BiFunction<T1, T2, R> biFunction() | Provides a do nothing implementation of BiFunction. This will always return null. |
| public static <T1, T2, R> BiFunction<T1, T2, R> biFunction(R output) | Provides a do nothing implementation of BiFunction with an opportunity to provide a default output. |
| public static <T1 extends R, T2, R> BiFunction<T1, T2, R> biFunctionArg1PassThru() | Provides a do nothing implementation of BiFunction where the first argument passes through as the output. |
| public static <T1, T2 extends R, R> BiFunction<T1, T2, R> biFunctionArg2PassThru() | Provides a do nothing implementation of BiFunction where the second argument passes through as the output. |
| public static <T1, T2> BiPredicate<T1, T2> biPredicate(Boolean output) | Provides a do nothing implementation of BiPredicate. Whereas, you have an option of what Boolean value you want to return by passing in a value to **output** parameter. |
| public static <T1, T2, X extends Exception> CheckedBiConsumer<T1, T2, X> checkedBiConsumer() | Provides a do nothing implementation of CheckedBiConsumer, which can throw a checked exception of type X. |
| public static <T1, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunction() | Provides a do nothing implementation of CheckedBiFunction, which can throw a checked exception of type X. This will always return null. |
| public static <T1, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunction(R output) | Provides a do nothing implementation of CheckedBiFunction with an opportunity to provide a default output, which can throw a checked exception of type X. |
| public static <T1 extends R, T2, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunctionArg1PassThru() | Provides a do nothing implementation of CheckedBiFunction where the first argument passes through as the output, which can throw a checked exception of type X. |
| public static <T1, T2 extends R, R, X extends Exception> CheckedBiFunction<T1, T2, R, X> checkedBiFunctionArg2PassThru() | Provides a do nothing implementation of CheckedBiFunction where the second argument passes through as the output, which can throw a checked exception of type X. |
| public static <T1, T2, X extends Exception> CheckedBiPredicate<T1, T2, X> checkedBiPredicate(Boolean output) | Provides a do nothing implementation of CheckedBiPredicate. Whereas, you have an option of what Boolean value you want to return by passing in a value to **output** parameter, which can throw a checked exception of type X. |
| public static <T, X extends Exception> CheckedConsumer<T, X> checkedConsumer() | Provides a do nothing implementation of CheckedConsumer, which can throw a checked exception of type X. |
| public static <T,R, X extends Exception> CheckedFunction<T,R, X> checkedFunction() | Provides a do nothing implementation of CheckedFunction, which can throw a checked exception of type X. This will always return null. |
| public static <T,R, X extends Exception> CheckedFunction<T,R, X> checkedFunction(R output) | Provides a do nothing implementation of CheckedFunction with an opportunity to return a default output, which can throw a checked exception of type X. |
| public static <T extends R,R, X extends Exception> CheckedFunction<T,R, X> checkedFunctionPassThru() | Provides a do nothing implementation of CheckedFunction where the argument passes through as the output, which can throw a checked exception of type X. |
| public static <T, X extends Exception> CheckedPredicate<T, X> checkedPredicate(Boolean output) | Provides a do nothing implementation of CheckedPredicate. Whereas, you have an option of what Boolean value you want to return by passing in a value to **output** parameter, which can throw a checked exception of type X. |
| public static <X extends Exception> CheckedSink<X> checkedSink() | Provides a do nothing implementation of CheckedSink, which can throw a checked exception of type X. |
| public static <T, X extends Exception> CheckedSupplier<T, X> checkedSupplier() | Provides a do nothing implementation of CheckedSupplier, which can throw a checked exception of type X. This will always return null. |
| public static <T> Consumer<T> consumer()                     | Provides a do nothing implementation of Consumer.            |
| public static <T,R> Function<T,R> function()                 | Provides a do nothing implementation of Function. This will always return null. |
| public static <T,R> Function<T,R> function(R output)         | Provides a do nothing implementation of Function with an opportunity to return a default output. |
| public static <T extends R,R> Function<T,R> functionPassThru() | Provides a do nothing implementation of Function where the argument passes through as the output. |
| public static <T> Predicate<T> predicate(Boolean output)     | Provides a do nothing implementation of Predicate. Whereas, you have an option of what Boolean value you want to return by passing in a value to **output** parameter. |
| public static Sink sink()                                    | Provides a do nothing implementation of Sink.                |
| public static <T> Supplier<T> supplier()                     | Provides a do nothing implementation of Supplier. This will always return null. |

[Table of Contents](USER_GUIDE_TOC.md)