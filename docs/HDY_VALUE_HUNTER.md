## ValueHunter Class
The `ValueHunter` class hunts for the first non-null value from various locations.

### Methods

#### hunt() Method

The `hunt()` method will return the first non-null value provided by the mercenaries. The builder method that was used must be the one that accepts the target *(i.e. getBuilder(\<target\>))*.

| Signature |
|------|
| public Optional\<String\> hunt() |

#### hunt(String target) Method

The `hunt(String target)` method will return the first non-null value for the specified target from various locations.

| Signature |
|------|
| public Optional\<String\> hunt(String target) |

## **ValueHunterBuilder** Class

The instance of this class is the only one that can create an instance of `ValueHunter`. To create an instance of this class you can do the following and must provide a target to find:

```java
ValueHunter.getBuilder(<target>)
```

Alternatively, you can create a builder without specifying a target initially:

```java
ValueHunter.getBuilder()
```

### Methods

| Method                                                       |
| ------------------------------------------------------------ |
| public ValueHunterBuilder **byEnvVar**()                     |
| public ValueHunterBuilder **asEnvVar**(final String **envVar**) <span style="color:red">//Can only be used when the builder knows about the target.</span> |
| public ValueHunterBuilder **bySysProp**()                    |
| public ValueHunterBuilder **asSysProp**(final String **sysProp**) <span style="color:red">//Can only be used when the builder knows about the target.</span> |
| public ValueHunterBuilder **byProperties**(final Properties **properties**) |
| public ValueHunterBuilder **byResourceBundle**(final ResourceBundle **resourceBundle**) |
| public ValueHunterBuilder **addHunter**(final Function\<String, String\> **hunter**) |
| public ValueHunter **build**() <span style="color:red">//The only method that creates an instance of ValueHunter.</span> |

#### Parameters

| Parameter      | Description                                                  |
| -------------- | ------------------------------------------------------------ |
| envVar         | The environment variable from which to retrieve the value of the target. |
| sysProp        | The system property from which to retrieve the value of the target. |
| properties     | The Properties object from which to retrieve the value of the target. |
| resourceBundle | The ResourceBundle object from which to retrieve the value of the target. |
| hunter         | A function that retrieves the value of the target.           |

## Sample Usage

### Usage of hunt method

```java
final var valueHunter = ValueHunter.getBuilder("MY_ENV_VAR")
        .byEnvVar()
        .bySysProp()
        .build();
final var value = valueHunter.hunt().orElse("default_value");
System.out.println(value);
```

> This will return the value of the environment variable `MY_ENV_VAR` if it exists; otherwise, it will return the value of the system property `MY_ENV_VAR` if it exists. If neither exists, it will return "default_value".

### Usage of hunt(String target) method

```java
final var valueHunter = ValueHunter.getBuilder()
        .byEnvVar()
        .bySysProp()
        .build();
final var value = valueHunter.hunt("MY_ENV_VAR").orElse("default_value");
System.out.println(value);
```

> This will return the value of the environment variable `MY_ENV_VAR` if it exists; otherwise, it will return the value of the system property `MY_ENV_VAR` if it exists. If neither exists, it will return "default_value".

[Table of Contents](USER_GUIDE_TOC.md)