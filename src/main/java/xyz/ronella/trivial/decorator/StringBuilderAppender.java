package xyz.ronella.trivial.decorator;

import java.util.function.Consumer;

public class StringBuilderAppender {
    private StringBuilder builder;
    private Consumer<StringBuilder> defaultBeforeAppend;
    private Consumer<StringBuilder> defaultAfterAppend;

    public StringBuilderAppender(StringBuilder builder,
                                 Consumer<StringBuilder> defaultBeforeAppend,
                                 Consumer<StringBuilder> defaultAfterAppend) {
        this.builder = builder;
        this.defaultBeforeAppend = defaultBeforeAppend;
        this.defaultAfterAppend = defaultAfterAppend;
    }

    public StringBuilderAppender(StringBuilder builder,
                                 Consumer<StringBuilder> defaultBeforeAppend) {
        this(builder, defaultBeforeAppend, null);
    }

    public StringBuilderAppender(StringBuilder builder) {
        this(builder, null);
    }

    public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend,
                                        Consumer<StringBuilder> afterAppend) {
        if (null!=beforeAppend) {
            beforeAppend.accept(builder);
        }
        else if (null==beforeAppend && null!=defaultBeforeAppend) {
            defaultBeforeAppend.accept(builder);
        }
        builder.append(text);
        if (null!=afterAppend) {
            afterAppend.accept(builder);
        }
        else if (null==afterAppend && null!=defaultAfterAppend) {
            defaultAfterAppend.accept(builder);
        }
        return this;
    }

    public StringBuilderAppender append(String text, Consumer<StringBuilder> beforeAppend) {
        return append(text, beforeAppend, null);
    }

    public StringBuilderAppender append(String text) {
        return append(text, null);
    }
}