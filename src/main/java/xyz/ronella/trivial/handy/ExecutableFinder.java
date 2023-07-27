package xyz.ronella.trivial.handy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A utility class for finding available executable in CLI.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
public final class ExecutableFinder {

    private ExecutableFinder() {
    }

    private static String getFinder(final OSType osType) {
        String command = "";
        switch (osType) {
            case Windows:
                command = "where";
                break;
            case Linux:
            default:
                command = "which";
                break;
        }
        return command;
    }

    /**
     * Create an instance of the File based on the discovered filename location.
     * @param executable The executable to find.
     * @return An optional instance of File.
     */
    public static Optional<File> getExecutable(final String executable) {
        final var osType = OSType.identify();
        final var finder = getFinder(osType);
        final var sbFQFN = new StringBuilder();
        Optional<File> execOutput = Optional.empty();;
        try {
            CommandRunner.runCommand((___output, ___error) -> {
                try(var output = new BufferedReader(new InputStreamReader(___output)))  {
                    sbFQFN.append(output.lines().collect(Collectors.joining("\n")));
                }
                catch (IOException exception) {
                    //Do nothing.
                }
            }, finder, executable);
            var fqfn = sbFQFN.toString();
            if (fqfn.length() > 0) {
                if (OSType.Windows == osType) {
                    fqfn = fqfn.split("\\r\\n")[0]; //Just the first valid entry of where.
                }
                final File fileExec = new File(fqfn);
                if (fileExec.exists()) {
                    execOutput = Optional.of(fileExec);
                }
            }
        } catch (MissingCommandException e) {
            //Do nothing.
        }
        return execOutput;
    }

}
