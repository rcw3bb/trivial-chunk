#  MapPutter
A decorator of Map implementation that exposes **conditional put methods** and ability to **put calculated elements**.

## Constructor

| Signature |
|---------|
| public **MapPutter**(final Map<TYPE_KEY, TYPE_VALUE> **map**) |

### **Parameter**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| <TYPE_KEY> | The type of the Map key. |
| <TYPE_KEY> | The type of the Map value.             |
| map | An instance Map<TYPE_KEY, TYPE_VALUE>. |

## Main Methods

| Signature |
|--------|
| public WhenThen<Map<TYPE_KEY, TYPE_VALUE>> **putAllWhen**(final Map<TYPE_KEY, TYPE_VALUE> ****) |
| public WhenThen<Map<TYPE_KEY, TYPE_VALUE>> **putAllWhen**(final Supplier<Map<TYPE_KEY, TYPE_VALUE>> **mapLogic**) |
| public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> **putWhen**(final Supplier<TYPE_KEY> **keyLogic**, final Supplier<TYPE_VALUE> **valueLogic**) |
| public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> **putWhen**(final Supplier<TYPE_KEY> **keyLogic**, final TYPE_VALUE **value**) |
| public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> **putWhen**(final TYPE_KEY **key**, final Supplier<TYPE_VALUE> **valueLogic**) |
| public WhenThenReturn<Map<TYPE_KEY, TYPE_VALUE>, TYPE_VALUE> **putWhen**(final TYPE_KEY **key**, final TYPE_VALUE **value**) |

### **Parameter**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| map | An instance of Map. |
| mapLogic | A logic that creates an instance of Map. |
| keyLogic | A logic that calculates a key for an item in the map. |
| valueLogic | A logic that calculates a value to be associated with the key. |
| key | A key for an item in the map. |
| value | A value to be associated with the key. |

**Sample Usage 1** 

```java
final var map = new HashMap<String, String>();
final var putter = new MapPutter<>(map);
final var insertMap = Map.of("One", "1");
putter.putWhen("One", ()-> "1").when(___ -> true);
System.out.println(map);
```

**Expected Output**

```
{One=1}
```
**Sample Usage 2** 

```java
final var map = new HashMap<String, String>();
final var putter = new MapPutter<>(map);
final var insertMap = Map.of("One", "1", "Two", "2");
putter.putAllWhen(insertMap).when(___ -> false);
System.out.println(map);
```

**Expected Output**

```
{}
```


[Table of Contents](USER_GUIDE_TOC.md)

