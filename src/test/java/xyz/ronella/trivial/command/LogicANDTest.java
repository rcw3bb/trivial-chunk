package xyz.ronella.trivial.command;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.command.logic.LogicAND;
import xyz.ronella.trivial.functional.Sink;

import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class LogicANDTest {

    @Test
    public void singleConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicAND(()-> Boolean.TRUE), ()-> sb.append("X"));
        assertEquals("X", sb.toString());
    }

    @Test
    public void multipleTrueConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicAND(()-> Boolean.TRUE, ()-> Boolean.TRUE), ()-> sb.append("X"));
        assertEquals("X", sb.toString());
    }

    @Test
    public void multipleConditionTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicAND(()-> Boolean.TRUE, ()-> Boolean.FALSE), ()-> sb.append("X"));
        assertEquals("", sb.toString());
    }

    @Test
    public void multipleConditionWithFalseLogicTest() {
        var sb = new StringBuilder();
        Invoker.execute(new LogicAND(()-> Boolean.TRUE, ()-> Boolean.FALSE), ()-> sb.append("X"), ()->sb.append("Z"));
        assertEquals("Z", sb.toString());
    }

    @Test
    public void singleConditionWithConstTruthLogicTest() {
        var sb = new StringBuilder();
        Invoker.execute((Consumer<Sink>) new LogicAND(()-> sb.append("X"), ()-> Boolean.TRUE));
        assertEquals("X", sb.toString());
    }

    @Test
    public void singleConditionWithConstTruthFalseLogicsTest() {
        var sb = new StringBuilder();
        Invoker.execute((BiConsumer<Sink, Sink>) new LogicAND(()-> sb.append("X"), ()-> sb.append("Z"), ()-> Boolean.FALSE));
        assertEquals("Z", sb.toString());
    }

    @Test
    public void listConditionWithConstTruthLogicTest() {
        var sb = new StringBuilder();
        Invoker.execute((Consumer<Sink>) new LogicAND(Collections.singletonList(() -> Boolean.TRUE), ()-> sb.append("X")));
        assertEquals("X", sb.toString());
    }

    @Test
    public void listConditionWithConstTruthFalseLogicsTest() {
        var sb = new StringBuilder();
        Invoker.execute((BiConsumer<Sink, Sink>) new LogicAND(Collections.singletonList(()-> Boolean.FALSE), ()-> sb.append("X"), ()-> sb.append("Z")));
        assertEquals("Z", sb.toString());
    }

}
