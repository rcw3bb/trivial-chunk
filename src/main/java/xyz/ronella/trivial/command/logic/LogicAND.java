package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.command.Sink;

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
     */
    public LogicAND(List<BooleanSupplier> conditions) {
        super(conditions);
    }

    /**
     * Creates an instance of LogicAND.
     *
     * @param conditions An array of BooleanSupplier.
     *
     * @since 2.0.0
     */
    public LogicAND(BooleanSupplier ... conditions) {
        super(conditions);
    }

    /**
     * The specific AND implementation.
     *
     * @param truthLogic The logic to be executed when all the conditions are true.
     * @param falseLogic The logic to be executed when at least one condition is false.
     */
    @Override
    public void accept(Sink truthLogic, Sink falseLogic) {
        if (!conditions.isEmpty() && conditions.stream().allMatch(BooleanSupplier::getAsBoolean)) {
            Optional.ofNullable(truthLogic).orElse(() -> {}).plummet();
        }
        else {
            Optional.ofNullable(falseLogic).orElse(() -> {}).plummet();
        }
    }
}
