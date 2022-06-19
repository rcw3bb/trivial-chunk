package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.decorator.Mutable;
import xyz.ronella.trivial.handy.impl.MatcherConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegExMatcherTest {

    @Test
    public void findMatchLogicFound() {
        var sbText = new StringBuilder();

        RegExMatcher.match( "(\\w*)\\s(\\w*)", "Hello world", MatcherConfig.getBuilder()
                .setMatchLogic(Matcher::find)
                .setMatchFoundLogic((___matcher) -> sbText.append(___matcher.group(1)))
                .setExceptionLogic((___matcher) -> sbText.append("test"))
                .build());

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void findMatchLogicFoundOnly() {
        var sbText = new StringBuilder();

        RegExMatcher.match("(\\w*)\\s(\\w*)", "Hello world", MatcherConfig.getBuilder()
                .setMatchLogic(Matcher::find)
                .setMatchFoundLogic((___matcher) -> sbText.append(___matcher.group(1)))
                .build());

        assertEquals("Hello", sbText.toString());
    }

    @Test
    public void findMatchLogicWithException() {
        var sbText = new StringBuilder();

        RegExMatcher.match("(\\w*)\\s(\\w*)", "Hello world", MatcherConfig.getBuilder()
                .setMatchLogic(Matcher::find)
                .setMatchFoundLogic((___matcher) -> {
                            throw new RuntimeException("Exception");
                })
                .setExceptionLogic((___exception) -> sbText.append("Exception"))
                .build());

        assertEquals("Exception", sbText.toString());
    }

    @Test
    public void findNoMatchLogicFound() {
        var sbText = new StringBuilder();

        RegExMatcher.match("test","Hello world", MatcherConfig.getBuilder()
                .setMatchLogic(Matcher::find)
                .setMatchFoundLogic((___matcher) -> sbText.append(___matcher.group(1)))
                .setNoMatchFoundLogic((___matcher) -> sbText.append("test")).build());

        assertEquals("test", sbText.toString());
    }

    @Test
    public void findRuntimeExceptionFound() {
        var sbText = new StringBuilder();

        RegExMatcher.match("test","Hello world", MatcherConfig.getBuilder()
                .setMatchLogic(Matcher::find)
                .setMatchFoundLogic((___matcher) -> sbText.append(___matcher.group(1)))
                .setNoMatchFoundLogic((___matcher) -> {
                    throw new RuntimeException("Error test");
                })
                .setExceptionLogic(___exception -> sbText.append(___exception.getMessage())).build());

        assertEquals("Error test", sbText.toString());
    }


    @Test
    public void findMatcherLogicFound() {
        var matcher = RegExMatcher.match("(\\w*)\\s(\\w*)", "Hello world",
                MatcherConfig.getBuilder().setMatchLogic(Matcher::find).build());
        assertEquals("Hello", matcher.group(1));
    }

    @Test
    public void findConvenienceMatcherLogicFound() {
        var mutableString = new Mutable<>("");
        RegExMatcher.find("(\\w*)\\s(\\w*)", "Hello world",
                ___matcher -> mutableString.set(___matcher.group(1)));
        assertEquals("Hello", mutableString.get());
    }

    @Test
    public void findConvenienceMatcherLogicFoundWithFlag() {
        var mutableString = new Mutable<>("");
        RegExMatcher.find("(\\w*)\\s(\\w*)", "Hello world", Pattern.MULTILINE,
                ___matcher -> mutableString.set(___matcher.group(1)));
        assertEquals("Hello", mutableString.get());
    }

    @Test
    public void findConvenienceMatcherLogicNotFound() {
        var mutableString = new Mutable<>("");
        RegExMatcher.find("Test", "Hello world",
                ___matcher -> mutableString.set(___matcher.group(1)),
                ___matcher -> mutableString.set(___matcher.pattern().pattern())
        );
        assertEquals("Test", mutableString.get());
    }

    @Test
    public void findConvenienceMatcherLogicNotFoundWithFlag() {
        var mutableString = new Mutable<>("");
        RegExMatcher.find("Test", "Hello world", Pattern.MULTILINE,
                ___matcher -> mutableString.set(___matcher.group(1)),
                ___matcher -> mutableString.set(___matcher.pattern().pattern())
        );
        assertEquals("Test", mutableString.get());
    }

    @Test
    public void findMatcherNoCustomLogic() {
        var matcher = RegExMatcher.find("(\\w*)\\s(\\w*)", "Hello world");
        assertEquals("Hello", matcher.group(1));
    }

    @Test
    public void findMatcherNoCustomLogicWithFlags() {
        var matcher = RegExMatcher.find("(\\w*)\\s(\\w*)", "Hello world", Pattern.MULTILINE);
        assertEquals("Hello", matcher.group(1));
    }


}
