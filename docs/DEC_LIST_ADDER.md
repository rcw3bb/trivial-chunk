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
| public void **add**(final BooleanSupplier **when**, final int **index**, final Supplier<TYPE_ELEMENT> **generateElement**) |
| public void **add**(final BooleanSupplier **when**, final int index, **final** TYPE_ELEMENT **element**) |
| public boolean **add**(final BooleanSupplier **when**, final Supplier<TYPE_ELEMENT> **generateElement**) |
| public boolean **add**(final BooleanSupplier **when**, final TYPE_ELEMENT **element**) |
| public void **add**(final int **index**, final Supplier<TYPE_ELEMENT> **generateElement**) |
| public void **add**(final int **index**, final TYPE_ELEMENT **element**) |
| public boolean **add**(final Supplier<TYPE_ELEMENT> **generateElement**) |
| public boolean **add**(final TYPE_ELEMENT **element**) |
| public boolean **addAll**(final BooleanSupplier **when**, final Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(final BooleanSupplier **when**, final int **index**,                       final Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(final BooleanSupplier **when**, final int **index**,                       final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |
| public boolean **addAll**(final BooleanSupplier **when**,                       final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |
| public boolean **addAll**(final Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(final int **index**, final Collection<? extends TYPE_ELEMENT> **elements**) |
| public boolean **addAll**(final int **index**, final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |
| public boolean **addAll**(final Supplier<Collection<? extends TYPE_ELEMENT>> **generateElements**) |

### **Parameter**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| element | The element to be added. |
| elements | The elements to be added. |
| generateElement | The logic to produce the element. |
| generateElements | The logic to produce the elements. |
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

