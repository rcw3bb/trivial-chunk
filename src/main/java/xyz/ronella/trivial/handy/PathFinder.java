package xyz.ronella.trivial.handy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * A class that will find the first existence of a file based on the directories provided.
 *
 * @author Ron Webb
 * @since 2.14.0
 */
final public class PathFinder {

    private final List<File> files;
    private final String filename;
    private final boolean fallbackToCL;

    private PathFinder(final PathFinderBuilder builder) {
        files = builder.files;
        filename = builder.filename;
        fallbackToCL = builder.fallbackToCL;
    }

    /**
     * Find the first existence of the file.
     * @return The first existence of the file.
     */
    public Optional<File> getFile() {
        return files.stream().filter(File::exists).findFirst();
    }

    /**
     * The InputStream of the resolved file. This method can use the ClassLoader as fallback.
     * @param process The logic to process the InputStream.
     * @throws IOException Thrown if there's an issue creating an instance of InputStream.
     */
    public void processInputStream(final Consumer<InputStream> process) throws IOException {
        final var optInputStream = getInputStream();

        if (optInputStream.isPresent()) {
            try(var inputStream = optInputStream.get()) {
                process.accept(inputStream);
            }
        }
    }

    /**
     * Find the first existence of the file as InputStream. This method can use the ClassLoader as fallback.
     * @return The InputStream of the file.
     * @since 2.15.0
     */
    public Optional<InputStream> getInputStream() throws IOException {
        var optInputStream = Optional.<InputStream>empty();

        if (getFile().isPresent()) {
            optInputStream = Optional.of(Files.newInputStream(getFile().get().toPath()));
        } else if (fallbackToCL) {
            optInputStream = Optional.ofNullable(ClassLoader.getSystemResourceAsStream(filename));
        }
        return optInputStream;
    }

    /**
     * The only method that can create an instance of PathFinder.
     * @param filename The filename to find the first existence.
     * @return An instance of PathFinderBuilder.
     */
    public static PathFinderBuilder getBuilder(final String filename) {
        return new PathFinderBuilder(filename);
    }

    /**
     * The only class that can create an instance of PathFinder.
     */
    public static class PathFinderBuilder {

        private final String filename;
        private final List<File> files;
        private boolean fallbackToCL;

        private PathFinderBuilder(final String filename) {
            this.filename = filename;
            this.files = new ArrayList<>();
        }

        /**
         * Build an instance of a PathFinder.
         * @return An instance of PathFinder.
         */
        public PathFinder build() {
            return new PathFinder(this);
        }

        /**
         * Adds a list of directories where to find first existence of the filename.
         * @param dirs A list of directories.
         * @return An instance of PathFinderBuilder.
         */
        public PathFinderBuilder addPaths(final List<String> dirs) {
            final var paths = dirs.stream().map(___dir -> ___dir.split(File.pathSeparator)).map(___dir -> {
                final var dirList = new ArrayList<>(Arrays.asList(___dir));
                dirList.add(filename);
                final var firstEntry = dirList.remove(0);
                return Paths.get(firstEntry, dirList.toArray(new String[]{})).toFile();
            }).collect(Collectors.toList());

            if (paths.isEmpty()) {
                paths.add(new File(filename));
            }

            files.addAll(paths);
            return this;
        }

        /**
         * Adds an array of directories where to find first existence of the filename.
         * @param dirs A list of directories.
         * @return An instance of PathFinderBuilder.
         */
        public PathFinderBuilder addPaths(final String ... dirs) {
            addPaths(Arrays.asList(dirs));
            return this;
        }

        /**
         * Set this to load the file using classloader if it doesn't exist in any of the paths provided.
         * This is only useful when using the PathFinder.processInputStream() method.
         * @param fallbackToCL indicates whether to use the classloader as fallback.
         * @return An instance of PathFinderBuilder.
         */
        public PathFinderBuilder setFallbackToClassloader(final boolean fallbackToCL) {
            this.fallbackToCL = fallbackToCL;
            return this;
        }
    }
}
