## NPESilencer Class
The class that collects expressions and evaluate them in sequence and return the output of very last expression. 

##### evaluate Method

The evaluate method executes all the expressions. If at lease one of the expression throws NPE then overall output will become **null**.

| Signature |
|------|
| public TYPE_OUTPUT evaluate() |

##### nullable Method

A convenience method that can hold the expression that can potentially throws an NPE. If an NPE was detected, the whole expression will just return null with throwing an NPE.

| Signature |
|------|
| public static <TYPE_OUTPUT> TYPE_OUTPUT nullable(Supplier expression) |

## NPESilencerBuilder Class

The instance of this class is the only one the can create an instance of NPESilencer. To create an instance of this class you can do the following:

```java
NPESilencer.getBuilder()
```

##### addRoot Method

Use this method to add an root object of all the expressions. The root the argument of the first expression. Using this method again will overwrite the existing root object.

| Signature                                                    |
| ------------------------------------------------------------ |
| public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addRoot(TYPE_ROOT_OBJECT root) |

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| root | The root of all expressions. This is not required if your expression is self contained. |

##### addExpr Method

Use this method to add an expression. This can be called multiple times and the expressions will be called in sequence. 

| Signatures | Comment |
|-------|-------|
| public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addExpr(Function expression) | Adding a series of **Function implementations** will create a chain of expression where the **result of the previous expression** is the **argument of the next expression**. Whereas, the argument of the **very first expression the root object**. |
| public NPESilencerBuilder<TYPE_ROOT_OBJECT, TYPE_OUTPUT> addExpr(Supplier expression) | Adding an expression using this signature will **ignore the result of the preceding expression**. It will just generate its output. |

**Parameters**

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| expression | The expression to be evaluated that has the potential to throw NPE. |


##### build method

This method is the only method that can create an instance of LogicMapper *(i.e. this method must be called last.)*.

| Signature |
|------|
| public NPESilencer<TYPE_ROOT_OBJECT, TYPE_OUTPUT> build() |

## Sample Execute Usage

```java
String nullString = null; //This was deliverately set to null.
var output = NPESilencer.<String>nullable(()-> nullString.toString()); //nullString.toString() must throw an NPE.
System.out.println(output);
```

**Expected Output**

```
null
```

> No NPE was thrown and the reaches the last line.

[Table of Contents](USER_GUIDE_TOC.md)

