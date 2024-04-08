# CommandProcessor Class

A convenience class for **creating operating system process** with customizable output logic.

## The ICommandArray Interface

The interface that must hold the logic of how to **generate an array equivalent of the command**. The implementation must assist in sequencing all the program, command, options and arguments as arrays.

```java
public interface ICommandArray {
    String[] getCommand();
}
```

## The CommandArray Class

The **default implementation of ICommandArray**.

### Using the CommandArrayBuilder class

```
CommandArray.getBuilder()
```

### Using the wrap methods

#### Syntax

```
CommandArray.wrap(commandWithArgs) //The delimiter is a space.
```

```
CommandArray.wrap(delim, commandWithArgs) //Using a different delimited other than a space.
```

#### Example

```
CommandArray.wrap("where cmd")
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

| Parameter       | Description                                                  |
| --------------- | ------------------------------------------------------------ |
| arg             | The argument to be added.                                    |
| args            | The collection of arguments to be added.                     |
| command         | The command for the program.                                 |
| commandWithArgs | The full command with arguments.                             |
| delim           | The delimiter used in separating command and arguments from commandWithArgs. |
| program         | The program to execute.                                      |
| when            | The logic to must return true for the argument(s) to be added. |

### Usage of Add Methods

| Methods          | Usage                                                        |
| ---------------- | ------------------------------------------------------------ |
| addArg/addArgs   | Use this to add argument(s) to the command.                  |
| addPArg/addPArgs | Use this to add argument(s) to the program.                  |
| addZArg/addZArgs | Use this to add argument(s) that will become available after the argument(s) added to the command. |

## The process Methods

Any variant of **process** method is responsible for creating a process based on the command passed into it. This method will return the defined by the implementation of output logic *(i.e. the default implementation is returning the status code of the executed command)*.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static <T_OUTPUT> Optional<**T_OUTPUT**> **process**(final Consumer<Process> **initProcess**,                                                     final Function<Process, T_OUTPUT> **outputLogic**,  final ICommandArray **command**) |
| public static Optional<Integer> **process**(final Consumer<Process> **initProcess**, final ICommandArray **command**) |
| public static <T_OUTPUT> Optional<**T_OUTPUT**> **process**(final Function<Process, T_OUTPUT> **outputLogic**, final ICommandArray **command**) |
| public static Optional<Integer> **process**(final ICommandArray **command**) |
| public static <T_OUTPUT> Optional<**T_OUTPUT**> **process**(final Supplier<ProcessBuilder> **genProcessBuilder**, final Consumer<Process> **initProcess**, final Function<Process, T_OUTPUT> **outputLogic**, final ICommandArray **command**) |
| public static Optional<Integer> **process**(final Supplier<ProcessBuilder> **genProcessBuilder**,                                                     final Consumer<Process> **initProcess**, final ICommandArray **command**) |
| public static <> Optional<**T_OUTPUT**> **process**(final Supplier<ProcessBuilder> **genProcessBuilder**, final Function<Process, T_OUTPUT> **outputLogic**, final ICommandArray **command**) |
| public static Optional<Integer> **process**(final Supplier<ProcessBuilder> **genProcessBuilder**,                                  final ICommandArray **command**) |

### Parameters

| Parameter         | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| command           | An **implementation of ICommandArray interface**.            |
| genProcessBuilder | This parameter allows you to **create an instance of a ProcessBuilder** that will be used to generate an instance of Process. |
| initProcess       | This parameter allows you to **add more initialization on the Process object**. |
| outputLogic       | This parameter allows you to **capture the Process object** and process it to generate an output. Some implementations is available in **CommandProcessor.ProcessOutputHandler** class. |

### CommandProcessor.ProcessOutputHandler class

The ProcessOutputHandler class holds some default processing implementation of captured Process object. The implementation are provided by static methods as follows:

| Method                                                       | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public static Function<Process, Integer> **captureOutputs**(final BiConsumer<String, String> **outputs**) | The output implementation that  accepts a logic to process the outputs as string *(i.e. output and error strings respectively)*. Always return the status of the Process. |
| public static Function<Process, Integer> **captureStreams**(final BiConsumer<InputStream, InputStream> **streams**) | The output implementation that  accepts a logic to process the outputs as stream *(i.e. output and error InputStreams respectively)*. Always return the status of the Process. |
| public static Function<Process, Integer> **defaultOutputHandler**() | The output implementation that prints to the default OutputStreams. Always return the status of the Process. <br /><br />This is the output logic when nothing is provided. |
| public static Function<Process, String> **errorToString**()  | The output implementation that returns the error as String.  |
| public static Function<Process, String> **outputToString**() | The output implementation that returns the output as String. |

## Sample Usage

### Using the default output logic

This output the process status and prints the output of the command.

```java
final var output = CommandProcessor.process(CommandArray.wrap("where cmd"));
System.out.println(output.orElse(-1));
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
0
```

### Using the outputToString output logic

```java
final var status = CommandProcessor.process(CommandProcessor.ProcessOutputHandler.outputToString(), 
                                            CommandArray.wrap("where cmd"));
System.out.println(status.orElse("No output"));
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
```

[Table of Contents](USER_GUIDE_TOC.md)