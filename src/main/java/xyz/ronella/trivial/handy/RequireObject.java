package xyz.ronella.trivial.handy;

/**
 * RequireObject for Require class.
 * @param object The object to test for nullity.
 * @param message The message to use when null.
 *
 * @author Ron Webb
 * @since 2.17.0
 */
public record RequireObject(Object object, String message) {

    /**
     * Create an instance of RequireObject without a message.
     * @param object The object to be tested for nullity.
     */
    public RequireObject(final Object object) {
        this(object, null);
    }
}
