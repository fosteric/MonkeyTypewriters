package io.zipcoder;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {

    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();
        lock.readLock().lock();
        try {
            while (stringIterator.hasNext()){
                copied += stringIterator.next() + " ";
            }
        } finally {
            lock.writeLock().unlock();
            lock.readLock().unlock();
        }

    }
}
