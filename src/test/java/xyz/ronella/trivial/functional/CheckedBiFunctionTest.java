package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedBiFunctionTest {

    @Test
    void throwException() {
        CheckedBiFunction<String, String, String, Exception> checkedFunction = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, () -> checkedFunction.checkedApply("Test1", "Test2"));
    }

    @Test
    void noException() {
        CheckedBiFunction<String, String, String, Exception> checkedFunction = (arg1, arg2) -> null;

        assertDoesNotThrow(() -> checkedFunction.checkedApply("Test1", "Test2"));
    }

    @Test
    void asFunctionNoError() {
        CheckedBiFunction<String, String, String, Exception> checkedFunction = (arg1, arg2) -> null;
        var function = checkedFunction.asBiFunction();

        assertInstanceOf(BiFunction.class, function);
    }

    @Test
    void asFunctionWithError() {
        CheckedBiFunction<String, String, String, Exception> checkedFunction = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        var function = checkedFunction.asBiFunction();

        assertThrows(RuntimeException.class, () -> function.apply("test", "test2"));
    }

    @Test
    void testBuilderOfNoError() {
        CheckedBiFunction<String, String, String, Exception> checkedFunction = (arg1, arg2) -> arg1;
        assertEquals("Test", checkedFunction.asBiFunction().apply("Test", "Test2"));
    }

    @Test
    void testBuilderOfWithError() {
        CheckedBiFunction<String, String, String, Exception> checkedFunction = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        assertThrows(RuntimeException.class, ()-> checkedFunction.asBiFunction().apply("Test", "Test2"));
    }

}
