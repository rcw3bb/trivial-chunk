package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.command.Sink;

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
     */
    public LogicOR(List<BooleanSupplier> conditions) {
        super(conditions);
    }

    /**
     * Creates an instance of LogicOR.
     *
     * @param conditions An array of BooleanSupplier.
     *
     * @since 2.0.0
     */
    public LogicOR(BooleanSupplier ... conditions) {
        super(conditions);
    }

    /**
     * The specific OR implementation.
     *
     * @param truthLogic The logic to be executed when at least one condition is true.
     * @param falseLogic The logic to be executed when all the conditions are false.
     */
    @Override
    public void accept(Sink truthLogic, Sink falseLogic) {
        if (conditions.stream().anyMatch(BooleanSupplier::getAsBoolean)) {
            Optional.ofNullable(truthLogic).orElse(() -> {}).plummet();
        }
        else {
            Optional.ofNullable(falseLogic).orElse(() -> {}).plummet();
        }
    }
}
