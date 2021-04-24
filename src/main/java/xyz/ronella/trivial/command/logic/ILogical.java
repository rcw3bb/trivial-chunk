package xyz.ronella.trivial.command.logic;

import xyz.ronella.trivial.command.Sink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * The framework of implementing a logical command.
 *
 * @author Ron Webb
 *
 * @since 2.0.0
 */
public interface ILogical extends Consumer<Sink>, BiConsumer<Sink, Sink> {
}
