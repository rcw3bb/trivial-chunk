package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.Sink;
import xyz.ronella.trivial.handy.AbstractKeyedMapLogic;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/**
 * A convenience class for creating a map as an executor.
 *
 * @author Ron Webb
 *
 * @since 2.4.0
 */
public class BooleanSupplierKeyedMapExecutor extends AbstractKeyedMapLogic<BooleanSupplier, Sink, Object> {

    @SafeVarargs
    public BooleanSupplierKeyedMapExecutor(Map<BooleanSupplier, Sink> map, Sink defaultLogic, Map.Entry<BooleanSupplier, Sink> ... logics) {
        super(map, defaultLogic, Arrays.asList(logics));
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public BooleanSupplierKeyedMapExecutor(Sink defaultLogic, Map.Entry<BooleanSupplier, Sink> ... logics) {
        this(null, defaultLogic, logics);
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public BooleanSupplierKeyedMapExecutor(Map.Entry<BooleanSupplier, Sink> ... logics) {
        this(null, logics);
    }

    @Override
    protected Sink handleDefaultLogicConstructorArgument(Sink defaultLogic) {
        return Optional.ofNullable(defaultLogic).orElse(NoOperation.sink());
    }

    /**
     * Executes the logic of the first key that return true otherwise the default will be executed.
     */
    public void execute() {
        Sink logic = getDefaultLogic();
        for (var key : internalMap.keySet()) {
            if (key.getAsBoolean()) {
                logic = internalMap.get(key);
                break;
            }
        }
        logic.plummet();
    }
}
