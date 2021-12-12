package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.AbstractStringKeyedMapLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringKeyedMapGeneratorTest {
    @Test
    public void withDefaultNonExistingKey() {
        StringKeyedMapGenerator<String> factory = new StringKeyedMapGenerator<>(()-> "DEFAULT");
        assertEquals("DEFAULT", factory.get("TEMP").get());
    }

    @Test
    public void withDefaultOnMapKey() {
        var logics = new HashMap<String, Supplier<String>>();
        StringKeyedMapGenerator<String> factory = new StringKeyedMapGenerator<>(logics, ()-> "DEFAULT");
        factory.get("TEMP");
        assertTrue(logics.containsKey(AbstractStringKeyedMapLogic.DEFAULT_LOGIC));
    }

    @Test
    public void withDefaultExistingKey() {
        StringKeyedMapGenerator<String> factory = new StringKeyedMapGenerator<>(()-> "DEFAULT",
                Map.entry("TEST1", ()-> "TEST1"),
                Map.entry("TEST2", ()-> "TEST2"));
        assertEquals("TEST1", factory.get("TEST1").get());
    }

    @Test
    public void withoutDefaultNonExistingKey() {
        StringKeyedMapGenerator<String> factory = new StringKeyedMapGenerator<>(Map.entry("TEST1", ()-> "TEST1"));
        assertTrue(factory.get("TEMP").isEmpty());
    }

    @Test
    public void withoutDefaultExistingKey() {
        StringKeyedMapGenerator<String> factory = new StringKeyedMapGenerator<>(
                Map.entry("TEST1", ()-> "TEST1"), Map.entry("TEST2", ()-> "TEST2"));
        assertEquals("TEST2", factory.get("TEST2").get());
    }

    @Test
    public void mapParamOnly() {
        var map = new HashMap<String, Supplier<String>>();
        map.put("TEST1", ()-> "TEST1");
        map.put("TEST2", ()-> "TEST2");

        StringKeyedMapGenerator<String> factory = new StringKeyedMapGenerator<>(map);
        assertEquals("TEST2", factory.get("TEST2").get());
    }
}