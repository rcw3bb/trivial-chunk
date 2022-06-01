package xyz.ronella.trivial.decorator;

import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * A class the wraps a List to have add methods with condition.
 *
 * @param <TYPE_ELEMENT> The type of object that the list will hold.
 *
 * @since 2.10.0
 * @author Ron Webb
 */
public class ListAdder<TYPE_ELEMENT> {

    private final List<TYPE_ELEMENT> list;

    /**
     * Creates an instance of ListAdder
     * @param list An instance of List to wrap.
     */
    public ListAdder(final List<TYPE_ELEMENT> list) {
        this.list = list;
    }

    /**
     * Add element to a wrapped list.
     *
     * @param when The logic that must return true to add the element.
     * @param element Specified in List.add
     * @return Specified in List.add
     */
    public boolean add(final BooleanSupplier when, final TYPE_ELEMENT element) {
        if (when.getAsBoolean()) {
            return list.add(element);
        }
        return false;
    }

    /**
     * Add element to a wrapped list.
     *
     * @param when The logic that must return true to add the element.
     * @param generateElement The logic that will generate the element.
     * @return Specified in List.add
     */
    public boolean add(final BooleanSupplier when, final Supplier<TYPE_ELEMENT> generateElement) {
        if (when.getAsBoolean()) {
            final var element = generateElement.get();
            return list.add(element);
        }
        return false;
    }

    /**
     * Add element to a wrapped list. Added for convenience.
     *
     * @param element Specified in List.add
     * @return Specified in List.add
     */
    public boolean add(final TYPE_ELEMENT element) {
        return list.add(element);
    }

    /**
     * Add element to a wrapped list.
     *
     * @param generateElement The logic that will generate the element.
     * @return Specified in List.add
     */
    public boolean add(final Supplier<TYPE_ELEMENT> generateElement) {
        final var element = generateElement.get();
        return list.add(element);
    }

    /**
     * Add elements to a wrapped list.
     *
     * @param when The logic that must return true to add the elements.
     * @param elements Specified in List.addAll
     * @return Specified in List.addAll
     */
    public boolean addAll(final BooleanSupplier when, final Collection<? extends TYPE_ELEMENT> elements) {
        if (when.getAsBoolean()) {
            return list.addAll(elements);
        }
        return false;
    }

    /**
     * Add elements to a wrapped list.
     *
     * @param when The logic that must return true to add the elements.
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     */
    public boolean addAll(final BooleanSupplier when,
                          final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        if (when.getAsBoolean()) {
            final var elements = generateElements.get();
            return list.addAll(elements);
        }
        return false;
    }

    /**
     * Add elements to a wrapped list. Added for convenience.
     *
     * @param elements Specified in List.addAll
     * @return Specified in List.addAll
     */
    public boolean addAll(final Collection<? extends TYPE_ELEMENT> elements) {
        return list.addAll(elements);
    }

    /**
     * Add elements to a wrapped list.
     *
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     */
    public boolean addAll(final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        final var elements = generateElements.get();
        return list.addAll(elements);
    }

    /**
     * Add element to a specific index of the wrapped list.
     * @param when The logic that must return true to add the element.
     * @param index Specified in List.add
     * @param element Specified in List.add
     */
    public void add(final BooleanSupplier when, final int index, final TYPE_ELEMENT element) {
        if (when.getAsBoolean()) {
            list.add(index, element);
        }
    }

    /**
     * Add element to a specific index of the wrapped list.
     * @param when The logic that must return true to add the element.
     * @param index Specified in List.add
     * @param generateElement The logic that will generate the element.
     */
    public void add(final BooleanSupplier when, final int index, final Supplier<TYPE_ELEMENT> generateElement) {
        if (when.getAsBoolean()) {
            final var element = generateElement.get();
            list.add(index, element);
        }
    }

    /**
     * Add element to a specific index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param element Specified in List.add
     */
    public void add(final int index, final TYPE_ELEMENT element) {
        list.add(index, element);
    }

    /**
     * Add element to a specific index of the wrapped list.
     * @param index Specified in List.add
     * @param generateElement The logic that will generate the element.
     */
    public void add(final int index, final Supplier<TYPE_ELEMENT> generateElement) {
        final var element = generateElement.get();
        list.add(index, element);
    }

    /**
     * Add elements to a specified index of the wrapped list.
     * @param when The logic that must return true to add the elements.
     * @param index Specified in List.add
     * @param elements Specified in List.add
     * @return Specified in List.addAll
     */
    public boolean addAll(final BooleanSupplier when, final int index,
                          final Collection<? extends TYPE_ELEMENT> elements) {
        if (when.getAsBoolean()) {
            return list.addAll(index, elements);
        }
        return false;
    }

    /**
     * Add elements to a specified index of the wrapped list.
     * @param when The logic that must return true to add the elements.
     * @param index Specified in List.add
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     */
    public boolean addAll(final BooleanSupplier when, final int index,
                          final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        if (when.getAsBoolean()) {
            final var elements = generateElements.get();
            return list.addAll(index, elements);
        }
        return false;
    }

    /**
     * Add elements to a specified index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param elements Specified in List.add
     * @return Specified in List.addAll
     */
    public boolean addAll(final int index, final Collection<? extends TYPE_ELEMENT> elements) {
        return list.addAll(index, elements);
    }

    /**
     * Add elements to a specified index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     */
    public boolean addAll(final int index, final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        final var elements = generateElements.get();
        return list.addAll(index, elements);
    }

}
