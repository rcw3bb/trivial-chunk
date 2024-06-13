package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

public class OSTypeTest {

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void windows() {
        var osType = OSType.identify();
        assertEquals(OSType.Windows, osType);
    }

    @Test
    @EnabledOnOs({OS.LINUX})
    public void linux() {
        var osType = OSType.identify();
        assertEquals(OSType.Linux, osType);
    }

    @Test
    @EnabledOnOs({OS.MAC})
    public void mac() {
        var osType = OSType.identify();
        assertEquals(OSType.Mac, osType);
    }

    @Test
    @EnabledOnOs({OS.AIX})
    public void aix() {
        var osType = OSType.identify();
        assertEquals(OSType.Linux, osType);
    }

    @Test
    @EnabledOnOs({OS.SOLARIS})
    public void solaris() {
        var osType = OSType.identify();
        assertEquals(OSType.Unknown, osType);
    }

    @Test
    @EnabledOnOs({OS.OTHER})
    public void other() {
        var osType = OSType.identify();
        assertEquals(OSType.Unknown, osType);
    }

    @Test
    public void checkUnix() {
        var osType = OSType.of("Unix");
        assertEquals(OSType.Linux, osType);
    }

    @Test
    public void checkLinux() {
        var osType = OSType.of("Ubuntu Linux");
        assertEquals(OSType.Linux, osType);
    }

    @Test
    public void checkWindows() {
        var osType = OSType.of("Windows 2000");
        assertEquals(OSType.Windows, osType);
    }

    @Test
    public void checkAIX() {
        var osType = OSType.of("AIX");
        assertEquals(OSType.Linux, osType);
    }

    @Test
    public void checkMac() {
        var osType = OSType.of("MacOS");
        assertEquals(OSType.Mac, osType);
    }

    @Test
    public void checkSolaris() {
        var osType = OSType.of("Solaris");
        assertEquals(OSType.Unknown, osType);
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void windowAppDataDir() {
        assertEquals(System.getenv("APPDATA"), OSType.Windows.getAppDataDir().get());
    }

    @Test
    @EnabledOnOs({OS.LINUX})
    public void linuxAppDataDir() {
        assertEquals(System.getenv("HOME") + "/.local",
                OSType.Linux.getAppDataDir().get());
    }

    @Test
    @EnabledOnOs({OS.MAC})
    public void macAppDataDir() {
        assertEquals(System.getenv("HOME") + "/Library/Application Support",
                OSType.Mac.getAppDataDir().get());
    }
    @Test
    public void unknownAppDataDir() {
        assertTrue(OSType.Unknown.getAppDataDir().isEmpty());
    }

}
