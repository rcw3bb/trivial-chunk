package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.functional.NoOperation;
import xyz.ronella.trivial.functional.WhenThen;
import xyz.ronella.trivial.functional.WhenThenReturn;
import xyz.ronella.trivial.handy.Require;
import xyz.ronella.trivial.handy.RequireObject;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * A class that wraps a Map to have put methods with condition.
 *
 * @param <TYPE_KEY>> The type of key that the map uses.
 * @param <TYPE_VALUE> The type of value that the key holds.
 *
 * @since 2.16.0
 * @author Ron Webb
 */
public class MapPutter<TYPE_KEY, TYPE_VALUE> {

    private final Map<TYPE_KEY, TYPE_VALUE> map;

    /**
     * Creates an instance of MapPutter.
     * @param map The map to wrap.
     */
    public MapPutter(final Map<TYPE_KEY, TYPE_VALUE> map) {
        Require.objects(map);
        this.map = map;
    }

    /**
     * Only place the key-value pair when the condition is true.
     * @param keyLogic The logic to generate the key.
     * @param valueLogic The logic to generate the value to associate with the key.
     * @return An implementation of WhenThenReturn that return previous value associated with the key when available if put is actually performed.
     */
    public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> putWhen(final Supplier<TYPE_KEY> keyLogic,
                                                                         final Supplier<TYPE_VALUE> valueLogic) {
        Require.objects(new RequireObject(keyLogic, "keyLogic cannot be null"),
                new RequireObject(valueLogic, "valueLogic cannot be null"));
        return ___when -> {
            Require.objects(___when);
            return ___when.test(map) ? map.put(keyLogic.get(), valueLogic.get()) : null;
        };
    }

    /**
     * Only place the key-value pair when the condition is true.
     * @param key The to key for the entry.
     * @param value The value associate with the key.
     * @return An implementation of WhenThenReturn that return previous value associated with the key when available if put is actually performed.
     */
    public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> putWhen(final TYPE_KEY key, final TYPE_VALUE value) {
        return putWhen(() -> key, () -> value);
    }

    /**
     * Only place the key-value pair when the condition is true.
     * @param keyLogic The logic to generate the key.
     * @param value The value associated with the key.
     * @return An implementation of WhenThenReturn that return previous value associated with the key when available if put is actually performed.
     */
    public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> putWhen(final Supplier<TYPE_KEY> keyLogic,
                                                                         final TYPE_VALUE value) {
        return putWhen(keyLogic, () -> value);
    }

    /**
     * Only place the key-value pair when the condition is true.
     * @param key The to key for the entry.
     * @param valueLogic The logic to generate the value to associate with the key.
     * @return An implementation of WhenThenReturn that return previous value associated with the key when available if put is actually performed.
     */
    public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> putWhen(final TYPE_KEY key, final Supplier<TYPE_VALUE> valueLogic) {
        return putWhen(() -> key, valueLogic);
    }

    /**
     * Only place absorb map when the condition is true.
     * @param mapLogic The logic to generate the map.
     * @return An implementation of WhenThen that accepts the condition if it is all good to absorb the map.
     */
    public WhenThen<Map<TYPE_KEY, TYPE_VALUE>> putAllWhen(final Supplier<Map<TYPE_KEY, TYPE_VALUE>> mapLogic) {
        Require.objects(mapLogic);
        final var valueSupplier = Optional.ofNullable(mapLogic).orElseGet(NoOperation::supplier);
        return ___when -> {
            Require.objects(___when);
            if (___when.test(map)) {
                map.putAll(valueSupplier.get());
            }
        };
    }

    /**
     * Only place absorb map when the condition is true.
     * @param map The map to absorb.
     * @return An implementation of WhenThen that accepts the condition if it is all good to absorb the map.
     */
    public WhenThen<Map<TYPE_KEY, TYPE_VALUE>> putAllWhen(final Map<TYPE_KEY, TYPE_VALUE> map) {
        return putAllWhen(() -> map);
    }

}
