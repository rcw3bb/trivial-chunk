package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.functional.Sink;
import xyz.ronella.trivial.handy.AbstractStringKeyedMapLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class StringKeyedMapFactoryTest {

    @Test
    public void withDefaultNonExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(()-> "DEFAULT");
        assertEquals("DEFAULT", factory.get("TEMP").get());
    }

    @Test
    public void withDefaultOnMapKey() {
        var logics = new HashMap<String, Supplier<String>>();
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(logics, ()-> "DEFAULT");
        factory.get("TEMP");
        assertTrue(logics.containsKey(AbstractStringKeyedMapLogic.DEFAULT_LOGIC));
    }


    @Test
    public void withDefaultExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(()-> "DEFAULT",
                Map.entry("TEST1", ()-> "TEST1"),
                Map.entry("TEST2", ()-> "TEST2"));
        assertEquals("TEST1", factory.get("TEST1").get());
    }

    @Test
    public void withoutDefaultNonExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(Map.entry("TEST1", ()-> "TEST1"));
        assertTrue(factory.get("TEMP").isEmpty());
    }

    @Test
    public void withoutDefaultExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(
                Map.entry("TEST1", ()-> "TEST1"), Map.entry("TEST2", ()-> "TEST2"));
        assertEquals("TEST2", factory.get("TEST2").get());
    }

}
