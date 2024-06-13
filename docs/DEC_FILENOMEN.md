# FileNomen Class

A decorator class for **operating with File** to extract the filename and the extension.

## Constructor

| Signatures |
|---------|
| public **FileNomen**(final File **file**) |

### **Parameter**

| Parameters | Description                               |
| --------- | ----------------------------------------- |
| file | An instance of the File to wrap. |

## Main Methods

| Signatures |
|--------|
| public Optional<String> **getFilename**() |
| public Optional<String> **getExtension**() |

**Sample Usage**

```java
final var file = new File("TestFile.txt");
final var nomen = new FileNomen(file);
System.out.printf("Filename: %s.%s%n", nomen.getFilename().get(), nomen.getExtension().get());
```

> This will output the following:
>
> ```Filename: TestFile.txt
> Filename: TestFile.txt
> ```

[Table of Contents](USER_GUIDE_TOC.md)