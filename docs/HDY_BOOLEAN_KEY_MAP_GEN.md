# BooleanSupplierKeyedMapGenerator Class

A group of logics that are contained in a map and **returns a value**. The key must be an implementation of **BooleanSupplier**.

## Constructors

| Signature                                                    |
| ------------------------------------------------------------ |
| public BooleanSupplierKeyedMapGenerator(Map.Entry<BooleanSupplier, Supplier<TYPE_OUTPUT>> ... logics) |
| public BooleanSupplierKeyedMapGenerator(Map<BooleanSupplier, Supplier<TYPE_OUTPUT>> map) |
| public BooleanSupplierKeyedMapGenerator(Map<BooleanSupplier, Supplier<TYPE_OUTPUT>> map, Supplier<TYPE_OUTPUT> defaultLogic, Map.Entry<BooleanSupplier, Supplier<TYPE_OUTPUT>> ... logics) |
| public BooleanSupplierKeyedMapGenerator(Supplier<TYPE_OUTPUT> defaultLogic, Map.Entry<BooleanSupplier, Supplier<TYPE_OUTPUT>> ... logics) |

**Parameters**

| Parameter     | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| <TYPE_OUTPUT> | The type of output the **get method** will return.           |
| defaultLogic  | A **composed Supplier** that will be executed when **no keys evaluated to true**. |
| logics        | This is a **varargs** of instances of **Map.Entry<BooleanSupplier, Supplier<TYPE_OUTPUT>>** that maps the **boolean key logic** to a **composed Supplier**. |
| map           | An instance of **Map<BooleanSupplier, Supplier<TYPE_OUTPUT>>** that houses the logic mapping. |

## Main Methods

| Signature                          |
| ---------------------------------- |
| public Optional<TYPE_OUTPUT> get() |

## Sample Usage

```java
var generator = new BooleanSupplierKeyedMapGenerator<String>(
    Map.entry(()-> Boolean.FALSE, ()-> "TEST1"),
    Map.entry(()-> Boolean.TRUE, ()-> "TEST2"));
System.out.println(generator.get().get());
```

**Expected Output**

```
TEST2
```

[Table of Contents](USER_GUIDE_TOC.md)