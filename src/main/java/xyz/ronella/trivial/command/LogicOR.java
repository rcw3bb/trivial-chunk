package xyz.ronella.trivial.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A class that accepts multiple conditions and if at least one condition is true it will pass control to the truthLogic
 * otherwise the falseLogic will take it.
 *
 * @author Ron Webb
 * @since 2019-11-30
 */
public class LogicOR implements Consumer<Sink>, BiConsumer<Sink, Sink> {

    private List<Supplier<Boolean>> conditions;

    public LogicOR(List<Supplier<Boolean>> conditions) {
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
}
