package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.handy.Require;

import java.io.File;
import java.util.Optional;
import java.util.function.Supplier;


/**
 * The Nomen class is the class that manages the file name operations.
 *
 * @since 2.19.0
 * @author Ron Webb
 */
public class Nomen {

    private final File file;

    /**
     * Creates an instance of a Nomen.
     * @param file An instance of File.
     */
    public Nomen(final File file) {
        this.file = file;
    }

    /**
     * Extract the filename without the extension.
     * @return The filename without the extension.
     */
    public Optional<String> getFilename() {
        Require.objects(file);

        final var fileName = file.getName();
        final var dotIndex = fileName.lastIndexOf(".");

        final Supplier<Optional<String>> noDotLogic = () -> Optional.of(dotIndex)
                .filter(___dotIndex -> /* Absence of dot in the filename */ ___dotIndex == -1)
                .map(___dotIndex -> fileName);

        return Optional.of(dotIndex).filter(___dotIndex -> ___dotIndex > 0)
                .map(___dotIndex -> fileName.substring(0, ___dotIndex))
                .or(noDotLogic);
    }

    /**
     * Extract the extension of the file.
     * @return The extension of the file.
     */
    public Optional<String> getExtension() {
        Require.objects(file);

        final var fileName = file.getName();
        final var dotIndex = fileName.lastIndexOf(".");

        final Supplier<Optional<String>> noDotLogic = () -> Optional.of(dotIndex)
                .filter(___dotIndex -> ___dotIndex == -1)
                .map(___dotIndex -> Optional.<String>empty())
                .orElse(Optional.of(fileName.replace(".", "")));

        return Optional.of(dotIndex).filter(___dotIndex -> ___dotIndex > 0)
                .map(___dotIndex -> fileName.substring(___dotIndex + 1))
                .or(noDotLogic);
    }
}
