package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import xyz.ronella.trivial.decorator.Mutable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PathFinderTest {

    @Test
    public void multiPathWithMatchingFirst() {
        final var expectation = new File("src/test/resources/pathfinder/dir1/test.txt");
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
                .build();
        final var file = pathFinder.getFile().get();
        assertEquals(expectation.getAbsolutePath(), file.getAbsolutePath());
    }

    @Test
    public void pathEmptyEnvVars() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addEnvVars().build();
        assertTrue(pathFinder.getFile().isEmpty());
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void pathValidWindowEnvVar() {
        final var pathFinder = PathFinder.getBuilder("System32")
                .addEnvVars("SystemRoot").build();

        final var file = pathFinder.getFile();
        assertTrue(file.isPresent());
        assertTrue(file.get().isDirectory());
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void pathNoneEnvVar() {
        final var pathFinder = PathFinder.getBuilder("System32")
                .addEnvVars("NON_EXISTENT").build();

        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void pathEmptySysProps() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addSysProps().build();
        assertTrue(pathFinder.getFile().isEmpty());
    }

    @Test
    public void pathValidSystemProp() {
        System.setProperty("dir1", "src/test/resources/pathfinder/dir1");

        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addSysProps("dir1").build();

        final var file = pathFinder.getFile();
        assertTrue(file.isPresent());
        assertTrue(file.get().isFile());
    }

    @Test
    public void pathNonExistentSystemProp() {
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addSysProps("non-existent").build();

        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void mixMode() {

        System.setProperty("System32", "C:\\WINDOWS");

        final var pathFinder = PathFinder.getBuilder("System32")
                .addEnvVars("SystemRoot")
                .addSysProps("System32")
                .addPaths("C:\\WINDOWS")
                .build();

        final var files = pathFinder.getFiles();
        assertEquals(4, files.size());
        final var filePaths = files.subList(0,3);
        final var lastEntry = files.get(files.size() - 1);
        assertTrue(filePaths.stream()
                .allMatch(___file -> new File("C:\\WINDOWS\\System32").equals(___file)));
        assertEquals("System32", lastEntry.getName());
    }

    @Test
    public void nullFileOnly() {
        assertThrows(ObjectRequiredException.class, () -> PathFinder.getBuilder(null).build());
    }

    @Test
    public void nonExistentFile() {
        final var pathFinder = PathFinder.getBuilder("test.txt").build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void existingFile() {
        final var pathFinder = PathFinder.getBuilder(".gitignore").build();
        final var file = pathFinder.getFile();
        assertEquals(1, pathFinder.getFiles().size());
        assertTrue(file.isPresent());
        assertTrue(file.get().isFile());
    }

    @Test
    public void nullArrayEnvVars() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addEnvVars((String[]) null).build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void nullListEnvVars() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addEnvVars((List<String>) null).build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void nullArrayPaths() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addPaths((String[]) null).build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void nullListPaths() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addPaths((List<String>) null).build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void nullArraySysProps() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addSysProps((String[]) null).build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void nullListSysProps() {
        final var pathFinder = PathFinder.getBuilder("test.txt").addSysProps((List<String>) null).build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void multiPathWithMatchingSecondUsingArray() {
        final var expectation = new File("src/test/resources/pathfinder/test2.txt");
        final var pathFinder = PathFinder.getBuilder("test2.txt")
                .addPaths("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder")
                .build();
        final var file = pathFinder.getFile().get();
        assertEquals(expectation.getAbsolutePath(), file.getAbsolutePath());
    }

    @Test
    public void multiPathWithMatchingFirstUsingArray() {
        final var expectation = new File("src/test/resources/pathfinder/dir1/test.txt");
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addPaths("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder")
                .build();
        final var file = pathFinder.getFile().get();
        assertEquals(expectation.getAbsolutePath(), file.getAbsolutePath());
    }

    @Test
    public void multiPathWithMatchingSecond() {
        final var expectation = new File("src/test/resources/pathfinder/test2.txt");
        final var pathFinder = PathFinder.getBuilder("test2.txt")
                .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
                .build();
        final var file = pathFinder.getFile().get();
        assertEquals(expectation.getAbsolutePath(), file.getAbsolutePath());
    }

    @Test
    public void noPaths() {
        final var expectation = new File(".gitignore");
        final var pathFinder = PathFinder.getBuilder(".gitignore")
                .addPaths(List.of())
                .build();
        final var file = pathFinder.getFile().get();
        assertEquals(expectation.getAbsolutePath(), file.getAbsolutePath());
    }

    @Test
    public void fileDoesNotExists() throws IOException {
        final var pathFinder = PathFinder.getBuilder("dummy")
                .build();
        final var inputStream = new Mutable<InputStream>(null);
        pathFinder.processInputStream(inputStream::set);
        assertNull(inputStream.get());
    }

    @Test
    public void fileDoesNotExistsWithCL() throws IOException {
        final var pathFinder = PathFinder.getBuilder("dummy")
                .setFallbackToClassloader(true)
                .build();
        final var inputStream = new Mutable<InputStream>(null);
        pathFinder.processInputStream(inputStream::set);
        assertNull(inputStream.get());
    }

    @Test
    public void fileDoesExistsWithCL() throws IOException {
        final var pathFinder = PathFinder.getBuilder("module-info.class")
                .setFallbackToClassloader(true)
                .build();
        final var inputStream = new Mutable<InputStream>(null);
        pathFinder.processInputStream(inputStream::set);
        assertNotNull(inputStream.get());
    }

    @Test
    public void fileISDoesExistsWithCL() throws IOException {
        final var pathFinder = PathFinder.getBuilder("module-info.class")
                .setFallbackToClassloader(true)
                .build();
        final var inputStream = pathFinder.getInputStream();
        assertTrue(inputStream.isPresent());
    }

    @Test
    public void fileDoesExistsInPath() throws IOException {
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addPaths("src/test/resources/pathfinder/dir1")
                .setFallbackToClassloader(true)
                .build();
        final var inputStream = new Mutable<InputStream>(null);
        pathFinder.processInputStream(inputStream::set);
        assertNotNull(inputStream.get());
    }

    @Test
    public void fileISDoesExistsInPath() throws IOException {
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addPaths("src/test/resources/pathfinder/dir1")
                .setFallbackToClassloader(true)
                .build();
        final var files = pathFinder.getFiles();
        final var inputStream = pathFinder.getInputStream();

        assertEquals(2, files.size());
        assertTrue(inputStream.isPresent());
    }

    @Test
    public void docTest1() throws IOException {
        final var pathFinder = PathFinder.getBuilder("test2.txt")
                .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
                .build();
        final var file = pathFinder.getFile().get();
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void docTest2() throws IOException {
        final var pathFinder = PathFinder.getBuilder("module-info.class")
                .setFallbackToClassloader(true)
                .build();
        pathFinder.processInputStream(System.out::println);
    }

    @Test
    public void docTest3() throws IOException {
        final var pathFinder = PathFinder.getBuilder("test2.txt")
                .addPaths(List.of("src/test/resources/pathfinder/dir1", "src/test/resources/pathfinder"))
                .build();
        final var inputStream = pathFinder.getInputStream().get();
        System.out.println(inputStream);
    }

    @Test
    public void addFinderWithValidFinder() {
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addFinder(filename -> new File("src/test/resources/pathfinder/dir1/" + filename))
                .build();
        final var file = pathFinder.getFile();
        assertTrue(file.isPresent());
        assertTrue(file.get().isFile());
    }

    @Test
    public void addFinderWithNullFinder() {
        assertThrows(ObjectRequiredException.class, () ->
            PathFinder.getBuilder("test.txt").addFinder(null).build()
        );
    }

    @Test
    public void addFinderWithNonExistentFile() {
        final var pathFinder = PathFinder.getBuilder("nonexistent.txt")
                .addFinder(filename -> new File("src/test/resources/pathfinder/dir1/" + filename))
                .build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }

    @Test
    public void addFinderWithNullResult() {
        final var pathFinder = PathFinder.getBuilder("test.txt")
                .addFinder(filename -> null)
                .build();
        final var file = pathFinder.getFile();
        assertTrue(file.isEmpty());
    }
}
