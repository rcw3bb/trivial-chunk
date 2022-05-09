package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.Scanner;

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
        assertEquals(0, CommandRunner.runCommands(()-> builder, "dummy"));
    }

    @Test
    public void badExitCode() throws NoCommandException, IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(CommandRunner.ERROR_EXIT_CODE);

        Mockito.when(builder.start()).thenReturn(process);
        assertEquals(CommandRunner.ERROR_EXIT_CODE, CommandRunner.runCommands(()-> builder, "dummy"));
    }

    @Test
    public void noCommand() throws IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(CommandRunner.ERROR_EXIT_CODE);

        Mockito.when(builder.start()).thenReturn(process);
        assertThrows(NoCommandException.class, () -> CommandRunner.runCommands(()-> builder));
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
        CommandRunner.runCommands(() -> builder, (out, err) -> {
            sbOutput.append(new Scanner(out).nextLine());
        } ,"dummy");

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
        CommandRunner.runCommands(() -> builder, (out, err) -> {
            sbOutput.append(new Scanner(err).nextLine());
        } ,"dummy");

        assertEquals("Mocked error", sbOutput.toString());
    }

}
