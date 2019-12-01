## LogicMapper Class
Creates a map of conditions and logic where all the conditions that evaluated to true will perform its corresponding logic.

This class can only instantiated by **LogicMapperBuilder**.

##### execute Method

| Signature |
|------|
| public void execute() |

## LogicMapperBuilder Class

The instance of this class is the only one the can create an instance of LogicMapper. To create an instance of this class you can do the following:

```java
LogicMapper.getBuilder()
```

##### addLogic Method

Use this method to create a set of condition and logic. 

| Signature |
|-------|
| public LogicMapperBuilder addLogic(Supplier<Boolean> condition, Sink logic) |

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| condition | An implementation of Boolean Supplier. The particular implementation must be evaluated to true to execute its corresponding logic. |
| logic | The implementation that will be executed if the condition is evaluated to true. |

##### build method

This method is the only method that can create an instance of LogicMapper *(i.e. this method must be called last.)*.

| Signature |
|------|
| public LogicMapper build() |

**Sample Usage**

```java
var builder = new StringBuilder();
var mapper = LogicMapper.getBuilder()
    .addLogic(()->Boolean.TRUE, ()-> builder.append("A"))
    .addLogic(()->Boolean.FALSE, ()-> builder.append("B"))
    .addLogic(()->Boolean.TRUE, ()-> builder.append("C"))
    .build();
mapper.execute();
System.out.println(builder.toString());
```

**Expected Output**

```
AC
```

[Table of Contents](USER_GUIDE_TOC.md)

