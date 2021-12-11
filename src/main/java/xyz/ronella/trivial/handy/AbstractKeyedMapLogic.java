package xyz.ronella.trivial.handy;

import java.util.*;

/**
 * The base class of the KeyedMapLogic implementations.
 *
 * @param <TYPE_KEY> The type of the key that can be matched.
 * @param <TYPE_LOGIC> The type of logic that the map will hold.
 * @param <TYPE_OUTPUT> The type of output that the logic will create.
 *
 * @author Ron Webb
 * @since 2.4.0
 */
public abstract class AbstractKeyedMapLogic<TYPE_KEY, TYPE_LOGIC, TYPE_OUTPUT> {
    protected final Map<TYPE_KEY, TYPE_LOGIC> internalMap;
    protected final TYPE_LOGIC defaultLogic;

    public AbstractKeyedMapLogic(Map<TYPE_KEY, TYPE_LOGIC> map, TYPE_LOGIC defaultLogic, List<Map.Entry<TYPE_KEY, TYPE_LOGIC>> logics) {
        this.internalMap = Optional.ofNullable(map).orElse(new LinkedHashMap<>());
        this.defaultLogic = handleDefaultLogicConstructorArgument(defaultLogic);

        Optional.ofNullable(logics).ifPresent(___logics -> ___logics.forEach(___logic ->  internalMap.put(___logic.getKey(), ___logic.getValue())));
    }

    /**
     * The default logic that will be executed if none of the keys matched.
     *
     * @return The default logic.
     */
    public TYPE_LOGIC getDefaultLogic() {
        return defaultLogic;
    }

    /**
     * Must have the implementation to handle the null defaultLogic.
     *
     * @param defaultLogic The default argument received from the constructor.
     *
     * @return The initial value of the internal defaultLogic.
     */
    protected abstract TYPE_LOGIC handleDefaultLogicConstructorArgument(TYPE_LOGIC defaultLogic);

}
