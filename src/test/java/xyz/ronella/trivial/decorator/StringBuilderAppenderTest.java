package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.functional.NoOperation;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
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
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen("Test"
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-")).when(___ -> Boolean.TRUE);
        assertEquals("+Test-", builder.toString());
    }

    @Test
    public void falseConditionFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen("Test"
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-")).when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionBeforeAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen("Test"
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.TRUE);
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void falseConditionBeforeAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen("Test"
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen( "Test").when(___ -> Boolean.TRUE);
        assertEquals("Test", builder.toString());
    }

    @Test
    public void falseConditionAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen("Test"
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionCustomFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen(
                ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-")).when(___ -> Boolean.TRUE);
        assertEquals("+Test-", builder.toString());
    }

    @Test
    public void falseConditionCustomFullMethod() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen(
                ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")
                , ___builder -> ___builder.append("-")).when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionBeforeCustomAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen(___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.TRUE);
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void falseConditionBeforeCustomAppend() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen( ___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void truthConditionCustomAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder())
                .appendWhen(___builder-> ___builder.append("Test")).when(___ -> Boolean.TRUE);
        assertEquals("Test", builder.toString());
    }

    @Test
    public void falseConditionCustomAppendOnly() {
        var builder = new StringBuilderAppender(new StringBuilder()).appendWhen(___builder-> ___builder.append("Test")
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void acceptingAString() {
        var builder = new StringBuilderAppender("").appendWhen("Test"
                , ___builder -> ___builder.append("+")).when(___ -> Boolean.TRUE);
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void multipleTextsWithBeforeAppend() {
        var builder = new StringBuilderAppender("").append(___builder -> ___builder.append("+"),
                "test1", "test2", "test3");
        assertEquals("+test1+test2+test3", builder.toString());
    }

    @Test
    public void multipleTexts() {
        var builder = new StringBuilderAppender("").append("test1", "test2");
        assertEquals("test1test2", builder.toString());
    }

    @Test
    public void multipleTextsConditionalTrueWithBeforeAppend() {
        var builder = new StringBuilderAppender("").appendWhen(___builder -> ___builder.append("+"),
                "test1", "test2", "test3").when(___ -> Boolean.TRUE);
        assertEquals("+test1+test2+test3", builder.toString());
    }

    @Test
    public void multipleTextsConditionalFalseWithBeforeAppend() {
        var builder = new StringBuilderAppender("").appendWhen(___builder -> ___builder.append("+"),
                "test1", "test2", "test3").when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void multipleConditionalTrueTexts() {
        var builder = new StringBuilderAppender("").appendWhen("test1", "test2").when(___ -> Boolean.TRUE);
        assertEquals("test1test2", builder.toString());
    }

    @Test
    public void multipleConditionalFalseTexts() {
        var builder = new StringBuilderAppender("").appendWhen("test1", "test2").when(___ -> Boolean.FALSE);
        assertEquals("", builder.toString());
    }

    @Test
    public void usingDefaultStringBuilder() {
        var builder = new StringBuilderAppender().append("Test");
        assertEquals("Test", builder.toString());
    }

    @Test
    public void usingDefaultStringBuilderBeforeAppend() {
        var builder = new StringBuilderAppender().append("Test"
                , ___builder -> ___builder.append("+"));
        assertEquals("+Test", builder.toString());
    }

    @Test
    public void usingDefaultStringBuilderBeforeAfterAppend() {
        var builder = new StringBuilderAppender().append("Test",
                ___builder -> ___builder.append("+"),
                ___builder -> ___builder.append("-"));
        assertEquals("+Test-", builder.toString());
    }

    @Test
    public void noOpTest() {
        var builder = new StringBuilderAppender(___builder -> ___builder.append("+"),
                ___builder -> ___builder.append("-")).append("Test", NoOperation.consumer(), NoOperation.consumer());
        assertEquals("Test", builder.toString());
    }

    @Test
    public void clearTest() {
        var builder = new StringBuilderAppender(___builder -> ___builder.append("+"),
                ___builder -> ___builder.append("-")).append("Test", NoOperation.consumer(), NoOperation.consumer());
        builder.clear();
        assertEquals("", builder.toString());
    }

    @Test
    public void clearTestThreadSafe() {
        var builder = new StringBuilderAppender(___builder -> ___builder.append("+"),
                ___builder -> ___builder.append("-")).append("Test", NoOperation.consumer(), NoOperation.consumer());
        builder.threadSafe().clear();
        assertEquals("", builder.toString());
    }

    @Test
    public void replace() {
        var builder = new StringBuilderAppender(
                ___builder -> ___builder.append("+"),
                ___builder -> ___builder.append("-"))
                .append("Hello");
        builder.replace("Hello", "World");
        assertEquals("+World-", builder.toString());
    }

    @Test void appendWhenSingleText() {
        var builder = new StringBuilderAppender(___builder -> ___builder.append(!___builder.isEmpty() ? ", " : ""));
        builder.appendWhen("a").when(___ -> true);
        builder.appendWhen("b").when(___ -> true);
        assertEquals("a, b", builder.toString());
    }

    @Test
    public void replaceThreadSafe() {
        var builder = new StringBuilderAppender(
                ___builder -> ___builder.append("+"),
                ___builder -> ___builder.append("-"))
                .append("Hello");
        builder.threadSafe().replace("Hello", "World");
        assertEquals("+World-", builder.toString());
    }
}
