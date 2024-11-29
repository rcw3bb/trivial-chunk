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
            System.getenv("APPDATA"),
            Boolean.FALSE,
            "where"),
    /**
     * Indicates that the OS type is Linux.
     */
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    LINUX(EndOfLine.LF,
            System.getenv("HOME") == null ? null : System.getenv("HOME") + "/.local",
            Boolean.TRUE,
            "which"),

    /**
     * Indicates that the OS type is Unix.
     */
    UNIX(EndOfLine.LF,
            System.getenv("HOME") == null ? null : System.getenv("HOME") + "/.local",
            Boolean.TRUE,
            "which"),

    /**
     * Indicates that the OS type is Mac.
     */
    MAC(EndOfLine.CR,
            System.getenv("HOME") == null ? null : System.getenv("HOME") + "/Library/Application Support",
            Boolean.TRUE,
            "which"),

    /**
     * Indicates that the OS type is Solaris.
     */
    SOLARIS(EndOfLine.LF,
            System.getenv("HOME"),
            Boolean.TRUE,
            "which"),
    /**
     * Indicates that the OS type cannot be determined.
     */
    UNKNOWN(EndOfLine.SYSTEM, null, null, null);

    private final EndOfLine eol;
    private final String appDataDir;
    private final Boolean posix;
    private final String cmdLocator;

    OSType(final EndOfLine eol, final String appDataDir, final Boolean posix, final String cmdLocator) {
        this.eol = eol;
        this.appDataDir = appDataDir;
        this.posix = posix;
        this.cmdLocator = cmdLocator;
    }

    /**
     * The end of line associated with the OS.
     * @return An instance of EndOfLine.
     */
    public EndOfLine getEOL() {
        return eol;
    }

    /**
     * The OS is POSIX compliant.
     * @return true if the OS is POSIX compliant.
     *
     * @since 3.1.0
     */
    public Optional<Boolean> isPosix() {
        return Optional.ofNullable(posix);
    }

    /**
     * The command locator associated with the OS.
     * @return The command locator.
     *
     * @since 3.1.0
     */
    public Optional<String> getCmdLocator() {
        return Optional.ofNullable(cmdLocator);
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
            if (lowerOsName.contains("mac") || lowerOsName.contains("darwin") || lowerOsName.contains("osx")) {
                return ___osType == MAC;
            }
            else if (lowerOsName.contains("win")) {
                return ___osType == WINDOWS;
            }
            else if (lowerOsName.contains("linux") || lowerOsName.contains("nux")) {
                return ___osType == LINUX;
            }
            else if (lowerOsName.contains("nix") || lowerOsName.contains("aix") || lowerOsName.contains("freebsd")) {
                return ___osType == UNIX;
            }
            else if (lowerOsName.contains("sunos") || lowerOsName.contains("solaris")) {
                return ___osType == SOLARIS;
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
