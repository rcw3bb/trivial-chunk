# StringBuilderDelim Consumer Implementation

An implementation of the Consumer functional interface that appends a delimiter between each element added with the accepted StringBuilder instance.

This is useful when use with `StringBuilderAppender` constructor that accepts **defaultBeforeAppend** parameter.

##### Constructor

| Signature                                        | Description                                                  |
| ------------------------------------------------ | ------------------------------------------------------------ |
| public **StringBuilderDelim**(final T **delim**) | Accepts the delimiter to use when when doing append operation of a non-empty StringBuilder instance. |

Example usage with StringBuilderAppender

```java
var builder = new StringBuilderAppender(new StringBuilderDelim<>(", "))
        .append("Hello")
        .append("World");
System.out.println(builder.toString());
```

Expect the following output:
```
Hello, World
```

[Table of Contents](USER_GUIDE_TOC.md)