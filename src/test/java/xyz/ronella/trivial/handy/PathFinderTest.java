package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
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
    public void docTest() throws IOException {
        final var pathFinder = PathFinder.getBuilder("module-info.class")
                .setFallbackToClassloader(true)
                .build();
        pathFinder.processInputStream(System.out::println);
    }
}
