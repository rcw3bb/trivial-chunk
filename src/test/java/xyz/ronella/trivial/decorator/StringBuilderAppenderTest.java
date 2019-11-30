package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBuilderAppenderTest {

    @Test
    public void defaultBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder, ___builder -> ___builder.append("("),
                ___builder -> ___builder.append(")"));
        appender.append("Test");
        assertEquals("(Test)", builder.toString());
    }

    @Test
    public void overrideDefaultBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder, ___builder -> ___builder.append("("),
                ___builder -> ___builder.append(")"));
        appender.append("Test", ___builder->___builder.append(")"), ___builder->___builder.append("("));
        assertEquals(")Test(", builder.toString());
    }

    @Test
    public void nullDefaultBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder, null, null);
        appender.append("Test");
        assertEquals("Test", builder.toString());
    }

    @Test
    public void defaultBeforeAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder, ___builder -> ___builder.append("+"));
        appender.append("Test");
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void overrideDefaultBeforeAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder, ___builder -> ___builder.append("+"));
        appender.append("Test", ___builder -> ___builder.append("-"));
        assertEquals("-Test", builder.toString());
    }

    @Test
    public void noDefaultBeforeAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder);
        appender.append("Test");
        assertEquals("Test", builder.toString());
    }

    @Test
    public void beforeAndAfterAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder);
        appender.append("Test", ___builder -> ___builder.append("("), ___builder -> ___builder.append(")"));
        assertEquals("(Test)", builder.toString());
    }

    @Test
    public void nullBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder);
        appender.append("Test", null, null);
        assertEquals("Test", builder.toString());
    }

    @Test
    public void beforeAppend() {
        var builder = new StringBuilder();
        var appender = new StringBuilderAppender(builder);
        appender.append("Test", ___builder -> ___builder.append("+"));
        assertEquals("+Test", builder.toString());
    }

}
