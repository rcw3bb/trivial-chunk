# NoOperation Class

The class that supplies a do nothing implementation of some of the functional interfaces. This is created so that you don't have to create it yourself.

##### Available Methods

| Method Signature                                       | Description                                         |
| ------------------------------------------------------ | --------------------------------------------------- |
| public static <T> Consumer<T> consumer()               | Provides a do nothing implementation of Consumer.   |
| public static <T1, T2> BiConsumer<T1, T2> biConsumer() | Provides a do nothing implementation of BiConsumer. |
| public static Sink sink()                              | Provides a do nothing implementation of Sink.       |

