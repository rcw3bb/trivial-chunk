package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.functional.WhenThen;
import xyz.ronella.trivial.functional.WhenThenReturn;

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
     *
     * @deprecated Use addWhen(TYPE_ELEMENT) instead.
     */
    @Deprecated
    public boolean add(final BooleanSupplier when, final TYPE_ELEMENT element) {
        return addWhen(element).when(when);
    }

    /**
     * Add element to a wrapped list.
     *
     * @param when The logic that must return true to add the element.
     * @param generateElement The logic that will generate the element.
     * @return Specified in List.add
     *
     * @deprecated Use addWhen(Supplier<TYPE_ELEMENT>) instead.
     */
    @Deprecated
    public boolean add(final BooleanSupplier when, final Supplier<TYPE_ELEMENT> generateElement) {
        return addWhen(generateElement).when(when);
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
     *
     * @deprecated Use addAllWhen(Collection<? extends TYPE_ELEMENT> elements) instead.
     */
    @Deprecated
    public boolean addAll(final BooleanSupplier when, final Collection<? extends TYPE_ELEMENT> elements) {
        return addAllWhen(elements).when(when);
    }

    /**
     * Add all the elements when the condition was met.
     * @param elements The elements to be added.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     */
    public WhenThenReturn<Boolean> addAllWhen(final Collection<? extends TYPE_ELEMENT> elements) {
        return (___when) -> ___when.getAsBoolean() && list.addAll(elements);
    }

    /**
     * Add elements to a wrapped list.
     *
     * @param when The logic that must return true to add the elements.
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     *
     * @deprecated Use addAllWhen(Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) instead.
     */
    @Deprecated
    public boolean addAll(final BooleanSupplier when,
                          final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        return addAllWhen(generateElements).when(when);
    }

    /**
     * Add all the elements when the condition was met.
     * @param generateElements The logic that will generate the elements.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     */
    public WhenThenReturn<Boolean> addAllWhen(final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        return (___when) -> ___when.getAsBoolean() && list.addAll(generateElements.get());
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
     *
     * @deprecated Use addWhen(int, TYPE_ELEMENT) instead.
     */
    @Deprecated
    public void add(final BooleanSupplier when, final int index, final TYPE_ELEMENT element) {
        addWhen(index, element).when(when);
    }

    /**
     * Add element to a specific index of the wrapped list.
     * @param when The logic that must return true to add the element.
     * @param index Specified in List.add
     * @param generateElement The logic that will generate the element.
     *
     * @deprecated Use addWhen(int, Supplier<TYPE_ELEMENT>) instead.
     */
    @Deprecated
    public void add(final BooleanSupplier when, final int index, final Supplier<TYPE_ELEMENT> generateElement) {
        addWhen(index, generateElement).when(when);
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
     *
     * @deprecated Use addAllWhen(int index, Collection<? extends TYPE_ELEMENT> elements) instead.
     */
    @Deprecated
    public boolean addAll(final BooleanSupplier when, final int index,
                          final Collection<? extends TYPE_ELEMENT> elements) {
        return addAllWhen(index, elements).when(when);
    }

    /**
     * Add all the elements when the condition was met.
     * @param index The index to add the elements.
     * @param elements The elements to be added.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     */
    public WhenThenReturn<Boolean> addAllWhen(final int index,
                                              final Collection<? extends TYPE_ELEMENT> elements) {
        return (___when) -> ___when.getAsBoolean() && list.addAll(index, elements);
    }

    /**
     * Add elements to a specified index of the wrapped list.
     * @param when The logic that must return true to add the elements.
     * @param index Specified in List.add
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     *
     * @deprecated Use addAllWhen(int index, Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) instead.
     */
    @Deprecated
    public boolean addAll(final BooleanSupplier when, final int index,
                          final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        return addAllWhen(index, generateElements).when(when);
    }

    /**
     * Add all the elements when the condition was met.
     * @param index The index to add the elements.
     * @param generateElements The logic that will generate the elements.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     */
    public WhenThenReturn<Boolean> addAllWhen(final int index,
                                              final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        return (___when) -> ___when.getAsBoolean() && list.addAll(index, generateElements.get());
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


    /**
     * Add an item when condition was met.
     * @param element The element to add conditionally.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     */
    public WhenThenReturn<Boolean> addWhen(final TYPE_ELEMENT element) {
        return ___when -> {
            if (___when.getAsBoolean()) {
                return list.add(element);
            }
            return false;
        };
    }

    /**
     * Add an item when condition was met.
     * @param index The exact location where to add the element.
     * @param element The element to add conditionally.
     * @return An implementation of WhenThen that update the list if the condition was met.
     */
    public WhenThen addWhen(final int index, final TYPE_ELEMENT element) {
        return ___when -> {
            if (___when.getAsBoolean()) {
                list.add(index, element);
            }
        };
    }

    /**
     * Add the generated element when condition was met.
     * @param generateElement The logic that will generate the element.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     */
    public WhenThenReturn<Boolean> addWhen(final Supplier<TYPE_ELEMENT> generateElement) {
        return ___when -> ___when.getAsBoolean() && list.add(generateElement.get());
    }

    /**
     * Add the generated element when condition was met.
     * @param index The exact location where to add the element.
     * @param generateElement The logic that will generate the element.
     * @return An implementation of WhenThen that update the list if the condition was met.
     */
    public WhenThen addWhen(final int index, final Supplier<TYPE_ELEMENT> generateElement) {
        return ___when -> {
            if (___when.getAsBoolean()) {
                list.add(index, generateElement.get());
            }
        };
    }

}
