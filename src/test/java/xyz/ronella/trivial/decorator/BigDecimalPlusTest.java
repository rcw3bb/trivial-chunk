package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.ObjectRequiredException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BigDecimalPlusTest {

    @Test
    public void testLessThanTrue() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(200);
        assertTrue(bd1.lessThan(bd2));
    }

    @Test
    public void constructorParamNull() {
        assertThrows(ObjectRequiredException.class, () -> new BigDecimalPlus(null));
    }

    @Test
    public void testLessThanFalse() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(200));
        final var bd2 = new BigDecimal(100);
        assertFalse(bd1.lessThan(bd2));
    }

    @Test
    public void testLessThanButEquals() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(100);

        assertFalse(bd1.lessThan(bd2));
    }

    @Test
    public void testGreaterThanTrue() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(200));
        final var bd2 = new BigDecimal(100);
        assertTrue(bd1.greaterThan(bd2));
    }

    @Test
    public void testGreaterThanFalse() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(200);
        assertFalse(bd1.greaterThan(bd2));
    }

    @Test
    public void testGreaterThanButEquals() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(100);

        assertFalse(bd1.greaterThan(bd2));
    }

    @Test
    public void testEqualsToTrue() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(100);
        assertTrue(bd1.equalsTo(bd2));
    }

    @Test
    public void testEqualsToFalse() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(200);
        assertFalse(bd1.equalsTo(bd2));
    }

    @Test
    public void testLessThanEqualsLess() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(200);
        assertTrue(bd1.lessThanEqualsTo(bd2));
    }

    @Test
    public void testLessThanEqualsEquals() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(100);
        assertTrue(bd1.lessThanEqualsTo(bd2));
    }

    @Test
    public void testLessThanEqualsGreater() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(200));
        final var bd2 = new BigDecimal(100);

        assertFalse(bd1.lessThanEqualsTo(bd2));
    }

    @Test
    public void testGreaterThanEqualsLess() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(200));
        final var bd2 = new BigDecimal(100);
        assertTrue(bd1.greaterThanEqualsTo(bd2));
    }

    @Test
    public void testGreaterThanEqualsEquals() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(100);
        assertTrue(bd1.greaterThanEqualsTo(bd2));
    }

    @Test
    public void testGreaterThanEqualsGreater() {
        final var bd1 = new BigDecimalPlus(new BigDecimal(100));
        final var bd2 = new BigDecimal(200);

        assertFalse(bd1.greaterThanEqualsTo(bd2));
    }

    @Test
    public void testBetweenFirstAndSecondNumber() {
        final var bd = new BigDecimalPlus(new BigDecimal(200));
        final var bd1 = new BigDecimal(100);
        final var bd2 = new BigDecimal(300);

        assertTrue(bd.between(bd1).and(bd2));
    }

    @Test
    public void testBetweenEqualsFirstNumber() {
        final var bd = new BigDecimalPlus(new BigDecimal(100));
        final var bd1 = new BigDecimal(100);
        final var bd2 = new BigDecimal(300);

        assertFalse(bd.between(bd1).and(bd2));
    }

    @Test
    public void testBetweenEqualsSecondNumber() {
        final var bd = new BigDecimalPlus(new BigDecimal(300));
        final var bd1 = new BigDecimal(100);
        final var bd2 = new BigDecimal(300);

        assertFalse(bd.between(bd1).and(bd2));
    }

}
