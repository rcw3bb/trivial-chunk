package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedSupplierTest {

    @Test
    void throwException() {
        CheckedSupplier<String, Exception> checkedSupplier = () -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, checkedSupplier::checkedGet);
    }

    @Test
    void noException() {
        CheckedSupplier<String, Exception> checkedSupplier = () -> "Test";

        assertDoesNotThrow(checkedSupplier::checkedGet);
    }

    @Test
    void asSupplierNoError() {
        CheckedSupplier<String, Exception> checkedSupplier = () -> "Test";
        var supplier = checkedSupplier.asSupplier();

        assertInstanceOf(Supplier.class, supplier);
    }

    @Test
    void asSupplierWithError() {
        CheckedSupplier<String, Exception> checkedSupplier = () -> {
            throw new Exception("Exception");
        };

        var supplier = checkedSupplier.asSupplier();

        assertThrows(RuntimeException.class, supplier::get);
    }

    @Test
    void testBuilderOfNoError() {
        CheckedSupplier<String, Exception> checkedSupplier = CheckedSupplier.of(() -> "Test");
        assertEquals("Test", checkedSupplier.asSupplier().get());
    }

    @Test
    void testBuilderOfWithError() {
        CheckedSupplier<String, Exception> checkedSupplier = CheckedSupplier.of(() -> {
            throw new Exception("Exception");
        });

        assertThrows(RuntimeException.class, ()-> checkedSupplier.asSupplier().get());
    }

}
