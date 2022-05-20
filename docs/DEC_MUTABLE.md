#  Mutable
A convenience class that **accepts an immutable object and making it possible to be replaced within itself**. This means keeping the reference of this class intact while changing the immutable object it carries.

## Constructor

| Signature |
|---------|
| public **Mutable**(TYPE_OBJECT **object**) |

### **Parameter**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| <TYPE_OBJECT> | The type of the immutable object. |
| object | The immutable object to carry.    |

## Main Methods

| Signature | Description |
|--------|--------|
| public TYPE_OBJECT **get**() | Return the held immutable object. |
| public void **set**(TYPE_OBJECT **object**) | Replace the held immutable object. |

### **Parameter**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| object | The object of the same type to replace the held immutable object. |

**Sample Usage**

```java
var mutable = new Mutable<>("Hello");
Consumer<String> consumer = (___value ) -> mutable.set(String.format("%s %s", mutable.get(), ___value));
consumer.accept("World");
System.out.println(mutable.get());
```

**Expected Output**

```
Hello World
```

[Table of Contents](USER_GUIDE_TOC.md)

