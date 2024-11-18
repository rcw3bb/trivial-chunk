package xyz.ronella.trivial.handy;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * A handy class for requiring objects.
 *
 * @author Ron Webb
 * @since 2.3.0
 */
public final class Require {

    private Require() {}

    /**
     * A method that checks if all the parameters were not null.
     *
     * @param param The required first parameter to check.
     * @param params The other parameters to check.
     *
     * @throws ObjectRequiredException This will be thrown when at least one of the parameters has thrown an NPE.
     *
     * @deprecated Use the objects method instead.
     */
    @Deprecated
    public static void all(final Object param, final Object ... params) throws ObjectRequiredException {
        objects(param, params);
    }

    /**
     * The method that checks if all the objects were not null.
     *
     * @param obj The required first obj to check.
     * @param objs The other objects to check.
     *
     * @throws ObjectRequiredException This will be thrown when at least one of the parameters has thrown an NPE.
     *
     * @since 2.5.0
     */
    public static void objects(final Object obj, final Object ... objs) throws ObjectRequiredException {
        objects(new RequireObject(obj), Arrays.stream(objs).map(RequireObject::new).toList().toArray(new RequireObject[] {}));
    }

    /**
     * The method that checks if all the objects were not null.
     * Note: It is required the all the parameters are an instance of RequireObject.
     *
     * @param obj The required first obj to check.
     * @param objs The other objects to check.
     *
     * @throws ObjectRequiredException This will be thrown when at least one of the parameters has thrown an NPE.
     *
     * @since 2.17.0
     */
    public static void objects(final RequireObject obj, final RequireObject ... objs) throws ObjectRequiredException {
        try {
            final var MSG_NULL = "RequireObject parameter(s) cannot be null";
            Objects.requireNonNull(obj, MSG_NULL);
            Objects.requireNonNull(objs, MSG_NULL);
            final var objValue = obj.object();
            final var objMsg = Optional.ofNullable(obj.message());
            objMsg.ifPresentOrElse(___message -> Objects.requireNonNull(objValue, ___message),
                    () -> Objects.requireNonNull(objValue));
            for (final var parameter : objs) {
                Objects.requireNonNull(parameter, MSG_NULL);
                final var paramValue = parameter.object();
                final var paramMsg = Optional.ofNullable(parameter.message());
                paramMsg.ifPresentOrElse(___message -> Objects.requireNonNull(paramValue, ___message),
                        () -> Objects.requireNonNull(paramValue));
            }
        }
        catch(NullPointerException npe) {
            throw new ObjectRequiredException(npe);
        }
    }

}
