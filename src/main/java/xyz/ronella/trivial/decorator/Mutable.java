package xyz.ronella.trivial.decorator;

/**
 * A convenience class for wrapping the immutable object and making it replaced within this class.
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
     * Creates an instance of Mutable.
     *
     * @param object The immutable object to carry.
     */
    public Mutable(TYPE_OBJECT object) {
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
    public void set(TYPE_OBJECT object) {
        this.object = object;
    }

}
