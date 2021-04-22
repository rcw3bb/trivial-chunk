## LogicMapper Class
Creates a map of conditions and logic where all the conditions that evaluated to true will perform its corresponding logic.

This class can only instantiated by **LogicMapperBuilder**.

##### execute Method

The execute method executes all the logics. This doesn't return any output.

| Signature |
|------|
| public void execute() |

##### output Method

The output method executes all the logics. This does return a typed output. 

This method evaluates the **addFinalLogic** method that the builder had added.

| Signature |
|------|
| public Optional<TYPE_OUTPUT> output() |

## LogicMapperBuilder Class

The instance of this class is the only one the can create an instance of LogicMapper. To create an instance of this class you can do the following:

```java
LogicMapper.getBuilder()
```

##### addInitialLogic Method

Use this method to add an logic that will be executed before the first addLogic will be executed.

| Signature                                             |
| ----------------------------------------------------- |
| public LogicMapperBuilder addInitialLogic(Sink logic) |

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| logic | The implementation to be executed before the addLogic execution. |

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

##### addFinalLogic Method

Use this method to add an logic that will be executed after the last addLogic was executed. 

This method is the one evaluated by the **LogicMapper.output** method. 

| Signature                                                    |
| ------------------------------------------------------------ |
| public LogicMapperBuilder addFinalLogic(Supplier<TYPE_OUTPUT> logic) |

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| logic | The implementation to be executed after the last addLogic was executed and return any desired typed output. |


##### build method

This method is the only method that can create an instance of LogicMapper *(i.e. this method must be called last.)*.

| Signature |
|------|
| public LogicMapper build() |

##### Sample Execute Usage

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

##### Sample Output Usage

```java
var builder = new StringBuilder();
var mapper = LogicMapper.<String>getBuilder()
    .addLogic(()->Boolean.TRUE, ()-> builder.append("A"))
    .addLogic(()->Boolean.FALSE, ()-> builder.append("B"))
    .addLogic(()->Boolean.TRUE, ()-> builder.append("C"))
    .addFinalLogic(builder::toString)
    .build();
System.out.println(mapper.output().get());
```

**Expected Output**

```
AC
```

[Table of Contents](USER_GUIDE_TOC.md)

