package xyz.ronella.trivial.decorator;

import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * A class the wraps a List to have add methods with condition.
 *
 * @param <TYPE_ELEMENT>
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
     * Add element to a wrapped list. Added for convenience.
     *
     * @param element Specified in List.add
     * @return Specified in List.add
     */
    public boolean add(TYPE_ELEMENT element) {
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
     * Add elements to a wrapped list. Added for convenience.
     *
     * @param elements Specified in List.addAll
     * @return Specified in List.addAll
     */
    public boolean addAll(Collection<? extends TYPE_ELEMENT> elements) {
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
     * Add element to a specific index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param element Specified in List.add
     */
    public void add(int index, TYPE_ELEMENT element) {
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
     * Add elements to a specified index of the wrapped list. Added for convenience.
     * @param index Specified in List.add
     * @param elements Specified in List.add
     * @return Specified in List.addAll
     */
    public boolean addAll(int index, Collection<? extends TYPE_ELEMENT> elements) {
        return list.addAll(index, elements);
    }

}
