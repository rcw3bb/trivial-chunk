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
    final private Lock INSTANCE_LOCK = new ReentrantLock();
    private boolean threadSafe;

    /**
     * Decorate the StringBuilder to have a default pre-append and post-append logic.
     *
     * @param builder An instance of StringBuilder.
     * @param defaultBeforeAppend The logic to perform before an append.
     * @param defaultAfterAppend The logic to perform after an append.
     */
    public StringBuilderAppender(StringBuilder builder,
                                 Consumer<StringBuilder> defaultBeforeAppend,
                                 Consumer<StringBuilder> defaultAfterAppend) {
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
    public StringBuilderAppender(String string,
                                 Consumer<StringBuilder> defaultBeforeAppend,
                                 Consumer<StringBuilder> defaultAfterAppend) {
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
    public StringBuilderAppender(Consumer<StringBuilder> defaultBeforeAppend,
                                 Consumer<StringBuilder> defaultAfterAppend) {
        this(new StringBuilder(), defaultBeforeAppend, defaultAfterAppend);
    }

    /**
     * Decorate the StringBuilder to have a default pre-append logic.
     *
     * @param builder An instance of StringBuilder.
     * @param defaultBeforeAppend The logic to perform before an append.
     */
    public StringBuilderAppender(StringBuilder builder,
                                 Consumer<StringBuilder> defaultBeforeAppend) {
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
    public StringBuilderAppender(String string,
                                 Consumer<StringBuilder> defaultBeforeAppend) {
        this(string, defaultBeforeAppend, null);
    }

    /**
     * Creates an instance of StringBuilderAppender with default StringBuilder.
     *
     * @param defaultBeforeAppend The logic to perform before an append.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(Consumer<StringBuilder> defaultBeforeAppend) {
        this(defaultBeforeAppend, null);
    }

    /**
     * Accepts a StringBuilder to decorate.
     *
     * @param builder An instance of StringBuilder.
     */
    public StringBuilderAppender(StringBuilder builder) {
        this(builder, null);
    }

    /**
     * Accepts a StringBuilder to decorate.
     *
     * @param string An instance of String.
     *
     * @since 2.1.0
     */
    public StringBuilderAppender(String string) {
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
        this.threadSafe = true;
        return this;
    }

    private void beforeAndAfterAppendLogic(Consumer<StringBuilder> logic, Consumer<StringBuilder> beforeAppend,
                                           Consumer<StringBuilder> afterAppend) {
        try {
            if (threadSafe) {
                INSTANCE_LOCK.lock();
            }
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
        finally {
            if (threadSafe) {
                INSTANCE_LOCK.unlock();
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
    public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend,
                                        Consumer<StringBuilder> afterAppend) {

        Optional.ofNullable(text).ifPresent( ___text ->
                beforeAndAfterAppendLogic(sb -> sb.append(___text), beforeAppend, afterAppend));

        return this;
    }

    private void conditionLogic(BooleanSupplier condition, Sink logic) {
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
    public StringBuilderAppender append(BooleanSupplier condition, String text, Consumer<StringBuilder> beforeAppend,
                                        Consumer<StringBuilder> afterAppend) {
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
    public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend) {
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
    public StringBuilderAppender append(BooleanSupplier condition, String text, Consumer<StringBuilder> beforeAppend) {
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
    public StringBuilderAppender append(String text) {
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
    public StringBuilderAppender append(BooleanSupplier condition, String text) {
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
    public StringBuilderAppender append(Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend,
                                        Consumer<StringBuilder> afterAppend) {

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
    public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend,
                                        Consumer<StringBuilder> afterAppend) {

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
    public StringBuilderAppender append(Consumer<StringBuilder> updateLogic, Consumer<StringBuilder> beforeAppend) {
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
    public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> updateLogic,
                                        Consumer<StringBuilder> beforeAppend) {
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
    public StringBuilderAppender append(Consumer<StringBuilder> updateLogic) {
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
    public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> updateLogic) {
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
    public StringBuilderAppender append(Consumer<StringBuilder> beforeAppend, Consumer<StringBuilder> afterAppend,
                                        String ... texts) {
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
    public StringBuilderAppender append(Consumer<StringBuilder> beforeAppend, String ... texts) {
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
    public StringBuilderAppender append(String ... texts) {
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
    public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> beforeAppend, Consumer<StringBuilder> afterAppend,
                                        String ... texts) {
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
    public StringBuilderAppender append(BooleanSupplier condition, Consumer<StringBuilder> beforeAppend, String ... texts) {
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
    public StringBuilderAppender append(BooleanSupplier condition, String ... texts) {
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
        try {
            if (threadSafe) {
                INSTANCE_LOCK.lock();
            }
            builder.delete(0, builder.length());
        }
        finally {
            if (threadSafe) {
                INSTANCE_LOCK.unlock();
            }
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
    public StringBuilderAppender replace(CharSequence target, CharSequence replacement) {
        try {
            if (threadSafe) {
                INSTANCE_LOCK.lock();
            }
            var tmpText = builder.toString().replace(target, replacement);
            clear();
            builder.append(tmpText);
        }
        finally {
            if (threadSafe) {
                INSTANCE_LOCK.unlock();
            }
        }
        return this;
    }

}