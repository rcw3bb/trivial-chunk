package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedBiConsumerTest {

    @Test
    void throwException() {
        CheckedBiConsumer<String, String, Exception> checkedConsumer = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, () -> checkedConsumer.checkedAccept("Test1", "Test2"));
    }

    @Test
    void noException() {
        CheckedBiConsumer<String, String, Exception> checkedConsumer = (arg1, arg2) -> {
            // Do nothing
        };

        assertDoesNotThrow(() -> checkedConsumer.checkedAccept("Test1", "Test2"));
    }

    @Test
    void asConsumerNoError() {
        CheckedBiConsumer<String, String, Exception> checkedConsumer = (arg1, arg2) -> {
            // Do nothing
        };

        var consumer = checkedConsumer.asBiConsumer();

        assertInstanceOf(BiConsumer.class, consumer);
    }

    @Test
    void asConsumerWithError() {
        CheckedBiConsumer<String, String, Exception> checkedConsumer = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        var consumer = checkedConsumer.asBiConsumer();
        assertThrows(RuntimeException.class, () -> consumer.accept("test", "test2"));
    }

    @Test
    void testBuilderOfNoError() {
        CheckedBiConsumer<String, String, Exception> checkedConsumer = (arg1, arg2) -> {
            // Do nothing
        };

        assertDoesNotThrow(() -> checkedConsumer.asBiConsumer().accept("Test", "Test2"));
    }

    @Test
    void testBuilderOfWithError() {
        CheckedBiConsumer<String, String, Exception> checkedConsumer = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        assertThrows(RuntimeException.class, ()-> checkedConsumer.asBiConsumer().accept("Test", "Test2"));
    }
}
