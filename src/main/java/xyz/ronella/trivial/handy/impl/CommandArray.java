package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.decorator.ListAdder;
import xyz.ronella.trivial.handy.ICommandArray;

import java.util.*;

/**
 * The default implementation of ICommandArray.
 *
 * @author Ron Webb
 * @since 2.10.0
 */
public class CommandArray implements ICommandArray {

    private final String program;
    private final String command;
    private final Set<String> arguments;
    private final Set<String> zArguments;

    private CommandArray(CommandBuilder builder) {
        this.program = builder.program;
        this.command = builder.command;
        this.arguments = builder.arguments;
        this.zArguments = builder.zArguments;
    }

    @Override
    public String[] getCommand() {
        var lstCommand = new ArrayList<String>();
        var lstAddrCommand = new ListAdder<>(lstCommand);

        lstAddrCommand.add(()-> null!=program, program);
        lstAddrCommand.add(()-> null!=command, command);
        lstAddrCommand.addAll(()-> arguments.size() > 0, arguments);
        lstAddrCommand.addAll(()-> zArguments.size() > 0, zArguments);

        return lstCommand.toArray(new String[] {});
    }

    public static CommandBuilder getBuilder() {
        return new CommandBuilder();
    }

    public static final class CommandBuilder {

        public String program;
        public String command;
        public final Set<String> arguments;
        public final Set<String> zArguments;

        public CommandBuilder() {
            arguments = new TreeSet<>();
            zArguments = new TreeSet<>();
        }

        public CommandArray build() {
            return new CommandArray(this);
        }

        public CommandBuilder setProgram(String program) {
            this.program = program;
            return this;
        }

        public CommandBuilder setCommand(String command) {
            this.command = command;
            return this;
        }

        public CommandBuilder addArgs(String ... args) {
            this.arguments.addAll(Arrays.asList(args));
            return this;
        }

        public CommandBuilder addZArgs(String ... zArgs) {
            this.zArguments.addAll(Arrays.asList(zArgs));
            return this;
        }

    }
}
