package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBuilderAppenderTest {

    @Test
    public void defaultBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder, ___builder -> ___builder.append("("),
                ___builder -> ___builder.append(")"))
                .append("Test1")
                .append("Test2");
        assertEquals("(Test1)(Test2)", builder.toString());
    }

    @Test
    public void defaultBeforeAndAfterAppendThreadSafe() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder, ___builder -> ___builder.append("("),
                ___builder -> ___builder.append(")"))
                .threadSafe()
                .append("Test1")
                .append("Test2");
        assertEquals("(Test1)(Test2)", builder.toString());
    }

    @Test
    public void overrideDefaultBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder, ___builder -> ___builder.append("("),
                ___builder -> ___builder.append(")"))
                .append("Test", ___builder->___builder.append(")"), ___builder->___builder.append("("));
        assertEquals(")Test(", builder.toString());
    }

    @Test
    public void nullDefaultBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder, null, null).append("Test");
        assertEquals("Test", builder.toString());
    }

    @Test
    public void defaultBeforeAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder, ___builder -> ___builder.append("+")).append("Test");
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void overrideDefaultBeforeAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder, ___builder -> ___builder.append("+"))
                .append("Test", ___builder -> ___builder.append("-"));
        assertEquals("-Test", builder.toString());
    }

    @Test
    public void noDefaultBeforeAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder).append("Test");
        assertEquals("Test", builder.toString());
    }

    @Test
    public void beforeAndAfterAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder)
                .append("Test1", ___builder -> ___builder.append("("), ___builder -> ___builder.append(")"))
                .append("Test2", ___builder -> ___builder.append(")"), ___builder -> ___builder.append("("));
        assertEquals("(Test1))Test2(", builder.toString());
    }

    @Test
    public void nullBeforeAndAfterAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder).append("Test", null, null);
        assertEquals("Test", builder.toString());
    }

    @Test
    public void beforeAppend() {
        var builder = new StringBuilder();
        new StringBuilderAppender(builder).append("Test", ___builder -> ___builder.append("+"));
        assertEquals("+Test", builder.toString());
    }

}
