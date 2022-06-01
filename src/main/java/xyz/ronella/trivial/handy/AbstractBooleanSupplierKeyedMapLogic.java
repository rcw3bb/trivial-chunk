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

    /**
     * A standard way of creating an instance of AbstractBooleanSupplierKeyedMapLogic.
     * @param map The map that holds the keyed map logics.
     * @param defaultLogic The default logic if no matching key was found.
     * @param logics The logics that will be stored to the map parameter.
     */
    @SafeVarargs
    public AbstractBooleanSupplierKeyedMapLogic(final Map<BooleanSupplier, TYPE_LOGIC> map,
                                                final TYPE_LOGIC defaultLogic,
                                                final Map.Entry<BooleanSupplier, TYPE_LOGIC> ... logics) {
        super(map, defaultLogic, Arrays.asList(logics));
    }

    /**
     * Returns the calculated output.
     * @return The typed output.
     */
    public abstract Optional<TYPE_OUTPUT> get();
}
