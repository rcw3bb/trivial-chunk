# CommandRunner Class

A convenience class for **creating operating system process**.

## The ICommandArray Interface

The interface that must hold the logic of how to **generate an array equivalent of the command**. 

```java
public interface ICommandArray {
    String[] getCommand();
}
```

## The CommandArray Class

The **default implementation of ICommandArray**. 

This implementation can only be instantiated by the following builder class:

```java
CommandArray.CommandArrayBuilder
```

This builder can only be  created by the following method:

```
CommandArray.getBuilder()
```

## The CommandArray.CommandArrayBuilder Class

This is the only class can can **create an instance of CommandArray**.

### Methods

| Method                                                       | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public CommandArrayBuilder **addArgs**(String ... **args**)  | Add the arguments of the command.                            |
| public CommandArrayBuilder **addZArgs**(String ... **zArgs**) | Add the arguments that must be after the normal arguments of the command. |
| public CommandArray **build**()                              | Build an instance of CommandArray.                           |
| public CommandArrayBuilder **setCommand**(String **command**) | Set the command of the program to use.                       |
| public CommandArrayBuilder **setProgram**(String **program**) | Set the program to run.                                      |

## The runCommand Method

Any variant of **runCommand** method is responsible for creating a process based on the command passed to it. 

This method will return the **exit code** of the completed process.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static int **runCommand**(BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray commandArray) throws NoCommandException |
| public static int **runCommand**(BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws NoCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray **commandArray**) throws NoCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws  NoCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, ICommandArray **commandArray**) throws NoCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, String ... **command**) throws NoCommandException |
| public static int **runCommand**(ICommandArray **commandArray**) throws NoCommandException |
| public static int **runCommand**(String... **command**) throws NoCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray **commandArray**) throws  NoCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws NoCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, ICommandArray **commandArray**) throws NoCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, String... **command**) throws NoCommandException |

### Parameters

| Parameter            | Description                                                  |
| -------------------- | ------------------------------------------------------------ |
| command              | This is the parameter that is common to all the runCommand methods variant. Emptiness of this will throw NoCommandException. <br /><br />This parameter must **have the command** from which the process will be based on. |
| commandArray         | An **implementation of ICommandArray interface**.            |
| createProcessBuilder | This parameter allows you to **create an instance of a ProcessBuilder** that will be used to generate an instance of Process. |
| initProcessBuilder   | This parameter allows you to **add more initialization on the ProcessBuilder** that the runCommand provided. |
| outputLogic          | This parameter allows you to **capture the input and error streams** of the process. |

### Fields

| Field                | Description                                                  |
| -------------------- | ------------------------------------------------------------ |
| DEFAULT_OUTPUT_LOGIC | For all the runCommand variants without the outputLogic parameter this is the logic used.<br /><br />The output logic is just **printing out the output and error string**. |
| ERROR_EXIT_CODE      | The one being **returned by the runCommand** if an **IOException was throw** when starting the process. |

### NoCommandException

The NoCommandException is being thrown if there is no command to create a process from.

## Sample Usage

```java
try {
    var command = CommandArray.getBuilder().
            setProgram("where").
            addArgs("cmd").
            build();
    CommandRunner.runCommand(command);
} catch (NoCommandException e) {
    e.printStackTrace();
}
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
```

[Table of Contents](USER_GUIDE_TOC.md)