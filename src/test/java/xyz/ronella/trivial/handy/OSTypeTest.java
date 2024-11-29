package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.Arrays;

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
        assertEquals(OSType.UNIX, osType);
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
        assertEquals(OSType.UNIX, osType);
    }

    @Test
    public void checkFreebsd() {
        var osType = OSType.of("Freebsd");
        assertEquals(OSType.UNIX, osType);
    }

    @Test
    public void checkMac() {
        var osType = OSType.of("MacOS");
        assertEquals(OSType.MAC, osType);
    }

    @Test
    public void checkSolaris() {
        var osType = OSType.of("Solaris");
        assertEquals(OSType.SOLARIS, osType);
    }

    @Test
    public void checkDarwin() {
        var osType = OSType.of("darwin");
        assertEquals(OSType.MAC, osType);
    }

    @Test
    public void checkOSX() {
        var osType = OSType.of("osx");
        assertEquals(OSType.MAC, osType);
    }

    @Test
    public void checkSunOS() {
        var osType = OSType.of("sunos");
        assertEquals(OSType.SOLARIS, osType);
    }

    @Test
    public void checkUnknown() {
        var osType = OSType.of("zzzz");
        assertEquals(OSType.UNKNOWN, osType);
    }

    @Test
    public void nonPosixWindows() {
        assertFalse(OSType.WINDOWS.isPosix().get());
    }

    @Test
    public void posixLinux() {
        assertTrue(OSType.LINUX.isPosix().get());
    }

    @Test
    public void posixMac() {
        assertTrue(OSType.MAC.isPosix().get());
    }

    @Test
    public void posixSolaris() {
        assertTrue(OSType.SOLARIS.isPosix().get());
    }

    @Test
    public void posixUnkown() {
        assertTrue(OSType.UNKNOWN.isPosix().isEmpty());
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
    public void unknownByEOL() {
        assertEquals(OSType.UNKNOWN, OSType.of(EndOfLine.SYSTEM));
    }

    @Test
    public void ofNullOS() {
        assertThrows(ObjectRequiredException.class, ()->OSType.of((String) null));
    }

    @Test
    public void ofNullEOL() {
        assertThrows(ObjectRequiredException.class, ()->OSType.of((EndOfLine) null));
    }

    @Test
    public void cmdLocatorWindows() {
        assertEquals("where", OSType.WINDOWS.getCmdLocator().get());
    }

    @Test
    public void cmdLocatorNonWindows() {
        Arrays.stream(OSType.values()).filter(osType -> osType != OSType.WINDOWS && osType != OSType.UNKNOWN)
                .forEach(osType -> assertEquals("which", osType.getCmdLocator().get()));
    }

    @Test
    public void cmdLocatorUnknown() {
        assertTrue(OSType.UNKNOWN.getCmdLocator().isEmpty());
    }

}
