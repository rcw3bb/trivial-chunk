# TextFile Class

A decorator class for **operating with TextFile**.

## Constructor

| Signatures |
|---------|
| public **TextFile**(final File **file**) |
| public **TextFile**(final File **file**, final Charset **charset**) |
| public **TextFile**(final File **file**, final Charset **charset**, final EndOfLine **endOfLine**) |
| public **TextFile**(final File **file**, final EndOfLine **endOfLine**) |
| public **TextFile**(final String **filename**) |
| public **TextFile**(final String **filename**, final Charset **charset**) |
| public **TextFile**(final String **filename**, final Charset **charset**, final EndOfLine **endOfLine**) |
| public **TextFile**(final String **filename**, final EndOfLine **endOfLine**) |

### **Parameter**

| Parameters | Description                               |
| --------- | ----------------------------------------- |
| file | An instance of the File to wrap. |
| filename | The filename to operate on. |
| charset | The character set to use with the text file. The default is **StandardCharsets.UTF_8**. |
| endOfLine | The end of line to use with the text file. The default is what ever the wrapped EOL of the file. |

## Main Methods

| Signatures |
|--------|
| public EndOfLine **getEndOfLine()** throws IOException |
| public String **getText**() throws IOException |
| public void **setText**(final String **text**) throws IOException |

### **Parameter**s

| Parameter | Descriptions              |
| --------- | ------------------------- |
| text | The text to set the content of the wrapped file. |

**Sample Usage**

```java
final var pathFinder = PathFinder.getBuilder("textfile-windows.txt")
        .addPaths(List.of("src/test/resources", "src/test/resources/pathfinder"))
        .build();
final var file = pathFinder.getFile().get();
final var textFile = new TextFile(file);
final var text = textFile.getText();
System.out.println(text);
```

> This will output the text content of the textfile-windows.txt.

[Table of Contents](USER_GUIDE_TOC.md)