## OSType Enumeration

An enumeration of OSes as follows:

| Enum           | Description             |
| -------------- | ----------------------- |
| OSType.WINDOWS | Indicates a Windows OS. |
| OSType.LINUX   | Indicates a Linux OS.   |
| OSType.UNIX    | Indicates a Unix OS.    |
| OSType.MAC     | Indicates a Mac OS.     |
| OSType.SOLARIS | Indicates a Solaris OS. |
| OSType.UNKNOWN | Unidentified OS.        |

### OSType.identify() Static method

A convenience method that identifies the current OS where the current code is being run.

**Sample Usage**

```java
System.out.println(OSType.identify());
```

**Expected Output**

```
WINDOWS
```

> Assuming you run the print command on Windows OS.

### OSType.of(String osName) Static method

A convenience method that returns the OSType instance based on the osName.

**Sample Usage**

```java
System.out.println(OSType.of("Windows"));
```

**Expected Output**

```
WINDOWS
```

### OSType.of(EndOfLine eol) Static method

A convenience method that returns the OSType instance based on the EndOfLine.

**Sample Usage**

```java
System.out.println(OSType.of(EndOfLine.CRLF));
```

**Expected Output**

```
WINDOWS
```

## The getEOL() Instance method

The getEOL() method returns the associated EndOfLine instance for a particular OS.

## The isPosix() Instance method

The isPosix() method returns an Optional\<Boolean\> indicating if the OS is POSIX compliant.

**Sample Usage**

```java
System.out.println(OSType.LINUX.isPosix().orElse(false));
```

**Expected Output**

```
true
```

## The getCmdLocator() Instance method

The getCmdLocator() method returns an Optional\<String\> with the command locator associated with the OS.

**Sample Usage**

```java
System.out.println(OSType.WINDOWS.getCmdLocator().orElse("not found"));
```

**Expected Output**

```
where
```

[Table of Contents](USER_GUIDE_TOC.md)