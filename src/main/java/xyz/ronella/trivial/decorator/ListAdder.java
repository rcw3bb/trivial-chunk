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
    public ListAdder(List<TYPE_ELEMENT> list) {
        this.list = list;
    }

    /**
     * Add element to a wrapped list.
     *
     * @param when The logic that must return true to add the element.
     * @param element Specified in List.add
     * @return Specified in List.add
     */
    public boolean add(BooleanSupplier when, TYPE_ELEMENT element) {
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
    public boolean add(BooleanSupplier when, Supplier<TYPE_ELEMENT> generateElement) {
        if (when.getAsBoolean()) {
            var element = generateElement.get();
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
    public boolean add(TYPE_ELEMENT element) {
        return list.add(element);
    }

    /**
     * Add element to a wrapped list.
     *
     * @param generateElement The logic that will generate the element.
     * @return Specified in List.add
     */
    public boolean add(Supplier<TYPE_ELEMENT> generateElement) {
        var element = generateElement.get();
        return list.add(element);
    }

    /**
     * Add elements to a wrapped list.
     *
     * @param when The logic that must return true to add the elements.
     * @param elements Specified in List.addAll
     * @return Specified in List.addAll
     */
    public boolean addAll(BooleanSupplier when, Collection<? extends TYPE_ELEMENT> elements) {
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
    public boolean addAll(BooleanSupplier when, Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        if (when.getAsBoolean()) {
            var elements = generateElements.get();
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
    public boolean addAll(Collection<? extends TYPE_ELEMENT> elements) {
        return list.addAll(elements);
    }

    /**
     * Add elements to a wrapped list.
     *
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     */
    public boolean addAll(Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        var elements = generateElements.get();
        return list.addAll(elements);
    }

    /**
     * Add element to a specific index of the wrapped list.
     * @param when The logic that must return true to add the element.
     * @param index Specified in List.add
     * @param element Specified in List.add
     */
    public void add(BooleanSupplier when, int index, TYPE_ELEMENT element) {
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
    public void add(BooleanSupplier when, int index, Supplier<TYPE_ELEMENT> generateElement) {
        if (when.getAsBoolean()) {
            var element = generateElement.get();
            list.add(index, element);
        }
    }

    /**
     * Add element to a specific index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param element Specified in List.add
     */
    public void add(int index, TYPE_ELEMENT element) {
        list.add(index, element);
    }

    /**
     * Add element to a specific index of the wrapped list.
     * @param index Specified in List.add
     * @param generateElement The logic that will generate the element.
     */
    public void add(int index, Supplier<TYPE_ELEMENT> generateElement) {
        var element = generateElement.get();
        list.add(index, element);
    }

    /**
     * Add elements to a specified index of the wrapped list.
     * @param when The logic that must return true to add the elements.
     * @param index Specified in List.add
     * @param elements Specified in List.add
     * @return Specified in List.addAll
     */
    public boolean addAll(BooleanSupplier when, int index, Collection<? extends TYPE_ELEMENT> elements) {
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
    public boolean addAll(BooleanSupplier when, int index, Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        if (when.getAsBoolean()) {
            var elements = generateElements.get();
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
    public boolean addAll(int index, Collection<? extends TYPE_ELEMENT> elements) {
        return list.addAll(index, elements);
    }

    /**
     * Add elements to a specified index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param generateElements The logic that will generate the elements.
     * @return Specified in List.addAll
     */
    public boolean addAll(int index, Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        var elements = generateElements.get();
        return list.addAll(index, elements);
    }

}
