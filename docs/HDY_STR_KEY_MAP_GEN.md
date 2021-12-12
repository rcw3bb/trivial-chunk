# StringKeyedMapGenerator Class

A group of logics that are contained in a map and **returns a value**.

#### Constructors

| Signature                                                    |
| ------------------------------------------------------------ |
| public StringKeyedMapGenerator(Map.Entry<String, Supplier<TYPE_OUTPUT>> ... logics) |
| public StringKeyedMapGenerator(Map<String, Supplier<TYPE_OUTPUT>> map) |
| public StringKeyedMapGenerator(Map<String, Supplier<TYPE_OUTPUT>> map, Supplier<TYPE_OUTPUT> defaultLogic, Map.Entry<String, Supplier<TYPE_OUTPUT>> ... logics) |
| public StringKeyedMapGenerator(Supplier<TYPE_OUTPUT> defaultLogic, Map.Entry<String, Supplier<TYPE_OUTPUT>> ... logics) |

**Parameters**

| Parameter     | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| <TYPE_OUTPUT> | The type of output the **get method** will return.           |
| defaultLogic  | A **composed Supplier** that will be executed when the key was not mapped to a particular logic. |
| logics        | This is a **varargs** of instances of **Map.Entry<String, Supplier<TYPE_OUTPUT>>** that maps the **string key** to a **composed Supplier**. |
| map           | An instance of **Map<String, Supplier<TYPE_OUTPUT>>** that houses the logic mapping. |

##### Main Methods

| Signature                                    |
| -------------------------------------------- |
| public Optional<TYPE_OUTPUT> get(String key) |

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| key       | The key of a particular logic to execute or if it doesn't exists choose the defaultLogic. |

##### Sample Usage

```
var factory = new StringKeyedMapGenerator<String>(()-> "DEFAULT",
    Map.entry("TEST1", ()-> "TEST1"),
    Map.entry("TEST2", ()-> "TEST2"));

System.out.println(factory.get("TEST1").get());
```

**Expected Output**

```
TEST1
```

[Table of Contents](USER_GUIDE_TOC.md)