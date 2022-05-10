package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegExMatcherTest {

    @Test
    public void matchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex( "(\\w*)\\s(\\w*)", "Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)),
                () -> sbText.append("test"));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void matchLogicFoundOnly() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("(\\w*)\\s(\\w*)", "Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void matchLogicWithException() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("(\\w*)\\s(\\w*)", "Hello world",
                (___matcher) -> {
                    throw new RuntimeException("Exception");
                },
                (___exception) -> sbText.append("Exception"));

        assertEquals("Exception", sbText.toString());
    }

    @Test
    public void noMatchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("test","Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)),
                () -> sbText.append("test"));

        assertEquals("test", sbText.toString());
    }

    @Test
    public void runtimeExceptionFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("test","Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)),
                () -> {
                    throw new RuntimeException("Error test");
                },
                ___exception -> sbText.append(___exception.getMessage())
        );

        assertEquals("Error test", sbText.toString());
    }


    @Test
    public void matcherLogicFound() {
        var matcher = RegExMatcher.findByRegex("(\\w*)\\s(\\w*)", "Hello world");
        assertEquals("Hello", matcher.group(1));
    }


}
