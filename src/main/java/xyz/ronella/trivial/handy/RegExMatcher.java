package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.Sink;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
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

    private static Matcher matcher(final String pattern, final String text, final Function<Matcher, Boolean> matchLogic,
                                   final Consumer<Matcher> matchFoundLogic, final Consumer<Matcher> noMatchFoundLogic,
                                   final Consumer<RuntimeException> exceptionLogic) {
        final var compiledPattern = Pattern.compile(pattern);
        final var matcher = compiledPattern.matcher(text);
        try {
            final var matchLogicOption = Optional.ofNullable(matchLogic).
                    orElse(Matcher::find);

            if (matchLogicOption.apply(matcher)) {
                matchFoundLogic.accept(matcher);
            } else {
                noMatchFoundLogic.accept(matcher);
            }
        }
        catch (RuntimeException exception) {
            exceptionLogic.accept(exception);
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
        return matcher(pattern, text, Matcher::find,
                NoOperation.consumer(), NoOperation.consumer(), NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher findWithMatchLogic(final String pattern, final String text,
                                             final Consumer<Matcher> matchFoundLogic) {
        return matcher(pattern, text, Matcher::find, matchFoundLogic, NoOperation.consumer(),
                NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher findWithMatchLogic(final String pattern, final String text,
                                             final Consumer<Matcher> matchFoundLogic,
                                             final Consumer<RuntimeException> exceptionLogic) {
        return matcher(pattern, text, Matcher::find, matchFoundLogic, NoOperation.consumer(),
                exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher findWithNoMatchLogic(final String pattern, final String text,
                                               final Consumer<Matcher> matchFoundLogic,
                                               final Consumer<Matcher> noMatchFoundLogic) {
        return matcher(pattern, text, Matcher::find, matchFoundLogic, noMatchFoundLogic,
                NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher findWithNoMatchLogic(final String pattern, final String text,
                                               final Consumer<Matcher> matchFoundLogic,
                                               final Consumer<Matcher> noMatchFoundLogic,
                                               final Consumer<RuntimeException> exceptionLogic) {
        return matcher(pattern, text, Matcher::find, matchFoundLogic, noMatchFoundLogic,
                exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to match.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be used for matching the regex.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher match(final String pattern, final String text, final Function<Matcher, Boolean> matchLogic) {
        return matcher(pattern, text, matchLogic,NoOperation.consumer(), NoOperation.consumer(),
                NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to match.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be used for matching the regex.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher match(final String pattern, final String text, final Function<Matcher, Boolean> matchLogic,
                                final Consumer<RuntimeException> exceptionLogic) {
        return matcher(pattern, text, matchLogic,NoOperation.consumer(), NoOperation.consumer(),
                exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to match.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be used for matching the regex.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher matchWithMatchLogic(final String pattern, final String text,
                                              final Function<Matcher, Boolean> matchLogic,
                                              final Consumer<Matcher> matchFoundLogic) {
        return matcher(pattern, text, matchLogic, matchFoundLogic, NoOperation.consumer(), NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to match.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be used for matching the regex.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher matchWithMatchLogic(final String pattern, final String text,
                                              final Function<Matcher, Boolean> matchLogic,
                                              final Consumer<Matcher> matchFoundLogic,
                                              final Consumer<RuntimeException> exceptionLogic) {
        return matcher(pattern, text, matchLogic, matchFoundLogic, NoOperation.consumer(), exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to match.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be used for matching the regex.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher matchWithNoMatchLogic(final String pattern, final String text,
                                                final Function<Matcher, Boolean> matchLogic,
                                                final Consumer<Matcher> matchFoundLogic,
                                                final Consumer<Matcher> noMatchFoundLogic) {
        return matcher(pattern, text, matchLogic, matchFoundLogic, noMatchFoundLogic, NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to match.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be used for matching the regex.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @since 2.6.0
     */
    public static Matcher matchWithNoMatchLogic(final String pattern, final String text,
                                                final Function<Matcher, Boolean> matchLogic,
                                                final Consumer<Matcher> matchFoundLogic,
                                                final Consumer<Matcher> noMatchFoundLogic,
                                                final Consumer<RuntimeException> exceptionLogic) {
        return matcher(pattern, text, matchLogic, matchFoundLogic,
                noMatchFoundLogic, exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be executed for matching the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @deprecated Use matchWithNoMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text,
                                       final Function<Matcher, Boolean> matchLogic,
                                       final Consumer<Matcher> matchFoundLogic,
                                       final Sink noMatchFoundLogic, final Consumer<RuntimeException> exceptionLogic) {
        return matcher(pattern, text, matchLogic, matchFoundLogic, (___matcher) -> noMatchFoundLogic.plummet(), exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @deprecated Use findWithNoMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text, final Consumer<Matcher> matchFoundLogic,
                                       final Sink noMatchFoundLogic, final Consumer<RuntimeException> exceptionLogic) {
        return matchByRegEx(pattern, text, null, matchFoundLogic, noMatchFoundLogic, exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     *
     * @deprecated Use findWithNoMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text, final Consumer<Matcher> matchFoundLogic,
                                       final Sink noMatchFoundLogic) {
        return matchByRegEx(pattern, text, matchFoundLogic, noMatchFoundLogic, NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be executed for matching the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     *
     * @deprecated Use matchWithNoMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text,
                                       final Function<Matcher, Boolean> matchLogic,
                                       final Consumer<Matcher> matchFoundLogic, final Sink noMatchFoundLogic) {
        return matchByRegEx(pattern, text, matchLogic, matchFoundLogic, noMatchFoundLogic, NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     *
     * @deprecated Use findWithMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text,
                                       final Consumer<Matcher> matchFoundLogic) {
        return matchByRegEx(pattern, text, matchFoundLogic, NoOperation.sink());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be executed for matching the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     *
     * @deprecated Use matchWithMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text,
                                       final Function<Matcher, Boolean> matchLogic,
                                       final Consumer<Matcher> matchFoundLogic) {
        return matchByRegEx(pattern, text, matchLogic, matchFoundLogic, NoOperation.sink());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @deprecated Use findWithMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text, final Consumer<Matcher> matchFoundLogic,
                                       final Consumer<RuntimeException> exceptionLogic) {
        return matchByRegEx(pattern, text, matchFoundLogic, NoOperation.sink(), exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be executed for matching the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     *
     * @deprecated Use matchWithMatchLogic instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text,
                                       final Function<Matcher, Boolean> matchLogic,
                                       final Consumer<Matcher> matchFoundLogic,
                                       final Consumer<RuntimeException> exceptionLogic) {
        return matchByRegEx(pattern, text, matchLogic, matchFoundLogic, NoOperation.sink(), exceptionLogic);
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @return An instance of Matcher.
     *
     * @deprecated Use find instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text) {
        return matchByRegEx(pattern, text, NoOperation.consumer(), NoOperation.sink());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchLogic The logic to be executed for matching the pattern.
     * @return An instance of Matcher.
     *
     * @deprecated Use match instead.
     */
    @Deprecated
    public static Matcher matchByRegEx(final String pattern, final String text,
                                       final Function<Matcher, Boolean> matchLogic) {
        return matchByRegEx(pattern, text, matchLogic, NoOperation.consumer(), NoOperation.sink());
    }
}
