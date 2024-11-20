package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedBiPredicateTest {

    @Test
    void throwException() {
        CheckedBiPredicate<String, String, Exception> checkedPredicate = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, () -> checkedPredicate.checkedTest("Test1", "Test2"));
    }

    @Test
    void noException() {
        CheckedBiPredicate<String, String, Exception> checkedPredicate = (arg1, arg2) -> true;

        assertDoesNotThrow(() -> checkedPredicate.checkedTest("Test1", "Test2"));
    }

    @Test
    void asPredicateNoError() {
        CheckedBiPredicate<String, String, Exception> checkedPredicate = (arg1, arg2) -> true;
        var predicate = checkedPredicate.asBiPredicate();

        assertInstanceOf(BiPredicate.class, predicate);
    }

    @Test
    void asPredicateWithError() {
        CheckedBiPredicate<String, String, Exception> checkedPredicate = (arg1, arg2) -> {
            throw new Exception("Exception");
        };

        var predicate = checkedPredicate.asBiPredicate();

        assertThrows(RuntimeException.class, () -> predicate.test("test1", "test2"));
    }

    @Test
    void testBuilderOfNoError() {
        CheckedBiPredicate<String, String, Exception> checkedPredicate = CheckedBiPredicate.of((arg1, arg2) -> true);
        assertTrue(checkedPredicate.asBiPredicate().test("Test", "Test2"));
    }

    @Test
    void testBuilderOfWithError() {
        CheckedBiPredicate<String, String, Exception> checkedPredicate = CheckedBiPredicate.of((arg1, arg2) -> {
            throw new Exception("Exception");
        });

        assertThrows(RuntimeException.class, ()-> checkedPredicate.asBiPredicate().test("Test", "Test2"));
    }

}
