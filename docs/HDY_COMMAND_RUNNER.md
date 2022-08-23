# CommandRunner Class

A convenience class for **creating operating system process**.

## The ICommandArray Interface

The interface that must hold the logic of how to **generate an array equivalent of the command**. The implementation must assist in sequencing all the program, command, options and arguments as arrays.

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

| Method                                                       |
| ------------------------------------------------------------ |
| public CommandArrayBuilder **addArg**(final BooleanSupplier **when**, final String arg) |
| public CommandArrayBuilder **addArg**(final String **arg**)  |
| public CommandArrayBuilder **addArgs**(final BooleanSupplier **when**, final Collection<String> **args**) |
| public CommandArrayBuilder **addArgs**(final Collection<String> **args**) |
| public CommandArrayBuilder **addPArg**(final BooleanSupplier **when**, final String **arg**) |
| public CommandArrayBuilder **addPArg**(final String **arg**) |
| public CommandArrayBuilder **addPArgs**(final BooleanSupplier **when**, final Collection<String> **args**) |
| public CommandArrayBuilder **addPArgs**(final Collection<String> **args**) |
| public CommandArrayBuilder **addZArg**(final BooleanSupplier when, final String **arg**) |
| public CommandArrayBuilder **addZArg**(final String **arg**) |
| public CommandArrayBuilder **addZArgs**(final BooleanSupplier **when**, final Collection<String> **args**) |
| public CommandArrayBuilder **addZArgs**(final Collection<String> **args**) |
| public CommandArray **build**() <span style="color:red">//The only method that creates an instance of CommandArray.</span> |
| public CommandArrayBuilder **setCommand**(final String **command**) <span style="color:red">//The command of for the program.</span> |
| public CommandArrayBuilder **setProgram**(final String **program**) <span style="color:red">//The program to execute *(i.e. the executable)*</span> |

#### Parameters

|         |                                                              |
| ------- | ------------------------------------------------------------ |
| arg     | The argument to be added.                                    |
| args    | The collection of arguments to be added.                     |
| command | The command for the program.                                 |
| program | The program to execute.                                      |
| when    | The logic to must return true for the argument(s) to be added. |

### Usage of Add Methods

| Methods          | Usage                                                        |
| ---------------- | ------------------------------------------------------------ |
| addArg/addArgs   | Use this to add argument(s) to the command.                  |
| addPArg/addPArgs | Use this to add argument(s) to the program.                  |
| addZArg/addZArgs | Use this to add argument(s) that will become available after the argument(s) added to the command. |

## The runCommand and startProcess Methods

Any variant of **runCommand** method is responsible for creating a process based on the command passed to it. This method will return the **exit code** of the completed process.

Any variant of **startProcess** methods behaves like the runCommand method with an exception that you have **access to the Process object** and returns an **instance of Process**.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static int **runCommand**(BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray commandArray) throws MissingCommandException |
| public static int **runCommand**(BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws MissingCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray **commandArray**) throws MissingCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws  MissingCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, ICommandArray **commandArray**) throws MissingCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, String ... **command**) throws MissingCommandException |
| public static int **runCommand**(ICommandArray **commandArray**) throws MissingCommandException |
| public static int **runCommand**(String... **command**) throws MissingCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray **commandArray**) throws  MissingCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws MissingCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, ICommandArray **commandArray**) throws MissingCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, String... **command**) throws MissingCommandException |
| public static int **startProcess**(Consumer<Process> **initProcess**, ICommandArray **commandArray**) throws MissingCommandException, CommandRunnerException |
| public static int **startProcess**(Consumer<ProcessBuilder> **initProcessBuilder**, Consumer<Process> **initProcess**, ICommandArray **commandArray**) throws MissingCommandException, CommandRunnerException |
| public static Process **startProcess**(ICommandArray **commandArray**) throws MissingCommandException, CommandRunnerException |
| public static int **startProcess**(Supplier<ProcessBuilder> **createProcessBuilder**, Consumer<Process> **initProcess**, BiConsumer<InputStream, InputStream> **outputLogic**, ICommandArray **commandArray**) throws MissingCommandException, CommandRunnerException |
| public static int **startProcess**(Supplier<ProcessBuilder> **createProcessBuilder**, Consumer<Process> **initProcess**, ICommandArray **commandArray**) throws MissingCommandException, CommandRunnerException |

### Parameters

| Parameter            | Description                                                  |
| -------------------- | ------------------------------------------------------------ |
| command              | This is the parameter that is common to all the runCommand and startProcess methods variant. Emptiness of this will throw MissingCommandException. <br /><br />This parameter must **have the command** from which the process will be based on. |
| commandArray         | An **implementation of ICommandArray interface**.            |
| createProcessBuilder | This parameter allows you to **create an instance of a ProcessBuilder** that will be used to generate an instance of Process. |
| initProcess          | This parameter allows you to **add more initialization on the Process object**. |
| initProcessBuilder   | This parameter allows you to **add more initialization on the ProcessBuilder**. |
| outputLogic          | This parameter allows you to **capture the input and error streams** of the process. |

### Fields

| Field                | Description                                                  |
| -------------------- | ------------------------------------------------------------ |
| DEFAULT_OUTPUT_LOGIC | For all the runCommand and startProcess variants without the outputLogic parameter this is the logic used.<br /><br />The output logic is just **printing out the output and error string**. |
| ERROR_EXIT_CODE      | The one being **returned by the runCommand** if an **IOException was throw** when starting the process. |

### CommandRunnerException

The CommandRunnerException must be handled for any variant of startProcess methods.

### MissingCommandException

The MissingCommandException is being thrown if there is no command to create a process from.

## Sample Usage

### Simple usage of runCommand

```java
try {
    var command = CommandArray.getBuilder().setProgram("where").addArgs("cmd").build();
    CommandRunner.runCommand(command);
} catch (MissingCommandException e) {
    e.printStackTrace();
}
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
```

### Simple usage of startProcess

```java
var command = CommandArray.getBuilder().setCommand("where").addArg("cmd").build();
try {
    CommandRunner.startProcess(command).onExit().get().exitValue();
} catch (InterruptedException | ExecutionException | MissingCommandException | CommandRunnerException e) {
    throw new RuntimeException(e);
}
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
```



[Table of Contents](USER_GUIDE_TOC.md)