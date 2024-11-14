# Checked Functional Interfaces

The checked functional interfaces provide a way to handle exceptions in functional interfaces.

## CheckedFunction

The `CheckedFunction` interface represents a function that accepts one argument and produces a result, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedFunction<T, R, X extends Exception> {
    R checkedApply(T arg) throws X;
}
```

### Example

```java
CheckedFunction<String, Integer, Exception> parseInt = Integer::parseInt;
try {
    int result = parseInt.checkedApply("123");
    System.out.println(result);
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asFunction`

Converts the `CheckedFunction` to a standard `Function` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedFunction<String, Integer, Exception> parseInt = Integer::parseInt;
Function<String, Integer> safeParseInt = parseInt.asFunction();
int result = safeParseInt.apply("123");
System.out.println(result);
```

### Usage of `of`

Creates a `CheckedFunction` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `Function`.

```java
int result = CheckedFunction.<String, Integer, Exception>of(Integer::parseInt).asFunction().apply("1");
System.out.println(result);
```

The preceding sample is cleaner than the following equivalent below:

```java
Function<String, Integer> function = ((___arg) -> {
    try {
        return Integer.parseInt(___arg);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

int result = function.apply("1");
System.out.println(result);
```

## CheckedConsumer

The `CheckedConsumer` interface represents an operation that accepts a single input argument and returns no result, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedConsumer<T, X extends Exception> {
    void checkedAccept(T arg) throws X;
}
```

### Example

```java
CheckedConsumer<String, Exception> print = System.out::println;
try {
    print.checkedAccept("Hello, World!");
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asConsumer`

Converts the `CheckedConsumer` to a standard `Consumer` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedConsumer<String, Exception> print = System.out::println;
Consumer<String> safePrint = print.asConsumer();
safePrint.accept("Hello, World!");
```

### Usage of `of`

Creates a `CheckedConsumer` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `Consumer`.

```java
CheckedConsumer.<String, Exception>of(System.out::println).asConsumer().accept("Hello, World!");
```

The preceding sample is cleaner than the following equivalent below:

```java
Consumer<String> consumer = ((___arg) -> {
    try {
        System.out.println(___arg);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

consumer.accept("Hello, World!");
```

## CheckedSupplier

The `CheckedSupplier` interface represents a supplier of results, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedSupplier<R, X extends Exception> {
    R checkedGet() throws X;
}
```

### Example

```java
CheckedSupplier<String, Exception> supplier = () -> "Hello, World!";
try {
    String result = supplier.checkedGet();
    System.out.println(result);
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asSupplier`

Converts the `CheckedSupplier` to a standard `Supplier` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedSupplier<String, Exception> supplier = () -> "Hello, World!";
Supplier<String> safeSupplier = supplier.asSupplier();
String result = safeSupplier.get();
System.out.println(result);
```

### Usage of `of`

Creates a `CheckedSupplier` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `Supplier`.

```java
String result = CheckedSupplier.<String, Exception>of(() -> "Hello, World!").asSupplier().get();
System.out.println(result);
```

The preceding sample is cleaner than the following equivalent below:

```java
Supplier<String> supplier = (() -> {
    try {
        return "Hello, World!";
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

String result = supplier.get();
System.out.println(result);
```

## CheckedPredicate

The `CheckedPredicate` interface represents a predicate (boolean-valued function) of one argument, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedPredicate<T, X extends Exception> {
    boolean checkedTest(T t) throws X;
}
```

### Example

```java
CheckedPredicate<String, Exception> isEmpty = String::isEmpty;
try {
    boolean result = isEmpty.checkedTest("");
    System.out.println("Result: " + result);
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asPredicate`

Converts the `CheckedPredicate` to a standard `Predicate` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedPredicate<String, Exception> isEmpty = String::isEmpty;
Predicate<String> safeIsEmpty = isEmpty.asPredicate();
boolean result = safeIsEmpty.test("");
System.out.println(result);
```

### Usage of `of`

Creates a `CheckedPredicate` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `Predicate`.

```java
boolean result = CheckedPredicate.<String, Exception>of(String::isEmpty).asPredicate().test("");
System.out.println(result);
```

The preceding sample is cleaner than the following equivalent below:

```java
Predicate<String> predicate = ((___arg) -> {
    try {
        return ___arg.isEmpty();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

boolean result = predicate.test("");
System.out.println(result);
```

## CheckedBiFunction

The `CheckedBiFunction` interface represents a function that accepts two arguments and produces a result, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedBiFunction<T1, T2, R, X extends Exception> {
    R checkedApply(T1 arg1, T2 arg2) throws X;
}
```

### Example

```java
CheckedBiFunction<String, String, Integer, Exception> compare = String::compareTo;
try {
    int result = compare.checkedApply("a", "b");
    System.out.println(result);
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asBiFunction`

Converts the `CheckedBiFunction` to a standard `BiFunction` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedBiFunction<String, String, Integer, Exception> compare = String::compareTo;
BiFunction<String, String, Integer> safeCompare = compare.asBiFunction();
int result = safeCompare.apply("a", "b");
System.out.println(result);
```

### Usage of `of`

Creates a `CheckedBiFunction` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `BiFunction`.

```java
int result = CheckedBiFunction.<String, String, Integer, Exception>of(String::compareTo).asBiFunction().apply("a", "b");
System.out.println(result);
```

The preceding sample is cleaner than the following equivalent below:

```java
BiFunction<String, String, Integer> biFunction = ((arg1, arg2) -> {
    try {
        return arg1.compareTo(arg2);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

int result = biFunction.apply("a", "b");
System.out.println(result);
```

## CheckedBiConsumer

The `CheckedBiConsumer` interface represents an operation that accepts two input arguments and returns no result, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedBiConsumer<T1, T2, X extends Exception> {
    void checkedAccept(T1 arg1, T2 arg2) throws X;
}
```

### Example

```java
CheckedBiConsumer<String, String, Exception> concatAndPrint = (s1, s2) -> System.out.println(s1 + s2);
try {
    concatAndPrint.checkedAccept("Hello, ", "World!");
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asBiConsumer`

Converts the `CheckedBiConsumer` to a standard `BiConsumer` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedBiConsumer<String, String, Exception> concatAndPrint = (s1, s2) -> System.out.println(s1 + s2);
BiConsumer<String, String> safeConcatAndPrint = concatAndPrint.asBiConsumer();
safeConcatAndPrint.accept("Hello, ", "World!");
```

### Usage of `of`

Creates a `CheckedBiConsumer` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `BiConsumer`.

```java
CheckedBiConsumer.<String, String, Exception>of((s1, s2) -> System.out.println(s1 + s2)).asBiConsumer().accept("Hello, ", "World!");
```

The preceding sample is cleaner than the following equivalent below:

```java
BiConsumer<String, String> biConsumer = ((arg1, arg2) -> {
    try {
        System.out.println(arg1 + arg2);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

biConsumer.accept("Hello, ", "World!");
```

## CheckedBiPredicate

The `CheckedBiPredicate` interface represents a predicate (boolean-valued function) of two arguments, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedBiPredicate<T1, T2, X extends Exception> {
    boolean checkedTest(T1 arg1, T2 arg2) throws X;
}
```

### Example

```java
CheckedBiPredicate<String, String, Exception> equals = String::equals;
try {
    boolean result = equals.checkedTest("a", "a");
    System.out.println(result);
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asBiPredicate`

Converts the `CheckedBiPredicate` to a standard `BiPredicate` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedBiPredicate<String, String, Exception> equals = String::equals;
BiPredicate<String, String> safeEquals = equals.asBiPredicate();
boolean result = safeEquals.test("a", "a");
System.out.println(result);
```

### Usage of `of`

Creates a `CheckedBiPredicate` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `BiPredicate`.

```java
boolean result = CheckedBiPredicate.<String, String, Exception>of(String::equals).asBiPredicate().test("a", "a");
System.out.println(result);
```

The preceding sample is cleaner than the following equivalent below:

```java
BiPredicate<String, String> biPredicate = ((arg1, arg2) -> {
    try {
        return arg1.equals(arg2);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

boolean result = biPredicate.test("a", "a");
System.out.println(result);
```

## CheckedSink

The `CheckedSink` interface represents a block of code that can be executed, potentially throwing a checked exception.

### Usage

```java
@FunctionalInterface
public interface CheckedSink<X extends Exception> {
    void checkedPlummet() throws X;
}
```

### Example

```java
CheckedSink<Exception> sink = () -> {
    System.out.println("Hello World");
};
try {
    sink.checkedPlummet();
} catch (Exception e) {
    e.printStackTrace();
}
```

### Usage of `asSink`

Converts the `CheckedSink` to a standard `Sink` that wraps any thrown exception in a `RuntimeException`.

```java
CheckedSink<Exception> sink = () -> {
    System.out.println("Hello World");
};
Sink safeSink = sink.asSink();
safeSink.plummet();
```

### Usage of `of`

Creates a `CheckedSink` from the given logic. This is useful whenever you want to use a logic that can throw a checked Exception but the target parameter is a standard `Sink`.

```java
CheckedSink.<Exception>of(() -> {
    System.out.println("Hello World");
}).asSink().plummet();
```

The preceding sample is cleaner than the following equivalent below:

```java
Sink sink = (() -> {
    try {
        System.out.println("Hello World");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

sink.plummet();
```

## Conclusion

The `Checked` functional interfaces provide a convenient way to handle checked exceptions in functional programming. By using these interfaces, you can write cleaner and more expressive code while still managing exceptions effectively.

[Table of Contents](USER_GUIDE_TOC.md)