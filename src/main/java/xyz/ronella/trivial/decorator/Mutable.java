package xyz.ronella.trivial.decorator;

/**
 * A convenience class that accepts an immutable object and making it possible to be replaced within itself.
 * This means keeping the reference of this class intact while changing the immutable object it carries.
 *
 * @param <TYPE_OBJECT> The type of immutable object.
 *
 * @author Ron Webb
 *
 * @since 2.9.0
 */
public class Mutable<TYPE_OBJECT> {

    private TYPE_OBJECT object;

    /**
     * Create an instance of Mutable.
     * @since 2.19.0
     */
    public Mutable() {
        // Allow the creation without an object to carry.
    }

    /**
     * Creates an instance of Mutable.
     *
     * @param object The immutable object to carry.
     */
    public Mutable(final TYPE_OBJECT object) {
        this.object = object;
    }

    /**
     * Access the immutable object.
     *
     * @return The immutable object.
     */
    public TYPE_OBJECT get() {
        return object;
    }

    /**
     * Replace the current immutable object.
     *
     * @param object The new immutable object.
     */
    public void set(final TYPE_OBJECT object) {
        this.object = object;
    }

}
