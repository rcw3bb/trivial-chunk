##  StringBuilderAppender
A decorator for StringBuilder that gives you a chance to add **pre-append** and **post-append** logic on every append operation.

##### Constructors

| Signature |
|---------|
| public StringBuilderAppender() |
| public StringBuilderAppender(Consumer<StringBuilder> defaultBeforeAppend) |
| public StringBuilderAppender(Consumer<StringBuilder> defaultBeforeAppend, Consumer<StringBuilder> defaultAfterAppend) |
| public StringBuilderAppender(String string) |
| public StringBuilderAppender(String string, Consumer<StringBuilder> defaultBeforeAppend) |
| public StringBuilderAppender(String string, Consumer<StringBuilder> defaultBeforeAppend, Consumer<StringBuilder> defaultAfterAppend) |
| public StringBuilderAppender(StringBuilder builder) |
| public StringBuilderAppender(StringBuilder builder, Consumer<StringBuilder> defaultBeforeAppend) |
| public StringBuilderAppender(StringBuilder builder, Consumer<StringBuilder> defaultBeforeAppend, Consumer<StringBuilder> defaultAfterAppend) |

**Parameters**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| string | An instance of String that will be wrapped by StringBuilder. |
| builder   | An instance of StringBuilder to decorate. |
| defaultBeforeAppend | Must hold the default pre-append implementation before performing the actual append operation. |
| defaultAfterAppend | Must hold the default post-append implementation after performing the actual append operation. |

##### Main Methods

| Signature |
|--------|
| public StringBuilderAppender append(final Consumer<StringBuilder> updateLogic) |
| public StringBuilderAppender append(final Consumer<StringBuilder> updateLogic,                                    final Consumer<StringBuilder> beforeAppend) |
| public StringBuilderAppender append(final Consumer<StringBuilder> updateLogic, final Consumer<StringBuilder> beforeAppend, final Consumer<StringBuilder> afterAppend) |
| public StringBuilderAppender append(final Consumer<StringBuilder> beforeAppend,                                    final Consumer<StringBuilder> afterAppend, final String ... texts) |
| public StringBuilderAppender append(final Consumer<StringBuilder> beforeAppend, final String ... texts) |
| public StringBuilderAppender append(final String text) |
| public StringBuilderAppender append(final String text, final Consumer<StringBuilder> beforeAppend) |
| public StringBuilderAppender append(final String text, final Consumer<StringBuilder> beforeAppend, final Consumer<StringBuilder> afterAppend) |
| public StringBuilderAppender append(final String ... texts)  |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final Consumer<StringBuilder> updateLogic) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final Consumer<StringBuilder> updateLogic, final Consumer<StringBuilder> beforeAppend) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final Consumer<StringBuilder> updateLogic, final Consumer<StringBuilder> beforeAppend,                                    final Consumer<StringBuilder> afterAppend) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final Consumer<StringBuilder> beforeAppend, final Consumer<StringBuilder> afterAppend, final String ... texts) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final Consumer<StringBuilder> beforeAppend, final String ... texts) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final String text) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final String text, final Consumer<StringBuilder> beforeAppend) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final String text, final Consumer<StringBuilder> beforeAppend, final Consumer<StringBuilder> afterAppend) |
| public WhenThenReturn<StringBuilder, StringBuilderAppender> appendWhen(final String ... texts) |
| public StringBuilderAppender clear()                         |
| public StringBuilder getStringBuilder()                      |
| public StringBuilderAppender replace(final CharSequence target, final CharSequence replacement) |
| public StringBuilderAppender threadSafe()                    |

**WhenThen/WhenThenReturn Interfaces**

For this decorator the both WhenThen and WhenThenReturn interfaces, the **when methods** provide the wrapped StringBuilder instance.

**Parameters**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| text      | The text to be appended. |
| texts | The texts to be appended where each invokes the beforeAppend and afterAppend logics. |
| beforeAppend | Must hold the pre-append logic that can override the default pre-append logic. |
| afterAppend | Must hold the post-append logic that can override the default post-append logic. |
| updateLogic | Must be evaluated to something that will perform an append. |
| target | The target text with the builder to replace. |
| replacement | The replacement of the target text. |

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

