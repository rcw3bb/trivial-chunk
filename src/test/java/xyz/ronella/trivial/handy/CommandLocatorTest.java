package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

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
    public void findNullAsString() {
        assertThrows(ObjectRequiredException.class, () -> CommandLocator.locateAsString(null));
    }

    @Test
    public void findNullAsFile() {
        assertThrows(ObjectRequiredException.class, () -> CommandLocator.locateAsFile(null));
    }

    @Test
    public void finderWindows() {
        assertEquals("where", CommandLocator.getFinder(OSType.WINDOWS));
    }

    @Test
    public void finderNonWindows() {
        Arrays.stream(OSType.values()).filter(osType -> osType != OSType.WINDOWS).forEach(osType -> {
            assertEquals("which", CommandLocator.getFinder(osType));
        });
    }

}
