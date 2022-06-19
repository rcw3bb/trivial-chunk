package xyz.ronella.trivial.handy.impl;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.decorator.Mutable;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class MatcherConfigTest {

    @Test
    public void patternFlagsDefault() {
        var regExConfig = MatcherConfig.getBuilder().build();
        assertEquals(0, regExConfig.getPatternFlags());
    }

    @Test
    public void patternFlags() {
        var regExConfig = MatcherConfig.getBuilder().build(Pattern.MULTILINE);
        assertEquals(Pattern.MULTILINE, regExConfig.getPatternFlags());
    }

    @Test
    public void matchLogicDefault() {
        var regExConfig = MatcherConfig.getBuilder().build();
        assertFalse(regExConfig.getMatchLogic().apply(null));
    }

    @Test
    public void matchLogic() {
        var regExConfig = MatcherConfig.getBuilder().setMatchLogic(___matcher -> true).build();
        assertTrue(regExConfig.getMatchLogic().apply(null));
    }

    @Test
    public void matchFoundLogic() {
        var mutableString = new Mutable<>("");
        var regExConfig = MatcherConfig.getBuilder()
                .setMatchFoundLogic(___matcher -> mutableString.set("Found"))
                .build();
        regExConfig.getMatchFoundLogic().accept(null);
        assertEquals("Found", mutableString.get());
    }

    @Test
    public void noMatchFoundLogic() {
        var mutableString = new Mutable<>("");
        var regExConfig = MatcherConfig.getBuilder()
                .setNoMatchFoundLogic(___matcher -> mutableString.set("NotFound"))
                .build();
        regExConfig.getNoMatchFoundLogic().accept(null);
        assertEquals("NotFound", mutableString.get());
    }

    @Test
    public void exceptionLogic() {
        var mutableString = new Mutable<>("");
        var regExConfig = MatcherConfig.getBuilder()
                .setExceptionLogic(___error -> mutableString.set("Error"))
                .build();
        regExConfig.getExceptionLogic().accept(null);
        assertEquals("Error", mutableString.get());
    }

}
