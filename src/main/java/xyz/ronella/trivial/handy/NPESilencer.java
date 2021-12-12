package xyz.ronella.trivial.handy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A handy class for silencing the NPE. This means if it detects a NPE was thrown from the expression
 * then the overall expression will be null.
 *
 * @param <TYPE_ROOT_OBJECT> The type of the root object of the expression.
 * @param <TYPE_OUTPUT> The type of output that the expression will produce.
 *
 * @author Ron Webb
 * @since 2.3.0
 */
@SuppressWarnings("unchecked")
public class NPESilencer<TYPE_ROOT_OBJECT, TYPE_OUTPUT> {

    private TYPE_ROOT_OBJECT root;
    private List<Function> expressions;

    private NPESilencer(NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> builder) {
        this.root = builder.root;
        this.expressions = builder.expressions;
    }

    /**
     * Creates the builder of the NPESilencer.
     *
     * @param <TYPE_ROOT_OBJECT> The type of the root object of the expression.
     * @param <TYPE_OUTPUT> The type of output that the expression will produce.
     *
     * @return An instance of NPESilencerBuilder
     */
    public static <TYPE_ROOT_OBJECT, TYPE_OUTPUT> NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> getBuilder() {
        return new NPESilencerBuilder<>();
    }

    /**
     * Evaluate all the expressions and detecting NPE.
     *
     * @return The desired output of the last expression.
     */
    public TYPE_OUTPUT evaluate() {
        Object arg = root;
        for (Function expression : expressions) {
            try {
                arg = expression.apply(arg);
            }
            catch(NullPointerException npe) {
                arg = null;
                break;
            }
        }
        return (TYPE_OUTPUT) arg;
    }

    /**
     * The only class that can create NPESilencer.
     *
     * @param <TYPE_ROOT_OBJECT> The type of the root object of the expression.
     * @param <TYPE_OUTPUT> The type of output that the expression will produce.
     */
    public static class NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> {

        private final List<Function> expressions = new ArrayList<>();
        private TYPE_ROOT_OBJECT root;

        /**
         * Add the root object of all the expressions. This can be called just once. If you call it again,
         * it will overwrite the existing root object.
         *
         * @param root The instance of the root object expression.
         *
         * @return An instance of the builder.
         */
        public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addRoot(TYPE_ROOT_OBJECT root) {
            this.root = root;
            return this;
        }

        /**
         * Add an expression to be evaluated. This can be called multiple times.
         * This will create a chain of expression evaluations where the output of earlier
         * expression will be the argument of the following expression.
         *
         * @param expression The instance of the root object expression.
         *
         * @return An instance of the builder.
         */
        public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addExpr(Function expression) {
            expressions.add(expression);
            return this;
        }

        /**
         * Add an expression to be evaluated. This can be called multiple times.
         * This will create a chain of expression evaluations where the output of earlier
         * expression will be ignored by the following expression.
         *
         * @param expression The instance of the root object expression.
         *
         * @return An instance of the builder.
         */
        public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addExpr(Supplier expression) {
            Optional.ofNullable(expression).ifPresent(___expression -> {
                expressions.add(___arg -> ___expression.get());
            });

            return this;
        }

        /**
         * Actually creates an instance of NPESilencer.
         *
         * @return An instance of NPESilencer.
         */
        public NPESilencer<TYPE_ROOT_OBJECT, TYPE_OUTPUT> build() {
            return new NPESilencer<>(this);
        }
    }

    /**
     * A convenience method for evaluating a single expression (i.e. the complexity of the expression
     * depends on how you construct it) that potentially throws an NPE.
     *
     * @param expression An expression that can potentially throws an NPE
     * @param <TYPE_OUTPUT> The output expression.
     *
     * @return The output of the expression.
     */
    public static <TYPE_OUTPUT> TYPE_OUTPUT nullable(Supplier expression) {
        var silencer = NPESilencer.<Object, TYPE_OUTPUT>getBuilder().addExpr(expression).build();
        return silencer.evaluate();

    }
}
