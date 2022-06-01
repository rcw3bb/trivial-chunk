package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.functional.Sink;

import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * A class that accepts multiple conditions and if at least one condition is true it will pass control to the truthLogic
 * otherwise the falseLogic will take it.
 *
 * @author Ron Webb
 * @since 2019-11-30
 */
public class LogicOR extends AbstractLogical {

    /**
     * Creates an instance of LogicOR.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     */
    public LogicOR(final List<BooleanSupplier> conditions, final Sink defaultTruthLogic, final Sink defaultFalseLogic) {
        super(conditions, defaultTruthLogic, defaultFalseLogic);
    }

    /**
     * Creates an instance of LogicOR.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     */
    public LogicOR(final List<BooleanSupplier> conditions, final Sink defaultTruthLogic) {
        super(conditions, defaultTruthLogic);
    }

    /**
     * Creates an instance of LogicOR.
     *
     * @param conditions A list of BooleanSupplier.
     */
    public LogicOR(final List<BooleanSupplier> conditions) {
        super(conditions);
    }

    /**
     * Creates an instance of LogicOR.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     * @param conditions An array of BooleanSupplier.
     */
    public LogicOR(final Sink defaultTruthLogic, final Sink defaultFalseLogic, final BooleanSupplier ... conditions) {
        super(defaultTruthLogic, defaultFalseLogic, conditions);
    }

    /**
     * Creates an instance of LogicOR.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param conditions An array of BooleanSupplier.
     */
    public LogicOR(final Sink defaultTruthLogic, final BooleanSupplier ... conditions) {
        super(defaultTruthLogic, conditions);
    }

    /**
     * Creates an instance of LogicOR.
     *
     * @param conditions An array of BooleanSupplier.
     */
    public LogicOR(final BooleanSupplier ... conditions) {
        super(conditions);
    }

    @Override
    public boolean getTruthCondition(final List<BooleanSupplier> conditions) {
        return conditions.stream().anyMatch(BooleanSupplier::getAsBoolean);
    }
}
