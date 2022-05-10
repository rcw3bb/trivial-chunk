package xyz.ronella.trivial.handy;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Run external command.
 *
 * @author Ron Webb
 * @since 2.5.0
 */
public final class CommandRunner {

    /**
     * The ERROR_EXIT_CODE when exception occurred running the command.
     */
    public static final int ERROR_EXIT_CODE = -100;

    /**
     * The default output logic implementation that just simply print the output stream to
     * System.out and error stream to System.err.
     */
    public static final BiConsumer<InputStream, InputStream> DEFAULT_OUTPUT_LOGIC;

    static {
        DEFAULT_OUTPUT_LOGIC = (___output, ___error) -> {
          var outputScanner = new Scanner(___output);
          var errorScanner = new Scanner(___error);

          while (outputScanner.hasNextLine()) {
              System.out.println(outputScanner.nextLine());
          }

          while (errorScanner.hasNextLine()) {
              System.err.println(errorScanner.nextLine());
          }
        };
    }

    private CommandRunner() {
    }

    /**
     * Run the commands received.
     *
     * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
     * @param command              The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommand(Supplier<ProcessBuilder> createProcessBuilder, String ... command) throws NoCommandException {
        return runCommand(createProcessBuilder, DEFAULT_OUTPUT_LOGIC, command);
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param command             The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommand(Consumer<ProcessBuilder> initProcessBuilder, String ... command) throws NoCommandException {
        return runCommand(initProcessBuilder, DEFAULT_OUTPUT_LOGIC, command);
    }
    
    /**
     * Run the commands received.
     *
     * @param command The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommand(String ... command) throws NoCommandException {
        return runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new, command);
    }

    /**
     * Run the commands received.
     *
     * @param command The commands to be executed.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommand(BiConsumer<InputStream, InputStream> outputLogic, String ... command) throws NoCommandException {
        return runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new, outputLogic, command);
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param command The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommand(Consumer<ProcessBuilder> initProcessBuilder,
                                 BiConsumer<InputStream, InputStream> outputLogic, String ... command) throws
            NoCommandException {

        return runCommand(() -> {
            var builder = new ProcessBuilder();
            initProcessBuilder.accept(builder);
            return builder;
        }, outputLogic, command);
    }

    /**
     * Run the commands received.
     *
     * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param command The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws NoCommandException Thrown with no command was passed.
     */
    public static int runCommand(Supplier<ProcessBuilder> createProcessBuilder,
                                 BiConsumer<InputStream, InputStream> outputLogic, String ... command) throws
            NoCommandException {

        try {
            if (command.length==0) {
                throw new NoCommandException();
            }

            var builder = Optional.of(createProcessBuilder.get()).orElse(new ProcessBuilder());
            builder.command(command);

            var process = builder.start();
            try (var output = process.getInputStream();
                 var error = process.getErrorStream()) {
                outputLogic.accept(output, error);
            }

            return process.exitValue();
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
            return ERROR_EXIT_CODE;
        }
    }

}

