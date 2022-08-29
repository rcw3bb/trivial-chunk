## PathFinder Class
The PathFinder class find the first existence of the file based on the supplied possible locations. 

Optionally, if being used to process the file as InputStream, it provides option to retrieve the file from ClassLoader.

### Methods

#### getFile() Method

The getFile() method will return the first existence of the file based on the supplied paths using the builder class. 

| Signature |
|------|
| public Optional<File> getFile() |

#### processInputStream Method

The processInputStream is the method to use for supplying a logic for processing the file InputStream. 

| Signature |
|------|
| public void processInputStream(final Consumer<InputStream> process) throws IOException |

## **PathFinderBuilder** Class

The instance of this class is the only one the can create an instance of PathFinder. To create an instance of this class you can do the following and must provide filename to find:

```java
PathFinder.getBuilder(<filename>)
```

### Methods

| Method                                                       |
| ------------------------------------------------------------ |
| public PathFinderBuilder **addPaths**(final List<String> **dirs**) |
| public PathFinderBuilder **addPaths**(final String ... **dirs**) |
| public PathFinder **build**() <span style="color:red">//The only method that creates an instance of PathFinder.</span> |
| public PathFinderBuilder **setFallbackToClassloader**(final boolean **fallbackToCL**) |

#### Parameters

| Parameter    | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| dirs         | The a list or array of directories where to find the file existence. |
| fallbackToCL | If you are using the PathFinder.processInputStream() method, you can use this to also check the ClassLoader for the existence of the file. |

## Sample Usage

### Usage of getFile method

```java
final var pathFinder = PathFinder.getBuilder("test2.txt")
        .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
        .build();
final var file = pathFinder.getFile().get();
System.out.println(file.getAbsolutePath());
```

> This will return the file in dir1 if it exists there otherwise it will return the file in pathfinder directory if it exists.

### Usage of processInputStream method

```java
final var pathFinder = PathFinder.getBuilder("module-info.class")
        .setFallbackToClassloader(true)
        .build();
pathFinder.processInputStream(System.out::println);
```

**Expected Similar Output**

```
java.io.ByteArrayInputStream@4d23015c
```

> This indicates that the module-info.class was found.

[Table of Contents](USER_GUIDE_TOC.md)

