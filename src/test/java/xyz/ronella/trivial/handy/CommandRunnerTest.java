package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.Mockito;
import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.handy.impl.CommandArray;

import java.io.*;
import java.util.Scanner;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class CommandRunnerTest {

    private ProcessBuilder builder;
    private InputStream output;
    private InputStream error;

    @BeforeEach
    public void beforeMethod() throws IOException {
        builder = Mockito.mock(ProcessBuilder.class);
        output = Mockito.mock(InputStream.class);
        error = Mockito.mock(InputStream.class);
    }

    @Test
    public void goodExitCode() throws NoCommandException, IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        assertEquals(0, CommandRunner.runCommand(()-> builder, "dummy"));
    }

    @Test
    public void badExitCode() throws NoCommandException, IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(CommandRunner.ERROR_EXIT_CODE);

        Mockito.when(builder.start()).thenReturn(process);
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand(()-> builder, "dummy"));
    }

    @Test
    public void badExitCodeICommandArray() throws NoCommandException, IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(CommandRunner.ERROR_EXIT_CODE);

        var command = CommandArray.getBuilder().setCommand("dummy").build();

        Mockito.when(builder.start()).thenReturn(process);
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand(()-> builder, command));
    }

    @Test
    public void noCommand() throws IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(CommandRunner.ERROR_EXIT_CODE);

        Mockito.when(builder.start()).thenReturn(process);
        assertThrows(NoCommandException.class, () -> CommandRunner.runCommand(()-> builder));
    }

    @Test
    public void outputTest() throws IOException, NoCommandException {
        var process = Mockito.mock(Process.class);
        var outputStream = new ByteArrayInputStream("Mocked output".getBytes());
        Mockito.when(process.getInputStream()).thenReturn(outputStream);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        var sbOutput = new StringBuilder();
        CommandRunner.runCommand(() -> builder, (out, err) -> {
            sbOutput.append(new Scanner(out).nextLine());
        } ,"dummy");

        assertEquals("Mocked output", sbOutput.toString());
    }

    @Test
    public void outputTestICommandArray() throws IOException, NoCommandException {
        var process = Mockito.mock(Process.class);
        var outputStream = new ByteArrayInputStream("Mocked output".getBytes());
        var command = CommandArray.getBuilder().setCommand("dummy").build();
        Mockito.when(process.getInputStream()).thenReturn(outputStream);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        var sbOutput = new StringBuilder();
        CommandRunner.runCommand(() -> builder, (out, err) -> {
            sbOutput.append(new Scanner(out).nextLine());
        } ,command);

        assertEquals("Mocked output", sbOutput.toString());
    }

    @Test
    public void errorTest() throws IOException, NoCommandException {
        var process = Mockito.mock(Process.class);
        var errorStream = new ByteArrayInputStream("Mocked error".getBytes());
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(errorStream);
        Mockito.when(process.exitValue()).thenReturn(CommandRunner.ERROR_EXIT_CODE);

        Mockito.when(builder.start()).thenReturn(process);
        var sbOutput = new StringBuilder();
        CommandRunner.runCommand(() -> builder, (out, err) -> {
            sbOutput.append(new Scanner(err).nextLine());
        } ,"dummy");

        assertEquals("Mocked error", sbOutput.toString());
    }

    @Test
    public void ioException() throws NoCommandException {
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand("dummy"));
    }

    @Test
    public void ioExceptionICommandArray() throws NoCommandException {
        var command = CommandArray.getBuilder().setCommand("dummy").build();
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand(command));
    }

    @Test
    public void usingConsumer() throws NoCommandException {
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand(NoOperation.consumer(), "dummy"));
    }

    @Test
    public void usingConsumerICommandArray() throws NoCommandException {
        var command = CommandArray.getBuilder().setCommand("dummy").build();
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand(NoOperation.consumer(), command));
    }

    @Test
    public void usingConsumerWithOutputLogic() throws NoCommandException {
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommand((Supplier<ProcessBuilder>) ProcessBuilder::new,
                NoOperation.biConsumer(), "dummy"));
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void defaultSuccessOutputLogic() throws NoCommandException {
        assertEquals(0, CommandRunner.runCommand(CommandRunner.DEFAULT_OUTPUT_LOGIC, "where", "cmd"));
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void defaultSuccessOutputLogicICommandArray() throws NoCommandException {
        var command = CommandArray.getBuilder().setCommand("where").addArg("cmd").build();
        assertEquals(0, CommandRunner.runCommand(CommandRunner.DEFAULT_OUTPUT_LOGIC, command));
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void defaultErrorOutputLogic() throws NoCommandException {
        assertTrue(CommandRunner.runCommand(CommandRunner.DEFAULT_OUTPUT_LOGIC, "where", "dummy") > 0);
    }
}
