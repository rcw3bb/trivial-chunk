package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedFunctionTest {

    @Test
    void throwException() {
        CheckedFunction<String, String, Exception> checkedFunction = arg -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, () -> checkedFunction.checkedApply("Test"));
    }

    @Test
    void noException() {
        CheckedFunction<String, String, Exception> checkedFunction = arg -> null;

        assertDoesNotThrow(() -> checkedFunction.checkedApply("Test"));
    }

    @Test
    void asFunctionNoError() {
        CheckedFunction<String, String, Exception> checkedFunction = arg -> null;
        var function = checkedFunction.asFunction();

        assertInstanceOf(Function.class, function);
    }

    @Test
    void asFunctionWithError() {
        CheckedFunction<String, String, Exception> checkedFunction = arg -> {
            throw new Exception("Exception");
        };

        var function = checkedFunction.asFunction();

        assertThrows(RuntimeException.class, () -> function.apply("test"));
    }

    @Test
    void testBuilderOfNoError() {
        CheckedFunction<String, String, Exception> checkedFunction = CheckedFunction.of(arg -> "Test");
        assertEquals("Test", checkedFunction.asFunction().apply("Test"));
    }

    @Test
    void testBuilderOfWithError() {
        CheckedFunction<String, String, Exception> checkedFunction = CheckedFunction.of(arg -> {
            throw new Exception("Exception");
        });

        assertThrows(RuntimeException.class, ()-> checkedFunction.asFunction().apply("Test"));
    }

}
