package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListAdderTest {

    @Test
    public void addWhenTrue() {
        var expected = Arrays.asList("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> true, expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addOnly() {
        var expected = Arrays.asList("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addWhenFalse() {
        var expected = Arrays.asList("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> false, expected.get(0));

        assertEquals(0, testList.size());
    }

    @Test
    public void addIdxWhenTrue() {
        var expected = Arrays.asList("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(()-> true, 0, expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addIdxOnly() {
        var expected = Arrays.asList("Test");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.add(0, expected.get(0));

        assertArrayEquals(expected.toArray(), testList.toArray());
    }

    @Test
    public void addIdxWhenFalse() {
        var expected = Arrays.asList("Test");
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
        adder.addAll(()-> true, expected);

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
        adder.addAll(()-> false, expected);

        assertEquals(0, testList.size());
    }

    @Test
    public void addAllIdxWhenTrue() {
        var expected = Arrays.asList("Test1", "Test2");
        var testList = new ArrayList<String>();
        var adder = new ListAdder<>(testList);
        adder.addAll(()-> true, 0, expected);

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
        adder.addAll(()-> false, 0, expected);

        assertEquals(0, testList.size());
    }

}
