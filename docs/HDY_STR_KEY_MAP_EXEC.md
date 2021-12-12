# StringKeyedMapExecutor Class

A group of logics that are contained in a map and **doesn't return a value**.

#### Constructors

| Signature                                                    |
| ------------------------------------------------------------ |
| public StringKeyedMapExecutor(Map.Entry<String, Sink> ... logics) |
| public StringKeyedMapExecutor(Map<String, Sink> map)         |
| public StringKeyedMapExecutor(Map<String, Sink> map, Sink defaultLogic, Map.Entry<String, Sink> ... logics) |
| public StringKeyedMapExecutor(Sink defaultLogic, Map.Entry<String, Sink> ... logics) |

**Parameters**

| Parameter    | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| defaultLogic | A **composed Sink** that will be executed when the key was not mapped to a particular logic. |
| logics       | This is a **varargs** of instances of **Map.Entry<String, Sink>** that maps the **string key** to a **composed Sink**. |
| map          | An instance of **Map<String, Sink>** that houses the logic mapping. |

##### Main Methods

| Signature                               |
| --------------------------------------- |
| public void execute(String key)         |
| public Optional<Object> get(String key) |

> The get behaves just like the execute method but always return an empty optional. Hence, it is **recommended to use execute method**.

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| key       | The key of a particular logic to execute or if it doesn't exists choose the defaultLogic. |

##### Sample Usage

```
var sb = new StringBuilder();
var executor = new StringKeyedMapExecutor(()-> sb.append("DEFAULT"),
    Map.entry("TEST1", ()-> sb.append("TEST1")),
    Map.entry("TEST2", ()-> sb.append("TEST2")));

executor.execute("TEST1");

System.out.println(sb.toString());
```

**Expected Output**

```
TEST1
```

[Table of Contents](USER_GUIDE_TOC.md)

