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
     * Get the finder command based on the OS type.
     * @param osType The OS type.
     * @return The finder command.
     */
    /* default */ static String getFinder(final OSType osType) {
        return OSType.WINDOWS == osType ? "where" : "which";
    }

    /**
     * Return the location of the file as File.
     * @param command The command to locate.
     * @return An optional instance of File.
     */
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public static Optional<File> locateAsFile(final String command) {
        Require.object(command);

        final var osType = OSType.identify();
        final var finder = getFinder(osType);
        Optional<File> execOutput = Optional.empty();
        try {
            var fqfn = CommandProcessor.process(CommandProcessor.ProcessOutputHandler.outputToString(),
                    CommandArray.wrap(String.format("%s %s", finder, command))).orElse("");
            if (!fqfn.isEmpty()) {
                if (OSType.WINDOWS == osType) {
                    fqfn = fqfn.split("\\r\\n")[0]; //Just the first valid entry of where.
                }
                final File fileExec = new File(fqfn);
                if (fileExec.exists()) {
                    execOutput = Optional.of(fileExec);
                }
            }
        } catch (CommandProcessorException e) {
            //Do nothing.
        }
        return execOutput;
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
