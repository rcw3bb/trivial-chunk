package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.command.Invoker;
import xyz.ronella.trivial.command.Sink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A class that accepts multiple conditions if at least one condition is true it will pass control to the truthLogic
 * otherwise the falseLogic will take it.
 *
 * @author Ron Webb
 * @since 2019-11-30
 */
public class ConditionsOR implements Consumer<Sink>, BiConsumer<Sink, Sink> {

    private List<Supplier<Boolean>> conditions;

    public ConditionsOR(List<Supplier<Boolean>> conditions) {
        this.conditions = Optional.ofNullable(conditions).orElse(new ArrayList<>());
    }

    @Override
    public void accept(Sink truthLogic) {
        accept(truthLogic, null);
    }

    @Override
    public void accept(Sink truthLogic, Sink falseLogic) {
        if (conditions.stream().anyMatch(Supplier::get)) {
            Optional.ofNullable(truthLogic).orElse(() -> {}).plummet();
        }
        else {
            Optional.ofNullable(falseLogic).orElse(() -> {}).plummet();
        }
    }

    /**
     * Accepts multiple conditions if at least one condition is true it will pass control to the truthLogic.
     *
     * @param conditions A list of Boolean suppliers.
     * @param truthLogic Must have the truthLogic to perform if at least one is evaluated to true.
     */
    public static void or(List<Supplier<Boolean>> conditions, Sink truthLogic) {
        Invoker.execute(new ConditionsOR(conditions), truthLogic);
    }

    /**
     * Accepts multiple conditions if at least one condition is true it will pass control to the truthLogic
     * otherwise the falseLogic will take it.
     *
     * @param conditions A list of Boolean suppliers.
     * @param truthLogic Must have the truthLogic to perform if at least one is evaluated to true.
     * @param falseLogic Must have the falseLogic to perform if nothing is evaluated to true.
     */
    public static void or(List<Supplier<Boolean>> conditions, Sink truthLogic, Sink falseLogic) {
        Invoker.execute(new ConditionsOR(conditions), truthLogic, falseLogic);
    }

}
