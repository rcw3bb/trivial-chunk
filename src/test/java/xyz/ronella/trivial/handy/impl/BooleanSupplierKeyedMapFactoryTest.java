package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.AbstractStringKeyedMapLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanSupplierKeyedMapFactoryTest {

    @Test
    public void withDefaultNoValidKey() {
        BooleanSupplierKeyedMapFactory<String> factory = new BooleanSupplierKeyedMapFactory<>(()-> "DEFAULT");
        assertEquals("DEFAULT", factory.get().get());
    }

    @Test
    public void withDefaultValidKey() {
        BooleanSupplierKeyedMapFactory<String> factory = new BooleanSupplierKeyedMapFactory<>(()-> "DEFAULT",
                Map.entry(()-> Boolean.FALSE, ()-> "TEST1"),
                Map.entry(()-> Boolean.TRUE, ()-> "TEST2"));
        assertEquals("TEST2", factory.get().get());
    }

    @Test
    public void withoutDefaultNoValidKey() {
        BooleanSupplierKeyedMapFactory<String> factory = new BooleanSupplierKeyedMapFactory<>(Map.entry(()-> Boolean.FALSE, ()-> "TEST1"));
        assertTrue(factory.get().isEmpty());
    }

    @Test
    public void withoutDefaultValidKey() {
        BooleanSupplierKeyedMapFactory<String> factory = new BooleanSupplierKeyedMapFactory<>(
                Map.entry(()-> Boolean.FALSE, ()-> "TEST1"),
                Map.entry(()-> Boolean.TRUE, ()-> "TEST2"));
        assertEquals("TEST2", factory.get().get());
    }

    @Test
    public void withoutDefaultExistingMultipleValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapFactory<StringBuilder> factory = new BooleanSupplierKeyedMapFactory<>(
                Map.entry(()->Boolean.FALSE, () -> sb.append("TEST1")),
                Map.entry(()->Boolean.FALSE, () -> sb.append("TEST2")),
                Map.entry(()->Boolean.TRUE, () -> sb.append("TEST3")),
                Map.entry(()->Boolean.TRUE, ()-> sb.append("TEST4"))
        );
        assertEquals("TEST3", factory.get().get().toString());
    }
}
