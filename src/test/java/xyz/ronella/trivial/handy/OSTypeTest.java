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
        assertEquals(OSType.WINDOWS, osType);
    }

    @Test
    @EnabledOnOs({OS.LINUX})
    public void linux() {
        var osType = OSType.identify();
        assertEquals(OSType.LINUX, osType);
    }

    @Test
    @EnabledOnOs({OS.MAC})
    public void mac() {
        var osType = OSType.identify();
        assertEquals(OSType.MAC, osType);
    }

    @Test
    @EnabledOnOs({OS.AIX})
    public void aix() {
        var osType = OSType.identify();
        assertEquals(OSType.LINUX, osType);
    }

    @Test
    @EnabledOnOs({OS.SOLARIS})
    public void solaris() {
        var osType = OSType.identify();
        assertEquals(OSType.UNKNOWN, osType);
    }

    @Test
    @EnabledOnOs({OS.OTHER})
    public void other() {
        var osType = OSType.identify();
        assertEquals(OSType.UNKNOWN, osType);
    }

    @Test
    public void checkUnix() {
        var osType = OSType.of("Unix");
        assertEquals(OSType.LINUX, osType);
    }

    @Test
    public void checkLinux() {
        var osType = OSType.of("Ubuntu Linux");
        assertEquals(OSType.LINUX, osType);
    }

    @Test
    public void checkWindows() {
        var osType = OSType.of("Windows 2000");
        assertEquals(OSType.WINDOWS, osType);
    }

    @Test
    public void checkAIX() {
        var osType = OSType.of("AIX");
        assertEquals(OSType.LINUX, osType);
    }

    @Test
    public void checkMac() {
        var osType = OSType.of("MacOS");
        assertEquals(OSType.MAC, osType);
    }

    @Test
    public void checkSolaris() {
        var osType = OSType.of("Solaris");
        assertEquals(OSType.UNKNOWN, osType);
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    public void windowAppDataDir() {
        assertEquals(System.getenv("APPDATA"), OSType.WINDOWS.getAppDataDir().get());
    }

    @Test
    @EnabledOnOs({OS.LINUX})
    public void linuxAppDataDir() {
        assertEquals(System.getenv("HOME") + "/.local",
                OSType.LINUX.getAppDataDir().get());
    }

    @Test
    @EnabledOnOs({OS.MAC})
    public void macAppDataDir() {
        assertEquals(System.getenv("HOME") + "/Library/Application Support",
                OSType.MAC.getAppDataDir().get());
    }
    @Test
    public void unknownAppDataDir() {
        assertTrue(OSType.UNKNOWN.getAppDataDir().isEmpty());
    }

    @Test
    public void windowsByEOL() {
        assertEquals(OSType.WINDOWS, OSType.of(EndOfLine.CRLF));
    }

    @Test
    public void linuxByEOL() {
        assertEquals(OSType.LINUX, OSType.of(EndOfLine.LF));
    }

    @Test
    public void macByEOL() {
        assertEquals(OSType.MAC, OSType.of(EndOfLine.CR));
    }

    @Test
    public void ofNullOS() {
        assertThrows(ObjectRequiredException.class, ()->OSType.of((String) null));
    }

    @Test
    public void ofNullEOL() {
        assertThrows(ObjectRequiredException.class, ()->OSType.of((EndOfLine) null));
    }

}
