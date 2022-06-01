package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.functional.Sink;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * A decorator for StringBuilder that gives you a chance to add pre-append and post-append logic.
 *
 * @author Ron Webb
 * @since 2019-12-01
 */
public class StringBuilderAppender {
    final private StringBuilder builder;
    final private Consumer<StringBuilder> defaultBeforeAppend;
    final private Consumer<StringBuilder> defaultAfterAppend;
    final private Lock instanceLock = new ReentrantLock();
    private boolean hasLocking;

    /**
     * Decorate the StringBuilder to have a default pre-append and post-append logic.
     *
     * @param builder An instance of StringBuilder.
     * @param defaultBeforeAppend The logic to perform before an append.
     * @param defaultAfterAppend The logic to perform after an append.
     */
    public StringBuilderAppender(final StringBuilder builder,
                                 final Consumer<StringBuilder> defaultBeforeAppend,
                                 final Consumer<StringBuilder> defaultAfterAppend) {
        this.builder = Optional.ofNullable(builder).orElseThrow();
        this.defaultBeforeAppend = defaultBeforeAppend;
        this.defaultAfterAppend = defaultAfterAppend;
    }

    /**
     * Decorate the StringBuilder to have a default pre-append and post-append logic.
     *
     * @param string An instance of String.
     * @param defaultBeforeAppend The logic to perform before an append.
     * @param defaultAfterAppend The logic to perform after an append.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(final String string,
                                 final Consumer<StringBuilder> defaultBeforeAppend,
                                 final Consumer<StringBuilder> defaultAfterAppend) {
        this(new StringBuilder(string), defaultBeforeAppend, defaultAfterAppend);
    }

    /**
     * Creates an instance of StringBuilderAppender with default StringBuilder.
     *
     * @param defaultBeforeAppend The logic to perform before an append.
     * @param defaultAfterAppend The logic to perform after an append.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(final Consumer<StringBuilder> defaultBeforeAppend,
                                 final Consumer<StringBuilder> defaultAfterAppend) {
        this(new StringBuilder(), defaultBeforeAppend, defaultAfterAppend);
    }

    /**
     * Decorate the StringBuilder to have a default pre-append logic.
     *
     * @param builder An instance of StringBuilder.
     * @param defaultBeforeAppend The logic to perform before an append.
     */
    public StringBuilderAppender(final StringBuilder builder,
                                 final Consumer<StringBuilder> defaultBeforeAppend) {
        this(builder, defaultBeforeAppend, null);
    }

    /**
     * Decorate the StringBuilder to have a default pre-append logic.
     *
     * @param string An instance of String.
     * @param defaultBeforeAppend The logic to perform before an append.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(final String string,
                                 final Consumer<StringBuilder> defaultBeforeAppend) {
        this(string, defaultBeforeAppend, null);
    }

    /**
     * Creates an instance of StringBuilderAppender with default StringBuilder.
     *
     * @param defaultBeforeAppend The logic to perform before an append.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(final Consumer<StringBuilder> defaultBeforeAppend) {
        this(defaultBeforeAppend, null);
    }

    /**
     * Accepts a StringBuilder to decorate.
     *
     * @param builder An instance of StringBuilder.
     */
    public StringBuilderAppender(final StringBuilder builder) {
        this(builder, null);
    }

