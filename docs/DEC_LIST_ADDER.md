#  ListAdder
A decorator of List implementation that exposes **conditional add methods**.

## Constructor

| Signature |
|---------|
| public **ListAdder**(List<**TYPE_ELEMENT**> **list**) |

### **Parameter**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| <TYPE_ELEMENT> | The type of the element that the list will hold. |
| list | An instance of list.                             |

## Main Methods

| Signature |
|--------|
| public void **add**(BooleanSupplier **when**, int **index**, TYPE_ELEMENT **element**) |
| public boolean **add**(BooleanSupplier **when**, TYPE_ELEMENT **element**) |
| public void **add**(int **index**, TYPE_ELEMENT **element**) |
| public boolean **add**(TYPE_ELEMENT **element**) |
| public boolean **addAll**(BooleanSupplier **when**, Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(BooleanSupplier **when**, int **index**, Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(BooleanSupplier **when**, int **index**, Collection<? extends TYPE_ELEMENT> **elements**) |

### **Parameter**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| element | The element to be added. |
| elements | The elements to be added. |
| index | The index where the element(s) to be added. |
| when | The condition if the element(s) can be added. |

**Sample Usage**

```java
var list = new ArrayList<String>();
var lstAdder = new ListAdder<>(list);
lstAdder.add(()-> true, "Sample1");
lstAdder.add(()-> false, "Sample2");
lstAdder.add(()-> true, "Sample3");
System.out.printf("List: %s%n", list);
```

**Expected Output**

```
List: [Sample1, Sample3]
```

[Table of Contents](USER_GUIDE_TOC.md)

