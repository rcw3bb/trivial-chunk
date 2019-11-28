# Trivial Chunk

A library of trivial codes.

## Requires

* Java 9

## Usage

1. Add the following **maven** dependency to your project:

   | Property    | Value              |
   | ----------- | ------------------ |
   | Group ID    | xyz.ronella.casual |
   | Artifact ID | trivial-chunk      |
   | Version     | 1.0.0              |

   > Using gradle, this can be added as a dependency entry like the following:
   >
   > ```groovy
   > compile group: 'xyz.ronella.casual', name: 'trivial-chunk', version: '1.0.0'
   > ```

2. Include the following to your **module-info.java**:

   ```java
   requires xyz.ronella.trivial.command;
   ```

## Invoker Class

A utility class that invokes an implementation of some standard functional interfaces in java. This is good for a **command pattern** implementation.

##### Available Methods

| Method Signature                                             | Description                                   |
| ------------------------------------------------------------ | --------------------------------------------- |
| static void **execute**(BiConsumer<TYPE_ARG1, TYPE_ARG2> logic, TYPE_ARG1 arg1, TYPE_ARG2 arg2) | Executes an implementation **BiConsumer**.    |
| static TYPE_RETURN **process**(BiFunction<TYPE_ARG1, TYPE_ARG2, TYPE_RETURN> logic, TYPE_ARG1 arg1, TYPE_ARG2 arg2) | Executes an implementation of **BiFunction**. |
| static void **execute**(Consumer<TYPE> logic, TYPE arg)      | Executes an implementation **Consumer**.      |
| static TYPE_RETURN **process**(Function<TYPE_ARG, TYPE_RETURN> logic, TYPE_ARG arg) | Executes an implementation of **Function**.   |
| static TYPE_RETURN **generate**(Supplier<TYPE_RETURN> logic) | Executes an implementation of **Supplier**.   |
| static void **plunge**(Sink logic)                           | Executes an implementation of **Sink**.       |

**Sample Usage**

```java
//Normally the following logic must be a separate class implementation.
//This is only for documentation purposes only.
Consumer<String> logic = ___name -> 
    System.out.println(String.format("Hello %s", ___name));

Invoker.execute(logic, "World");
```

**Expected Output**

```
Hello World
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

## Author

* Ronaldo Webb
