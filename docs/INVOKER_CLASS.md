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

[Table of Contents](USER_GUIDE_TOC.md)

