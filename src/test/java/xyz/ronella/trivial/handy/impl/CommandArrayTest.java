package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CommandArrayTest {

    @Test
    public void fullCommand() {
        var expected = new String[] {"Program", "PArgs1", "Command", "Args1", "ZArg1"};
        var command = CommandArray.getBuilder()
                .setProgram(expected[0])
                .addPArg(expected[1])
                .setCommand(expected[2])
                .addArg(expected[3])
                .addZArg(expected[4])
                .build();

        assertArrayEquals(expected, command.getCommand());
    }

    @Test
    public void fullCommandAsList() {
        var expected = List.of("Program", "PArgs1", "Command", "Args1", "ZArg1");
        var command = CommandArray.getBuilder()
                .setProgram(expected.get(0))
                .addPArg(expected.get(1))
                .setCommand(expected.get(2))
                .addArg(expected.get(3))
                .addZArg(expected.get(4))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommandAsList().toArray());
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
        var expected = List.of("Arg1", "Arg2");
        var command = CommandArray.getBuilder()
                .addArgs(expected)
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justArgWhenTrue() {
        var expected = List.of("Arg1");
        var command = CommandArray.getBuilder()
                .addArg(() -> true, expected.get(0))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justArgWhenFalse() {
        var expected = Collections.emptyList();
        var command = CommandArray.getBuilder()
                .addArg(() -> false, "Arg1")
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justArgsWhenTrue() {
        var expected = List.of("Arg1", "Arg2");
        var command = CommandArray.getBuilder()
                .addArgs(() -> true, expected)
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justArgsWhenFalse() {
        var expected = Collections.emptyList();
        var command = CommandArray.getBuilder()
                .addArgs(() -> false, List.of("Arg1", "Arg2"))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justZArgs() {
        var expected = List.of("Arg1", "Arg2");
        var command = CommandArray.getBuilder()
                .addZArgs(expected)
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justZArgWhenTrue() {
        var expected = List.of("Arg1");
        var command = CommandArray.getBuilder()
                .addZArg(() -> true, expected.get(0))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justZArgWhenFalse() {
        var expected = Collections.emptyList();
        var command = CommandArray.getBuilder()
                .addZArg(() -> false, "Arg1")
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justZArgsWhenTrue() {
        var expected = List.of("Arg1", "Arg2");
        var command = CommandArray.getBuilder()
                .addZArgs(() -> true, expected)
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justZArgsWhenFalse() {
        var expected = Collections.emptyList();
        var command = CommandArray.getBuilder()
                .addZArgs(() -> false, List.of("Arg1", "Arg2"))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }


    @Test
    public void justPArgs() {
        var expected = List.of("Arg1", "Arg2");
        var command = CommandArray.getBuilder()
                .addPArgs(expected)
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justPArgWhenTrue() {
        var expected = List.of("Arg1");
        var command = CommandArray.getBuilder()
                .addPArg(() -> true, expected.get(0))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justPArgWhenFalse() {
        var expected = Collections.emptyList();
        var command = CommandArray.getBuilder()
                .addPArg(() -> false, "Arg1")
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justPArgsWhenTrue() {
        var expected = List.of("Arg1", "Arg2");
        var command = CommandArray.getBuilder()
                .addPArgs(() -> true, expected)
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }

    @Test
    public void justPArgsWhenFalse() {
        var expected = Collections.emptyList();
        var command = CommandArray.getBuilder()
                .addPArgs(() -> false, List.of("Arg1", "Arg2"))
                .build();

        assertArrayEquals(expected.toArray(), command.getCommand());
    }


}
