package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListAdderTest {

    @Test
    public void addWhenTrue() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> true, expected.get(0));

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
        adder.add(()-> false, expected.get(0));

        assertEquals(0, testList.size());
    }

    @Test
    public void addIdxWhenTrue() {
        var expected = List.of("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> true, 0, expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
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
        adder.add(()-> false, 0, expected.get(0));

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
    public void addAllIdxWhenTrue() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAllWhen(0, expected).when(___-> true);

        assertArrayEquals(expected.toArray(), testList.toArray());
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
        adder.add(()-> true, ()-> "Generated");

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addGeneratedWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> false, ()-> "Generated");

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
    public void addAllGenerated() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll(()-> List.of("Generated"));

        assertEquals("Generated", testList.get(0));
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
    public void addAllGeneratedIdx() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll( 0, ()-> expected);

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addGeneratedIdxWhenTrue() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> true, 0, ()-> "Generated");

        assertEquals("Generated", testList.get(0));
    }

    @Test
    public void addGeneratedIdxWhenFalse() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> false, 0, ()-> "Generated");

        assertTrue(testList.isEmpty());
    }

    @Test
    public void addGeneratedIdx() {
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add( 0, ()-> "Generated");

        assertEquals("Generated", testList.get(0));
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

}
