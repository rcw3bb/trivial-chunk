package xyz.ronella.trivial.test;

import java.util.Optional;

/**
 * A utility class that provides support for detecting if the code is running within GitHub Actions.
 * This class is final and cannot be instantiated.
 */
final class GHActionsSupport {

    /**
     * Private constructor to prevent instantiation.
     */
    private GHActionsSupport() {
    }

    /**
     * Checks if the code is running within GitHub Actions.
     *
     * @return true if the code is running within GitHub Actions, false otherwise.
     */
    public static boolean inGitHubActions() {
        return Optional.ofNullable(System.getenv("IN_GITHUB_ACTIONS")).map(Boolean::valueOf).orElse(false);
    }
}