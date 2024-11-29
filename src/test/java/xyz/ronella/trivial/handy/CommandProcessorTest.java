package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.Mockito;
import xyz.ronella.trivial.decorator.Mutable;
import xyz.ronella.trivial.handy.impl.CommandArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandProcessorTest {
    private ProcessBuilder builder;
    private InputStream output;
    private InputStream error;

    @BeforeEach
    public void beforeMethod() {
        builder = Mockito.mock(ProcessBuilder.class);
        output = Mockito.mock(InputStream.class);
        error = Mockito.mock(InputStream.class);
    }

    @Test
    public void goodExitCode() throws IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        assertEquals(0, CommandProcessor.process(() -> builder, CommandArray.wrap("dummy")).get());
    }

    @Test
    public void badExitCode() throws IOException {
        var process = Mockito.mock(Process.class);
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(1);

        Mockito.when(builder.start()).thenReturn(process);
        assertEquals(1, CommandProcessor.process(()-> builder, CommandArray.wrap("dummy")).get());
    }

    @Test
    public void builderError() throws IOException {
        var mockedBuilder = Mockito.mock(ProcessBuilder.class);
        Mockito.when(mockedBuilder.start()).thenThrow(new IOException());

        assertThrows(CommandProcessorException.class, () -> {
            CommandProcessor.process(()-> mockedBuilder, CommandArray.wrap("dummy")).get();
        });
    }

    @Test
    public void outputToString() throws IOException {
        var process = Mockito.mock(Process.class);
        var outputStream = new ByteArrayInputStream("Mocked output".getBytes());
        Mockito.when(process.getInputStream()).thenReturn(outputStream);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        var output = CommandProcessor.process(() -> builder, CommandProcessor.ProcessOutputHandler.outputToString(),
                CommandArray.wrap("dummy"));

        assertEquals("Mocked output", output.get());
    }

    @Test
    public void outputToStringException() throws IOException {
        var process = Mockito.mock(Process.class);
        var outputStream = new ByteArrayInputStream("Mocked output".getBytes());

        var mockOutputStream = Mockito.spy(outputStream);

        Mockito.doThrow(new IOException()).when(mockOutputStream).close();
        Mockito.when(process.getInputStream()).thenReturn(mockOutputStream);
        Mockito.when(process.getErrorStream()).thenReturn(error);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);

        assertThrows(CommandProcessorException.class, () -> {
            CommandProcessor.process(() -> builder, CommandProcessor.ProcessOutputHandler.outputToString(),
                CommandArray.wrap("dummy"));
        });
    }

    @Test
    public void errorToStringUsingDefault() throws IOException {
        var process = Mockito.mock(Process.class);
        var outputStream = new ByteArrayInputStream("Error output".getBytes());
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(outputStream);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        var output = CommandProcessor.process(() -> builder, CommandProcessor.ProcessOutputHandler.defaultOutputHandler(),
                CommandArray.wrap("dummy"));

        assertEquals(0, output.get());
    }

    @Test
    public void errorToString() throws IOException {
        var process = Mockito.mock(Process.class);
        var eol = OSType.identify().getEOL().eol();
        var mockString = String.format("Mocked output%sSecond line", eol);
        var outputStream = new ByteArrayInputStream(mockString.getBytes());
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(outputStream);
        Mockito.when(process.exitValue()).thenReturn(0);

        Mockito.when(builder.start()).thenReturn(process);
        var output = CommandProcessor.process(() -> builder, CommandProcessor.ProcessOutputHandler.errorToString(),
                CommandArray.wrap("dummy"));

        assertEquals(mockString, output.get());
    }

    @Test
    public void errorToStringException() throws IOException {
        var process = Mockito.mock(Process.class);
        var errorStream = new ByteArrayInputStream("Mocked output".getBytes());
        var mockErrorStream = Mockito.spy(errorStream);

        Mockito.doThrow(new IOException()).when(mockErrorStream).close();
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(mockErrorStream);

        Mockito.when(builder.start()).thenReturn(process);
        assertThrows(CommandProcessorException.class, () -> {
            CommandProcessor.process(() -> builder, CommandProcessor.ProcessOutputHandler.errorToString(),
                CommandArray.wrap("dummy"));
        });
    }

    @Test
    public void initializerTest() throws IOException {
        var process = Mockito.mock(Process.class);
        var outputStream = new ByteArrayInputStream("Mocked output".getBytes());
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(outputStream);
        Mockito.when(process.exitValue()).thenReturn(0);
        Mockito.when(builder.start()).thenReturn(process);

        var output = CommandProcessor.process(() -> builder, Process::toHandle,
                CommandProcessor.ProcessOutputHandler.errorToString(),
                CommandArray.wrap("dummy"));

        Mockito.verify(process, Mockito.times(1)).toHandle();

        assertEquals("Mocked output", output.get());
    }

    @Test
    public void initializerTestNullInitProcess() {
        assertThrows(ObjectRequiredException.class, () ->
            CommandProcessor.process(() -> builder, null, CommandProcessor.ProcessOutputHandler.errorToString(),
                    CommandArray.wrap("dummy"))
        );
    }

    @Test
    public void initializerTestNullOutputHandler() {
        assertThrows(ObjectRequiredException.class, () ->
                CommandProcessor.process(() -> builder, Process::toHandle, null, CommandArray.wrap("dummy")));
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void testingDefaultBehavior() {
        assertEquals(0, CommandProcessor.process(CommandArray.wrap("where cmd")).get());
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void customOutputHandler() {

        final var output = new Mutable<>("");
        CommandProcessor.process(CommandProcessor.ProcessOutputHandler.captureStreams((___output, ___error) -> {
            final var outputScanner = new Scanner(___output);

            while (outputScanner.hasNextLine()) {
                output.set(outputScanner.nextLine());
            }
        }), CommandArray.wrap("where cmd"));

        assertEquals("C:\\Windows\\System32\\cmd.exe", output.get());
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void customStreamHandlerError() throws IOException {
        var process = Mockito.mock(Process.class);
        var eol = OSType.identify().getEOL().eol();
        var mockString = String.format("Mocked output%sSecond line", eol);
        var errorStream = new ByteArrayInputStream(mockString.getBytes());
        var mockErrorStream = Mockito.spy(errorStream);

        Mockito.doThrow(new IOException()).when(mockErrorStream).close();
        Mockito.when(process.getInputStream()).thenReturn(output);
        Mockito.when(process.getErrorStream()).thenReturn(mockErrorStream);

        Mockito.when(builder.start()).thenReturn(process);
        assertThrows(CommandProcessorException.class, () -> {
            CommandProcessor.process(() -> builder, CommandProcessor.ProcessOutputHandler.captureStreams(
                    (___output, ___error) -> {}),
                    CommandArray.wrap("dummy"));
        });
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void initializerTest2() {
        final var output = new Mutable<>("");
        final var process = new Mutable<>("");

        CommandProcessor.process((___process) -> {
            process.set("Processed");
        }, CommandProcessor.ProcessOutputHandler.captureOutputs((___output, ___error) -> {
            output.set(___output);
        }), CommandArray.wrap("where cmd"));

        assertEquals("Processed", process.get());
        assertEquals("C:\\Windows\\System32\\cmd.exe", output.get());
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void initializerTest3() {
        final var process = new Mutable<>("");

        CommandProcessor.process((___process) -> {
            process.set("Processed");
        }, CommandArray.wrap("where cmd"));

        assertEquals("Processed", process.get());
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void initializerTest4() {
        final var process = new Mutable<>("");

        final var output = CommandProcessor.process(ProcessBuilder::new, (___process) -> {
            process.set("Processed");
        }, CommandArray.wrap("where cmd"));

        assertEquals("Processed", process.get());
        assertEquals(0, output.get());
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void docExampleWithoutOutputHandler() {
        final var output = CommandProcessor.process(CommandArray.wrap("where cmd"));
        System.out.println(output.orElse(-1));
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void docExampleToCaptureOutputAsString() {
        final var status = CommandProcessor.process(CommandProcessor.ProcessOutputHandler.outputToString(),
                CommandArray.wrap("where cmd"));
        System.out.println(status.orElse("No output"));
    }

}
