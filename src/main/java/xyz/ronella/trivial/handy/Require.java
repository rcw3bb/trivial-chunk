package xyz.ronella.trivial.handy;

import java.util.Objects;

public class Require {

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
