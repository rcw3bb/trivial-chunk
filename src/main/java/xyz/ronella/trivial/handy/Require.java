package xyz.ronella.trivial.handy;

import java.util.Objects;

/**
 * A handy class for requiring objects.
 *
 * @author Ron Webb
 * @since 2.3.0
 */
public class Require {

    private Require() {}

    /**
     * A method that checks if all the parameters were not null.
     *
     * @param param The required first parameter to check.
     * @param params The other parameters to check.
     *
     * @throws RequireAllException This will be thrown when at least one of the parameters has thrown an NPE.
     *
     * @deprecated Use the objects method instead.
     */
    @Deprecated
    public static void all(Object param, Object ... params) throws RequireAllException {
        objects(param, params);
    }

    /**
     * The method that checks if all the objects were not null.
     *
     * @param obj The required first obj to check.
     * @param objs The other objects to check.
     *
     * @throws RequireAllException This will be thrown when at least one of the parameters has thrown an NPE.
     *
     * @since 2.5.0
     */
    public static void objects(Object obj, Object ... objs) throws RequireAllException {
        try {
            Objects.requireNonNull(obj);
            for (var parameter : objs) {
                Objects.requireNonNull(parameter);
            }
        }
        catch(NullPointerException npe) {
            throw new RequireAllException();
        }
    }

}
