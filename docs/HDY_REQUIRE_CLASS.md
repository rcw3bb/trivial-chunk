# Require Class

A handy class for requiring some objects.

##### all Method

A method that accepts multiple objects to checks for non-nullity. If at least one of the parameters has thrown NPE, the method will throw **RequireAllException**.

| Signature                                                    |
| ------------------------------------------------------------ |
| public static void all(Object param, Object ... params) throws RequireAllException |

## Sample Usage

```java
String param1 = "param1";
String param2 = null;

try {
    Require.all(param1, param2);
    System.out.println("Everythings fine.");
}
catch(RequireAllException rae) {
    System.out.println("NPE Detected");
}
```

**Expected Output**

```
NPE Detected
```

[Table of Contents](USER_GUIDE_TOC.md)