# Require Class

A handy class for requiring some objects.

##### The objects Method

A method that accepts multiple objects to check for nullity. If at least one of the parameters has thrown NPE, the method will throw **RequireAllException**.

| Signatures                                                   | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public static void objects(Object obj, Object ... objs) throws RequireAllException | Doesn't support custom error message.                        |
| public static void objects(RequireObject obj, RequireObject ... objs) throws RequireAllException | Supports custom error message when the object being tested is null. |

## Sample Usage without Custom Message

```java
String param1 = "param1";
String param2 = null;

try {
    Require.objects(param1, param2);
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

## Sample Usage with Custom Message

```java
String param1 = "param1";
String param2 = null;

try {
    Require.objects(new RequireObject(param1, "param1 is required"), new RequireObject(param2, "param2 is required"));
    System.out.println("Everythings fine.");
}
catch(RequireAllException rae) {
    System.out.println(rae.getMessage());
}
```

**Expected Output**

```
java.lang.NullPointerException: param2 is required
```

[Table of Contents](USER_GUIDE_TOC.md)