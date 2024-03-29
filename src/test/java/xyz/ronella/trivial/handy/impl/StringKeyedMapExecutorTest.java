package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.functional.Sink;
import xyz.ronella.trivial.handy.AbstractStringKeyedMapLogic;

import java.util.HashMap;
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
    public void withDefaultOnMapKey() {
        var logics = new HashMap<String, Sink>();
        var sb = new StringBuilder();
        StringKeyedMapExecutor executor = new StringKeyedMapExecutor(logics, ()-> sb.append("DEFAULT"));
        executor.execute("TEMP");
        assertTrue(logics.containsKey(AbstractStringKeyedMapLogic.DEFAULT_LOGIC));
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

    @Test
    public void mapParamOnly() {
        var sb = new StringBuilder();
        var map = new HashMap<String, Sink>();
        map.put("TEST1", () -> sb.append("TEST1"));
        map.put("TEST2", ()-> sb.append("TEST2"));

        StringKeyedMapExecutor executor = new StringKeyedMapExecutor(map);
        executor.execute("TEST2");
        assertEquals("TEST2", sb.toString());
    }

}
