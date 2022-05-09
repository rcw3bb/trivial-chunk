package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.functional.NoOperation;

import java.io.*;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Run external commands.
 *
 * @author Ron Webb
 * @since 2.5.0
 */
public final class CommandRunner {

    /**
     * The ERROR_EXIT_CODE when exception occurred running the command.
     */
    public static final int ERROR_EXIT_CODE = -100;

    private CommandRunner() {
    }

    /**
     * Run the commands received.
     *
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param commands    The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommands(BiConsumer<InputStream, InputStream> outputLogic, String... commands) throws NoCommandException {
        return runCommands(NoOperation.supplier(), outputLogic, commands);
    }

    /**
     * Run the commands received.
     *
     * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
     * @param commands             The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommands(Supplier<ProcessBuilder> createProcessBuilder, String... commands) throws NoCommandException {
        return runCommands(createProcessBuilder, NoOperation.biConsumer(), commands);
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param commands             The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommandsWithPB(Consumer<ProcessBuilder> initProcessBuilder, String... commands) throws NoCommandException {
        return runCommandsWithPB(initProcessBuilder, NoOperation.biConsumer(), commands);
    }
    
    /**
     * Run the commands received.
     *
     * @param commands The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommands(String... commands) throws NoCommandException {
        return runCommands(NoOperation.supplier(), commands);
    }

    /**
     *Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param commands The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommandsWithPB(Consumer<ProcessBuilder> initProcessBuilder,
                                 BiConsumer<InputStream, InputStream> outputLogic, String... commands) throws
            NoCommandException {

        return runCommands(() -> {
            var builder = new ProcessBuilder();
            initProcessBuilder.accept(builder);
            return builder;
        }, outputLogic, commands);
    }

    /**
     * Run the commands received.
     *
     * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param commands The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommands(Supplier<ProcessBuilder> createProcessBuilder,
                                 BiConsumer<InputStream, InputStream> outputLogic, String ... commands) throws
            NoCommandException {

        try {
            if (commands.length==0) {
                throw new NoCommandException();
            }

            var builder = Optional.of(createProcessBuilder.get()).orElse(new ProcessBuilder());
            builder.command(commands);

            var process = builder.start();
            try (var output = process.getInputStream();
                 var error = process.getErrorStream()) {
                outputLogic.accept(output, error);
            }

            return process.exitValue();
        }
        catch(IOException ioe) {
            ioe.printStackTrace(System.err);
            return ERROR_EXIT_CODE;
        }
    }
}

