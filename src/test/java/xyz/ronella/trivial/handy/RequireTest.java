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
    public void allParametersNonNull() {
        String param1 = "param1";
        String param2 = "param2";
        assertDoesNotThrow(()-> {
            Require.objects(param1, param2);
        });
    }

    @Test
    public void allParametersNonNullUsingAll() {
        String param1 = "param1";
        String param2 = "param2";
        assertDoesNotThrow(()-> {
            Require.all(param1, param2);
        });
    }
}
