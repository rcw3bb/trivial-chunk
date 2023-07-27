package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExecutableFinderTest {
    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void findCMD() {
        final var command = ExecutableFinder.getExecutable("cmd");
        assertEquals("C:\\Windows\\System32\\cmd.exe", command.get().getPath());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void findNonExistent() {
        final var command = ExecutableFinder.getExecutable("12345");
        assertTrue(command.isEmpty());
    }
}
