# BooleanSupplierKeyedMapExecutor Class

A group of logics that are contained in a map and **doesn't return a value**.

## Constructors

| Signature                                                    |
| ------------------------------------------------------------ |
| public BooleanSupplierKeyedMapExecutor(Map.Entry<BooleanSupplier, Sink> ... logics) |
| public BooleanSupplierKeyedMapExecutor(Map<BooleanSupplier, Sink> map) |
| public BooleanSupplierKeyedMapExecutor(Map<BooleanSupplier, Sink> map, Sink defaultLogic, Map.Entry<BooleanSupplier, Sink> ... logics) |
| public BooleanSupplierKeyedMapExecutor(Sink defaultLogic, Map.Entry<BooleanSupplier, Sink> ... logics) |

**Parameters**

| Parameter    | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| defaultLogic | A **composed Sink** that will be executed when **no keys evaluated to true**. |
| logics       | This is a **varargs** of instances of **Map.Entry<BooleanSupplier, Sink>** that maps the **boolean key logic** to a **composed Sink**. |
| map          | An instance of **Map<BooleanSupplier, Sink>** that houses the logic mapping. |

## Main Methods

| Signature                     |
| ----------------------------- |
| public void execute()         |
| public Optional<Object> get() |

> The get behaves just like the execute method but always return an empty optional. Hence, it is **recommended to use execute method**.

## Sample Usage

```java
var sb = new StringBuilder();
var executor = new BooleanSupplierKeyedMapExecutor(
    Map.entry(()-> Boolean.TRUE, () -> sb.append("TEST1")),
    Map.entry(()-> Boolean.FALSE, ()-> sb.append("TEST2")));
executor.execute();

System.out.println(sb);
```

**Expected Output**

```
TEST1
```

[Table of Contents](USER_GUIDE_TOC.md)

