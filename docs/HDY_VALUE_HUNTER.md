## ValueHunter Class
The `ValueHunter` class hunts for the first non-null value from various locations.

### Methods

#### hunt() Method

The `hunt()` method will return the first non-null value provided by the mercenaries.

| Signature |
|------|
| public Optional\<String\> hunt() |

## **ValueHunterBuilder** Class

The instance of this class is the only one that can create an instance of `ValueHunter`. To create an instance of this class you can do the following and must provide a target to find:

```java
ValueHunter.getBuilder(<target>)
```

### Methods

| Method                                                       |
| ------------------------------------------------------------ |
| public ValueHunterBuilder **byEnvVar**() |
| public ValueHunterBuilder **asEnvVar**(final String **envVar**) |
| public ValueHunterBuilder **bySysProp**() |
| public ValueHunterBuilder **asSysProp**(final String **sysProp**) |
| public ValueHunterBuilder **byProperties**(final Properties **properties**) |
| public ValueHunterBuilder **byResourceBundle**(final ResourceBundle **properties**) |
| public ValueHunterBuilder **addHunter**(final Function\<String, String\> **hunter**) |
| public ValueHunter **build**() <span style="color:red">//The only method that creates an instance of ValueHunter.</span> |

#### Parameters

| Parameter    | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| envVar       | The environment variable from which to retrieve the value of the target. |
| sysProp      | The system property from which to retrieve the value of the target. |
| properties   | The Properties object from which to retrieve the value of the target. |
| properties   | The ResourceBundle object from which to retrieve the value of the target. |
| hunter       | A function that retrieves the value of the target. |

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

[Table of Contents](USER_GUIDE_TOC.md)