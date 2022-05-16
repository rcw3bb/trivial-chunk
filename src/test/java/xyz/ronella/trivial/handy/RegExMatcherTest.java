package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegExMatcherTest {

    @Test
    public void findMatchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findWithMatchLogic( "(\\w*)\\s(\\w*)", "Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)),
                (___matcher) -> sbText.append("test"));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void findMatchLogicFoundOnly() {
        var sbText = new StringBuilder();
        RegExMatcher.findWithMatchLogic("(\\w*)\\s(\\w*)", "Hello world",
                (Consumer<Matcher>) (___matcher) -> sbText.append(___matcher.group(1)));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void findMatchLogicWithException() {
        var sbText = new StringBuilder();
        RegExMatcher.findWithMatchLogic("(\\w*)\\s(\\w*)", "Hello world",
                (___matcher) -> {
                    throw new RuntimeException("Exception");
                },
                (___exception) -> sbText.append("Exception"));

        assertEquals("Exception", sbText.toString());
    }

    @Test
    public void findNoMatchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findWithNoMatchLogic("test","Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)),
                (___matcher) -> sbText.append("test"));

        assertEquals("test", sbText.toString());
    }

    @Test
    public void findRuntimeExceptionFound() {
        var sbText = new StringBuilder();
        RegExMatcher.findWithNoMatchLogic("test","Hello world",
                (___matcher) -> sbText.append(___matcher.group(1)),
                (___matcher) -> {
                    throw new RuntimeException("Error test");
                },
                ___exception -> sbText.append(___exception.getMessage())
        );

        assertEquals("Error test", sbText.toString());
    }


    @Test
    public void findMatcherLogicFound() {
        var matcher = RegExMatcher.find("(\\w*)\\s(\\w*)", "Hello world");
        assertEquals("Hello", matcher.group(1));
    }

    @Test
    public void matchMatchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.matchWithMatchLogic( "(\\w*)\\s(\\w*)", "Hello world",
                Matcher::find,
                (___matcher) -> sbText.append(___matcher.group(1)),
                (___matcher) -> sbText.append("test"));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void matchMatchLogicFoundOnly() {
        var sbText = new StringBuilder();
        RegExMatcher.matchWithMatchLogic("(\\w*)\\s(\\w*)", "Hello world",
                Matcher::find, (___matcher) -> sbText.append(___matcher.group(1)));

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void matchMatchLogicWithException() {
        var sbText = new StringBuilder();
        RegExMatcher.matchWithMatchLogic("(\\w*)\\s(\\w*)", "Hello world",
                Matcher::find,
                (___matcher) -> {
                    throw new RuntimeException("Exception");
                },
                (___exception) -> sbText.append("Exception"));

        assertEquals("Exception", sbText.toString());
    }

    @Test
    public void matchNoMatchLogicFound() {
        var sbText = new StringBuilder();
        RegExMatcher.matchWithNoMatchLogic("test","Hello world",
                Matcher::find,
                (___matcher) -> sbText.append(___matcher.group(1)),
                (___matcher) -> sbText.append("test"));

        assertEquals("test", sbText.toString());
    }

    @Test
    public void matchRuntimeExceptionFound() {
        var sbText = new StringBuilder();
        RegExMatcher.matchWithNoMatchLogic("test","Hello world",
                Matcher::find,
                (___matcher) -> sbText.append(___matcher.group(1)),
                (___matcher) -> {
                    throw new RuntimeException("Error test");
                },
                ___exception -> sbText.append(___exception.getMessage())
        );

        assertEquals("Error test", sbText.toString());
    }

    @Test
    public void matchMatcherLogicFound() {
        var matcher = RegExMatcher.match("(\\w*)\\s(\\w*)", "Hello world", Matcher::find);
        assertEquals("Hello", matcher.group(1));
    }

    @Test
    public void matchExceptionFound() {
        var sbText = new StringBuilder();
        RegExMatcher.match("test","Hello world",
                (___matcher) -> {
                    throw new RuntimeException("Error test");
                },
                ___exception -> sbText.append(___exception.getMessage())
        );

        assertEquals("Error test", sbText.toString());
    }

}
