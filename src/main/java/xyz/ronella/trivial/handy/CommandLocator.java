package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.handy.impl.CommandArray;

import java.io.File;
import java.util.Optional;

/**
 * A utility class for finding available executable in CLI.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
public final class CommandLocator {

    private CommandLocator() {
    }

    /**
     * Return the location of the file as File.
     * @param command The command to locate.
     * @return An optional instance of File.
     */
    public static Optional<File> locateAsFile(final String command) {
        Require.object(command);

        final var osType = OSType.identify();

        return osType.getCmdLocator().map(___finder -> {

            final var cmdArray = CommandArray.wrap(String.format("%s %s", ___finder, command));
            final var outputHandler = CommandProcessor.ProcessOutputHandler.outputToString();
            final var eol = osType.getEOL().eol();
            final var optPath = CommandProcessor.process(outputHandler, cmdArray)
                    .map(___path -> OSType.WINDOWS == osType
                            ? /* Just the first valid entry of where. */ ___path.split(eol)[0]
                            : ___path);

            return optPath.map(File::new).filter(File::exists).orElse(null);

        });
    }

    /**
     * Return the location of the file as string.
     * @param command The command to locate.
     * @return An optional String.
     */
    public static Optional<String> locateAsString(final String command) {
        return locateAsFile(command).map(File::getAbsolutePath);
    }

}
