package xyz.ronella.trivial.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class CheckedPredicateTest {

    @Test
    void throwException() {
        CheckedPredicate<String, Exception> checkedPredicate = arg -> {
            throw new Exception("Exception");
        };

        assertThrows(Exception.class, () -> checkedPredicate.checkedTest("Test"));
    }

    @Test
    void noException() {
        CheckedPredicate<String, Exception> checkedPredicate = arg -> true;

        assertDoesNotThrow(() -> checkedPredicate.checkedTest("Test"));
    }

    @Test
    void asPredicateNoError() {
        CheckedPredicate<String, Exception> checkedPredicate = arg -> true;
        var predicate = checkedPredicate.asPredicate();

        assertInstanceOf(Predicate.class, predicate);
    }

    @Test
    void asPredicateWithError() {
        CheckedPredicate<String, Exception> checkedPredicate = arg -> {
            throw new Exception("Exception");
        };

        var predicate = checkedPredicate.asPredicate();

        assertThrows(RuntimeException.class, () -> predicate.test("test"));
    }

    @Test
    void testBuilderOfNoError() {
        CheckedPredicate<String, Exception> checkedPredicate = CheckedPredicate.of((___arg) -> true);
        assertTrue(checkedPredicate.asPredicate().test("Test"));
    }

    @Test
    void testBuilderOfWithError() {
        CheckedPredicate<String, Exception> checkedPredicate = CheckedPredicate.of((___args) -> {
            throw new Exception("Exception");
        });

        assertThrows(RuntimeException.class, ()-> checkedPredicate.asPredicate().test("Test"));
    }

}
