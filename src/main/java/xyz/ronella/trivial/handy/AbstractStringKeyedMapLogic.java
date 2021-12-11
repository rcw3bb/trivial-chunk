package xyz.ronella.trivial.handy;

import java.util.Arrays;
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
public abstract class AbstractStringKeyedMapLogic<TYPE_LOGIC, TYPE_OUTPUT> extends AbstractKeyedMapLogic<String, TYPE_LOGIC, TYPE_OUTPUT> {
    public static final String DEFAULT_LOGIC = "___DEFAULT_LOGIC__";

    /**
     * Creates an instance of AbstractStringKeyedMapLogic
     *
     * @param map An external map that will be used as storage of logic.
     * @param defaultLogic The default create logic if the key used was not in the map.
     * @param logics An arrays of create logic mapped to key.
     */
    @SafeVarargs
    public AbstractStringKeyedMapLogic(Map<String, TYPE_LOGIC> map, TYPE_LOGIC defaultLogic, Map.Entry<String, TYPE_LOGIC> ... logics) {
        super(map, defaultLogic, Arrays.asList(logics));
        this.internalMap.put(DEFAULT_LOGIC, defaultLogic);
    }

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
