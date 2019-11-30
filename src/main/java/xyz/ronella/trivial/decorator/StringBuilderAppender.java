package xyz.ronella.trivial.decorator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * A decorator for StringBuilder that gives you chance to add pre-append and post-append logic.
 *
 * @author Ron Webb
 * @since 2019-12-01
 */
public class StringBuilderAppender {
    private StringBuilder builder;
    private Consumer<StringBuilder> defaultBeforeAppend;
    private Consumer<StringBuilder> defaultAfterAppend;
    private Lock INSTANCE_LOCK = new ReentrantLock();
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
        this.builder = builder;
        this.defaultBeforeAppend = defaultBeforeAppend;
        this.defaultAfterAppend = defaultAfterAppend;
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
     * Accepts a StringBuilder to decorate.
     *
     * @param builder An instance of StringBuilder.
     */
    public StringBuilderAppender(StringBuilder builder) {
        this(builder, null);
    }

    public StringBuilderAppender threadSafe() {
        this.threadSafe = true;
        return this;
    }

    /**
     * Perform an append operation with pre-append and post-append logic.
     * This will override the default pre-append and post-append logic.
     *
     * @param text The text to be appended.
     * @param beforeAppend The logic to perform before an append.
     * @param afterAppend The logic to perform after an append.
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend,
                                        Consumer<StringBuilder> afterAppend) {

        try {
            if (threadSafe) {
                INSTANCE_LOCK.lock();
            }
            if (null != beforeAppend) {
                beforeAppend.accept(builder);
            } else if (null == beforeAppend && null != defaultBeforeAppend) {
                defaultBeforeAppend.accept(builder);
            }
            builder.append(text);
            if (null != afterAppend) {
                afterAppend.accept(builder);
            } else if (null == afterAppend && null != defaultAfterAppend) {
                defaultAfterAppend.accept(builder);
            }
        }
        finally {
            if (threadSafe) {
                INSTANCE_LOCK.unlock();
            }
        }
        return this;
    }

    /**
     * Perform an append operation with pre-append logic.
     * This will override the default pre-append logic.
     *
     * @param text The text to be appended.
     * @param beforeAppend The logic to perform before an append.
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend) {
        return append(text, beforeAppend, null);
    }

    /**
     * Perform a normal append without any pre-append or post-append logic.
     *
     * @param text The text to be appended.
     * @return An instance of StringBuilderAppender.
     */
    public StringBuilderAppender append(String text) {
        return append(text, null);
    }
}