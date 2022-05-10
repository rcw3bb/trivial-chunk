# CommandRunner Class

A convenience class for creating operating system process.

## The runCommand Method

Any variant of **runCommand** method is responsible for creating a process based on the command passed to it. 

This method will return the **exit code** of the completed process.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static int **runCommand**(BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws NoCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws  NoCommandException |
| public static int **runCommand**(Consumer<ProcessBuilder> **initProcessBuilder**, String ... **command**) throws NoCommandException |
| public static int **runCommand**(String... **command**) throws NoCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, BiConsumer<InputStream, InputStream> **outputLogic**, String ... **command**) throws NoCommandException |
| public static int **runCommand**(Supplier<ProcessBuilder> **createProcessBuilder**, String... **command**) throws NoCommandException |

### Parameters

| Parameter            | Description                                                  |
| -------------------- | ------------------------------------------------------------ |
| command              | This is the parameter that is common to all the runCommand methods variant. Emptiness of this will throw NoCommandException. <br /><br />This parameter must **have the command** from which the process will be based on. |
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
    CommandRunner.runCommand("where", "cmd");
} catch (NoCommandException e) {
    e.printStackTrace();
}
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
```

[Table of Contents](USER_GUIDE_TOC.md)