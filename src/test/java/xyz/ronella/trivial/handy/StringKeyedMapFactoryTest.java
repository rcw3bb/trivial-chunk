package xyz.ronella.trivial.handy;


import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StringKeyedMapFactoryTest {

    @Test
    public void withDefaultNonExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(()-> "DEFAULT");
        assertEquals("DEFAULT", factory.create("TEMP").get());
    }

    @Test
    public void withDefaultExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(()-> "DEFAULT",
                Map.entry("TEST1", ()-> "TEST1"),
                Map.entry("TEST2", ()-> "TEST2"));
        assertEquals("TEST1", factory.create("TEST1").get());
    }

    @Test
    public void withoutDefaultNonExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(Map.entry("TEST1", ()-> "TEST1"));
        assertTrue(factory.create("TEMP").isEmpty());
    }

    @Test
    public void withoutDefaultExistingKey() {
        StringKeyedMapFactory<String> factory = new StringKeyedMapFactory<>(
                Map.entry("TEST1", ()-> "TEST1"), Map.entry("TEST2", ()-> "TEST2"));
        assertEquals("TEST2", factory.create("TEST2").get());
    }

}
