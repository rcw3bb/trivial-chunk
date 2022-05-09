package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.command.Invoker;
import xyz.ronella.trivial.command.logic.LogicAND;
import xyz.ronella.trivial.command.logic.LogicOR;
import xyz.ronella.trivial.functional.Sink;

import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * A collection of logic implementations.
 *
 * @author Ron Webb
 * @since 2019-11-30
 */
public final class Conditions {

    private Conditions() {}

    /**
     * Accepts multiple conditions if at least one condition is true it will pass control to the truthLogic.
     *
     * @param conditions A list of Boolean suppliers.
     * @param truthLogic Must have the truthLogic to perform if at least one is evaluated to true.
     */
    public static void or(List<BooleanSupplier> conditions, Sink truthLogic) {
        Invoker.execute(new LogicOR(conditions), truthLogic);
    }

    /**
     * Accepts multiple conditions if at least one condition is true it will pass control to the truthLogic
     * otherwise the falseLogic will take it.
     *
     * @param conditions A list of Boolean suppliers.
     * @param truthLogic Must have the truthLogic to perform if at least one is evaluated to true.
     * @param falseLogic Must have the falseLogic to perform if nothing is evaluated to true.
     */
    public static void or(List<BooleanSupplier> conditions, Sink truthLogic, Sink falseLogic) {
        Invoker.execute(new LogicOR(conditions), truthLogic, falseLogic);
    }

    /**
     * Accepts multiple conditions if all conditions are true it will pass control to the truthLogic.
     *
     * @param conditions A list of Boolean suppliers.
     * @param truthLogic Must have the truthLogic to perform if all are evaluated to true.
     */
    public static void and(List<BooleanSupplier> conditions, Sink truthLogic) {
        Invoker.execute(new LogicAND(conditions), truthLogic);
    }

    /**
     * Accepts multiple conditions if all condition are true it will pass control to the truthLogic
     * otherwise the falseLogic will take it.
     *
     * @param conditions A list of Boolean suppliers.
     * @param truthLogic Must have the truthLogic to perform if all are evaluated to true.
     * @param falseLogic Must have the falseLogic to perform if at least one is evaluated to false.
     */
    public static void and(List<BooleanSupplier> conditions, Sink truthLogic, Sink falseLogic) {
        Invoker.execute(new LogicAND(conditions), truthLogic, falseLogic);
    }
}
