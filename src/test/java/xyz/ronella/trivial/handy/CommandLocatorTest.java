package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandLocatorTest {
    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void findCMD() {
        final var command = CommandLocator.locateAsFile("cmd");
        assertEquals("C:\\Windows\\System32\\cmd.exe", command.get().getPath());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void findNonExistent() {
        final var command = CommandLocator.locateAsFile("12345");
        assertTrue(command.isEmpty());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void findCMDAsString() {
        final var command = CommandLocator.locateAsString("cmd");
        assertEquals("C:\\Windows\\System32\\cmd.exe", command.get());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void findNonExistentAsString() {
        final var command = CommandLocator.locateAsString("12345");
        assertTrue(command.isEmpty());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void nonWindows() {
        final var original = System.getProperty("os.name");
        try {
            System.setProperty("os.name", "Linux");
            assertThrows(CommandProcessorException.class, () -> CommandLocator.locateAsFile("ls"));
        }
        finally {
            System.setProperty("os.name", original);
            assertEquals(OSType.WINDOWS, OSType.identify());
        }
    }

    @Test
    public void findNullAsString() {
        assertThrows(ObjectRequiredException.class, () -> CommandLocator.locateAsString(null));
    }

    @Test
    public void findNullAsFile() {
        assertThrows(ObjectRequiredException.class, () -> CommandLocator.locateAsFile(null));
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    @SuppressWarnings("unchecked")
    public void commandLocatorFirstPathOnly() {
        final var eol = OSType.WINDOWS.getEOL().eol();
        final var locatorOutput = String.format("C:\\dev\\apps\\graalvm-jdk-21.0.3+7.1\\bin\\java.exe%s" +
                        "C:\\Program Files\\Common Files\\Oracle\\Java\\javapath\\java.exe%s", eol, eol);

        try (var mockCommandProcessor = mockStatic(CommandProcessor.class)) {
            mockCommandProcessor.when(() -> CommandProcessor.process(any(Function.class), any(ICommandArray.class)))
                    .thenReturn(Optional.of(locatorOutput));

            final var command = CommandLocator.locateAsFile("java");
            assertEquals("C:\\dev\\apps\\graalvm-jdk-21.0.3+7.1\\bin\\java.exe", command.get().getPath());
        }
    }

}
