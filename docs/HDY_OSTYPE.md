## OSType Enumeration

An enumeration of OSes as follows:

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

## The getEOL() Instance method

The getEOL() method returns the associated EndOfLine instance for a particular OS.

[Table of Contents](USER_GUIDE_TOC.md)