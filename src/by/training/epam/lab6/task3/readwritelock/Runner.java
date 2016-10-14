package by.training.epam.lab6.task3.readwritelock;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Aliaksandr_Harmaza on 10/14/2016.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        ThreadSafeArrayList<String> list = new ThreadSafeArrayList<>();

        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread(){

                @Override
                public void run() {
                    try {
                        System.out.printf("Thread №%d add element\n", this.getId());
                        list.add(String.valueOf(this.getId()));

                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000 + 1));

                        System.out.printf("Thread №%d get element\n", this.getId());
                        list.get(0);
                    }
                    catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }

            };
            threads[i].start();
        }

        for (Thread thread : threads){
            thread.join();
        }
    }
}
