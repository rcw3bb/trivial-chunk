# TextFile Class

A decorator class for **operating with TextFile**.

## Constructor

| Signatures |
|---------|
| public **TextFile**(final File **file**) |
| public **TextFile**(final String **filename**) |

### **Parameter**

| Parameters | Description                               |
| --------- | ----------------------------------------- |
| file | An instance of the File to wrap. |
| filename | The filename to operate on. |

## Main Methods

| Signatures |
|--------|
| public EndOfLine **getEndOfLine()** throws IOException |
| public String **getText**() throws IOException |
| public String **getText**(final Charset **charset**) throws IOException |
| public String **getText**(final Charset **charset**, final EndOfLine **endOfLine**) throws IOException |
| public String **getText**(final EndOfLine **endOfLine**) throws IOException |

### **Parameter**s

| Parameter | Descriptions              |
| --------- | ------------------------- |
| charset | An instance of the Charset to use. If not set it is defaulted to **StandardCharsets.UTF_8**. |
| endOfLine | An instance of the EndOfLine to use. If not set it is defaulted to **EndOfLine.SYSTEM**. |

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