package xyz.ronella.trivial.handy;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

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
    WINDOWS(EndOfLine.CRLF,
            System.getenv("APPDATA")),
    /**
     * Indicates that the OS type is Linux.
     */
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    LINUX(EndOfLine.LF,
            System.getenv("HOME") == null ? null : System.getenv("HOME") + "/.local"),
    /**
     * Indicates that the OS type is Mac.
     */
    MAC(EndOfLine.CR,
            System.getenv("HOME") == null ? null : System.getenv("HOME") + "/Library/Application Support"),
    /**
     * Indicates that the OS type cannot be determined.
     */
    UNKNOWN(EndOfLine.SYSTEM, null);

    private final EndOfLine eol;
    private final String appDataDir;

    OSType(final EndOfLine eol, final String appDataDir) {
        this.eol = eol;
        this.appDataDir = appDataDir;
    }

    /**
     * The end of line associated with the OS.
     * @return An instance of EndOfLine.
     */
    public EndOfLine getEOL() {
        return eol;
    }

    /**
     * The application data directory associated with the OS.
     * @return The application data directory.
     *
     * @since 2.19.0
     */
    public Optional<String> getAppDataDir() {
        return Optional.ofNullable(appDataDir);
    }

    /**
     * Identifies the current OS where the code is running.
     *
     * @return An instance of OSType.
     */
    public static OSType identify() {
        final var osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        return of(osName);
    }

    /**
     * Identifies the OS type based on the OS name.
     *
     * @param osName The OS name.
     * @return An instance of OSType.
     *
     * @since 2.19.0
     */
    @SuppressWarnings("PMD.ShortMethodName")
    public static OSType of(final String osName) {
        Require.object(osName);
        return Arrays.stream(values()).filter(___osType-> {
            final var lowerOsName = osName.toLowerCase(Locale.ROOT);
            if (lowerOsName.contains("win")) {
                return ___osType == WINDOWS;
            }
            else if (lowerOsName.contains("mac")) {
                return ___osType == MAC;
            }
            else if (lowerOsName.contains("nux") || lowerOsName.contains("nix") || lowerOsName.contains("aix")) {
                return ___osType == LINUX;
            }
            return false;
        }).findFirst().orElse(UNKNOWN);
    }

    /**
     * Identifies the OS type based on the end of line.
     *
     * @param eol The end of line.
     * @return An instance of OSType.
     *
     * @since 2.20.0
     */
    @SuppressWarnings("PMD.ShortMethodName")
    public static OSType of(final EndOfLine eol) {
        Require.object(eol);

        return switch (eol) {
            case CRLF -> WINDOWS;
            case LF -> LINUX;
            case CR -> MAC;
            default -> UNKNOWN;
        };
    }
}
