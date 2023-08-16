#  ListAdder
A decorator of List implementation that exposes **conditional add methods** and ability to **add calculated elements**.

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
| public WhenThen<List<TYPE_ELEMENT>> **addWhen**(final int **index**, final Supplier<TYPE_ELEMENT> **generateElement**) |
| public WhenThen<List<TYPE_ELEMENT>> **addWhen**(final int **index**, final TYPE_ELEMENT element**)** |
| public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> **addWhen**(final Supplier<TYPE_ELEMENT> **generateElement**) |
| public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> **addWhen**(final TYPE_ELEMENT **element**) |
| public void **add**(final int **index**, final Supplier<TYPE_ELEMENT> **generateElement**) |
| public void **add**(final int **index**, final TYPE_ELEMENT **element**) |
| public boolean **add**(final Supplier<TYPE_ELEMENT> **generateElement**) |
| public boolean **add**(final TYPE_ELEMENT **element**) |
| public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> **addAllWhen**(final Collection<? extends TYPE_ELEMENT> **elements**) |
| public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> **addAllWhen**(final int **index**,<br/>                                          final Collection<? extends TYPE_ELEMENT> **elements**) |
| public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> **addAllWhen**(final int **index**,<br/>                                          final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |
| public WhenThenReturn<List<TYPE_ELEMENT>, Boolean> **addAllWhen**(final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |
| public boolean **addAll**(final Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(final int **index**, final Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(final int **index**, final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |
| public boolean **addAll**(final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |

## WhenThen/WhenThenReturn Interfaces

For this decorator the both WhenThen and WhenThenReturn interfaces, the **when methods** provide the list that was passed from the constructor.

### **Parameter**s

| Parameter | Descriptions              |
| --------- | ------------------------- |
| element | The element to be added. |
| elements | The elements to be added. |
| generateElement | The logic to produce the element. |
| generateElements | The logic to produce the elements. |
| index | The index where the element(s) to be added. |

**Sample Usage**

```java
var list = new ArrayList<String>();
var lstAdder = new ListAdder<>(list);
lstAdder.addWhen("Sample1").when(___-> true);
lstAdder.addWhen("Sample2").when(___-> false);
lstAdder.addWhen("Sample3").when(___-> true);
System.out.printf("List: %s%n", list);
```

> The when method here provides the target list passed from the constructor. The use of ___ means that the list was never used in the logic.

**Expected Output**

```
List: [Sample1, Sample3]
```

[Table of Contents](USER_GUIDE_TOC.md)

