package by.training.epam.lab6.task3.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Aliaksandr_Harmaza on 10/14/2016.
 */
public class Runner {


    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        String names[] = new String[]{"Alex", "Olga"};
        Thread[] threads = new Thread[2];

        for (int i = 0; i < threads.length; i++){
            threads[i] = new Human(names[i], exchanger);
            threads[i].start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }


    private static class Human extends Thread {

        private String name;
        private Exchanger<String> exchanger;


        public Human(String name, Exchanger<String> exchanger){
            this.name = name;
            this.exchanger = exchanger;
        }


        @Override
        public void run() {
            try {
                System.out.printf("%s: hi, %s!\n", this.name, exchanger.exchange(this.name));
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 6000 + 1));
                System.out.printf("%s: goodbye, %s!\n", this.name, exchanger.exchange(this.name));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
