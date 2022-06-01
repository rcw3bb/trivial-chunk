package xyz.ronella.trivial.handy;

import java.util.Locale;

/**
 * The enumerator that identifies the OSType.
 *
 * @author Ron Webb
 * @since 2020-05-15
 */
public enum OSType {

    /**
     * Indicates that the OS type is Windows.
     */
    Windows,
    /**
     * Indicates that the OS type is Linux.
     */
    Linux,
    /**
     * Indicates that the OS type is Mac.
     */
    Mac,
    /**
     * Indicates that the OS type cannot be determined.
     */
    Unknown;

    /**
     * Identifies the current OS where the code is running.
     *
     * @return An instance of OSType.
     */
    public static OSType identify() {
        final var osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if (osName.contains("win")) {
            return OSType.Windows;
        }
        else if (osName.contains("mac")) {
            return OSType.Mac;
        }
        else if (osName.contains("nux") || osName.contains("nix") || osName.contains("aix")) {
            return OSType.Linux;
        }
        return OSType.Unknown;
    }
}
