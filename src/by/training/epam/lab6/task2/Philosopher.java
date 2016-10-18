package by.training.epam.lab6.task2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;


public class Philosopher extends Thread {

    private Semaphore waiter;
    private String name;


    public Philosopher(Semaphore waiter, String name){
        this.waiter = waiter;
        this.name = name;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                //Поток пытается захватить семафор
                //Если 2 потока уже захватили семафор, то текущий поток
                //ожидает пока один из потоков не освободит семафор
                waiter.acquire();

                System.out.printf("%s eating spaghetti\n", name);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000 + 1));
                System.out.printf("%s finished eating spaghetti\n", name);
            } catch (InterruptedException e) {
                System.out.printf("%s apparently overeaten spaghetti\n", name);
                Thread.currentThread().interrupt();
            }
            finally {
                //Освобождаем семафор
                waiter.release();
            }
        }
    }
}
