package xyz.ronella.trivial.handy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class NPESilencer<TYPE_ROOT_OBJECT, TYPE_OUTPUT> {

    private TYPE_ROOT_OBJECT root;
    private List<Function> expressions;

    private NPESilencer(NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> builder) {
        this.root = builder.root;
        this.expressions = builder.expressions;
    }

    public static <TYPE_ROOT_OBJECT, TYPE_OUTPUT> NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> getBuilder() {
        return new NPESilencerBuilder<>();
    }

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

    public static class NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> {

        private final List<Function> expressions = new ArrayList<>();
        private TYPE_ROOT_OBJECT root;

        public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addRoot(TYPE_ROOT_OBJECT root) {
            this.root = root;
            return this;
        }

        public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addExpr(Function expression) {
            expressions.add(expression);
            return this;
        }

        public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addExpr(Supplier expression) {
            Optional.ofNullable(expression).ifPresent(___expression -> {
                expressions.add(___arg -> ___expression.get());
            });

            return this;
        }

        public NPESilencer<TYPE_ROOT_OBJECT, TYPE_OUTPUT> build() {
            return new NPESilencer<>(this);
        }
    }

    public static <TYPE_OUTPUT> TYPE_OUTPUT nullable(Supplier expression) {
        var silencer = NPESilencer.<Object, TYPE_OUTPUT>getBuilder().addExpr(expression).build();
        return silencer.evaluate();

    }
}
