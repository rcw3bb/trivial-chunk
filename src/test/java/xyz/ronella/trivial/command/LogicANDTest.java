package xyz.ronella.trivial.command;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.command.logic.LogicAND;

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

}
