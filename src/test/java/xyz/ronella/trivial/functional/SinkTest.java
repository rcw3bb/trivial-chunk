package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SinkTest {

    @Test
    public void drainTo() {
        var sb = new StringBuilder();
        Sink sink1 = () -> sb.append("1");
        Sink sink2 = sink1.drainsTo(() -> sb.append("2"));
        sink2.plummet();

        assertEquals("12", sb.toString());
    }

}
