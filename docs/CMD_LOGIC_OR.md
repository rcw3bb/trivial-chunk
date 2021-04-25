## LogicOR Command
A class that accepts multiple conditions and if **at least one condition is true** it will pass control to the truthLogic otherwise the falseLogic will take it.

##### Constructor

| Signature                                                    | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public LogicOR(List<BooleanSupplier> **conditions**)         | The constructor that accepts a list of **BooleanSupplier**. This Supplier must have the logic for the condition to be evaluated to Boolean. For the **truthLogic** to be executed, at least one of the content of the list must be evaluated to true. |
| public LogicOR(List<BooleanSupplier> **conditions**, Sink **defaultTruthLogic**) | The constructor that accepts a list of **BooleanSupplier** and a **defaultTruthLogic** that will be executed only if the **argument passed is null on the accept method** and provided that **one of the conditions were evaluated to true.** |
| public LogicOR(List<BooleanSupplier> **conditions**, Sink **defaultTruthLogic**, Sink defaultFalseLogic) | The constructor that accepts a list of **BooleanSupplier** and a **defaultTruthLogic** and **defaultFalseLogic**. These logics is/are to be executed only if **their corresponding arguments on accept method is/are null**. If **one of the conditions were evaluated to true the defaultTruthLogic will be executed** otherwise the defaultFalseLogic will run. |
| public LogicOR(BooleanSupplier ... **conditions**)           | The constructor that accepts a list of **BooleanSupplier **as **varargs**. This Supplier must have the logic for the condition to be evaluated to Boolean. For the **truthLogic** to be executed, at least one of the content of the list must be evaluated to true. |
| public LogicOR(Sink **defaultTruthLogic**, BooleanSupplier ... **conditions**) | The constructor that accepts a list of **BooleanSupplier **as **varargs** and a **defaultTruthLogic** that will be executed only if the **argument passed is null on the accept method** and provided that **one of the conditions were evaluated to true.** |
| public LogicOR(Sink **defaultTruthLogic**, Sink **defaultFalseLogic**, BooleanSupplier ... **conditions**) | The constructor that accepts a list of **BooleanSupplier** as **varargs** and a **defaultTruthLogic** and **defaultFalseLogic**. These logics is/are to be executed only if **their corresponding arguments on accept method is/are null**. If **one of the conditions were evaluated to true the defaultTruthLogic will be executed** otherwise the defaultFalseLogic will run. |

##### Methods

| Signature                                                    | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public void accept(Sink **truthLogic**)                      | The method that accepts an implementation of **Sink** that will be evaluated when one of the conditions is evaluated to true.<br /><br />Passing in null will invoke the **defaultTruthLogic** if available. |
| public void accept(Sink **truthLogic**, Sink **falseLogic**) | The method that accepts two implementations of **Sinks**. The first implementation must hold the **truthLogic**. The second implementation must hold the implementation of the **falseLogic**. The truthLogic will be executed if and only if one of the conditions is evaluated to true. Otherwise, the falseLogic will be executed.<br /><br />Passing in null to either of the parameters will invoke its corresponding default logic passed via constructor |

**Sample Usage**

```java
var builder = new StringBuilder();

var conditions = new ArrayList<BooleanSupplier>();
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

