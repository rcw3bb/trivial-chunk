package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.functional.Sink;

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

    /**
     * A list of BooleanSupplier.
     */
    protected List<BooleanSupplier> conditions;

    /**
     * The default truth logic
     */
    protected Sink defaultTruthLogic;

    /**
     * The default false logic.
     */
    protected Sink defaultFalseLogic;

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions A list of BooleanSupplier.
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     */
    public AbstractLogical(final List<BooleanSupplier> conditions, final Sink defaultTruthLogic,
                           final Sink defaultFalseLogic) {
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
    public AbstractLogical(final List<BooleanSupplier> conditions, final Sink defaultTruthLogic) {
        this(conditions, defaultTruthLogic, null);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions A list of BooleanSupplier.
     */
    public AbstractLogical(final List<BooleanSupplier> conditions) {
        this(conditions, null);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param defaultFalseLogic The default false logic.
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(final Sink defaultTruthLogic, final Sink defaultFalseLogic,
                           final BooleanSupplier ... conditions) {
        this(Arrays.asList(Optional.ofNullable(conditions).orElseThrow()), defaultTruthLogic, defaultFalseLogic);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param defaultTruthLogic The default truth logic.
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(final Sink defaultTruthLogic, final BooleanSupplier ... conditions) {
        this(defaultTruthLogic, null, conditions);
    }

    /**
     * Creates an instance of AbstractLogical.
     *
     * @param conditions An array of BooleanSupplier.
     */
    public AbstractLogical(final BooleanSupplier ... conditions) {
        this(null, conditions);
    }

    /**
     * The default implementation is just passing to the BiConsumer implementation.
     *
     * @param truthLogic Overrides the defaultTruthLogic if not null.
     */
    @Override
    public void accept(final Sink truthLogic) {
        accept(Optional.ofNullable(truthLogic).orElse(defaultTruthLogic), null);
    }

    /**
     * Determines the truth condition.
     *
     * @param conditions The list of conditions
     * @return True when the truthLogic must be called.
     */
    abstract public boolean getTruthCondition(List<BooleanSupplier> conditions);

    /**
     * Calculates if the truthLogic or falseLogic must be executed.
     *
     * @param truthLogic The logic to be executed when all the conditions are true.
     * @param falseLogic The logic to be executed when at least one condition is false.
     */
    @Override
    public void accept(final Sink truthLogic, final Sink falseLogic) {
        Optional.ofNullable(conditions).ifPresent(___condition -> {
            if (getTruthCondition(___condition)) {
                Optional.ofNullable(truthLogic).orElse(Optional.ofNullable(defaultTruthLogic).orElse(() -> {})).plummet();
            } else {
                Optional.ofNullable(falseLogic).orElse(Optional.ofNullable(defaultFalseLogic).orElse(() -> {})).plummet();
            }
        });
    }
}
