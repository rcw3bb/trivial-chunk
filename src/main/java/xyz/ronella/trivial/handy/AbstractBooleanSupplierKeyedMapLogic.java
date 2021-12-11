package xyz.ronella.trivial.handy;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/**
 * The base class of the BooleanSupplierKeyedMapLogic implementations.
 *
 * @param <TYPE_LOGIC> The type of logic that the map will hold.
 * @param <TYPE_OUTPUT> The type of output that the logic will create.
 *
 * @author Ron Webb
 * @since 2.4.0
 */
public abstract class AbstractBooleanSupplierKeyedMapLogic<TYPE_LOGIC, TYPE_OUTPUT> extends AbstractKeyedMapLogic<BooleanSupplier, TYPE_LOGIC, TYPE_OUTPUT> {

    @SafeVarargs
    public AbstractBooleanSupplierKeyedMapLogic(Map<BooleanSupplier, TYPE_LOGIC> map, TYPE_LOGIC defaultLogic, Map.Entry<BooleanSupplier, TYPE_LOGIC> ... logics) {
        super(map, defaultLogic, Arrays.asList(logics));
    }

    public abstract Optional<TYPE_OUTPUT> get();
}
