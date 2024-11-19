package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.ObjectRequiredException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testNullConstructor() {
        assertThrows(ObjectRequiredException.class, () -> new MapPutter<>(null));
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
    public void testWhenAllMapSupplierNull() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1", "Two", "2");
        assertThrows(ObjectRequiredException.class, () -> putter.putAllWhen(() -> insertMap).when(null));
    }

    @Test
    public void testWhenAllMapNullTrue() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        final var insertMap = Map.of("One", "1", "Two", "2");
        assertThrows(ObjectRequiredException.class, () -> putter.putAllWhen((Supplier<Map<String, String>>) null)
                .when(___ -> true));
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

    @Test
    public void testWhenKeyLogicNull() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        assertThrows(ObjectRequiredException.class, () -> putter.putWhen((Supplier<String>) null, ()-> "value")
                .when(___ -> true));
    }

    @Test
    public void testWhenValueLogicNull() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        assertThrows(ObjectRequiredException.class, () -> putter.putWhen(() -> "key", (Supplier<String>) null)
                .when(___ -> true));
    }

    @Test
    public void testWhenNull() {
        final var map = new HashMap<String, String>();
        final var putter = new MapPutter<>(map);
        assertThrows(ObjectRequiredException.class, () -> putter.putWhen(() -> "key", () -> "value")
                .when(null));
    }
}
