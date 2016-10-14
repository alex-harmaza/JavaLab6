package by.training.epam.lab6.task3.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Aliaksandr_Harmaza on 10/14/2016.
 */
public class ThreadSafeArrayList<E> {

    private final ReadWriteLock readWriteLock;
    private final List<E> list = new ArrayList<E>();


    public ThreadSafeArrayList(){
        readWriteLock = new ReentrantReadWriteLock();
    }


    public void add(E o) {
        readWriteLock.writeLock().lock();
        try {
            list.add(o);
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public E get(int i) {
        try {
            readWriteLock.readLock().lock();
            return list.get(i);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }
}

