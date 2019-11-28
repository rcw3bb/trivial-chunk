package xyz.ronella.trivial.command;

import java.util.function.*;

public class Invoker {

    private Invoker() {}

    public static <TYPE_ARG1, TYPE_ARG2> void execute(
            BiConsumer<TYPE_ARG1, TYPE_ARG2> logic, TYPE_ARG1 param1, TYPE_ARG2 param2)
    {
        logic.accept(param1, param2);
    }

    public static <TYPE_ARG1, TYPE_ARG2, TYPE_RETURN>
    TYPE_RETURN process(BiFunction<TYPE_ARG1, TYPE_ARG2, TYPE_RETURN> logic, TYPE_ARG1 arg1, TYPE_ARG2 arg2) {
        return logic.apply(arg1, arg2);
    }

    public static <TYPE> void execute(Consumer<TYPE> logic, TYPE param) {
        logic.accept(param);
    }

    public static <TYPE_ARG, TYPE_RETURN> TYPE_RETURN process(Function<TYPE_ARG, TYPE_RETURN> logic, TYPE_ARG arg) {
        return logic.apply(arg);
    }

    public static <TYPE_RETURN> TYPE_RETURN generate(Supplier<TYPE_RETURN> logic) {
        return logic.get();
    }

    public static void plunge(Sink logic) {
        logic.plummet();
    }

}
