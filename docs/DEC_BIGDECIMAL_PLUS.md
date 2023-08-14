# BigDecimalPlus Class

A decorator class for **operating with BigDecimal**.

## Constructor

| Signature |
|---------|
| public **BigDecimalPlus**(BigDecimal **bigDecimal**) |

### **Parameter**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| bigDecimal | An instance of BigDecimal to decorate. |

## The LogicalAND Functional Interface

The interface that must hold the implementation on how to do an AND logic with BigDecimal. Thus, it only provide an **and method** that accepts **BigDecimal** and must return a **boolean value**.

```java
@FunctionalInterface
public interface LogicalAND {
    Boolean and(BigDecimal object);
}
```

This is currently used with **between method**.

## Main Methods

| Signature |
|--------|
| public LogicalAND **between**(final BigDecimal **firstNumber**) |
| public boolean **equalsTo**(final BigDecimal **other**) |
| public boolean **greaterThan**(final BigDecimal **other**) |
| public boolean **greaterThanEqualsTo**(final BigDecimal **other**) |
| public boolean **lessThan**(final BigDecimal **other**) |
| ppublic boolean **lessThanEqualsTo**(final BigDecimal **other**) |

### **Parameter**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| firstNumber | Use in between method to accept the first BigDecimal instance use the and method to accept the second BigDecimal. |
| other | The other BigDecimal instance do comparison. |

**Sample Usage 1**

```java
final var bd = new BigDecimalPlus(new BigDecimal(200));
final var bd1 = new BigDecimal(100);
final var bd2 = new BigDecimal(300);

System.out.println(bd.between(bd1).and(bd2));
```

**Expected Output**

```
true
```

**Sample Usage 2**

```java
final var bd1 = new BigDecimalPlus(new BigDecimal(200));
final var bd2 = new BigDecimal(100);
System.out.println(bd1.greaterThan(bd2));
```

**Expected Output**

```
true
```

[Table of Contents](USER_GUIDE_TOC.md)