    /**
     * Accepts a StringBuilder to decorate.
     *
     * @param string An instance of String.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(final String string) {
        this(string, null);
    }

    /**
     * Creates an instance of StringBuilderAppender with default StringBuilder.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender() {
        this((Consumer<StringBuilder>) null);
    }

    /**
     * Make the appending task thread safe.
     *
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender threadSafe() {
        this.hasLocking = true;
        return this;
    }

    private void beforeAndAfterAppendLogic(final Consumer<StringBuilder> logic,
                                           final Consumer<StringBuilder> beforeAppend,
                                           final Consumer<StringBuilder> afterAppend) {
        try(var ___ = new CloseableLock(instanceLock, this::isThreadSafe)) {
            if (null != beforeAppend) {
                beforeAppend.accept(builder);
            } else if (null != defaultBeforeAppend) {
                defaultBeforeAppend.accept(builder);
            }
            logic.accept(builder);
            if (null != afterAppend) {
                afterAppend.accept(builder);
            } else if (null != defaultAfterAppend) {
                defaultAfterAppend.accept(builder);
            }
        }
    }

    /**
     * Perform an append operation with pre-append and post-append logic.
     * This will override the default pre-append and post-append logic.
     *
     * @param text The text to be appended.
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     *
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender append(final String text, final Consumer<StringBuilder> beforeAppend,
                                        final Consumer<StringBuilder> afterAppend) {

        Optional.ofNullable(text).ifPresent( ___text ->
                beforeAndAfterAppendLogic(sb -> sb.append(___text), beforeAppend, afterAppend));

        return this;
    }

    private void conditionLogic(final BooleanSupplier condition, final Sink logic) {
        Optional.ofNullable(condition).ifPresent(___condition -> {
            if (___condition.getAsBoolean()) {
                logic.plummet();
            }
        });
    }

    /**
     * Perform an append operation with pre-append and post-append logic.
     * This will override the default pre-append and post-append logic.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param text The text to be appended.
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final String text,
                                        final Consumer<StringBuilder> beforeAppend,
                                        final Consumer<StringBuilder> afterAppend) {
        conditionLogic(condition, ()-> append(text, beforeAppend, afterAppend));
        return this;
    }

    /**
     * Perform an append operation with pre-append logic.
     * This will override the default pre-append logic.
     *
     * @param text The text to be appended.
     * @param beforeAppend The logic to perform before an append.
     *
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender append(final String text, final Consumer<StringBuilder> beforeAppend) {
        return append(text, beforeAppend, null);
    }


    /**
     * Perform an append operation with pre-append logic.
     * This will override the default pre-append logic.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param text The text to be appended.
     * @param beforeAppend The logic to perform before an append.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final String text,
                                        final Consumer<StringBuilder> beforeAppend) {
        conditionLogic(condition, ()-> append(text, beforeAppend));
        return this;
    }

    /**
     * Perform a normal append without any pre-append or post-append logic.
     *
     * @param text The text to be appended.
     *
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender append(final String text) {
        return append(text, null);
    }

    /**
     * Perform a normal append without any pre-append or post-append logic.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param text The text to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final String text) {
        conditionLogic(condition, ()-> append(text));
        return this;
    }

    /**
     * String representation of the internal StringBuilder that the decorator is holding.
     * The one that passed in the constructor or the one generated automatically.
     *
     * @return The string representation.
     *
     * @since 2.0.0
     */
    @Override
    public String toString() {
        return builder.toString();
    }

    /**
     * Access the internal StringBuilder that the decorator is holding.
     *
     * @return An instance of StringBuilder
     *
     * @since 2.0.0
     */
    public StringBuilder getStringBuilder() {
        return builder;
    }

