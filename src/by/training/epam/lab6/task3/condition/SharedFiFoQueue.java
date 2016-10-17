package by.training.epam.lab6.task3.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Aliaksandr_Harmaza on 10/17/2016.
 */
public class SharedFiFoQueue<T> {

    private Object[] queue;
    private int current;
    private int placeIndex;
    private int removeIndex;

    private final Lock lock = new ReentrantLock();
    private final Condition isEmpty = lock.newCondition();
    private final Condition isFull = lock.newCondition();


    public SharedFiFoQueue(int capacity) {
        this.queue = new Object[capacity];
    }


    public void add(T element) throws InterruptedException {
        lock.lock();

        while(current >= queue.length){
            isFull.await();
        }

        queue[placeIndex] = element;
        placeIndex = (placeIndex + 1) % queue.length;
        ++current;
        isEmpty.signal();
        lock.unlock();
    }

    public T remove() throws InterruptedException {
        T element;

        lock.lock();

        while(current <= 0){
            isEmpty.await();
        }

        element = (T) queue[removeIndex];
        removeIndex = (removeIndex + 1) % queue.length;
        --current;

        isFull.signal();
        lock.unlock();
        return element;
    }

}
