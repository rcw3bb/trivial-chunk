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
    public void noopFunctionOutputTest() {
        Function<String, String> function = NoOperation.function("output");
        assertEquals("output", function.apply("test"));
    }

    @Test
    public void noopFunctionPassThru() {
        Function<String, String> function = NoOperation.functionPassThru();
        assertEquals("output", function.apply("output"));
    }

    @Test
    public void noopBiFunctionTest() {
        BiFunction<String, String, String> biFunction = NoOperation.biFunction();
        assertNull(biFunction.apply("test", "test2`"));
    }

    @Test
    public void noopBiFunctionOutputTest() {
        BiFunction<String, String, String> biFunction = NoOperation.biFunction("output");
        assertEquals("output", biFunction.apply("test", "test2`"));
    }

    @Test
    public void noopBiFunctionArg1PassThru() {
        BiFunction<String, String, String> biFunction = NoOperation.biFunctionArg1PassThru();
        assertEquals("arg1", biFunction.apply("arg1", "arg2`"));
    }

    @Test
    public void noopBiFunctionArg2PassThru() {
        BiFunction<String, String, String> biFunction = NoOperation.biFunctionArg2PassThru();
        assertEquals("arg2", biFunction.apply("arg1", "arg2"));
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

    @Test
    public void noopCheckedConsumerTest() throws Exception {
        var sb = new StringBuilder();
        CheckedConsumer<String, Exception> consumer = (arg1) -> sb.append(arg1);
        consumer = NoOperation.checkedConsumer();
        consumer.checkedAccept("Test");

        assertEquals("", sb.toString());
    }

    @Test
    public void noopCheckedBiConsumerTest() throws Exception {
        var sb = new StringBuilderAppender();
        CheckedBiConsumer<String, String, Exception> biConsumer = (arg1, arg2) -> sb.append(arg1, arg2);
        biConsumer = NoOperation.checkedBiConsumer();
        biConsumer.checkedAccept("Test1", "Test2");

        assertEquals("", sb.toString());
    }

    @Test
    public void noopCheckedSinkTest() throws Exception {
        var sb = new StringBuilder();
        CheckedSink<Exception> sink = () -> sb.append("Test");
        sink = NoOperation.checkedSink();
        sink.checkedPlummet();

        assertEquals("", sb.toString());
    }

    @Test
    public void noopCheckedSupplierTest() throws Exception {
        CheckedSupplier<String, Exception> supplier = NoOperation.checkedSupplier();
        assertNull(supplier.checkedGet());
    }

    @Test
    public void noopCheckedPredicateTrueTest() throws Exception {
        CheckedPredicate<String, Exception> predicate = NoOperation.checkedPredicate(Boolean.TRUE);
        assertTrue(predicate.checkedTest("test"));
    }

    @Test
    public void noopCheckedPredicateFalseTest() throws Exception {
        CheckedPredicate<String, Exception> predicate = NoOperation.checkedPredicate(Boolean.FALSE);
        assertFalse(predicate.checkedTest("test"));
    }

    @Test
    public void noopCheckedFunctionTest() throws Exception {
        CheckedFunction<String, String, Exception> function = NoOperation.checkedFunction();
        assertNull(function.checkedApply("test"));
    }

    @Test
    public void noopCheckedFunctionOutputTest() throws Exception {
        CheckedFunction<String, String, Exception> function = NoOperation.checkedFunction("output");
        assertEquals("output", function.checkedApply("test"));
    }

    @Test
    public void noopCheckedFunctionPassThru() throws Exception {
        CheckedFunction<String, String, Exception> function = NoOperation.checkedFunctionPassThru();
        assertEquals("output", function.checkedApply("output"));
    }

    @Test
    public void noopCheckedBiFunctionTest() throws Exception {
        CheckedBiFunction<String, String, String, Exception> biFunction = NoOperation.checkedBiFunction();
        assertNull(biFunction.checkedApply("test", "test2"));
    }

    @Test
    public void noopCheckedBiFunctionOutputTest() throws Exception {
        CheckedBiFunction<String, String, String, Exception> biFunction = NoOperation.checkedBiFunction("output");
        assertEquals("output", biFunction.checkedApply("test", "test2"));
    }

    @Test
    public void noopCheckedBiFunctionArg1PassThru() throws Exception {
        CheckedBiFunction<String, String, String, Exception> biFunction = NoOperation.checkedBiFunctionArg1PassThru();
        assertEquals("arg1", biFunction.checkedApply("arg1", "arg2"));
    }

    @Test
    public void noopCheckedBiFunctionArg2PassThru() throws Exception {
        CheckedBiFunction<String, String, String, Exception> biFunction = NoOperation.checkedBiFunctionArg2PassThru();
        assertEquals("arg2", biFunction.checkedApply("arg1", "arg2"));
    }

    @Test
    public void noopCheckedBiPredicateTrueTest() throws Exception {
        CheckedBiPredicate<String, String, Exception> biPredicate = NoOperation.checkedBiPredicate(Boolean.TRUE);
        assertTrue(biPredicate.checkedTest("test", "test2"));
    }

    @Test
    public void noopCheckedBiPredicateFalseTest() throws Exception {
        CheckedBiPredicate<String, String, Exception> biPredicate = NoOperation.checkedBiPredicate(Boolean.FALSE);
        assertFalse(biPredicate.checkedTest("test", "test2"));
    }
}
