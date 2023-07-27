package xyz.ronella.trivial.decorator;

import java.math.BigDecimal;

/**
 * A decorator for BigDecimal.
 *
 * @author Ron Webb
 * @since 2.16.0
 */
public class BigDecimalPlus {

    final private BigDecimal bigDecimal;

    /**
     * Create an instance of BigDecimalPlus.
     * @param bigDecimal The BigDecimal instance to decorate.
     */
    public BigDecimalPlus(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    /**
     * Compare the current decorated BigDecimal is less than the other BigDecimal.
     * @param other The other BigDecimal instance.
     * @return Return true if less than.
     */
    public boolean lessThan(final BigDecimal other) {
        return bigDecimal.compareTo(other) < 0;
    }

    /**
     * Compare the current decorated BigDecimal is greater than the other BigDecimal.
     * @param other The other BigDecimal instance.
     * @return Return true if greater than.
     */
    public boolean greaterThan(final BigDecimal other) {
        return bigDecimal.compareTo(other) > 0;
    }

    /**
     * Compare the current decorated BigDecimal is equals to the other BigDecimal.
     * @param other The other BigDecimal instance.
     * @return Return true if equal.
     */
    public boolean equalsTo(final BigDecimal other) {
        return bigDecimal.compareTo(other) == 0;
    }

    /**
     * Compare the current decorated BigDecimal is less than or equals to the other BigDecimal.
     * @param other The other BigDecimal instance.
     * @return Return true if less than or equal.
     */
    public boolean lessThanEqualsTo(final BigDecimal other) {
        return this.lessThan(other) || this.equalsTo(other);
    }

    /**
     * Compare the current decorated BigDecimal is greater than or equals to the other BigDecimal.
     * @param other The other BigDecimal instance.
     * @return Return true if greater than or equal.
     */
    public boolean greaterThanEqualsTo(final BigDecimal other) {
        return this.greaterThan(other) || this.equalsTo(other);
    }

    /**
     * Compare the current decorated BigDecimal between the provided numbers.
     * @param firstNumber The firstNumber to where the decorated BigDecimal must be greater.
     * @return Return An instance of LogicalAnd that accepts the secondNumber where the decorated BigDecimal must be less than.
     */
    public LogicalAND between(final BigDecimal firstNumber) {
        return (___secondNumber) -> greaterThan(firstNumber) && lessThan(___secondNumber);
    }

    /**
     * The implementation must be used for concatenating boolean expression derived from BigDecimal.
     */
    @FunctionalInterface
    public interface LogicalAND {
        /**
         * Must have the logic to return boolean output that complies with AND logical relationship.
         * @param object An instance to check.
         * @return Returns boolean.
         */
        Boolean and(BigDecimal object);
    }

}
