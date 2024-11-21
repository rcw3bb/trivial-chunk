# Require Class

A handy class for requiring non-null objects.

##### The object Method

A method that accepts single object to check for nullity. If the object parameter is null, the method will throw **ObjectRequiredException**.

| Signatures                                                   | Description                                            |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| public static void **object**(Object obj) throws ObjectRequiredException | Single object null check without custom error message. |
| public static void **object**(Object obj, String message) throws ObjectRequiredException | Single object null check with custom error message.    |

##### The objects Method

A method that accepts multiple objects to check for nullity. If at least one of the parameters is null, the method will throw **ObjectRequiredException**.

| Signatures                                                   | Description                                               |
| ------------------------------------------------------------ | --------------------------------------------------------- |
| public static void **objects**(Object obj, Object ... objs) throws ObjectRequiredException | Multiple objects null check without custom error message. |
| public static void **objects**(RequireObject obj, RequireObject ... objs) throws ObjectRequiredException | Multiple objects null check with custom error message.    |

## Sample Usage without Custom Message

```java
String param1 = "param1";
String param2 = null;

try {
    Require.objects(param1, param2);
    System.out.println("Everythings fine.");
}
catch(ObjectRequiredException rae) {
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
catch(ObjectRequiredException rae) {
    System.out.println(rae.getMessage());
}
```

**Expected Output**

```
java.lang.NullPointerException: param2 is required
```

[Table of Contents](USER_GUIDE_TOC.md)