package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.handy.impl.MatcherConfig;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A handy class to find a RegEx pattern from a text.
 *
 * @author Ron Webb
 * @since 2.5.0
 */
final public class RegExMatcher {

    private RegExMatcher() {}

    /**
     * Matching the pattern in text based on the configuration.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param config An implementation of IRegExMatcherConfig dictates how the match will behave.
     * @return An instance of Matcher.
     *
     * @since 2.11.0
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public static Matcher match(final String pattern, final String text, final IMatcherConfig config) {
        Require.objects(new RequireObject(pattern, "pattern cannot be null"),
                new RequireObject(text, "text cannot be null"),
                new RequireObject(config, "config cannot be null"));

        final var compiledPattern = Pattern.compile(pattern);
        final var matcher = compiledPattern.matcher(text);
        try {
            final var matchLogicOption = Optional.ofNullable(config.getMatchLogic()).
                    orElse(Matcher::find);

            if (matchLogicOption.apply(matcher)) {
                config.getMatchFoundLogic().accept(matcher);
            } else {
                config.getNoMatchFoundLogic().accept(matcher);
            }
        }
        catch (RuntimeException exception) {
            config.getExceptionLogic().accept(exception);
        }
        return matcher;
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher find(final String pattern, final String text) {
        return match(pattern, text, MatcherConfig.getBuilder().setMatchLogic(Matcher::find).build());
    }

    /**
     * Find the RegEx from a text with pattern flags.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param flags The pattern flags to use.
     * @return An instance of Matcher.
     *
     * @since 2.11.0
     */
    public static Matcher find(final String pattern, final String text, final int flags) {
        return match(pattern, text, MatcherConfig.getBuilder().setMatchLogic(Matcher::find).build(flags));
    }

    /**
     * A convenience method for the find matching.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     *
     * @since 2.11.0
     */
    public static Matcher find(final String pattern, final String text,
                               final Consumer<Matcher> matchFoundLogic,
                               final Consumer<Matcher> noMatchFoundLogic) {
        return match(pattern, text, MatcherConfig.getBuilder().setMatchLogic(Matcher::find)
                .setMatchFoundLogic(matchFoundLogic).setNoMatchFoundLogic(noMatchFoundLogic).build());
    }

    /**
     * A convenience method for the find matching.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     *
     * @since 2.11.0
     */
    public static Matcher find(final String pattern, final String text,
                               final Consumer<Matcher> matchFoundLogic) {
        return match(pattern, text, MatcherConfig.getBuilder().setMatchLogic(Matcher::find)
                .setMatchFoundLogic(matchFoundLogic).setNoMatchFoundLogic(NoOperation.consumer())
                .build());
    }

    /**
     * A convenience method for the find matching with pattern flags.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param flags The Pattern flags to use.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     *
     * @since 2.11.0
     */
    public static Matcher find(final String pattern, final String text, final int flags,
                               final Consumer<Matcher> matchFoundLogic,
                               final Consumer<Matcher> noMatchFoundLogic) {
        return match(pattern, text, MatcherConfig.getBuilder().setMatchLogic(Matcher::find)
                .setMatchFoundLogic(matchFoundLogic).setNoMatchFoundLogic(noMatchFoundLogic)
                .build(flags));
    }

    /**
     * A convenience method for the find matching with pattern flags.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param flags The Pattern flags to use.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     *
     * @since 2.11.0
     */
    public static Matcher find(final String pattern, final String text, final int flags,
                               final Consumer<Matcher> matchFoundLogic) {
        return match(pattern, text, MatcherConfig.getBuilder().setMatchLogic(Matcher::find)
                .setMatchFoundLogic(matchFoundLogic).setNoMatchFoundLogic(NoOperation.consumer()).build(flags));
    }

}
