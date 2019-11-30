package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.command.Sink;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class LogicMapper {

    private Map<Supplier<Boolean>, Sink> logicMap = null;

    private LogicMapper(Map<Supplier<Boolean>, Sink> logicMap) {
        this.logicMap = Optional.ofNullable(logicMap).orElse(new LinkedHashMap<>());
    }

    public LogicMapper addLogic(Supplier<Boolean> condition, Sink logic) {
        logicMap.put(condition, logic);
        return this;
    }

    public void execute() {
        logicMap.forEach((___condition, ___logic) -> {
            if (Optional.ofNullable(___condition).orElse(()->Boolean.FALSE).get()) {
                ___logic.plummet();
            }
        });
    }

    public static LogicMapper build() {
        return new LogicMapper(new LinkedHashMap<>());
    }

}
