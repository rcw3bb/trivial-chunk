package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedSinkTest {

    @Test
    void throwException() {
        CheckedSink<Exception> checkedSupplier = () -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, checkedSupplier::checkedPlummet);
    }

    @Test
    void noException() {
        CheckedSink<Exception> checkedSupplier = () -> {};

        assertDoesNotThrow(checkedSupplier::checkedPlummet);
    }

    @Test
    void asSinkNoError() {
        CheckedSink<Exception> checkedSupplier = () -> {};
        var sink = checkedSupplier.asSink();

        assertInstanceOf(Sink.class, sink);
    }

    @Test
    void asSinkWithError() {
        CheckedSink<Exception> checkedSink = () -> {
            throw new Exception("Exception");
        };

        var sink = checkedSink.asSink();

        assertThrows(RuntimeException.class, sink::plummet);
    }

    @Test
    void testBuilderOfNoError() {
        CheckedSink<Exception> checkedSink = CheckedSink.of(() -> {});
        assertDoesNotThrow(checkedSink.asSink()::plummet);
    }

    @Test
    void testBuilderOfWithError() {
        CheckedSink<Exception> checkedSink = () -> {
            throw new Exception("Exception");
        };

        assertThrows(RuntimeException.class, ()-> checkedSink.asSink().plummet());
    }

}
