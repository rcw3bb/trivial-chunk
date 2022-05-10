package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.Sink;

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
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     */
    public static Matcher findByRegex(String pattern, String text, Consumer<Matcher> matchFoundLogic,
                                      Sink noMatchFoundLogic, Consumer<RuntimeException> exceptionLogic) {
        var compiledPattern = Pattern.compile(pattern);
        var matcher = compiledPattern.matcher(text);
        try {
            if (matcher.find()) {
                matchFoundLogic.accept(matcher);
            } else {
                noMatchFoundLogic.plummet();
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
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param noMatchFoundLogic The logic executed when the pattern was not found.
     * @return An instance of Matcher.
     */
    public static Matcher findByRegex(String pattern, String text, Consumer<Matcher> matchFoundLogic,
                                      Sink noMatchFoundLogic) {
        return findByRegex(pattern, text, matchFoundLogic, noMatchFoundLogic, NoOperation.consumer());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @return An instance of Matcher.
     */
    public static Matcher findByRegex(String pattern, String text, Consumer<Matcher> matchFoundLogic) {
        return findByRegex(pattern, text, matchFoundLogic, NoOperation.sink());
    }

    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @param matchFoundLogic The logic executed when the pattern was found.
     * @param exceptionLogic The logic for capturing exception RuntimeException.
     * @return An instance of Matcher.
     */
    public static Matcher findByRegex(String pattern, String text, Consumer<Matcher> matchFoundLogic,
                                      Consumer<RuntimeException> exceptionLogic) {
        return findByRegex(pattern, text, matchFoundLogic, NoOperation.sink(), exceptionLogic);
    }


    /**
     * Find the RegEx from a text.
     *
     * @param pattern The RegEx pattern to find.
     * @param text The text where to find the pattern.
     * @return An instance of Matcher.
     */
    public static Matcher findByRegex(String pattern, String text) {
        return findByRegex(pattern, text, NoOperation.consumer(), NoOperation.sink());
    }
}
