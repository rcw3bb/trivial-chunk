package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void toStringTest() {
        var builder = new StringBuilderAppender(new StringBuilder()).append("Test", ___builder -> ___builder.append("+"));
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void customAppendTest() {
        var builder = new StringBuilderAppender(new StringBuilder());
        builder.append(sb -> sb.append("+"));
        assertEquals("+", builder.toString());
    }

    @Test
    public void customAppendWithBeforeLogicTest() {
        var builder = new StringBuilderAppender(new StringBuilder());
        builder.append(sb -> sb.append("+"), ___builder -> ___builder.append("-"));
        assertEquals("-+", builder.toString());
    }

    @Test
    public void customAppendWithBeforeAndLogicTest() {
        var builder = new StringBuilderAppender(new StringBuilder());
        builder.append(sb -> sb.append("+"), ___builder -> ___builder.append("-"), ___builder -> ___builder.append("!"));
        assertEquals("-+!", builder.toString());
    }

    @Test
    public void nullCustomAppendWithBeforeAndLogicTest() {
        var builder = new StringBuilderAppender(new StringBuilder());
        Consumer<StringBuilder> sbLogic = null;
        builder.append(sbLogic, ___builder -> ___builder.append("-"), ___builder -> ___builder.append("!"));
        assertEquals("", builder.toString());
    }

    @Test
    public void nullAppendWithBeforeAndLogicTest() {
        var builder = new StringBuilderAppender(new StringBuilder());
        String text = null;
        builder.append(text, ___builder -> ___builder.append("-"), ___builder -> ___builder.append("!"));
        assertEquals("", builder.toString());
    }

    @Test
    public void retrieveStringBuilderTest() {
        var sbStringBuilder = new StringBuilder();
        var builder = new StringBuilderAppender(sbStringBuilder).append("Test", ___builder -> ___builder.append("+"));
        assertSame(sbStringBuilder, builder.getStringBuilder());
    }

    @Test
    public void truthConditionFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.TRUE, "Test"
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-"));
        assertEquals("+Test-", builder.toString());
    }

    @Test
    public void falseConditionFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.FALSE, "Test"
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-"));
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionBeforeAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.TRUE, "Test"
                , ___builder -> ___builder.append("+"));
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void falseConditionBeforeAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.FALSE, "Test"
                , ___builder -> ___builder.append("+"));
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.TRUE, "Test");
        assertEquals("Test", builder.toString());
    }

    @Test
    public void falseConditionAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.FALSE, "Test"
                , ___builder -> ___builder.append("+"));
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionCustomFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.TRUE
                , ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-"));
        assertEquals("+Test-", builder.toString());
    }

    @Test
    public void falseConditionCustomFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.FALSE
                , ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-"));
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionBeforeCustomAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.TRUE
                , ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+"));
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void falseConditionBeforeCustomeAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.FALSE
                , ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+"));
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionCustomAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.TRUE
                , ___builder-> ___builder.append("Test"));
        assertEquals("Test", builder.toString());
    }

    @Test
    public void falseConditionCustomAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).append(()-> Boolean.FALSE
                , ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+"));
        assertEquals("", builder.toString());
    }

}
