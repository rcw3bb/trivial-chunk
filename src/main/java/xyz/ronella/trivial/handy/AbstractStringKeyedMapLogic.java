package xyz.ronella.trivial.handy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The base class of the StringKeyedMapLogic implementations.
 *
 * @param <TYPE_LOGIC> The type of logic that the map will hold.
 * @param <TYPE_OUTPUT> The type of output that the logic will create.
 *
 * @author Ron Webb
 * @since 2.2.0
 */
public abstract class AbstractStringKeyedMapLogic<TYPE_LOGIC, TYPE_OUTPUT> {

    protected final Map<String, TYPE_LOGIC> internalMap;
    protected final TYPE_LOGIC defaultLogic;

    /**
     * Creates an instance of AbstractStringKeyedMapLogic
     *
     * @param map An external map that will be used as storage of logic.
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public AbstractStringKeyedMapLogic(Map<String, TYPE_LOGIC> map, TYPE_LOGIC defaultLogic, Map.Entry<String, TYPE_LOGIC> ... logics) {
        this.internalMap = Optional.ofNullable(map).orElse(new HashMap<>());
        this.defaultLogic = handleDefaultLogicConstructorArgument(defaultLogic);
        Optional.ofNullable(logics).ifPresent(___logics -> Arrays.asList(___logics).forEach(___logic ->  internalMap.put(___logic.getKey(), ___logic.getValue())));
    }

    /**
     * Creates an instance of AbstractStringKeyedMapLogic
     *
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public AbstractStringKeyedMapLogic(TYPE_LOGIC defaultLogic, Map.Entry<String, TYPE_LOGIC> ... logics) {
        this(null, defaultLogic, logics);
    }

    /**
     * Creates an instance of AbstractStringKeyedMapLogic
     *
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public AbstractStringKeyedMapLogic(Map.Entry<String, TYPE_LOGIC> ... logics) {
        this(null, logics);
    }

    /**
     * Must have the implementation to handle the null defaultLogic.
     *
     * @param defaultLogic The default argument received from the constructor.
     *
     * @return The initial value of the internal defaultLogic.
     */
    protected abstract TYPE_LOGIC handleDefaultLogicConstructorArgument(TYPE_LOGIC defaultLogic);

    /**
     * Must have the implementation on how to get the logic based on a key and must be able to handle
     * if that key doesn't exists.
     *
     * @param key The target key of the corresponding logic.
     *
     * @return An optional logic implementation.
     */
    public abstract Optional<TYPE_OUTPUT> get(String key);

}
