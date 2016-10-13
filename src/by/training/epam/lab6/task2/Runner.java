package by.training.epam.lab6.task2;


import java.util.concurrent.Semaphore;

/**
 * Created by alexh on 07.10.2016.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Semaphore waiter = new Semaphore(2, true);
        for (String name : new String[]{ "Parmenides", "Aristotle", "Marcus Aurelius",
                "Benedict Spinoza", "Arthur Schopenhauer"}){
            Philosopher philosopher = new Philosopher(waiter, name);
            philosopher.setDaemon(true);
            philosopher.start();
        }
        Thread.sleep(10000);
    }

}
