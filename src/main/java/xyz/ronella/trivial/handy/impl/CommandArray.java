package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.decorator.ListAdder;
import xyz.ronella.trivial.handy.ICommandArray;
import xyz.ronella.trivial.handy.Require;
import xyz.ronella.trivial.handy.RequireObject;

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

        lstAddrCommand.addWhen(program).when(___ -> null!=program);
        lstAddrCommand.addAllWhen(progArgs).when(___-> !progArgs.isEmpty());
        lstAddrCommand.addWhen(command).when(___ -> null!=command);
        lstAddrCommand.addAllWhen(arguments).when(___-> !arguments.isEmpty());
        lstAddrCommand.addAllWhen(zArguments).when(___-> !zArguments.isEmpty());

        return lstCommand.toArray(new String[] {});
    }

    /**
     * Wrap the string command to become an instance of command array.
     *
     * @param delim   The delimiter regex to use to split the command.
     * @param command The command to wrap.
     * @return An instance of CommandArray.
     * @since 2.18.0
     */
    public static CommandArray wrap(final String delim, final String command) {
        Require.objects(new RequireObject(delim, "The delim parameter cannot be null"));

        final var splitCommand = command.split(delim);
        return wrap(Arrays.stream(splitCommand).toList());
    }

    /**
     * Wrap a list of string command to become an instance of command array.
     *
     * @param command The command to wrap.
     * @return An instance of CommandArray.
     * @since 2.22.0
     */
    public static CommandArray wrap(final List<String> command) {
        final var builder = getBuilder();

        for (int index = 0; index < command.size(); index++) {
            if (index == 0) {
                builder.setCommand(command.get(index));
            } else {
                builder.addArg(command.get(index));
            }
        }

        return builder.build();
    }

    /**
     * Wrap the string command to become an instance of command array.
     *
     * @param command The command to wrap.
     * @return An instance of CommandArray.
     * @since 2.18.0
     */
    public static CommandArray wrap(final String command) {
        return wrap(" ", command);
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
         *
         * @return An instance of CommandArray.
         */
        public CommandArray build() {
            return new CommandArray(this);
        }

        /**
         * Accepts the program name to be executed.
         *
         * @param program The name of the program.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder setProgram(final String program) {
            this.program = program;
            return this;
        }

        /**
         * Accepts the command to be run with the program.
         *
         * @param command The name of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder setCommand(final String command) {
            this.command = command;
            return this;
        }

        /**
         * Accepts the arguments of the program itself.
         *
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addPArgs(final Collection<String> args) {
            this.progArgs.addAll(args);
            return this;
        }

        /**
         * Accepts the arguments of the program itself.
         *
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
         *
         * @param arg The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addPArg(final String arg) {
            this.progArgs.add(arg);
            return this;
        }

        /**
         * Accepts an argument of the program itself.
         *
         * @param when Only apply the method when this returns true.
         * @param arg  The argument of the command.
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
         *
         * @param args The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArgs(final Collection<String> args) {
            this.arguments.addAll(args);
            return this;
        }

        /**
         * Accepts the arguments of the command.
         *
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
         *
         * @param arg The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArg(final String arg) {
            this.arguments.add(arg);
            return this;
        }

        /**
         * Accepts the arguments of the command.
         *
         * @param when Only apply the method when this returns true.
         * @param arg  The argument of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addArg(final BooleanSupplier when, final String arg) {
            if (when.getAsBoolean()) {
                this.arguments.add(arg);
            }
            return this;
        }

        /**
         * Accepts the arguments that must be at the end of the command arguments.
         *
         * @param args The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArgs(final Collection<String> args) {
            this.zArguments.addAll(args);
            return this;
        }

        /**
         * Accepts the arguments that must be at the end of the command arguments.
         *
         * @param when Only apply the method when this returns true.
         * @param args The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArgs(final BooleanSupplier when, final Collection<String> args) {
            if (when.getAsBoolean()) {
                this.zArguments.addAll(args);
            }
            return this;
        }

        /**
         * Accepts an argument that must be at the end of the command arguments.
         *
         * @param arg The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArg(final String arg) {
            this.arguments.add(arg);
            return this;
        }

        /**
         * Accepts an argument that must be at the end of the command arguments.
         *
         * @param when Only apply the method when this returns true.
         * @param arg  The last arguments of the command.
         * @return An instance of CommandArrayBuilder
         */
        public CommandArrayBuilder addZArg(final BooleanSupplier when, final String arg) {
            if (when.getAsBoolean()) {
                this.arguments.add(arg);
            }
            return this;
        }
   }
}
