package xyz.ronella.trivial.command;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvokerTest {

    @Test
    public void executeWithBiConsumerArg1() {
        var arg1 = new MutableBoolean(Boolean.FALSE);
        var arg2 = new MutableBoolean(Boolean.FALSE);
        Invoker.execute((MutableBoolean ___arg1, MutableBoolean ___arg2) -> {
            ___arg1.setTrue();
            ___arg2.setTrue();
        }, arg1, arg2);
        assertTrue(arg1.booleanValue());
    }

    @Test
    public void executeWithBiConsumerArg2() {
        var arg1 = new MutableBoolean(Boolean.FALSE);
        var arg2 = new MutableBoolean(Boolean.FALSE);
        Invoker.execute((MutableBoolean ___arg1, MutableBoolean ___arg2) -> {
            ___arg1.setTrue();
            ___arg2.setTrue();
        }, arg1, arg2);
        assertTrue(arg2.booleanValue());
    }

    @Test
    public void executeWithBiFunctionArg1() {
        var arg1 = new MutableInt(1);
        var arg2 = new MutableInt(2);
        Invoker.process((MutableInt ___arg1, MutableInt ___arg2) -> {
            ___arg1.add(5);
            return ___arg1.addAndGet(___arg2.intValue());
        }, arg1, arg2);
        assertEquals(8, arg1.intValue());
    }

    @Test
    public void executeWithBiFunctionArg2() {
        var arg1 = new MutableInt(1);
        var arg2 = new MutableInt(2);
        Invoker.process((MutableInt ___arg1, MutableInt ___arg2) -> {
            ___arg2.add(4);
            return ___arg1.addAndGet(___arg2.intValue());
        }, arg1, arg2);
        assertEquals(6, arg2.intValue());
    }

    @Test
    public void executeWithBiFunctionReturn() {
        var arg1 = new MutableInt(1);
        var arg2 = new MutableInt(2);
        var returnValue = Invoker.process((MutableInt ___arg1, MutableInt ___arg2) ->
                ___arg1.addAndGet(___arg2.intValue()), arg1, arg2);
        assertEquals(3, returnValue);
    }

    @Test
    public void executeWithConsumer() {
        var arg = new MutableBoolean(Boolean.FALSE);
        Invoker.execute((MutableBoolean ___arg) -> {
            ___arg.setTrue();
        }, arg);
        assertTrue(arg.booleanValue());
    }

    @Test
    public void executeWithFunctionArg() {
        var arg = new MutableInt(1);
        Invoker.process((MutableInt ___arg) -> ___arg.addAndGet(4), arg);
        assertEquals(5, arg.intValue());
    }

    @Test
    public void executeWithFunctionReturn() {
        var arg = new MutableInt(1);
        var returnValue = Invoker.process((MutableInt ___arg) -> ___arg.addAndGet(4), arg);
        assertEquals(5, returnValue);
    }

    @Test
    public void generateWithSupplierReturn() {
        var returnValue = Invoker.generate(() -> "Test");
        assertEquals("Test", returnValue);
    }

    @Test
    public void plungeWithSink() {
        var arg = new MutableBoolean(Boolean.FALSE);
        Invoker.plunge(() -> arg.setTrue());
        assertTrue(arg.booleanValue());
    }

}
