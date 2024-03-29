package xyz.ronella.trivial.handy;

import xyz.ronella.trivial.functional.Sink;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Creates a map of conditions and logic where all the conditions that evaluated to true will perform its
 * corresponding logic.
 *
 * @param <TYPE_OUTPUT> Specifies the actual type of the output method.
 *
 * @author Ron Webb
 * @since 2019-12-01
 */
public final class LogicMapper<TYPE_OUTPUT> {

    final private Map<BooleanSupplier, Sink> logicMap;
    final private Sink initialLogic;
    final private Supplier<TYPE_OUTPUT> finalLogic;

    private LogicMapper(final LogicMapperBuilder<TYPE_OUTPUT> builder) {
        this.logicMap = Optional.ofNullable(builder.logicMap).orElse(new LinkedHashMap<>());
        this.initialLogic = Optional.ofNullable(builder.initialLogic).orElse(()->{});
        this.finalLogic = Optional.ofNullable(builder.finalLogic).orElse(()-> null);
    }

    /**
     * Executes the collection of logic with return the finalLogic output.
     *
     * @return An instance of TYPE_OUTPUT
     *
     * @since 1.3.0
     */
    public Optional<TYPE_OUTPUT> output() {
        initialLogic.plummet();
        logicMap.forEach((___condition, ___logic) -> {
            if (Optional.ofNullable(___condition).orElse(()->Boolean.FALSE).getAsBoolean()) {
                ___logic.plummet();
            }
        });
        return Optional.ofNullable(finalLogic.get());
    }

    /**
     * Executes the collection of logic
     */
    public void execute() {
        output();
    }

    /**
     * A class that can build an instance of LogicMapper.
     */
    public static class LogicMapperBuilder<TYPE_OUTPUT> {

        final private Map<BooleanSupplier, Sink> logicMap;
        private Sink initialLogic;
        private Supplier<TYPE_OUTPUT> finalLogic;
        final private AtomicInteger inlineIdx;

        /**
         * Creates an instance that can build a LogicMapper instance.
         */
        public LogicMapperBuilder() {
            inlineIdx = new AtomicInteger();
            logicMap = new LinkedHashMap<>();
        }

        /**
         * Add a logic to the collection.
         *
         * @param condition A Boolean Supplier.
         * @param logic The logic to be executed if the condition is evaluated to true.
         * @return The builder instance.
         */
        public LogicMapperBuilder<TYPE_OUTPUT> addLogic(final BooleanSupplier condition, final Sink logic) {
            logicMap.put(condition, logic);
            return this;
        }

        /**
         * Add a logic that will definitely added.
         * @param logic The logic to be executed.
         *
         * @return The builder instance.
         *
         * @since 2.0.0
         */
        public LogicMapperBuilder<TYPE_OUTPUT> addInlineLogic(final Sink logic) {
            logicMap.put(()-> {
                final Predicate<Integer> dummy = __ -> Boolean.TRUE;
                return dummy.test(inlineIdx.incrementAndGet());
            }, logic);
            return this;
        }

        /**
         * The very first logic to execute before the first addLogic execution.
         *
         * @param logic The very first  logic.
         * @return The builder instance
         *
         * @since 1.3.0
         */
        public LogicMapperBuilder<TYPE_OUTPUT> addInitialLogic(final Sink logic) {
            this.initialLogic = logic;
            return this;
        }

        /**
         * The very last logic to execute after the last addLogic execution.
         *
         * @param logic The very first  logic.
         * @return The builder instance
         *
         * @since 1.3.0
         */
        public LogicMapperBuilder<TYPE_OUTPUT> addFinalLogic(final Supplier<TYPE_OUTPUT> logic) {
            this.finalLogic = logic;
            return this;
        }

        /**
         * Builds an instance of LogicMapper.
         *
         * @return An instance of LogicMapper.
         */
        public LogicMapper<TYPE_OUTPUT> build() {
            return new LogicMapper<>(this);
        }

    }

    /**
     * Build an instance of LogicMapper.
     *
     * @param <TYPE_OUTPUT> Identifies the type of the output method.
     *
     * @return An instance of LogicMapper.
     */
    public static <TYPE_OUTPUT> LogicMapperBuilder<TYPE_OUTPUT> getBuilder() {
        return new LogicMapperBuilder<>();
    }

}
