# RegExMatcher Class

A handy class to match a RegEx pattern from a text.

## The matchByRegEx Method

The matchByRegEx method is the method to use to **match the RegEx pattern from a text**. This method returns the **instance of Matcher that was used**.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static Matcher **matchByRegEx**(String **pattern**, String **text**) |
| public static Matcher **matchByRegEx**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**) |
| public static Matcher **matchByRegEx**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**,                                 Consumer<RuntimeException> **exceptionLogic**) |
| public static Matcher **matchByRegEx**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**,                                   Sink **noMatchFoundLogic**) |
| public static Matcher **matchByRegEx**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**,                                   Sink **noMatchFoundLogic**, Consumer<RuntimeException> **exceptionLogic**) |

### Parameters

| Parameter         | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| pattern           | The **regex pattern** to match.                              |
| text              | The **text to match** the pattern from.                      |
| matchFoundLogic   | The **logic** that will be executed if the pattern was **matched successfully**. |
| noMatchFoundLogic | The **logic** that will be executed if the pattern was **not found**. |
| exceptionLogic    | The **logic** that will be executed if a **RuntimeException was thrown** from **matchFoundLogic or noMatchFoundLogic logic**. |

## Sample Usage

```java
RegExMatcher.matchByRegEx("(\\w*)\\s(\\w*)", "Hello world",
        (___matcher) -> {
            System.out.printf("Pattern: %s%n", ___matcher.pattern().pattern());
            System.out.printf("Text: %s%n",___matcher.group(0));
            System.out.printf("1st pattern group: %s%n", ___matcher.group(1));
            System.out.printf("2nd pattern group: %s%n", ___matcher.group(2));
        }
);
```

**Expected Output**

```
Pattern: (\w*)\s(\w*)
Text: Hello world
1st pattern group: Hello
2nd pattern group: world
```

[Table of Contents](USER_GUIDE_TOC.md)