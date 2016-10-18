package by.training.epam.lab6.task2;


import java.util.concurrent.Semaphore;

/**
 * Задача "Проблема обедающих философов"
 * (проблема избежания взаимной блокировки)
 * https://ru.wikipedia.org/wiki/Проблема_обедающих_философов
 *
 * В качестве решения задачи была описана реализация с официантом
 * (3-яя сторона, которая контролирует количество обедающих философов)
 *
 * Предметная область: информатика, кибернетика и программирование:
 * ситуации в многозадачной среде или СУБД, при которой несколько процессов
 * находятся в состоянии бесконечного ожидания ресурсов, занятых самими этими процессами.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {

        //Семафор, который контролирует количество философов,
        //которые могут одновременно есть (для 5 философов могут есть одновременно только 2)
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
