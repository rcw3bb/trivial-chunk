package xyz.ronella.trivial.handy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
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
     * Get the list of files.
     *
     * @return An unmodifiable list of files.
     */
    /* default */ List<File> getFiles() {
        return Collections.unmodifiableList(files);
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
     * @throws IOException Thrown if there's an issue creating an instance of InputStream.
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
        Require.object(filename, "The filename parameter must not be null.");

        return new PathFinderBuilder(filename);
    }

    /**
     * The only class that can create an instance of PathFinder.
     */
    final public static class PathFinderBuilder {

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
            Optional.ofNullable(filename).ifPresent(___filename -> files.add(new File(___filename)));
            return new PathFinder(this);
        }

        /**
         * Adds a list of directories where to find first existence of the filename.
         * @param dirs A list of directories.
         * @return An instance of PathFinderBuilder.
         */
        public PathFinderBuilder addPaths(final List<String> dirs) {
            final var paths = Optional.ofNullable(dirs).orElseGet(List::of).stream()
                    .filter(Objects::nonNull)
                    .map(___dir -> ___dir.split(File.pathSeparator))
                    .map(___dir -> {

                final var dirList = new ArrayList<>(Arrays.asList(___dir));
                dirList.add(filename);
                final var firstEntry = dirList.remove(0);
                return Paths.get(firstEntry, dirList.toArray(new String[]{})).toFile();

            }).toList();

            files.addAll(paths);
            return this;
        }

        /**
         * Adds paths from environment variables where to find the first existence of the filename.
         *
         * @param envVars An array of environment variables.
         * @return An instance of PathFinderBuilder.
         *
         * @since 3.0.0
         */
        public PathFinderBuilder addEnvVars(final String ... envVars) {
            return addEnvVars(Arrays.stream(Optional.ofNullable(envVars)
                    .orElseGet(() -> List.<String>of().toArray(new String[] {}))).toList());
        }

        /**
         * Adds paths from environment variables where to find the first existence of the filename.
         *
         * @param envVars A list of environment variables.
         * @return An instance of PathFinderBuilder.
         *
         * @since 3.0.0
         */
        public PathFinderBuilder addEnvVars(final List<String> envVars) {
            return addPaths(Optional.ofNullable(envVars).orElseGet(List::of).stream()
                    .map(System::getenv)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
        }

        /**
         * Adds paths from system properties where to find the first existence of the filename.
         *
         * @param sysProps An array of system property names.
         * @return An instance of PathFinderBuilder.
         *
         * @since 3.0.0
         */
        public PathFinderBuilder addSysProps(final String ... sysProps) {
            return addSysProps(Arrays.stream(Optional.ofNullable(sysProps)
                    .orElseGet(() -> List.<String>of().toArray(new String[] {}))).toList());
        }

        /**
         * Adds a finder to find the first existence of the filename.
         * @param finder The finder to find the first existence of the filename.
         * @return An instance of PathFinderBuilder.
         *
         * @since 3.2.0
         */
        public PathFinderBuilder addFinder(final Function<String, File> finder) {
            Require.object(finder, "The finder parameter must not be null.");

            Optional.ofNullable(finder.apply(filename)).ifPresent(files::add);
            return this;
        }

        /**
         * Adds paths from system properties where to find the first existence of the filename.
         *
         * @param sysProps A list of system property names.
         * @return An instance of PathFinderBuilder.
         *
         * @since 3.0.0
         */
        public PathFinderBuilder addSysProps(final List<String> sysProps) {
            return addPaths(Optional.ofNullable(sysProps).orElseGet(List::of).stream()
                    .map(System::getProperty)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
        }

        /**
         * Adds an array of directories where to find first existence of the filename.
         * @param dirs A list of directories.
         * @return An instance of PathFinderBuilder.
         */
        public PathFinderBuilder addPaths(final String ... dirs) {
            addPaths(Arrays.asList(Optional.ofNullable(dirs).orElseGet(() -> List.<String>of()
                    .toArray(new String[] {})))
            );
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
