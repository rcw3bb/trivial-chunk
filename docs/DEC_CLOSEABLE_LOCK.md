##  CloseableLock
Lock *(i.e. java.util.concurrent.locks.Lock)* implementation is **not Closeable by default**  so normally you will use lock like this:

```java
try {
	//LOCK is an instance of ReentrantLock
    LOCK.lock();
    //builder is the shared resource and instance of StringBuilder.
    builder.delete(0, builder.length());
finally {
    LOCK.unlock();
}
```

**Wrapping the Lock implementation with CloseableLock makes it Closeable** so now you can do something like:

```java
//LOCK is an instance of ReentrantLock
try(var ___ = new CloseableLock(LOCK)) {
    //builder is the shared resource and instance of StringBuilder.
    builder.delete(0, builder.length());
}
```

The later construct now becomes less verbose and you are **ensured that the unlock method will be called** when the processing goes beyond the **try-with-resource block**.

Since the **CloseableLock was designed to be used with try-with-resource block** the constructor by default will also call the **lock method**.

##### Constructors

| Signature |
|---------|
| public **CloseableLock**(Lock **lock**) |
| public **CloseableLock**(Lock **lock**, boolean **noLockCall**) |
| public **CloseableLock**(Lock **lock**, boolean **noLockCall**, BooleanSupplier **lockOnlyWhen**) |
| public **CloseableLock**(Lock **lock**, BooleanSupplier **lockOnlyWhen**) |

**Parameters**

| Parameter | Description                               |
| --------- | ----------------------------------------- |
| lock | The **instance of the java.util.concurrent.locks.Lock** to be wrapped. |
| noLockCall | Set to **true to instruct the constructor not to call the lock method**. This means you are intending to call the lock/unluck method yourself. |
| lockOnlyWhen | The logic that **must return true to actually invoke the lock/unluck of the wrapped Lock**. |

##### Main Methods

| Signature | Description |
|--------|--------|
| public void close() | The method that will be called by the try-with-resource block in the finally clause. <br /><br />**This will just call the unluck method.** |
| public void lock() | Calls the **lock method of the the wrapped Lock instance** when the **lockOnlyWhen logic returns true**. <br /><br />*This is available because you can still use the CloseableLock wrapper not within the try-with-resource block.* |
| public void unlock() | Calls the **unlock method of the the wrapped Lock instance** when the **lockOnlyWhen logic returns true**.<br /><br />*This is available because you can still use the CloseableLock wrapper not within the try-with-resource block.* |

[Table of Contents](USER_GUIDE_TOC.md)
