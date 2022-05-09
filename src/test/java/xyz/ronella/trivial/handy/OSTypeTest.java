package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
