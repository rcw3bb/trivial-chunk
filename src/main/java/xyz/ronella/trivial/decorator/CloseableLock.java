package xyz.ronella.trivial.decorator;

import xyz.ronella.trivial.handy.Require;
import xyz.ronella.trivial.handy.RequireObject;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.function.BooleanSupplier;

/**
 * A class that decorates an implementation of Lock to become AutoCloseable.
 *
 * @author Ron Webb
 * @since 2.9.0
 */
public class CloseableLock implements AutoCloseable {

    private final Lock receivedLock;
    private final BooleanSupplier lockOnlyWhen;
    private int lockCount;

    /**
     * Creates an instance of CloseableLock. This constructor will call the lock method by default.
     *
     * @param lock An instance of Lock
     */
    public CloseableLock(final Lock lock) {
        this(lock, false);
    }

    /**
     * Creates an instance of CloseableLock. This constructor will call the lock method by default.
     *
     * @param lock An instance of Lock
     * @param lockOnlyWhen The logic if lock will actually be needed.
     */
    public CloseableLock(final Lock lock, final BooleanSupplier lockOnlyWhen) {
        this(lock, false, lockOnlyWhen);
    }

    /**
     * Creates an instance of CloseableLock.
     *
     * @param lock An instance of Lock
     * @param noLockCall When true the constructor will not call the lock method.
     */
    public CloseableLock(final Lock lock, final boolean noLockCall) {
        this(lock, noLockCall, () -> Boolean.TRUE);
    }

    /**
     * Creates an instance of CloseableLock.
     *
     * @param lock An instance of Lock
     * @param noLockCall When true the constructor will not call the lock method.
     * @param lockOnlyWhen The logic if lock will actually be needed.
     */
    public CloseableLock(final Lock lock, final boolean noLockCall, final BooleanSupplier lockOnlyWhen) {
        Require.objects(new RequireObject(lock, "lock cannot be null"));
        this.receivedLock = lock;
        this.lockOnlyWhen = Optional.ofNullable(lockOnlyWhen).orElse(() -> Boolean.TRUE);
        if (!noLockCall) {
            lock();
        }
    }

    /**
     * Calls the lock method if the lockOnlyWhen returns true.
     */
    public void lock() {
        if (lockOnlyWhen.getAsBoolean()) {
            receivedLock.lock();
            ++lockCount;
        }
    }

    /**
     * Calls the unlock method if the lockOnlyWhen returns true.
     */
    public void unlock() {
        if (lockCount>0 && lockOnlyWhen.getAsBoolean()) {
            --lockCount;
            receivedLock.unlock();
        }
    }

    /**
     * Calls the unlock method.
     */
    @Override
    public void close() {
        unlock();
    }
}
