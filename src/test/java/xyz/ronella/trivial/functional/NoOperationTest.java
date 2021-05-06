package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.decorator.StringBuilderAppender;

import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

public class NoOperationTest {

    @Test
    public void noopSinkTest() {
        var sb = new StringBuilder();
        Sink sink = () -> sb.append("Test");
        sink = NoOperation.sink();
        sink.plummet();

        assertEquals("", sb.toString());
    }

    @Test
    public void noopConsumerTest() {
        var sb = new StringBuilder();
        Consumer<String> consumer = (arg1) -> sb.append(arg1);
        consumer = NoOperation.consumer();
        consumer.accept("Test");

        assertEquals("", sb.toString());
    }

    @Test
    public void noopBiConsumerTest() {
        var sb = new StringBuilderAppender();
        BiConsumer<String, String> biConsumer = (arg1, arg2) -> sb.append(arg1, arg2);
        biConsumer = NoOperation.biConsumer();
        biConsumer.accept("Test1", "Test2");

        assertEquals("", sb.toString());
    }

    @Test
    public void noopSupplierTest() {
        Supplier<String> supplier = NoOperation.supplier();
        assertNull(supplier.get());
    }

    @Test
    public void noopPredicateTrueTest() {
        Predicate<String> predicate = NoOperation.predicate(Boolean.TRUE);
        assertTrue(predicate.test("test"));
    }

    @Test
    public void noopPredicateFalseTest() {
        Predicate<String> predicate = NoOperation.predicate(Boolean.FALSE);
        assertFalse(predicate.test("test"));
    }

    @Test
    public void noopFunctionTest() {
        Function<String, String> function = NoOperation.function();
        assertNull(function.apply("test"));
    }

    @Test
    public void noopBiFunctionTest() {
        BiFunction<String, String, String> biFunction = NoOperation.biFunction();
        assertNull(biFunction.apply("test", "test2`"));
    }

    @Test
    public void noopBiPredicateTrueTest() {
        BiPredicate<String, String> biPredicate = NoOperation.biPredicate(Boolean.TRUE);
        assertTrue(biPredicate.test("test", "test2"));
    }

    @Test
    public void noopBiPredicateFalseTest() {
        BiPredicate<String, String> biPredicate = NoOperation.biPredicate(Boolean.FALSE);
        assertFalse(biPredicate.test("test", "test2"));
    }
}
