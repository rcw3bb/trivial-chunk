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
    public StringKeyedMapExecutor(final Map<String, Sink> map, final Sink defaultLogic,
                                  final Map.Entry<String, Sink> ... logics) {
        super(map, defaultLogic, logics);
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public StringKeyedMapExecutor(final Sink defaultLogic, final Map.Entry<String, Sink> ... logics) {
        this(null, defaultLogic, logics);
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public StringKeyedMapExecutor(final Map.Entry<String, Sink> ... logics) {
        this(null, logics);
    }

    /**
     * Creates an instance of StringKeyedMapExecutor
     *
     * @param map An external map that already contains logics.
     */
    public StringKeyedMapExecutor(final Map<String, Sink> map) {
        super(map, null);
    }

    @Override
    protected Sink handleDefaultLogicConstructorArgument(final Sink defaultLogic) {
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
    public Optional<Object> get(final String key) {
        execute(key);
        return Optional.empty();
    }

    /**
     * Executes the logic that corresponds to a particular key.
     *
     * @param key The target key of the corresponding logic.
     */
    public void execute(final String key) {
        internalMap.getOrDefault(key, defaultLogic).plummet();
    }
}
