## PathFinder Class
The PathFinder class finds the first existence of the file based on the supplied possible locations.

Optionally, if being used to process the file as InputStream, it provides an option to retrieve the file from ClassLoader.

### Methods

#### getFile() Method

The getFile() method will return the first existence of the file based on the supplied paths using the builder class.

| Signature |
|------|
| public Optional\<File\> getFile() |

#### getInputStream() Method

The getInputStream() method will return the first existence of the file InputStream based on the supplied paths using the builder class. This method can fallback to ClassLoader when indicated using the setFallbackToClassloader method.

| Signature                                                    |
| ------------------------------------------------------------ |
| public Optional\<InputStream\> getInputStream() throws IOException |

#### processInputStream Method

The processInputStream method is used for supplying logic for processing the file InputStream. This method can fallback to ClassLoader when indicated using the setFallbackToClassloader method.

| Signature |
|------|
| public void processInputStream(final Consumer\<InputStream\> process) throws IOException |

## **PathFinderBuilder** Class

The instance of this class is the only one that can create an instance of PathFinder. To create an instance of this class you can do the following and must provide a filename to find:

```java
PathFinder.getBuilder(<filename>)
```

### Methods

| Method                                                       |
| ------------------------------------------------------------ |
| public PathFinderBuilder **addEnvVars**(final List\<String\> **envVars**) |
| public PathFinderBuilder **addEnvVars**(final String ... **envVars**) |
| public PathFinderBuilder **addPaths**(final List\<String\> **dirs**) |
| public PathFinderBuilder **addPaths**(final String ... **dirs**) |
| public PathFinderBuilder **addSysProps**(final List\<String\> **sysProps**) |
| public PathFinderBuilder **addSysProps**(final String ... **sysProps**) |
| public PathFinderBuilder **addFinder**(final Function\<String, File\> **finder**) |
| public PathFinder **build**() <span style="color:red">//The only method that creates an instance of PathFinder.</span> |
| public PathFinderBuilder **setFallbackToClassloader**(final boolean **fallbackToCL**) |

#### Parameters

| Parameter    | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| envVars      | A list or array of environment variables where to find the file. <br />The environment variable must hold a valid directory where to find the file. |
| dirs         | A list or array of directories where to find the file.   |
| sysProps     | A list or array of system properties where to find the file. <br />The system property must hold a valid directory where to find the file. |
| finder       | A function the provides the possible location of the file. |
| fallbackToCL | If you are using the PathFinder.processInputStream() or PathFinder.getInputStream method, you can use this to also check the ClassLoader for the existence of the file. |

## Sample Usage

### Usage of getFile method

```java
final var pathFinder = PathFinder.getBuilder("test2.txt")
        .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
        .build();
final var file = pathFinder.getFile().get();
System.out.println(file.getAbsolutePath());
```

> This will return the file in dir1 if it exists there; otherwise, it will return the file in the pathfinder directory if it exists.

### Usage of getInputStream method

```java
final var pathFinder = PathFinder.getBuilder("test2.txt")
        .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
        .build();
final var inputStream = pathFinder.getInputStream().get();
System.out.println(inputStream);
```

> This will return the file InputStream in dir1 if it exists there; otherwise, it will return the file InputStream in the pathfinder directory if it exists.

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