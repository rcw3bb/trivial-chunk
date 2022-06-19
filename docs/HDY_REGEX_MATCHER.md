# RegExMatcher Class

A handy class to match a RegEx pattern from a text.

## The IMatcherConfig Interface

The implementation IMatcherConfig interface must be used to **configure the RegExMatcher.match method**.

## The MatcherConfig class

The MatcherConfig class is the **default implementation of IMatcherConfig Interface**. This can only be initialized using the following builder class:

```
MatcherConfig.MatcherConfigBuilder
```

## The MatcherConfigBuilder class

The MatcherConfigBuilder is the only class that can create an instance of MatcherConfig.

### Methods

| Signatures                                                   |
| ------------------------------------------------------------ |
| public MatcherConfig **build**()                             |
| public MatcherConfig **build**(final int **patternFlags**)   |
| public MatcherConfigBuilder **setExceptionLogic**(final Consumer<RuntimeException> **exceptionLogic**) |
| public MatcherConfigBuilder **setMatchFoundLogic**(final Consumer<Matcher>  **matchFoundLogic**) |
| public MatcherConfigBuilder **setMatchLogic**(final Function<Matcher, Boolean> **matchLogic**) |
| public MatcherConfigBuilder **setNoMatchFoundLogic**(final Consumer<Matcher>  **noMatchFoundLogic**) |

> The **build methods** are the only methods that can create an instance of MatcherConfig .

### Parameters

| Parameter         | Description                           |
| ----------------- | ------------------------------------- |
| exceptionLogic    | Must hold the exception logic.        |
| matchFoundLogic   | Must hold the matched found logic.    |
| matchLogic        | Must hold the matching logic.         |
| noMatchFoundLogic | Must hold the no matched found logic. |
| patternFlags      | Must hold the pattern flags to use.   |

## RegExMatcher Methods

### The find methods

These are the convenience methods with **Matcher.find()** as the default matching logic. 

All of these methods returns the instance of the Matcher that was used.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static Matcher **find**(String **pattern**, String **text**) |
| public static Matcher **find**(final String **pattern**, final String **text**, final Consumer<Matcher> **matchFoundLogic**) |
| public static Matcher **find**(final String **pattern**, final String **text**, final Consumer<Matcher> **matchFoundLogic**, final Consumer<Matcher> **noMatchFoundLogic**) |
| public static Matcher **find**(final String **pattern**, final String **text**, final int **flags**) |
| public static Matcher **find**(final String **pattern**, final String **text**, final int **flags**,                            final Consumer<Matcher> **matchFoundLogic**) |
| public static Matcher **find**(final String **pattern**, final String **text**, final int **flags**,                            final Consumer<Matcher> **matchFoundLogic**, final Consumer<Matcher> **noMatchFoundLogic**) |

### The match methods

These is the method that requires an implementation of **IMatcherConfig** be passed in. 

This methods returns the instance of the Matcher that was used.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static Matcher **match**(final String **pattern**, final String text, **final** IMatcherConfig **config**) |

### Parameters

| Parameter         | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| config            | An **implementation of IMatcherConfig** that holds the configuration of RegExMatcher. |
| flags             | The **pattern flags** to use when doing the matching *(e.g. Pattern.MULTILINE \| Pattern.CASE_INSENSITIVE)*. |
| pattern           | The **regex pattern** to match.                              |
| text              | The **text where to match** the pattern from.                |
| matchLogic        | The **logic** to perform the matching. The find methods uses Matcher.find() by default. |
| matchFoundLogic   | The **logic** that will be executed if the pattern was **matched successfully**. |
| noMatchFoundLogic | The **logic** that will be executed if the pattern was **not found**. |
| exceptionLogic    | The **logic** that will be executed if a **RuntimeException was thrown** from **matchFoundLogic or noMatchFoundLogic logic**. |

## Sample usage

```java
RegExMatcher.find("(\\w*)\\s(\\w*)", "Hello world",
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