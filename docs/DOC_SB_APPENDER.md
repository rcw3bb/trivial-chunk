##  StringBuilderAppender
A decorator for StringBuilder that gives you a chance to add **pre-append** and **post-append** logic on every append operation.

##### Constructors

| Signature |
|---------|
| public StringBuilderAppender(StringBuilder builder) |
| public StringBuilderAppender(StringBuilder builder, Consumer<StringBuilder> defaultBeforeAppend) |
| public StringBuilderAppender(StringBuilder builder, Consumer<StringBuilder> defaultBeforeAppend, Consumer<StringBuilder> defaultAfterAppend) |

**Parameters**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| builder   | An instance of StringBuilder to decorate. |
| defaultBeforeAppend | Must hold the default pre-append implementation before performing the actual append operation. |
| defaultAfterAppend | Must hold the default post-append implementation after performing the actual append operation. |

##### Main Methods

| Signature |
|--------|
| public StringBuilderAppender append(String text) |
| public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend) |
| public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend, Consumer<StringBuilder> afterAppend) |
| public StringBuilderAppender append(BooleanSupplier condition, String text) |
| public StringBuilderAppender append(BooleanSupplier condition, String text, Consumer<StringBuilder> beforeAppend) |
| public StringBuilderAppender append(BooleanSupplier condition, String text, Consumer<StringBuilder> beforeAppend,                                 Consumer<StringBuilder> afterAppend) |
| public StringBuilderAppender append(Consumer<StringBuilder> updateLogic) |
| public StringBuilderAppender append(Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend) |
| public StringBuilderAppender append(Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend,                                     Consumer<StringBuilder> afterAppend) |
| public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> updateLogic) |
| public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend) |
| public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend, Consumer<StringBuilder> afterAppend) |
| public StringBuilderAppender threadSafe() //Make the appending task threadsafe. |
| public String toString() //The String representation of the internal StringBuilder that the decorator is holding. |
| public StringBuilder getStringBuilder() //Access the internal StringBuilder that the decorator is holding. |

**Parameters**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| text      | The text to be appended. |
| beforeAppend | Must hold the pre-append logic that can override the default pre-append logic. |
| afterAppend | Must hold the post-append logic that can override the default post-append logic. |
| condition | Must be evaluated to true before the append can be done. |
| updateLogic | Must be evaluated to something that will perform an append. |

**Sample Usage**

```java
var builder = new StringBuilder();
var appender = new StringBuilderAppender(builder, ___builder -> 
               ___builder.append(___builder.length() > 0 ? "," : ""));
appender.append("1")
    .append("2")
    .append("3");

System.out.println(builder.toString());
```

**Expected Output**

```
1,2,3
```

[Table of Contents](USER_GUIDE_TOC.md)

