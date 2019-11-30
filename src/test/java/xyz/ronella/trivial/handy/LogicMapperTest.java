package xyz.ronella.trivial.handy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicMapperTest {

    @Test
    public void allFalse() {
        var builder = new StringBuilder();
        var mapper = LogicMapper.build()
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"));
        mapper.execute();
        assertEquals("", builder.toString());
    }

    @Test
    public void oneTrue() {
        var builder = new StringBuilder();
        var mapper = LogicMapper.build()
                .addLogic(()->Boolean.FALSE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.FALSE, ()-> builder.append("C"));
        mapper.execute();
        assertEquals("B", builder.toString());
    }

    @Test
    public void allTrue() {
        var builder = new StringBuilder();
        var mapper = LogicMapper.build()
                .addLogic(()->Boolean.TRUE, ()-> builder.append("A"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("B"))
                .addLogic(()->Boolean.TRUE, ()-> builder.append("C"));
        mapper.execute();
        assertEquals("ABC", builder.toString());
    }

}
