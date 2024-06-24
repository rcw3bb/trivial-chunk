package xyz.ronella.trivial.functional.impl;

import java.util.function.Consumer;

/**
 * A consumer that appends a delimiter to the StringBuilder if it is not empty.
 *
 * @param <T> The type of the delimiter.
 *
 * @author Ron Webb
 * @since 2.20.0
 */
public class StringBuilderDelim<T> implements Consumer<StringBuilder> {

    private final T delim;

    /**
     * Constructor.
     *
     * @param delim The delimiter to append.
     */
    public StringBuilderDelim(final T delim) {
        this.delim = delim;
    }

    @Override
    public void accept(final StringBuilder stringBuilder) {
        if (!stringBuilder.isEmpty()) {
            stringBuilder.append(delim);
        }
    }
}
