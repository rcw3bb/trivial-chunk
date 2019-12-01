## LogicOR Command
A class that accepts multiple conditions and if **at least one condition is true** it will pass control to the truthLogic otherwise the falseLogic will take it.

##### Constructor

| Signature                                          | Description                                                  |
| -------------------------------------------------- | ------------------------------------------------------------ |
| public LogicOR(List<Supplier<Boolean>> conditions) | The constructor that accepts a list of **Boolean Supplier**. This Supplier must have the logic for the condition to be evaluated to Boolean. |

##### Methods

| Signature                                            | Description                                                  |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| public void accept(Sink truthLogic)                  | The method that accepts an implementation of **Sink** that will be evaluated when one of the conditions is evaluated to true. |
| public void accept(Sink truthLogic, Sink falseLogic) | The method that accepts two implementations of **Sinks**. The first implementation must hold the **truthLogic**. The second implementation must hold the implementation of the **falseLogic**. The truthLogic will be executed if and only if one of the conditions is evaluated to true. Otherwise, the falseLogic will be executed. |

**Sample Usage**

```java
var builder = new StringBuilder();

var conditions = new ArrayList<Supplier<Boolean>>();
conditions.add(() -> Boolean.TRUE);
conditions.add(() -> Boolean.False);

Invoker.execute(new LogicOR(conditions), () -> builder.append("In"), 
                () -> builder.append("Out"));
System.out.println(builder.toString());
```

**Expected Output**

```
In
```

[Table of Contents](USER_GUIDE_TOC.md)

