package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.command.Sink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/**
 * A partial implementation of the logic command.
 *
 * @author Ron Webb
 * @since 2.0.0
 */
public abstract class AbstractLogical implements ILogical {

    protected List<BooleanSupplier> conditions;

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions A list of BooleanSupplier.
     */
    public AbstractLogical(List<BooleanSupplier> conditions) {
        this.conditions = Optional.ofNullable(conditions).orElse(new ArrayList<>());
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(BooleanSupplier ... conditions) {
        this(Arrays.asList(Optional.ofNullable(conditions).orElseThrow()));
    }

    /**
     * The default implementation is just passing the BiConsumer implementation.
     *
     * @param truthLogic
     */
    @Override
    public void accept(Sink truthLogic) {
        accept(truthLogic, null);
    }
}
