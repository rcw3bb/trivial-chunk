package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.functional.Sink;

import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * A class that accepts multiple conditions if all conditions are true it will pass control to the truthLogic
 * otherwise the falseLogic will take it.
 *
 * @author Ron Webb
 * @since 2019-11-30
 */
public class LogicAND extends AbstractLogical {

    /**
     * Creates an instance of LogicAND.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     */
    public LogicAND(List<BooleanSupplier> conditions, Sink defaultTruthLogic, Sink defaultFalseLogic) {
        super(conditions, defaultTruthLogic, defaultFalseLogic);
    }

    /**
     * Creates an instance of LogicAND.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     */
    public LogicAND(List<BooleanSupplier> conditions, Sink defaultTruthLogic) {
        super(conditions, defaultTruthLogic);
    }

    /**
     * Creates an instance of LogicAND.
     *
     * @param conditions A list of BooleanSupplier.
     */
    public LogicAND(List<BooleanSupplier> conditions) {
        super(conditions);
    }

    /**
     * Creates an instance of LogicAND.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     * @param conditions An array of BooleanSupplier.
     */
    public LogicAND(Sink defaultTruthLogic, Sink defaultFalseLogic, BooleanSupplier ... conditions) {
        super(defaultTruthLogic, defaultFalseLogic, conditions);
    }

    /**
     * Creates an instance of LogicAND.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param conditions An array of BooleanSupplier.
     */
    public LogicAND(Sink defaultTruthLogic, BooleanSupplier ... conditions) {
        super(defaultTruthLogic, conditions);
    }

    /**
     * Creates an instance of LogicAND.
     *
     * @param conditions An array of BooleanSupplier.
     */
    public LogicAND(BooleanSupplier ... conditions) {
        super(conditions);
    }

    @Override
    public boolean getTruthCondition(List<BooleanSupplier> conditions) {
        return !conditions.isEmpty() && conditions.stream().allMatch(BooleanSupplier::getAsBoolean);
    }
}
