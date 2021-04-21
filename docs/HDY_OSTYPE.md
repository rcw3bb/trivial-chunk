## OSType Enumeration

An enumeration of that contains the following:

| Enum           | Description             |
| -------------- | ----------------------- |
| OSType.Windows | Indicates a windows OS. |
| OSType.Linux   | Indicates a linux OS.   |
| OSType.Mac     | Indicates a mac OS.     |
| OSType.Unknown | Unidentified OS.        |

### OSType.identify() Static method

A convenience method that identifies the current OS where the current code is being run.

**Sample Usage**

```java
System.out.println(OSType.identify());
```

**Expected Output**

```
Windows
```

> Assuming you run the print command on windows OS.

[Table of Contents](USER_GUIDE_TOC.md)