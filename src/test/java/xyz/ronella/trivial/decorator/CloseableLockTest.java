package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.locks.Lock;

public class CloseableLockTest {

    @Test
    public void constructWithLockCall() {
        var lock = Mockito.spy(Lock.class);
        try(var ___ = new CloseableLock(lock)) {
            Mockito.verify(lock, Mockito.times(1)).lock();
        }
        Mockito.verify(lock, Mockito.times(1)).unlock();
    }

    @Test
    public void constructWithjoutLockCall() {
        var lock = Mockito.spy(Lock.class);
        new CloseableLock(lock, true);
        Mockito.verify(lock, Mockito.times(0)).lock();
        Mockito.verify(lock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithLockCallFalseNoLockWhen() {
        var lock = Mockito.spy(Lock.class);
        try(var ___ = new CloseableLock(lock, ()->false)) {
            Mockito.verify(lock, Mockito.times(0)).lock();
        }
        Mockito.verify(lock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithLockCallTrueNoLockWhen() {
        var lock = Mockito.spy(Lock.class);
        try(var ___ = new CloseableLock(lock, ()->true)) {
            Mockito.verify(lock, Mockito.times(1)).lock();
        }
        Mockito.verify(lock, Mockito.times(1)).unlock();
    }

    @Test
    public void constructWithNoLockCallTrueNoLockWhen() {
        var lock = Mockito.spy(Lock.class);
        try(var ___ = new CloseableLock(lock, true,  ()->true)) {
            Mockito.verify(lock, Mockito.times(0)).lock();
        }
        Mockito.verify(lock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithNoLockCallFalseNoLockWhen() {
        var lock = Mockito.spy(Lock.class);
        try(var ___ = new CloseableLock(lock, true,  ()->false)) {
            Mockito.verify(lock, Mockito.times(0)).lock();
        }
        Mockito.verify(lock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithNoLockCallTrueNoLockWhenExplicitLock() {
        var spyLock = Mockito.spy(Lock.class);
        try(var lock = new CloseableLock(spyLock, true,  ()->true)) {
            lock.lock();
            Mockito.verify(spyLock, Mockito.times(1)).lock();
        }
        Mockito.verify(spyLock, Mockito.times(1)).unlock();
    }

    @Test
    public void constructWithNoLockCallTrueNoLockWhenExplicitLockUnluck() {
        var spyLock = Mockito.spy(Lock.class);
        var lock = new CloseableLock(spyLock, true,  ()->true);
        lock.lock();
        lock.unlock();
        Mockito.verify(spyLock, Mockito.times(1)).lock();
        Mockito.verify(spyLock, Mockito.times(1)).unlock();
    }

    @Test
    public void constructWithNoLockCallFalseNoLockWhenExplicitLockUnluck() {
        var spyLock = Mockito.spy(Lock.class);
        var lock = new CloseableLock(spyLock, true,  ()->false);
        lock.lock();
        lock.unlock();
        Mockito.verify(spyLock, Mockito.times(0)).lock();
        Mockito.verify(spyLock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithNoLockCallTrueNoLockWhenExplicitUnluckOnly() {
        var spyLock = Mockito.spy(Lock.class);
        var lock = new CloseableLock(spyLock, true,  ()->true);
        lock.unlock();
        Mockito.verify(spyLock, Mockito.times(0)).lock();
        Mockito.verify(spyLock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithNoLockCallFalseNoLockWhenExplicitUnluckOnly() {
        var spyLock = Mockito.spy(Lock.class);
        var lock = new CloseableLock(spyLock, true,  ()->false);
        lock.unlock();
        Mockito.verify(spyLock, Mockito.times(0)).lock();
        Mockito.verify(spyLock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithNoLockCallTrueNoLockWhenExplicitLockOutsideTry() {
        var spyLock = Mockito.spy(Lock.class);
        var lock = new CloseableLock(spyLock, true,  ()->true);
        try(lock) {
            lock.lock();
            Mockito.verify(spyLock, Mockito.times(1)).lock();
        }
        Mockito.verify(spyLock, Mockito.times(1)).unlock();
    }

    @Test
    public void constructNested() {
        var spyLock = Mockito.spy(Lock.class);
        try(var ___ = new CloseableLock(spyLock)) {
            try (var ____ = new CloseableLock(spyLock)) {
                Mockito.verify(spyLock, Mockito.times(2)).lock();
            }
        }
        Mockito.verify(spyLock, Mockito.times(2)).unlock();
    }

    @Test
    public void constructWithNoLockCallFalseNoLockWhenUnlock() {
        var spyLock = Mockito.spy(Lock.class);
        try(var lock = new CloseableLock(spyLock, false, () -> false)) {
            Mockito.verify(spyLock, Mockito.times(0)).lock();
            lock.unlock();
        }
        Mockito.verify(spyLock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructWithNoLockCallTrueNoLockWhenUnlock() {
        var spyLock = Mockito.spy(Lock.class);
        try(var lock = new CloseableLock(spyLock, false, () -> true)) {
            Mockito.verify(spyLock, Mockito.times(1)).lock();
            lock.unlock();
        }
        Mockito.verify(spyLock, Mockito.times(1)).unlock();
    }

    @Test
    public void constructCallTrueNoLockWhenUnlock() {
        var spyLock = Mockito.spy(Lock.class);
        try(var lock = new CloseableLock(spyLock, true, () -> true)) {
            Mockito.verify(spyLock, Mockito.times(0)).lock();
            lock.unlock();
        }
        Mockito.verify(spyLock, Mockito.times(0)).unlock();
    }

    @Test
    public void constructCallFalseNoLockWhenUnlock() {
        var spyLock = Mockito.spy(Lock.class);
        try(var lock = new CloseableLock(spyLock, true, () -> false)) {
            lock.lock();
            Mockito.verify(spyLock, Mockito.times(0)).lock();
            lock.unlock();
        }
        Mockito.verify(spyLock, Mockito.times(0)).unlock();
    }
}
