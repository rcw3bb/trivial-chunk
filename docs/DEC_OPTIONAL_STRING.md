##  OptionalString
A decorator for Optional of type String that gives more string specific simplification.

##### Constructors

| Signature |
|---------|
| public **OptionalString**(final Optional<String> **optional**) |

**Parameters**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| optional | An instance of Optional of type string. |

##### Main Methods

| Signature |
|--------|
| public void **ifPresentNotBlank**(final Consumer<String> **action**) |
| public void **ifPresentNotBlankOrElse**(final Consumer<String> **action**, final Runnable **emptyAction**) |
| public void **ifPresentNotEmpty**(final Consumer<String> **action**) |
| public void **ifPresentNotEmptyOrElse**(final Consumer<String> **action**, final Runnable **emptyAction**) |
| public boolean **isBlank**() //This is true if the string value is blank *(i.e. only contains whitespaces)* and not null. |
| public boolean **isEmpty**() //This is true if the string value is empty *(i.e. size is 0)* and not null. |

**Parameters**

| Parameter | Descriptions              |
| --------- | ------------------------- |
| action | The action to perform if the string value is not blank or empty. |
| emptyAction | The action to perform if the string value is blank or empty.. |

**Sample Usage**

```java
var text="NotBlank";
final var controlText = new Mutable<>("");
new OptionalString(Optional.of(text)).ifPresentNotBlank(___text -> controlText.set("NotBlank"));

System.out.println(controlText.get());
```

**Expected Output**

```
NotBlank
```

[Table of Contents](USER_GUIDE_TOC.md)

