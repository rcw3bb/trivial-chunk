package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.ronella.trivial.handy.LogicMapper.getBuilder;

public class LogicMapperTest {

    @Test
    public void allFalse() {
        var builder = new StringBuilder();
        var mapper = getBuilder()
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"))
                .build();
        mapper.execute();
        assertEquals("", builder.toString());
    }

    @Test
    public void oneTrue() {
        var builder = new StringBuilder();
        var mapper = getBuilder()
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"))
                .build();
        mapper.execute();
        assertEquals("B", builder.toString());
    }

    @Test
    public void initialLogic() {
        var builder = new StringBuilder();
        var mapper = getBuilder()
                .addInitialLogic(() -> builder.append("["))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"))
                .build();
        mapper.execute();
        assertEquals("[B", builder.toString());
    }

    @Test
    public void finalLogic() {
        var builder = new StringBuilder();
        var mapper = getBuilder()
                .addFinalLogic(()-> builder.append("]"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"))
                .build();
        mapper.execute();
        assertEquals("B]", builder.toString());
    }

    @Test
    public void initAndFinalLogic() {
        var builder = new StringBuilder();
        var mapper = getBuilder()
                .addInitialLogic(()-> builder.append("["))
                .addFinalLogic(()-> builder.append("]"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"))
                .build();
        mapper.execute();
        assertEquals("[B]", builder.toString());
    }

    @Test
    public void strOutput() {
        var builder = new StringBuilder();
        var mapper= LogicMapper.<String>getBuilder()
                .addInitialLogic(()-> builder.append("["))
                .addFinalLogic(builder::toString)
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"))
                .build();
        assertEquals("[B", mapper.output().orElse(""));
    }
}
