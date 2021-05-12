package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static xyz.ronella.trivial.handy.NPESilencer.*;

public class NPESilencerTest {

    @Test
    public void nullRoot() {
        var value = NPESilencer.<String, String>getBuilder()
                .addExpr(Object::toString)
                .build().evaluate();
        assertNull(value);
    }

    @Test
    public void chainFields() {
        var value = NPESilencer.<String, String>getBuilder()
                .addRoot("Test")
                .addExpr(Object::toString)
                .addExpr(Object::toString)
                .build().evaluate();
        assertEquals("Test", value);
    }

    @Test
    public void nullInChainFields() {
        var value = NPESilencer.<String, String>getBuilder()
                .addRoot("Test")
                .addExpr(Object::toString)
                .addExpr(___arg -> null)
                .addExpr(Object::toString)
                .build().evaluate();
        assertNull(value);
    }

    @Test
    public void chainWithoutRootFields() {
        var value = NPESilencer.<String, String>getBuilder()
                .addExpr(___ -> "Test")
                .addExpr(arg -> ((String) arg).toUpperCase())
                .addExpr(Object::toString)
                .build().evaluate();
        assertEquals("TEST", value);
    }

    @Test
    public void nullInChainWithoutRootFields() {
        var value = NPESilencer.<String, String>getBuilder()
                .addExpr(___ -> "Test")
                .addExpr(arg -> ((String) arg).toUpperCase())
                .addExpr(___ -> null)
                .build().evaluate();
        assertNull(value);
    }

    @Test
    public void chainWithoutRootSupplierFields() {
        var value = getBuilder()
                .addExpr(() -> "Test")
                .addExpr(arg -> ((String) arg).toUpperCase())
                .build().evaluate();
        assertEquals("TEST", value);
    }

    @Test
    public void silenceNullExpression() {
        String nullStr = null;
        var value = nullable(()-> nullStr.toString());
        assertNull(value);
    }

    @Test
    public void silenceNonNullExpression() {
        String nonNullStr = "Test";
        var value = nullable(()-> nonNullStr.toString());
        assertEquals("Test", value);
    }

}
