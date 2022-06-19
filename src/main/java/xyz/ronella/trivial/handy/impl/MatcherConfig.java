package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.handy.IMatcherConfig;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;

/**
 * The default implementation of IMatcherConfig.
 *
 * @author Ron Webb
 * @since 2.11.0
 */
public final class MatcherConfig implements IMatcherConfig {

    private final int patternFlags;
    private final Function<Matcher, Boolean> matchLogic;
    private final Consumer<Matcher> matchFoundLogic;
    private final Consumer<Matcher> noMatchFoundLogic;
    private final Consumer<RuntimeException> exceptionLogic;

    private MatcherConfig(final MatcherConfigBuilder builder) {
        patternFlags = builder.patternFlags;
        matchLogic = builder.matchLogic;
        matchFoundLogic = builder.matchFoundLogic;
        noMatchFoundLogic = builder.noMatchFoundLogic;
        exceptionLogic = builder.exceptionLogic;
    }

    @Override
    public int getPatternFlags() {
        return patternFlags;
    }

    @Override
    public Function<Matcher, Boolean> getMatchLogic() {
        return matchLogic==null ? NoOperation.function(false): matchLogic;
    }

    @Override
    public Consumer<Matcher> getMatchFoundLogic() {
        return matchFoundLogic==null ? NoOperation.consumer() : matchFoundLogic;
    }

    @Override
    public Consumer<Matcher> getNoMatchFoundLogic() {
        return noMatchFoundLogic==null ? NoOperation.consumer() : noMatchFoundLogic;
    }

    @Override
    public Consumer<RuntimeException> getExceptionLogic() {
        return exceptionLogic==null ? NoOperation.consumer() : exceptionLogic;
    }

    /**
     * Returns an instance of MatcherConfigBuilder
     * @return An instance of MatcherConfigBuilder
     */
    public static MatcherConfigBuilder getBuilder() {
        return new MatcherConfigBuilder();
    }

    /**
     * The only class the can create an instance of MatcherConfig.
     *
     * @since 2.11.0
     */
    public static final class MatcherConfigBuilder {

        private int patternFlags;
        private Function<Matcher, Boolean> matchLogic;
        private Consumer<Matcher> matchFoundLogic;
        private Consumer<Matcher> noMatchFoundLogic;
        private Consumer<RuntimeException> exceptionLogic;

        private MatcherConfigBuilder() {}

        /**
         * Must return how the matching logic will be performed.
         * @param matchLogic The matching logic.
         * @return An instance of MatcherConfigBuilder.
         */
        public MatcherConfigBuilder setMatchLogic(final Function<Matcher, Boolean> matchLogic) {
            this.matchLogic = matchLogic;
            return this;
        }

        /**
         * Must return how the match found logic will be performed.
         * @param matchFoundLogic The match found logic.
         * @return An instance of MatcherConfigBuilder.
         */
        public MatcherConfigBuilder setMatchFoundLogic(final Consumer<Matcher>  matchFoundLogic) {
            this.matchFoundLogic = matchFoundLogic;
            return this;
        }

        /**
         * Must return how the no match found logic will be performed.
         * @param noMatchFoundLogic The no match found logic.
         * @return An instance of MatcherConfigBuilder.
         */
        public MatcherConfigBuilder setNoMatchFoundLogic(final Consumer<Matcher>  noMatchFoundLogic) {
            this.noMatchFoundLogic = noMatchFoundLogic;
            return this;
        }

        /**
         * Must return how the exception logic will be performed.
         * @param exceptionLogic The exception logic.
         * @return An instance of MatcherConfigBuilder.
         */
        public MatcherConfigBuilder setExceptionLogic(final Consumer<RuntimeException> exceptionLogic) {
            this.exceptionLogic = exceptionLogic;
            return this;
        }

        /**
         * Build an instance of MatcherConfig.
         * @return An instance of MatcherConfig.
         */
        public MatcherConfig build() {
            return new MatcherConfig(this);
        }

        /**
         * Build an instance of MatcherConfig with pattern flags.
         * @param patternFlags The pattern flags to use.
         * @return An instance of MatcherConfig.
         */
        public MatcherConfig build(final int patternFlags) {
            this.patternFlags = patternFlags;
            return new MatcherConfig(this);
        }
    }

}
