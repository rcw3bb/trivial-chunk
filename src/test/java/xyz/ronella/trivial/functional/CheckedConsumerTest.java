package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedConsumerTest {

    @Test
    void throwException() {
        CheckedConsumer<String, Exception> checkedConsumer = arg -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, () -> checkedConsumer.checkedAccept("Test"));
    }

    @Test
    void noException() {
        CheckedConsumer<String, Exception> checkedConsumer = arg -> {
            // Do nothing
        };

        assertDoesNotThrow(() -> checkedConsumer.checkedAccept("Test"));
    }

    @Test
    void asConsumerNoError() {
        CheckedConsumer<String, Exception> checkedConsumer = arg -> {
            // Do nothing
        };
        var consumer = checkedConsumer.asConsumer();

        assertInstanceOf(Consumer.class, consumer);
    }

    @Test
    void asConsumerWithError() {
        CheckedConsumer<String, Exception> checkedConsumer = arg -> {
            throw new Exception("Exception");
        };

        var consumer = checkedConsumer.asConsumer();

        assertThrows(RuntimeException.class, () -> consumer.accept("test"));
    }

    @Test
    void testBuilderOfNoError() {
        CheckedConsumer<String, Exception> checkedConsumer = CheckedConsumer.of(arg -> {
            // Do nothing
        });
        assertDoesNotThrow(() -> checkedConsumer.asConsumer().accept("Test"));
    }

    @Test
    void testBuilderOfWithError() {
        CheckedConsumer<String, Exception> checkedConsumer = CheckedConsumer.of(arg -> {
            throw new Exception("Exception");
        });

        assertThrows(RuntimeException.class, ()-> checkedConsumer.asConsumer().accept("Test"));
    }
}
