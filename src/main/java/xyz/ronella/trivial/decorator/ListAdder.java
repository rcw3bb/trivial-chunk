package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.functional.WhenThen;
import xyz.ronella.trivial.functional.WhenThenReturn;
import xyz.ronella.trivial.handy.Require;

import java.util.Collection;
import java.util.List;
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
        Require.objects(list);
        this.list = list;
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
        Require.objects(generateElement);
        final var element = generateElement.get();
        return list.add(element);
    }

    /**
     * Add all the elements when the condition was met.
     * @param elements The elements to be added.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     *
     * @since 2.16.0
     */
    public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> addAllWhen(final Collection<? extends TYPE_ELEMENT> elements) {
        return (___when) -> {
            Require.objects(___when);
            return ___when.test(list) && list.addAll(elements);
        };
    }

    /**
     * Add all the elements when the condition was met.
     * @param generateElements The logic that will generate the elements.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     *
     * @since 2.16.0
     */
    public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> addAllWhen(final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        Require.objects(generateElements);
        return (___when) -> {
            Require.objects(___when);
            return ___when.test(list) && list.addAll(generateElements.get());
        };
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
        Require.objects(generateElements);
        final var elements = generateElements.get();
        return list.addAll(elements);
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
        Require.objects(generateElement);
        final var element = generateElement.get();
        list.add(index, element);
    }

    /**
     * Add all the elements when the condition was met.
     * @param index The index to add the elements.
     * @param elements The elements to be added.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     *
     * @since 2.16.0
     */
    public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> addAllWhen(final int index,
                                              final Collection<? extends TYPE_ELEMENT> elements) {
        return (___when) -> {
            Require.objects(___when);
            return ___when.test(list) && list.addAll(index, elements);
        };
    }

    /**
     * Add all the elements when the condition was met.
     * @param index The index to add the elements.
     * @param generateElements The logic that will generate the elements.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     *
     * @since 2.16.0
     */
    public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> addAllWhen(final int index,
                                              final Supplier<Collection<? extends TYPE_ELEMENT>> generateElements) {
        Require.objects(generateElements);
        return (___when) -> {
            Require.objects(___when);
            return ___when.test(list) && list.addAll(index, generateElements.get());
        };
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
        Require.objects(generateElements);
        final var elements = generateElements.get();
        return list.addAll(index, elements);
    }


    /**
     * Add an item when condition was met.
     * @param element The element to add conditionally.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     *
     * @since 2.16.0
     */
    public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> addWhen(final TYPE_ELEMENT element) {
        return ___when -> {
            Require.objects(___when);
            return ___when.test(list) && list.add(element);
        };
    }

    /**
     * Add an item when condition was met.
     * @param index The exact location where to add the element.
     * @param element The element to add conditionally.
     * @return An implementation of WhenThen that update the list if the condition was met.
     *
     * @since 2.16.0
     */
    @SuppressWarnings("PMD.SimplifyBooleanReturns")
    public WhenThen<List<TYPE_ELEMENT>> addWhen(final int index, final TYPE_ELEMENT element) {
        return ___when -> {
            Require.objects(___when);
            if (___when.test(list)) {
                list.add(index, element);
            }
        };
    }

    /**
     * Add the generated element when condition was met.
     * @param generateElement The logic that will generate the element.
     * @return An implementation of WhenThenReturn that returns true if the list changed.
     *
     * @since 2.16.0
     */
    @SuppressWarnings("PMD.SimplifyBooleanReturns")
    public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> addWhen(final Supplier<TYPE_ELEMENT> generateElement) {
        Require.objects(generateElement);
        return ___when -> {
            Require.objects(___when);
            return ___when.test(list) && list.add(generateElement.get());
        };
    }

    /**
     * Add the generated element when condition was met.
     * @param index The exact location where to add the element.
     * @param generateElement The logic that will generate the element.
     * @return An implementation of WhenThen that update the list if the condition was met.
     */
    public WhenThen<List<TYPE_ELEMENT>> addWhen(final int index, final Supplier<TYPE_ELEMENT> generateElement) {
        return ___when -> {
            Require.objects(___when);
            if (___when.test(list)) {
                list.add(index, generateElement.get());
            }
        };
    }

}
