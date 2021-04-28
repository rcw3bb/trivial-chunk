package xyz.ronella.trivial.command;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.Sink;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InvokerTest {

    @Test
    public void executeWithBiConsumerArg1() {
        var arg1 = new StringBuilder();
        var arg2 = new StringBuilder();
        Invoker.execute((StringBuilder ___arg1, StringBuilder ___arg2) -> {
            ___arg1.append("arg1");
            ___arg2.append("arg2");
        }, arg1, arg2);
        assertEquals("arg1", arg1.toString());
    }

    @Test
    public void executeWithBiConsumerNoArgs() {
        var arg1 = new StringBuilder();
        var arg2 = new StringBuilder();
        Invoker.execute((StringBuilder ___arg1, StringBuilder ___arg2) -> {
            Optional.ofNullable(___arg1).ifPresent(____arg1 -> ____arg1.append("arg1"));
            Optional.ofNullable(___arg2).ifPresent(____arg2 -> ____arg2.append("arg2"));
        });
        assertEquals("", arg1.toString());
    }

    @Test
    public void executeWithBiConsumerArg2() {
        var arg1 = new StringBuilder();
        var arg2 = new StringBuilder();
        Invoker.execute((StringBuilder ___arg1, StringBuilder ___arg2) -> {
            ___arg1.append("arg1");
            ___arg2.append("arg2");
        }, arg1, arg2);
        assertEquals("arg2", arg2.toString());
    }

    @Test
    public void executeWithBiFunctionArg1() {
        var arg1 = new StringBuilder("arg1");
        var arg2 = new StringBuilder("arg2");
        Invoker.process((StringBuilder ___arg1, StringBuilder ___arg2) -> {
            ___arg1.append(___arg2);
            return ___arg1;
        }, arg1, arg2);
        assertEquals("arg1arg2", arg1.toString());
    }

    @Test
    public void executeWithBiFunctionArg2() {
        var arg1 = new StringBuilder("arg1");
        var arg2 = new StringBuilder("arg2");
        Invoker.process((StringBuilder ___arg1, StringBuilder ___arg2) -> {
            ___arg1.append(___arg2);
            return ___arg1;
        }, arg1, arg2);
        assertEquals("arg2", arg2.toString());
    }

    @Test
    public void executeWithBiFunctionReturn() {
        var arg1 = new StringBuilder("arg1");
        var arg2 = new StringBuilder("arg2");
        var returnValue = Invoker.process((StringBuilder ___arg1, StringBuilder ___arg2) -> {
            ___arg1.append(___arg2);
            return ___arg1;
        }, arg1, arg2);
        assertEquals("arg1arg2", returnValue.toString());
    }

    @Test
    public void executeWithConsumer() {
        var arg = new StringBuilder();
        Invoker.execute((StringBuilder ___arg) -> {
            ___arg.append("arg");
        }, arg);
        assertEquals("arg", arg.toString());
    }

    @Test
    public void executeWithConsumerNoArg() {
        var arg = new StringBuilder();
        Invoker.execute((StringBuilder ___arg) -> {
            Optional.ofNullable(___arg).ifPresent(____arg -> ____arg.append("arg"));
        });
        assertEquals("", arg.toString());
    }

    @Test
    public void executeWithFunctionArg() {
        var arg = new StringBuilder();
        Invoker.process((StringBuilder ___arg) -> ___arg.append("arg"), arg);
        assertEquals("arg", arg.toString());
    }

    @Test
    public void executeWithFunctionReturn() {
        var arg = new StringBuilder();
        var returnValue = Invoker.process((StringBuilder ___arg) -> ___arg.append("arg"), arg);
        assertEquals("arg", returnValue.toString());
    }

    @Test
    public void generateWithSupplierReturn() {
        var returnValue = Invoker.generate(() -> "Test");
        assertEquals("Test", returnValue.toString());
    }

    @Test
    public void plungeWithSink() {
        var arg = new StringBuilder();
        Invoker.plunge(() -> arg.append("arg"));
        assertEquals("arg", arg.toString());
    }
}
