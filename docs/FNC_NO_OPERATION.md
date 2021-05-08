# NoOperation Class

The class that supplies a do nothing implementation of some of the functional interfaces. This is created so that you don't have to create it yourself.

##### Available Methods

| Method Signature                                             | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public static <T1, T2> BiConsumer<T1, T2> biConsumer()       | Provides a do nothing implementation of BiConsumer.          |
| public static <T1, T2, R> BiFunction<T1, T2, R> biFunction() | Provides a do nothing implementation of BiFunction. This will always return null. |
| public static <T1, T2> BiPredicate<T1, T2> biPredicate(Boolean output) | Provides a do nothing implementation of BiPredicate. Whereas, you have an option of what Boolean value you want to return by passing in a value to **output** parameter. |
| public static <T> Consumer<T> consumer()                     | Provides a do nothing implementation of Consumer.            |
| public static <T,R> Function<T,R> function()                 | Provides a do nothing implementation of Function. This will always return null. |
| public static <T> Predicate<T> predicate(Boolean output)     | Provides a do nothing implementation of Predicate. Whereas, you have an option of what Boolean value you want to return by passing in a value to **output** parameter. |
| public static Sink sink()                                    | Provides a do nothing implementation of Sink.                |
| public static <T> Supplier<T> supplier()                     | Provides a do nothing implementation of Supplier. This will always return null. |

[Table of Contents](USER_GUIDE_TOC.md)