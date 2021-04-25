package xyz.ronella.trivial.command;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.command.logic.LogicOR;
import xyz.ronella.trivial.functional.Sink;

import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class LogicORTest {

    @Test
    public void singleConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicOR(()-> Boolean.TRUE), ()-> sb.append("X"));
        assertEquals("X", sb.toString());
    }

    @Test
    public void multipleTrueConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicOR(()-> Boolean.TRUE, ()-> Boolean.TRUE), ()-> sb.append("X"));
        assertEquals("X", sb.toString());
    }

    @Test
    public void multipleFalseConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicOR(()-> Boolean.FALSE, ()-> Boolean.FALSE), ()-> sb.append("X"));
        assertEquals("", sb.toString());
    }

    @Test
    public void multipleConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicOR(()-> Boolean.TRUE, ()-> Boolean.FALSE), ()-> sb.append("X"));
        assertEquals("X", sb.toString());
    }

    @Test
    public void multipleConditionWithFalseLogicTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicOR(()-> Boolean.TRUE, ()-> Boolean.FALSE), ()-> sb.append("X"), ()->sb.append("Z"));
        assertEquals("X", sb.toString());
    }

    @Test
    public void singleConditionWithConstTruthLogicTest() {
        var sb = new StringBuilder();
        Invoker.execute((Consumer<Sink>) new LogicOR(()-> sb.append("X"), ()-> Boolean.TRUE));
        assertEquals("X", sb.toString());
    }

    @Test
    public void singleConditionWithConstTruthFalseLogicsTest() {
        var sb = new StringBuilder();
        Invoker.execute((BiConsumer<Sink, Sink>) new LogicOR(()-> sb.append("X"), ()-> sb.append("Z"), ()-> Boolean.FALSE));
        assertEquals("Z", sb.toString());
    }

    @Test
    public void listConditionWithConstTruthLogicTest() {
        var sb = new StringBuilder();
        Invoker.execute((Consumer<Sink>) new LogicOR(Collections.singletonList(() -> Boolean.TRUE), ()-> sb.append("X")));
        assertEquals("X", sb.toString());
    }

    @Test
    public void listConditionWithConstTruthFalseLogicsTest() {
        var sb = new StringBuilder();
        Invoker.execute((BiConsumer<Sink, Sink>) new LogicOR(Collections.singletonList(()-> Boolean.FALSE), ()-> sb.append("X"), ()-> sb.append("Z")));
        assertEquals("Z", sb.toString());
    }
}
