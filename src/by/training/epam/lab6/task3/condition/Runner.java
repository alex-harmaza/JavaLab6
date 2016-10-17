package by.training.epam.lab6.task3.condition;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Aliaksandr_Harmaza on 10/17/2016.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        SharedFiFoQueue<Long> queue = new SharedFiFoQueue<>(5);

        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    final long threadID = Thread.currentThread().getId();
                    try {
                        System.out.printf("Thread №%d adds element\n", threadID);
                        queue.add(threadID);
                        System.out.printf("Thread №%d added element\n", threadID);

                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000 + 1));

                        System.out.printf("Thread №%d removes element\n", threadID );
                        queue.remove();
                        System.out.printf("Thread №%d removed element\n", threadID );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            threads[i].start();
        }

        for (Thread thread : threads){
            thread.join();
        }
    }

}
