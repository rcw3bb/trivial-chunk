package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanSupplierKeyedMapGeneratorTest {

    @Test
    public void withDefaultNoValidKey() {
        BooleanSupplierKeyedMapGenerator<String> factory = new BooleanSupplierKeyedMapGenerator<>(()-> "DEFAULT");
        assertEquals("DEFAULT", factory.get().get());
    }

    @Test
    public void withDefaultValidKey() {
        BooleanSupplierKeyedMapGenerator<String> factory = new BooleanSupplierKeyedMapGenerator<>(()-> "DEFAULT",
                Map.entry(()-> Boolean.FALSE, ()-> "TEST1"),
                Map.entry(()-> Boolean.TRUE, ()-> "TEST2"));
        assertEquals("TEST2", factory.get().get());
    }

    @Test
    public void withoutDefaultNoValidKey() {
        BooleanSupplierKeyedMapGenerator<String> factory = new BooleanSupplierKeyedMapGenerator<>(Map.entry(()-> Boolean.FALSE, ()-> "TEST1"));
        assertTrue(factory.get().isEmpty());
    }

    @Test
    public void withoutDefaultValidKey() {
        BooleanSupplierKeyedMapGenerator<String> factory = new BooleanSupplierKeyedMapGenerator<>(
                Map.entry(()-> Boolean.FALSE, ()-> "TEST1"),
                Map.entry(()-> Boolean.TRUE, ()-> "TEST2"));
        assertEquals("TEST2", factory.get().get());
    }

    @Test
    public void withoutDefaultExistingMultipleValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapGenerator<StringBuilder> factory = new BooleanSupplierKeyedMapGenerator<>(
                Map.entry(()->Boolean.FALSE, () -> sb.append("TEST1")),
                Map.entry(()->Boolean.FALSE, () -> sb.append("TEST2")),
                Map.entry(()->Boolean.TRUE, () -> sb.append("TEST3")),
                Map.entry(()->Boolean.TRUE, ()-> sb.append("TEST4"))
        );
        assertEquals("TEST3", factory.get().get().toString());
    }

    @Test
    public void mapParamOnly() {
        var sb = new StringBuilder();
        var map = new LinkedHashMap<BooleanSupplier, Supplier<StringBuilder>>();
        map.put(()->Boolean.FALSE, () -> sb.append("TEST1"));
        map.put(()->Boolean.FALSE, () -> sb.append("TEST2"));
        map.put(()->Boolean.TRUE, () -> sb.append("TEST3"));
        map.put(()->Boolean.TRUE, ()-> sb.append("TEST4"));

        BooleanSupplierKeyedMapGenerator<StringBuilder> factory = new BooleanSupplierKeyedMapGenerator<>(map);
        assertEquals("TEST3", factory.get().get().toString());
    }
}
