package xyz.ronella.trivial.handy;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;

/**
 * The implementation of this interface can be used to change the behaviour of RegExMatcher.
 *
 * @author Ron Webb
 * @since 2.11.0
 */
public interface IMatcherConfig {

    /**
     * Must return the Pattern flags to use.
     * @return The Pattern flags
     */
    int getPatternFlags();

    /**
     * The logic to be used for matching the regex.
     * @return The matching logic.
     */
    Function<Matcher, Boolean> getMatchLogic();

    /**
     * The logic executed when the pattern was found.
     * @return The found logic.
     */
    Consumer<Matcher> getMatchFoundLogic();

    /**
     * The logic executed when the pattern was not found.
     * @return The not found logic
     */
    Consumer<Matcher> getNoMatchFoundLogic();

    /**
     * The logic for capturing exception RuntimeException.
     * @return The exception logic.
     */
    Consumer<RuntimeException> getExceptionLogic();

}
