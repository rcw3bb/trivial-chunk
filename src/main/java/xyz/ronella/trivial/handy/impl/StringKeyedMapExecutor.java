package xyz.ronella.trivial.handy.impl;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.Sink;
import xyz.ronella.trivial.handy.AbstractStringKeyedMapLogic;

import java.util.Map;
import java.util.Optional;

/**
 * A convenience class for creating a map as executor.
 *
 * @author Ron Webb
 *
 * @since 2.2.0
 */
public class StringKeyedMapExecutor extends AbstractStringKeyedMapLogic<Sink, Object> {

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param map An external map that will be used as storage of logics.
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public StringKeyedMapExecutor(Map<String, Sink> map, Sink defaultLogic, Map.Entry<String, Sink> ... logics) {
        super(map, defaultLogic, logics);
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public StringKeyedMapExecutor(Sink defaultLogic, Map.Entry<String, Sink> ... logics) {
        super(defaultLogic, logics);
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public StringKeyedMapExecutor(Map.Entry<String, Sink> ... logics) {
        super(logics);
    }

    @Override
    protected Sink handleDefaultLogicConstructorArgument(Sink defaultLogic) {
        return Optional.ofNullable(defaultLogic).orElse(NoOperation.sink());
    }

    /**
     * This will always return empty values. Use the execute method instead to perform any logic.
     *
     * @param key The target key of the corresponding logic.
     *
     * @return Always empty.
     */
    @Override
    public Optional<Object> get(String key) {
        return Optional.empty();
    }

    /**
     * Executes the logic that corresponds to a particular key.
     *
     * @param key The target key of the corresponding logic.
     */
    public void execute(String key) {
        internalMap.getOrDefault(key, defaultLogic).plummet();
    }
}