    /**
     * Ability to append using your own custom logic that this decorator cannot handle.
     *
     * @param updateLogic Must hold the custom logic for appending.
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final Consumer<StringBuilder> updateLogic,
                                        final Consumer<StringBuilder> beforeAppend,
                                        final Consumer<StringBuilder> afterAppend) {

        Optional.ofNullable(updateLogic).ifPresent(logic -> beforeAndAfterAppendLogic(sb -> logic.accept(builder), beforeAppend, afterAppend));

        return this;
    }

    /**
     * Ability to append using your own custom logic that this decorator cannot handle.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param updateLogic Must hold the custom logic for appending.
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final Consumer<StringBuilder> updateLogic,
                                        final Consumer<StringBuilder> beforeAppend,
                                        final Consumer<StringBuilder> afterAppend) {

        conditionLogic(condition, ()-> append(updateLogic, beforeAppend, afterAppend));
        return this;
    }

    /**
     * Ability to append using your own custom logic that this decorator cannot handle.
     *
     * @param updateLogic Must hold the custom logic for appending.
     * @param beforeAppend The logic to perform before an append.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final Consumer<StringBuilder> updateLogic,
                                        final Consumer<StringBuilder> beforeAppend) {
        return append(updateLogic, beforeAppend, (Consumer<StringBuilder>) null);
    }

    /**
     * Ability to append using your own custom logic that this decorator cannot handle.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param updateLogic Must hold the custom logic for appending.
     * @param beforeAppend The logic to perform before an append.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final Consumer<StringBuilder> updateLogic,
                                        final Consumer<StringBuilder> beforeAppend) {
        conditionLogic(condition, ()-> append(updateLogic, beforeAppend));
        return this;
    }

    /**
     * Ability to append using your own custom logic that this decorator cannot handle.
     *
     * @param updateLogic Must hold the custom logic for appending.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final Consumer<StringBuilder> updateLogic) {
        return append(updateLogic, (Consumer<StringBuilder>) null);
    }

    /**
     * Ability to append using your own custom logic that this decorator cannot handle.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param updateLogic Must hold the custom logic for appending.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.0.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final Consumer<StringBuilder> updateLogic) {
        conditionLogic(condition, ()-> append(updateLogic));
        return this;
    }

    /**
     * Perform an append operation with pre-append and post-append logic.
     * This will soverride the default pre-append and post-append logic.
     *
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     * @param texts The array of texts to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender append(final Consumer<StringBuilder> beforeAppend,
                                        final Consumer<StringBuilder> afterAppend,
                                        final String ... texts) {
        Optional.ofNullable(texts).ifPresent(___texts -> {
            Arrays.asList(___texts).forEach(___text -> {
                append(___text, beforeAppend, afterAppend);
            });
        });

        return this;
    }

    /**
     * Perform an append operation with pre-append logic.
     * This will override the default pre-append logic.
     *
     * @param beforeAppend The logic to perform before an append.
     * @param texts The array of texts to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender append(final Consumer<StringBuilder> beforeAppend, final String ... texts) {
        return append(beforeAppend, null, texts);
    }

    /**
     * Perform a normal append without any pre-append or post-append logic.
     *
     * @param texts The array of texts to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender append(final String ... texts) {
        return append((Consumer<StringBuilder>) null, texts);
    }

    /**
     * Perform an append operation with pre-append and post-append logic.
     * This will override the default pre-append and post-append logic.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     * @param texts The array of texts to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final Consumer<StringBuilder> beforeAppend,
                                        final Consumer<StringBuilder> afterAppend,
                                        final String ... texts) {
        Optional.ofNullable(texts).ifPresent(___texts -> {
            Arrays.asList(___texts).forEach(___text -> {
                append(condition, ___text, beforeAppend, afterAppend);
            });
        });

        return this;
    }

    /**
     * Perform an append operation with pre-append logic.
     * This will override the default pre-append logic.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param beforeAppend The logic to perform before an append.
     * @param texts The array of texts to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final Consumer<StringBuilder> beforeAppend,
                                        final String ... texts) {
        return append(condition, beforeAppend, null, texts);
    }

    /**
     * Perform a normal append without any pre-append or post-append logic.
     *
     * @param condition An implementation of BooleanSupplier that must return true to perform any append.
     * @param texts The array of texts to be appended.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender append(final BooleanSupplier condition, final String ... texts) {
        return append(condition, null, texts);
    }

    /**
     * Clears the content of the target string builder
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.7.0
     */
    public StringBuilderAppender clear() {
        try(var ___ = new CloseableLock(instanceLock, this::isThreadSafe)) {
            builder.delete(0, builder.length());
        }
        return this;
    }

    /**
     * Replace part of StringBuilder
     * @param target The target string to replacement.
     * @param replacement The replacement of the target.
     *
     * @return An instance of StringBuilderAppender.
     *
     * @since 2.8.0
     */
    public StringBuilderAppender replace(final CharSequence target, final CharSequence replacement) {
        try(var ___ = new CloseableLock(instanceLock, this::isThreadSafe)) {
            final var tmpText = builder.toString().replace(target, replacement);
            clear();
            builder.append(tmpText);
        }
        return this;
    }

    private boolean isThreadSafe() {
        return hasLocking;
    }

}