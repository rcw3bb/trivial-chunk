## EndOfLine Enumeration

An enumeration of EndOfLine as follows:

| Enum             | Description                                   |
| ---------------- | --------------------------------------------- |
| EndOfLine.CR     | Carriage return only.                         |
| EndOfLine.CRLF   | Combination of carriage return and line feed. |
| EndOfLine.LF     | Line feed only.                               |
| EndOfLine.SYSTEM | System detected EOL.                          |

### eol method

A method that returns the string representation of the EOL.

**Sample Usage**

```java
System.out.println(EndOfLine.CR.eol());
```

**Expected Output**

```
\r
```

[Table of Contents](USER_GUIDE_TOC.md)