package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.decorator.ListAdder;
import xyz.ronella.trivial.handy.ICommandArray;

import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * The default implementation of ICommandArray.
 *
 * @author Ron Webb
 * @since 2.10.0
 */
public final class CommandArray implements ICommandArray {

    private final String program;
    private final List<String> progArgs;
    private final String command;
    private final List<String> arguments;
    private final List<String> zArguments;

    private CommandArray(final CommandArrayBuilder builder) {
        this.program = builder.program;
        this.progArgs = builder.progArgs;
        this.command = builder.command;
        this.arguments = builder.arguments;
        this.zArguments = builder.zArguments;
    }

    /**
     * Generates the command as array.
     * @return The array representation of the command.
     */
    @Override
    public String[] getCommand() {
        final var lstCommand = new ArrayList<String>();
        final var lstAddrCommand = new ListAdder<>(lstCommand);

        lstAddrCommand.add(()-> null!=program, program);
        lstAddrCommand.addAll(()-> !progArgs.isEmpty(), progArgs);
        lstAddrCommand.add(()-> null!=command, command);
        lstAddrCommand.addAll(()-> !arguments.isEmpty(), arguments);
        lstAddrCommand.addAll(()-> !zArguments.isEmpty(), zArguments);

        return lstCommand.toArray(new String[] {});
    }

    /**
     * Return the builder of the CommandArray.
     *
     * @return An instance of CommandArrayBuilder.
     */
    public static CommandArrayBuilder getBuilder() {
        return new CommandArrayBuilder();
    }

    /**
     * The only instance that can create CommandArray.
     */
    public static final class CommandArrayBuilder {

        private String program;
        private final List<String> progArgs;
        private String command;
        private final List<String> arguments;
        private final List<String> zArguments;

        /**
         * Creates an instance of CommandArrayBuilder.
         */
        private CommandArrayBuilder() {
            progArgs = new ArrayList<>();
            arguments = new ArrayList<>();
            zArguments = new ArrayList<>();
        }

        /**
         * The method that can create an instance of CommandArray.
         * @return An instance of CommandArray.
         */
        public CommandArray build() {
            return new CommandArray(this);
        }

        /**
         * Accepts the program name to be executed.
         * @param program The name of the program.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder setProgram(final String program) {
            this.program = program;
            return this;
        }

        /**
         * Accepts the command to be run with the program.
         * @param command The name of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder setCommand(final String command) {
            this.command = command;
            return this;
        }

        /**
         * Accepts the arguments of the program itself.
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addPArgs(final Collection<String> args) {
            this.progArgs.addAll(args);
            return this;
        }

        /**
         * Accepts the arguments of the program itself.
         * @param when Only apply the method when this returns true.
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addPArgs(final BooleanSupplier when, final Collection<String> args) {
            if (when.getAsBoolean()) {
                this.progArgs.addAll(args);
            }
            return this;
        }

        /**
         * Accepts an argument of the program itself.
         * @param arg The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addPArg(final String arg) {
            this.progArgs.add(arg);
            return this;
        }

        /**
         * Accepts an argument of the program itself.
         * @param when Only apply the method when this returns true.
         * @param arg The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addPArg(final BooleanSupplier when, final String arg) {
            if (when.getAsBoolean()) {
                this.progArgs.add(arg);
            }
            return this;
        }

        /**
         * Accepts the arguments of the command.
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArgs(final Collection<String> args) {
            this.arguments.addAll(args);
            return this;
        }

        /**
         * Accepts the arguments of the command.
         * @param when Only apply the method when this returns true.
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArgs(final BooleanSupplier when, final Collection<String> args) {
            if (when.getAsBoolean()) {
                this.arguments.addAll(args);
            }
            return this;
        }

        /**
         * Accepts the arguments of the command.
         * @param arg The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArg(final String arg) {
            this.arguments.add(arg);
            return this;
        }

        /**
         * Accepts the arguments of the command.
         * @param when Only apply the method when this returns true.
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArg(final BooleanSupplier when, final String args) {
            if (when.getAsBoolean()) {
                this.arguments.add(args);
            }
            return this;
        }

        /**
         * Accepts the arguments that must be at the end of the command arguments.
         * @param zArgs The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArgs(final Collection<String> zArgs) {
            this.zArguments.addAll(zArgs);
            return this;
        }

        /**
         * Accepts the arguments that must be at the end of the command arguments.
         * @param when Only apply the method when this returns true.
         * @param zArgs The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArgs(final BooleanSupplier when, final Collection<String> zArgs) {
            if (when.getAsBoolean()) {
                this.zArguments.addAll(zArgs);
            }
            return this;
        }

        /**
         * Accepts an argument that must be at the end of the command arguments.
         * @param zArg The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArg(final String zArg) {
            this.arguments.add(zArg);
            return this;
        }

        /**
         * Accepts an argument that must be at the end of the command arguments.
         * @param when Only apply the method when this returns true.
         * @param zArg The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArg(final BooleanSupplier when, final String zArg) {
            if (when.getAsBoolean()) {
                this.arguments.add(zArg);
            }
            return this;
        }
    }
}
