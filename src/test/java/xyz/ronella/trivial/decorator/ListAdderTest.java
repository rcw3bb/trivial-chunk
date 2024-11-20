package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.ObjectRequiredException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class ListAdderTest {

    @Test
    public void addWhenTrue() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(expected.get(0)).when(___ -> true);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addOnly() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addWhenFalse() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(expected.get(0)).when(___ -> false);

        assertEquals(0, testList.size());
    }

    @Test
    public void addIdxWhenTrue() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(0, expected.get(0)).when(___ -> true);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addIdxWhenNull() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addWhen(0, expected.get(0)).when(null));
    }

    @Test
    public void addIdxOnly() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(0, expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addIdxWhenFalse() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(0, expected.get(0)).when(___ -> false);

        assertEquals(0, testList.size());
    }

    @Test
    public void addAllWhenTrue() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(expected).when(___ -> true);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addAllOnly() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll(expected);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addAllWhenFalse() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(expected).when(___ -> false);

        assertEquals(0, testList.size());
    }

    @Test
    public void addAllWhenNull() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAllWhen(expected).when(null));
    }

    @Test
    public void addAllIdxWhenTrue() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(0, expected).when(___-> true);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addAllIdxWhenNull() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAllWhen(0, expected).when(null));
    }

    @Test
    public void addAllIdxOnly() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll(0, expected);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addAllIdxWhenFalse() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(0, expected).when(___ -> false);
        assertEquals(0, testList.size());
    }

    @Test
    public void addGeneratedWhenTrue() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(() -> "Generated").when(___ -> true);

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addGeneratedWhenNull() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, () -> adder.addWhen(()-> "Generated").when(null));
    }

    @Test
    public void addNullWhenTrue() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, () -> adder.addWhen((Supplier<String>) null).when(___ -> true));
    }

    @Test
    public void addGeneratedWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(()-> "Generated").when(___ -> false);

        assertTrue(testList.isEmpty());
    }

    @Test
    public void addGenerated() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> "Generated");

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addNullGenerated() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);

        assertThrows(ObjectRequiredException.class, ()-> adder.add((Supplier<String>) null));
    }

    @Test
    public void addAllGeneratedWhenTrue() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(()-> List.of("Generated")).when(___ -> true);

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addAllGeneratedWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(()-> List.of("Generated")).when(___ -> false);

        assertTrue(testList.isEmpty());
    }

    @Test
    public void addAllGeneratedWhenNull() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAllWhen(()-> List.of("Generated")).when(null));
    }

    @Test
    public void addAllNullWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAllWhen((Supplier<Collection<? extends String>>) null)
                .when(___ -> false));
    }

    @Test
    public void addAllGenerated() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll(()-> List.of("Generated"));

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addAllNullGenerated() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAll((Supplier<Collection<? extends String>>) null));
    }

    @Test
    public void addAllGeneratedIdxWhenTrue() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(0, ()-> expected).when(___ -> true);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addAllGeneratedIdxWhenFalse() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(0, ()-> expected).when(___ -> false);

        assertEquals(0, testList.size());
    }

    @Test
    public void addAllGeneratedIdxWhenNull() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAllWhen(0, ()-> expected).when(null));
    }

    @Test
    public void addAllNullIdxWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAllWhen(0, (Supplier<Collection<? extends String>>) null)
                .when(___ -> false));
    }

    @Test
    public void addAllGeneratedIdx() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll( 0, ()-> expected);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addAllNullGeneratedIdx() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addAll( 0, (Supplier<Collection<? extends String>>) null));
    }

    @Test
    public void addGeneratedIdxWhenTrue() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(0, ()-> "Generated").when(___-> true);

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addGeneratedIdxWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen(0, ()-> "Generated").when(___-> false);

        assertTrue(testList.isEmpty());
    }

    @Test
    public void addGeneratedIdxWhenNull() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addWhen(0, ()-> "Generated").when(null));
    }

    @Test
    public void addGeneratedIdx() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add( 0, ()-> "Generated");

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addGeneratedNullSupplier() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.add( 0, (Supplier<String>) null));
    }

    @Test
    public void addElementWhenTrue() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen( "Element").when(___ -> Boolean.TRUE);
        assertEquals("Element", testList.get(0));
    }

    @Test
    public void addElementWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addWhen( "Element").when(___ -> Boolean.FALSE);
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testDocumentation() {
        var list = new ArrayList<String>();
        var lstAdder = new ListAdder<>(list);
        lstAdder.addWhen("Sample1").when(___-> true);
        lstAdder.addWhen("Sample2").when(___-> false);
        lstAdder.addWhen("Sample3").when(___-> true);
        System.out.printf("List: %s%n", list);
    }

    @Test
    public void testNullList() {
        assertThrows(ObjectRequiredException.class, ()-> new ListAdder<>(null));
    }

    @Test
    public void addElementWhenNull() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        assertThrows(ObjectRequiredException.class, ()-> adder.addWhen( "Element").when(null));
    }

}
