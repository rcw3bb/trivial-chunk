# RegExMatcher Class

A handy class to match a RegEx pattern from a text.

## The find methods

These are the convenience methods with **Matcher.find()** as the default matching logic. 

All of these methods returns the instance of the Matcher that was used.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static Matcher **find**(String **pattern**, String **text**) |
| public static Matcher **findWithMatchLogic**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**) |
| public static Matcher **findWithMatchLogic**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**,                                          Consumer<RuntimeException> **exceptionLogic**) |
| public static Matcher **findWithNoMatchLogic**(String **pattern**, String **text**, Consumer<Matcher> **matchFoundLogic**,                                            Consumer<Matcher> **noMatchFoundLogic**) |
| public static Matcher **findWithNoMatchLogic**(String **pattern**, String **text, Consumer**<Matcher> **matchFoundLogic**,                                          Consumer<Matcher> **noMatchFoundLogic**, Consumer<RuntimeException> **exceptionLogic**) |

## The match methods

There are the methods that requires **matchLogic** be passed in. Use one of these if the matching logic is more than Matcher.find().

All of these methods returns the instance of the Matcher that was used.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static Matcher **match**(String **pattern**, String **text**, Function<Matcher, Boolean> **matchLogic**) |
| public static Matcher **match**(String **pattern**, String **text**, Function<Matcher, Boolean> **matchLogic**,                             Consumer<RuntimeException> **exceptionLogic**) |
| public static Matcher **matchWithMatchLogic**(String **pattern**, String **text**, Function<Matcher, Boolean> **matchLogic**,                                           Consumer<Matcher> **matchFoundLogic**) |
| public static Matcher **matchWithMatchLogic**(String **pattern**, String **text**, Function<Matcher, Boolean> **matchLogic**,                                           Consumer<Matcher> **matchFoundLogic**, Consumer<RuntimeException> **exceptionLogic**) |
| public static Matcher **matchWithNoMatchLogic**(String **pattern**, String **text**, Function<Matcher, Boolean> **matchLogic**,                                             Consumer<Matcher> **matchFoundLogic**, Consumer<Matcher> **noMatchFoundLogic**) |
| public static Matcher **matchWithNoMatchLogic**(String **pattern**, String **text**, Function<Matcher, Boolean> **matchLogic**,                                             Consumer<Matcher> **matchFoundLogic**, Consumer<Matcher> **noMatchFoundLogic**,                                       Consumer<RuntimeException> **exceptionLogic**) |

## Parameters

| Parameter         | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| pattern           | The **regex pattern** to match.                              |
| text              | The **text where to match** the pattern from.                |
| matchLogic        | The **logic** to perform the matching. The find methods uses Matcher.find() by default. |
| matchFoundLogic   | The **logic** that will be executed if the pattern was **matched successfully**. |
| noMatchFoundLogic | The **logic** that will be executed if the pattern was **not found**. |
| exceptionLogic    | The **logic** that will be executed if a **RuntimeException was thrown** from **matchFoundLogic or noMatchFoundLogic logic**. |

## Method suffixes

| Suffix           | Description                                                  |
| ---------------- | ------------------------------------------------------------ |
| WithMatchLogic   | These are methods that **accept matched logic** by **matchFoundLogic parameter**. |
| WithNoMatchLogic | There are methods that accept both **matched** and **non-matched logics** by the **matchFoundLogic and noMatchFoundLogic parameters**, respectively. |

## Sample usage

```java
RegExMatcher.findWithMatchLogic("(\\w*)\\s(\\w*)", "Hello world",
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