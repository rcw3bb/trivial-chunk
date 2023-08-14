package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapPutterTest {

    @Test
    public void testWhenAllMapTrue() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1", "Two", "2");
        putter.putAllWhen(insertMap).when(___ -> true);
        assertEquals(insertMap, map);
    }

    @Test
    public void testWhenAllMapFalse() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1", "Two", "2");
        putter.putAllWhen(insertMap).when(___ -> false);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testWhenAllMapSupplierTrue() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1", "Two", "2");
        putter.putAllWhen(() -> insertMap).when(___ -> true);
        assertEquals(insertMap, map);
    }

    @Test
    public void testWhenKeyValueTrue() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1");
        putter.putWhen("One", "1").when(___ -> true);
        assertEquals(insertMap, map);
    }

    @Test
    public void testWhenKeyValueFalse() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        putter.putWhen("One", "1").when(___ -> false);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testWhenKeySupplierValueTrue() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1");
        putter.putWhen(()-> "One", "1").when(___ -> true);
        assertEquals(insertMap, map);
    }

    @Test
    public void testWhenKeyValueSupplierTrue() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1");
        putter.putWhen("One", ()-> "1").when(___ -> true);
        assertEquals(insertMap, map);
    }
}
