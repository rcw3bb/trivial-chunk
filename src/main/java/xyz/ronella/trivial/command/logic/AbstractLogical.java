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
    protected Sink defaultTruthLogic;
    protected Sink defaultFalseLogic;

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     */
    public AbstractLogical(List<BooleanSupplier> conditions, Sink defaultTruthLogic, Sink defaultFalseLogic) {
        this.conditions = Optional.ofNullable(conditions).orElse(new ArrayList<>());
        this.defaultTruthLogic = defaultTruthLogic;
        this.defaultFalseLogic = defaultFalseLogic;
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     */
    public AbstractLogical(List<BooleanSupplier> conditions, Sink defaultTruthLogic) {
        this(conditions, defaultTruthLogic, null);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions A list of BooleanSupplier.
     */
    public AbstractLogical(List<BooleanSupplier> conditions) {
        this(conditions, null);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(Sink defaultTruthLogic, Sink defaultFalseLogic, BooleanSupplier ... conditions) {
        this(Arrays.asList(Optional.ofNullable(conditions).orElseThrow()), defaultTruthLogic, defaultFalseLogic);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(Sink defaultTruthLogic, BooleanSupplier ... conditions) {
        this(defaultTruthLogic, null, conditions);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(BooleanSupplier ... conditions) {
        this(null, conditions);
    }

    /**
     * The default implementation is just passing the BiConsumer implementation.
     *
     * @param truthLogic
     */
    @Override
    public void accept(Sink truthLogic) {
        accept(Optional.ofNullable(truthLogic).orElse(defaultTruthLogic), null);
    }

    /**
     * Determines the truth condition.
     *
     * @param conditions The list of conditions
     * @return True when the truthLogic must be called.
     */
    abstract public boolean getTruthCondition(final List<BooleanSupplier> conditions);

    /**
     * Calculates if the truthLogic or falseLogic must be executed.
     *
     * @param truthLogic The logic to be executed when all the conditions are true.
     * @param falseLogic The logic to be executed when at least one condition is false.
     */
    @Override
    public void accept(Sink truthLogic, Sink falseLogic) {
        Optional.ofNullable(conditions).ifPresent(___condition -> {
            if (getTruthCondition(___condition)) {
                Optional.ofNullable(truthLogic).orElse(Optional.ofNullable(defaultTruthLogic).orElse(() -> {})).plummet();
            } else {
                Optional.ofNullable(falseLogic).orElse(Optional.ofNullable(defaultFalseLogic).orElse(() -> {})).plummet();
            }
        });
    }
}
