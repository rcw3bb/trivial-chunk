package xyz.ronella.trivial.handy;

import static xyz.ronella.trivial.handy.ConditionsOR.or;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionsORTest {

    @Test
    public void nullConditions() {
        var builder = new StringBuilder();
        or(null, () -> {
            builder.append("In");
        });
        assertEquals("", builder.toString());
    }

    @Test
    public void nullTrueFalseLogics() {
        var builder = new StringBuilder();
        or(null, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }

    @Test
    public void emptyConditions() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        or(conditions, () -> {
            builder.append("In");
        });
        assertEquals("", builder.toString());
    }

    @Test
    public void emptyTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        or(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }


    @Test
    public void singeFalseConditions() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        conditions.add(() -> Boolean.FALSE);
        or(conditions, () -> {
            builder.append("In");
        });
        assertEquals("", builder.toString());
    }

    @Test
    public void singeTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        conditions.add(() -> Boolean.FALSE);
        or(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }


    @Test
    public void singeTrueConditions() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        conditions.add(() -> Boolean.TRUE);
        or(conditions, () -> {
            builder.append("In");
        });
        assertEquals("In", builder.toString());
    }

    @Test
    public void singeTrueTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        conditions.add(() -> Boolean.TRUE);
        or(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("In", builder.toString());
    }

    @Test
    public void singeFaLseTrueTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        conditions.add(() -> Boolean.FALSE);
        conditions.add(() -> Boolean.TRUE);
        or(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("In", builder.toString());
    }

    @Test
    public void singeFaLseFalseTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<Supplier<Boolean>>();
        conditions.add(() -> Boolean.FALSE);
        conditions.add(() -> Boolean.FALSE);
        or(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }


}
