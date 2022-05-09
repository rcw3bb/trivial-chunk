package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegExMatcherTest {

    @Test
    public void matchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("Hello world", "(\\w*)\\s(\\w*)",
                (___matcher) -> sbText.append(___matcher.group(1)),
                () -> sbText.append("test"));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void noMatchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("Hello world", "test",
                (___matcher) -> sbText.append(___matcher.group(1)),
                () -> sbText.append("test"));

        assertEquals("test", sbText.toString());
    }

    @Test
    public void runtimeExceptionFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findByRegex("Hello world", "test",
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
        var matcher = RegExMatcher.findByRegex("Hello world", "(\\w*)\\s(\\w*)");
        assertEquals("Hello", matcher.group(1));
    }

}
