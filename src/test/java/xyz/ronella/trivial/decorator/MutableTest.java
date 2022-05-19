package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class MutableTest {

    @Test
    public void mutableBoolean() {
        var mutable = new Mutable<>(Boolean.TRUE);
        assertTrue(mutable.get());
        Consumer<Boolean> consumer = mutable::set;
        consumer.accept(Boolean.FALSE);
        assertFalse(mutable.get());
    }

    @Test
    public void mutableInteger() {
        var mutable = new Mutable<>(0);
        assertEquals(0, mutable.get());
        Consumer<Integer> consumer = (___value ) -> {
            mutable.set(mutable.get() + ___value);
        };
        consumer.accept(2);
        assertEquals(2, mutable.get());
    }

    @Test
    public void mutableString() {
        var mutable = new Mutable<>("Hello");
        assertEquals("Hello", mutable.get());
        Consumer<String> consumer = (___value ) -> {
            mutable.set(String.format("%s %s", mutable.get(), ___value));
        };
        consumer.accept("World");
        assertEquals("Hello World", mutable.get());
    }

    @Test
    public void nullTest() {
        var mutable = new Mutable<String>(null);
        assertNull(mutable.get());
        Consumer<String> consumer = mutable::set;
        consumer.accept("World");
        assertEquals("World", mutable.get());
    }
}
