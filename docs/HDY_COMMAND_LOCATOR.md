# CommandLocator Class

A convenience class for **locating a command**.

### Methods

| Method                                                       | Description                                                |
| ------------------------------------------------------------ | ---------------------------------------------------------- |
| public static Optional<File> **locateAsFile**(final String **command**) | Locate the command and return an optional file instance.   |
| public static Optional<String> **locateAsString**(final String **command**) | Locate the command and return an optional string instance. |

#### Parameters

| Parameter | Description                       |
| --------- | --------------------------------- |
| command   | The executable command to locate. |

## Sample Usage

```java
final var command = CommandLocator.locateAsFile("cmd");
System.out.println(command.get().getPath());
```

**Expected Output**

```
C:\Windows\System32\cmd.exe
```

[Table of Contents](USER_GUIDE_TOC.md)