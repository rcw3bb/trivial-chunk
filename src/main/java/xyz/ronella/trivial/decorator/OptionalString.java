package xyz.ronella.trivial.decorator;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * A class that wraps an Optional instance of type String.
 * @param <T> An instance of String type.
 *
 * @author Ron Webb
 * @since 2.12.0
 */
public class OptionalString {

    final private Optional<String> optional;

    /**
     * Creates an instance of OptionalString.
     * @param optional An instance Optional of type String.
     */
    public OptionalString(final Optional<String> optional) {
        this.optional = optional;
    }

    /**
     * Identifies if optional string is empty.
     * @return Returns true when the string is empty.
     */
    public boolean isEmpty() {
        return optional.isEmpty() || optional.get().isEmpty();
    }

    /**
     * Identifies if the optional string is blank.
     * @return Returns true when the string is blank.
     */
    public boolean isBlank() {
        return optional.isEmpty() || optional.get().isBlank();
    }

    /**
     * If the string value is present and not blank, performs the given action with the value, otherwise does nothing.
     * @param action The action to be performed, if a value is present
     * @throws NullPointerException If value is present and the given action is null
     */
    public void ifPresentNotBlank(final Consumer<String> action) {
        optional.ifPresent(text -> {
            if (!text.isBlank()) {
                action.accept(text);
            }
        });
    }

    /**
     * If the string value is present and not empty, performs the given action with the value, otherwise does nothing.
     * @param action The action to be performed, if a value is present
     * @throws NullPointerException If value is present and the given action is null
     */
    public void ifPresentNotEmpty(final Consumer<String> action) {
        optional.ifPresent(text -> {
            if (!text.isEmpty()) {
                action.accept(text);
            }
        });
    }

    /**
     * If the string value is present and not blank, performs the given action with the value, otherwise performs the given empty-based action.
     * @param action The action to be performed, if a value is present
     * @param emptyAction The empty-based action to be performed, if no value is present
     * @throws NullPointerException If a value is present and the given action is null, or no value is present and the given empty-based action is null.
     */
    public void ifPresentNotBlankOrElse(final Consumer<String> action, final Runnable emptyAction) {
        String text = null;

        if (optional.isPresent() && !optional.get().isBlank()) {
            text = optional.get();
        }

        Optional.ofNullable(text).ifPresentOrElse(action, emptyAction);
    }

    /**
     * If the string value is present and not empty, performs the given action with the value, otherwise performs the given empty-based action.
     * @param action The action to be performed, if a value is present
     * @param emptyAction The empty-based action to be performed, if no value is present
     * @throws NullPointerException If a value is present and the given action is null, or no value is present and the given empty-based action is null.
     */
    public void ifPresentNotEmptyOrElse(final Consumer<String> action, final Runnable emptyAction) {
        String text = null;

        if (optional.isPresent() && !optional.get().isEmpty()) {
            text = optional.get();
        }

        Optional.ofNullable(text).ifPresentOrElse(action, emptyAction);
    }

}
