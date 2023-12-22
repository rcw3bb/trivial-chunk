package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequireTest {

    @Test
    public void onlyParameterNull() {
        String param = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(param);
        });
    }

    @Test
    public void onlyParameterNullWithRequireObject() {
        String param = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param, "Param cannot be null."));
        });
    }

    @Test
    public void nullRequireObjectPrimary() {
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(null);
        });
    }

    @Test
    public void nullRequireObjectSubsequent() {
        String param = "HasValue";
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param, "Param cannot be null."), (RequireObject) null);
        });
    }

    @Test
    public void nullSecondParameter() {
        String param = "HasValue";
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param, "Param cannot be null."), (RequireObject) null);
        });
    }

    @Test
    public void onlyParameterNullWithRequireObjectNoMessage() {
        String param = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param));
        });
    }

    @Test
    public void twoParametersNullWithRequireObject() {
        String param1 = null;
        String param2 = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param1, "param1 cannot be null"),
                    new RequireObject(param2, "param2 cannot be null"));
        });
    }

    @Test
    public void twoParametersNullWithRequireObjectNoMessage() {
        String param1 = null;
        String param2 = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param1), new RequireObject(param2));
        });
    }

    @Test
    public void twoParametersNull() {
        String param1 = null;
        String param2 = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(param1, param2);
        });
    }

    @Test
    public void firstParameterNonNull() {
        String param1 = "param1";
        String param2 = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(param1, param2);
        });
    }

    @Test
    public void firstParameterNonNullWithRequireObject() {
        String param1 = "param1";
        String param2 = null;
        assertThrows(RequireAllException.class, ()-> {
            Require.objects(new RequireObject(param1, "param1 is required"), new RequireObject(param2, "param2 is required"));
        });
    }

    @Test
    public void allParametersNonNull() {
        String param1 = "param1";
        String param2 = "param2";
        assertDoesNotThrow(()-> {
            Require.objects(param1, param2);
        });
    }

    @Test
    public void allParametersNonNullWithRequireObject() {
        String param1 = "param1";
        String param2 = "param2";
        assertDoesNotThrow(()-> {
            Require.objects(new RequireObject(param1, "param1 is required"), new RequireObject(param2, "param2 is required"));
        });
    }
}
