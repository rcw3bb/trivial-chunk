package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.Sink;
import xyz.ronella.trivial.handy.AbstractBooleanSupplierKeyedMapLogic;

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
public class BooleanSupplierKeyedMapExecutor extends AbstractBooleanSupplierKeyedMapLogic<Sink, Object> {

    /**
     * Creates an instance of BooleanSupplierKeyedMapExecutor
     *
     * @param map An external map that will be used as storage of logics.
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public BooleanSupplierKeyedMapExecutor(Map<BooleanSupplier, Sink> map, Sink defaultLogic, Map.Entry<BooleanSupplier, Sink> ... logics) {
        super(map, defaultLogic, logics);
    }

    /**
     * Creates an instance of BooleanSupplierKeyedMapExecutor
     *
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public BooleanSupplierKeyedMapExecutor(Sink defaultLogic, Map.Entry<BooleanSupplier, Sink> ... logics) {
        this(null, defaultLogic, logics);
    }

    /**
     * Creates an instance of BooleanSupplierKeyedMapExecutor
     *
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public BooleanSupplierKeyedMapExecutor(Map.Entry<BooleanSupplier, Sink> ... logics) {
        this(null, logics);
    }

    /**
     * Creates an instance of BooleanSupplierKeyedMapExecutor
     *
     * @param map An external map that already contains logics.
     */
    public BooleanSupplierKeyedMapExecutor(Map<BooleanSupplier, Sink> map) {
        super(map, null);
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

    /**
     * This will always return empty values. Use the execute method instead to perform any logic.
     *
     * @return Always empty.
     */
    @Override
    public Optional<Object> get() {
        execute();
        return Optional.empty();
    }
}
