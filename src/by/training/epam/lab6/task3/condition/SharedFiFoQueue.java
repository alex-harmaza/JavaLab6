package by.training.epam.lab6.task3.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SharedFiFoQueue<T> {

    private Object[] queue;
    private int current;
    private int placeIndex;
    private int removeIndex;

    private final Lock lock;
    private final Condition isNotEmpty;
    private final Condition isNotFull;


    public SharedFiFoQueue(int capacity) {
        this.queue = new Object[capacity];

        //Потоки получают блокировку в том порядке,
        //в каком они ее запрашивали (флаг true)
        lock = new ReentrantLock(true);

        isNotEmpty = lock.newCondition();
        isNotFull = lock.newCondition();
    }


    public void add(T element) throws InterruptedException {
        lock.lock();
        try {
            //Ожидаем, пока не появится место в очереди
            while(current >= queue.length){
                isNotFull.await();
            }

            queue[placeIndex] = element;
            placeIndex = (placeIndex + 1) % queue.length;
            ++current;

            //Сообщаем ожидающим потокам, которые хотят удалить элемент,
            //что очередь не пустая
            isNotEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        T element;
        lock.lock();
        try {

            //Ожидаем пока в очереди не появится элемент
            while(current <= 0){
                isNotEmpty.await();
            }

            element = (T) queue[removeIndex];
            removeIndex = (removeIndex + 1) % queue.length;
            --current;

            //Сообщаем ожидающим потокам, которые хотят добавить элемент,
            //что в очереди освободилось место
            isNotFull.signal();
        }
        finally {
            lock.unlock();
        }
        return element;
    }

}
