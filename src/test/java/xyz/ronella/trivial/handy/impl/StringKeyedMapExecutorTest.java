package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StringKeyedMapExecutorTest {

    @Test
    public void withDefaultNonExistingKey() {
        var sb = new StringBuilder();
        StringKeyedMapExecutor executor = new StringKeyedMapExecutor(()-> sb.append("DEFAULT"));
        executor.execute("TEMP");
        assertEquals("DEFAULT", sb.toString());
    }

    @Test
    public void withDefaultExistingKey() {
        var sb = new StringBuilder();
        StringKeyedMapExecutor executor = new StringKeyedMapExecutor(()-> sb.append("DEFAULT"),
                Map.entry("TEST1", ()-> sb.append("TEST1")),
                Map.entry("TEST2", ()-> sb.append("TEST2")));
        executor.execute("TEST1");
        assertEquals("TEST1", sb.toString());
    }

    @Test
    public void withoutDefaultNonExistingKey() {
        var sb = new StringBuilder();
        StringKeyedMapExecutor executor = new StringKeyedMapExecutor(Map.entry("TEST1", ()-> sb.append("TEST1")));
        executor.execute("TEST");
        assertEquals("", sb.toString());
    }

    @Test
    public void withoutDefaultExistingKey() {
        var sb = new StringBuilder();
        StringKeyedMapExecutor executor = new StringKeyedMapExecutor(
                Map.entry("TEST1", () -> sb.append("TEST1")), Map.entry("TEST2", ()-> sb.append("TEST2")));
        executor.execute("TEST2");
        assertEquals("TEST2", sb.toString());
    }

}
