package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.ronella.trivial.handy.Conditions.and;

public class ConditionsANDTest {

    @Test
    public void nullConditions() {
        var builder = new StringBuilder();
        and(null, () -> {
            builder.append("In");
        });
        assertEquals("", builder.toString());
    }

    @Test
    public void nullTrueFalseLogics() {
        var builder = new StringBuilder();
        and(null, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }

    @Test
    public void emptyConditions() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        and(conditions, () -> {
            builder.append("In");
        });
        assertEquals("", builder.toString());
    }

    @Test
    public void emptyTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        and(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }


    @Test
    public void singleFalseConditions() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.FALSE);
        and(conditions, () -> {
            builder.append("In");
        });
        assertEquals("", builder.toString());
    }

    @Test
    public void singleTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.FALSE);
        and(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }


    @Test
    public void singleTrueConditions() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.TRUE);
        and(conditions, () -> {
            builder.append("In");
        });
        assertEquals("In", builder.toString());
    }

    @Test
    public void singleTrueTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.TRUE);
        and(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("In", builder.toString());
    }

    @Test
    public void multiFaLseTrueTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.FALSE);
        conditions.add(() -> Boolean.TRUE);
        and(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }

    @Test
    public void multiFaLseFalseTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.FALSE);
        conditions.add(() -> Boolean.FALSE);
        and(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("Out", builder.toString());
    }

    @Test
    public void multiTrueTrueTrueFalseLogics() {
        var builder = new StringBuilder();
        var conditions = new ArrayList<BooleanSupplier>();
        conditions.add(() -> Boolean.TRUE);
        conditions.add(() -> Boolean.TRUE);
        and(conditions, () -> {
            builder.append("In");
        }, () -> {
            builder.append("Out");
        });
        assertEquals("In", builder.toString());
    }

}
