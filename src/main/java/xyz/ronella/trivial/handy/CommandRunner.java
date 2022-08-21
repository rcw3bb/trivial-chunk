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
          final var outputScanner = new Scanner(___output);
          final var errorScanner = new Scanner(___error);

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
     * @throws MissingCommandException Thrown with no command was passed.
     */
    public static int runCommand(final Supplier<ProcessBuilder> createProcessBuilder,final String ... command) throws MissingCommandException {
        return runCommand(createProcessBuilder, DEFAULT_OUTPUT_LOGIC, command);
    }

    /**
     * Run the commands received.
     *
     * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
     * @param commandArray An implementation of ICommandArray.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     *
     * @since 2.10.0
     */
    public static int runCommand(final Supplier<ProcessBuilder> createProcessBuilder, final ICommandArray commandArray) throws MissingCommandException {
        return runCommand(createProcessBuilder, DEFAULT_OUTPUT_LOGIC, commandArray.getCommand());
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param command             The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     */
    public static int runCommand(final Consumer<ProcessBuilder> initProcessBuilder, final String ... command) throws MissingCommandException {
        return runCommand(initProcessBuilder, DEFAULT_OUTPUT_LOGIC, command);
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param commandArray An implementation of ICommandArray.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     *
     * @since 2.10.0
     */
    public static int runCommand(final Consumer<ProcessBuilder> initProcessBuilder, final ICommandArray commandArray) throws MissingCommandException {
        return runCommand(initProcessBuilder, DEFAULT_OUTPUT_LOGIC, commandArray);
    }

    /**
     * Run the commands received.
     *
     * @param commandArray An implementation of ICommandArray.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     *
     * @since 2.10.0
     */
    public static int runCommand(final ICommandArray commandArray) throws MissingCommandException {
        return runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new, commandArray.getCommand());
    }
    
    /**
     * Run the commands received.
     *
     * @param command The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     */
    public static int runCommand(final String ... command) throws MissingCommandException {
        return runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new, command);
    }

    /**
     * Run the commands received.
     *
     * @param command The commands to be executed.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     */
    public static int runCommand(final BiConsumer<InputStream, InputStream> outputLogic, final String ... command) throws MissingCommandException {
        return runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new, outputLogic, command);
    }

    /**
     * Run the commands received.
     *
     * @param commandArray An implementation of ICommandArray.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     *
     * @since 2.10.0
     */
    public static int runCommand(final BiConsumer<InputStream, InputStream> outputLogic,
                                 final ICommandArray commandArray) throws MissingCommandException {
        return runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new, outputLogic, commandArray);
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param command The commands to be executed.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     */
    public static int runCommand(final Consumer<ProcessBuilder> initProcessBuilder,
                                 final BiConsumer<InputStream, InputStream> outputLogic,
                                 final String ... command) throws
            MissingCommandException {

        return runCommand(() -> {
            final var builder = new ProcessBuilder();
            initProcessBuilder.accept(builder);
            return builder;
        }, outputLogic, command);
    }

    /**
     * Run the commands received.
     *
     * @param initProcessBuilder Must hold the additional initialization logic for ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param commandArray An implementation of ICommandArray.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     *
     * @since 2.10.0
     */
    public static int runCommand(final Consumer<ProcessBuilder> initProcessBuilder,
                                 final BiConsumer<InputStream, InputStream> outputLogic,
                                 final ICommandArray commandArray) throws
            MissingCommandException {

        return runCommand(initProcessBuilder, outputLogic, commandArray.getCommand());

    }

    /**
     * Run the commands received.
     *
     * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
     * @param outputLogic Must hold the logic on dealing with the output and error result.
     * @param commandArray An implementation of ICommandArray.
     * @return The exit code after the completion of the process.
     *
     * @throws MissingCommandException Thrown with no command was passed.
     *
     * @since 2.10.0
     */
    public static int runCommand(final Supplier<ProcessBuilder> createProcessBuilder,
                                 final BiConsumer<InputStream, InputStream> outputLogic,
                                 final ICommandArray commandArray) throws
            MissingCommandException {
        return runCommand(createProcessBuilder, outputLogic, commandArray.getCommand());
    }

        /**
         * Run the commands received.
         *
         * @param createProcessBuilder Manually creates an instance of ProcessBuilder.
         * @param outputLogic Must hold the logic on dealing with the output and error result.
         * @param command The commands to be executed.
         * @return The exit code after the completion of the process.
         *
         * @throws MissingCommandException Thrown with no command was passed.
         */
    public static int runCommand(final Supplier<ProcessBuilder> createProcessBuilder,
                                 final BiConsumer<InputStream, InputStream> outputLogic,
                                 final String ... command) throws
            MissingCommandException {

        try {
            if (command.length==0) {
                throw new MissingCommandException();
            }

            final var builder = Optional.of(createProcessBuilder.get()).orElse(new ProcessBuilder());
            builder.command(command);

            final var process = builder.start();
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

