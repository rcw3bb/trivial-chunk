package xyz.ronella.trivial.handy;

import java.util.Objects;

/**
 * A handy class for requiring objects.
 *
 * @author Ron Webb
 * @since 2.3.0
 */
public class Require {

    /**
     * A method that checks if all the parameters were not null.
     *
     * @param param The required first parameter to check.
     * @param params The other parameters to check.
     *
     * @throws RequireAllException This will be thrown with at least one of the parameters throws an NPE.
     */
    public static void all(Object param, Object ... params) throws RequireAllException {
        try {
            Objects.requireNonNull(param);
            for (var parameter : params) {
                Objects.requireNonNull(parameter);
            }
        }
        catch(NullPointerException npe) {
            throw new RequireAllException();
        }
    }

}
