package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CommandArrayTest {

    @Test
    public void fullCommand() {
        var expected = new String[] {"Program", "Command", "Args1", "ZArg1"};
        var command = CommandArray.getBuilder()
                .setProgram(expected[0])
                .setCommand(expected[1])
                .addArgs(expected[2])
                .addZArgs(expected[3])
                .build();

        assertArrayEquals(expected, command.getCommand());
    }

    @Test
    public void justProgram() {
        var expected = new String[] {"Program"};
        var command = CommandArray.getBuilder()
                .setProgram(expected[0])
                .build();

        assertArrayEquals(expected, command.getCommand());
    }

    @Test
    public void justCommand() {
        var expected = new String[] {"Command"};
        var command = CommandArray.getBuilder()
                .setCommand(expected[0])
                .build();

        assertArrayEquals(expected, command.getCommand());
    }

    @Test
    public void justArgs() {
        var expected = new String[] {"Arg1", "Arg2"};
        var command = CommandArray.getBuilder()
                .addArgs(expected)
                .build();

        assertArrayEquals(expected, command.getCommand());
    }

    @Test
    public void justZArgs() {
        var expected = new String[] {"Arg1", "Arg2"};
        var command = CommandArray.getBuilder()
                .addZArgs(expected)
                .build();

        assertArrayEquals(expected, command.getCommand());
    }

}
