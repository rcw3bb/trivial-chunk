package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.functional.Sink;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanSupplierKeyedMapExecutorTest {

    @Test
    public void withDefaultNoValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapExecutor executor = new BooleanSupplierKeyedMapExecutor(()-> sb.append("DEFAULT"));
        executor.execute();
        assertEquals("DEFAULT", sb.toString());
    }

    @Test
    public void withDefaultValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapExecutor executor = new BooleanSupplierKeyedMapExecutor(()-> sb.append("DEFAULT"),
                Map.entry(()-> Boolean.FALSE, ()-> sb.append("TEST1")),
                Map.entry(()-> Boolean.TRUE, ()-> sb.append("TEST2")));
        executor.execute();
        assertEquals("TEST2", sb.toString());
    }

    @Test
    public void withoutDefaultNoValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapExecutor executor = new BooleanSupplierKeyedMapExecutor(Map.entry(()-> Boolean.FALSE, ()-> sb.append("TEST1")));
        executor.execute();
        assertEquals("", sb.toString());
    }

    @Test
    public void withoutDefaultExistingValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapExecutor executor = new BooleanSupplierKeyedMapExecutor(
                Map.entry(()->Boolean.TRUE, () -> sb.append("TEST1")), Map.entry(()-> Boolean.FALSE, ()-> sb.append("TEST2")));
        executor.execute();
        assertEquals("TEST1", sb.toString());
    }

    @Test
    public void withoutDefaultExistingMultipleValidKey() {
        var sb = new StringBuilder();
        BooleanSupplierKeyedMapExecutor executor = new BooleanSupplierKeyedMapExecutor(
                Map.entry(()->Boolean.FALSE, () -> sb.append("TEST1")),
                Map.entry(()->Boolean.FALSE, () -> sb.append("TEST2")),
                Map.entry(()->Boolean.TRUE, () -> sb.append("TEST3")),
                Map.entry(()->Boolean.TRUE, ()-> sb.append("TEST4"))
        );
        executor.execute();
        assertEquals("TEST3", sb.toString());
    }

    @Test
    public void mapParamOnly() {
        var sb = new StringBuilder();
        var map = new LinkedHashMap<BooleanSupplier, Sink>();
        map.put(()->Boolean.FALSE, () -> sb.append("TEST1"));
        map.put(()->Boolean.FALSE, () -> sb.append("TEST2"));
        map.put(()->Boolean.TRUE, () -> sb.append("TEST3"));
        map.put(()->Boolean.TRUE, ()-> sb.append("TEST4"));

        BooleanSupplierKeyedMapExecutor executor = new BooleanSupplierKeyedMapExecutor(map);
        executor.execute();
        assertEquals("TEST3", sb.toString());
    }
}
