package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.command.Sink;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Create a map of conditions and logic where all the conditions that evaluated to true will perform its
 * the corresponding logic.
 *
 * @author Ron Webb
 * @since 2019-12-01
 */
public class LogicMapper {

    private Map<Supplier<Boolean>, Sink> logicMap = null;

    private LogicMapper(Map<Supplier<Boolean>, Sink> logicMap) {
        this.logicMap = Optional.ofNullable(logicMap).orElse(new LinkedHashMap<>());
    }

    /**
     * Add a logic to the collection.
     *
     * @param condition A Boolean Supplier.
     * @param logic The logic to be executed if the condition is evaluated to true.
     * @return The current instance.
     */
    public LogicMapper addLogic(Supplier<Boolean> condition, Sink logic) {
        logicMap.put(condition, logic);
        return this;
    }

    /**
     * Executes the collection of logic
     */
    public void execute() {
        logicMap.forEach((___condition, ___logic) -> {
            if (Optional.ofNullable(___condition).orElse(()->Boolean.FALSE).get()) {
                ___logic.plummet();
            }
        });
    }

    /**
     * Build an instance of LogicMapper.
     *
     * @return An instance of LogicMapper.
     */
    public static LogicMapper build() {
        return new LogicMapper(new LinkedHashMap<>());
    }

}
