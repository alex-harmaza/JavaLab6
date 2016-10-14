package by.training.epam.lab6.task3.lock;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Aliaksandr_Harmaza on 10/14/2016.
 */
public class Runner {

    public static void main(String[] args) throws IOException, InterruptedException {
        Lock locker = new ReentrantLock();
        Writer writer = new FileWriter("example.txt");
        Thread[] threadList = new Thread[10];

        for (int i = 0; i < threadList.length; i++){
            threadList[i] = new Thread(){

                @Override
                public void run() {
                    locker.lock();
                    try {
                        System.out.printf("Thread №%d writes to a file\n", this.getId());
                        writer.write(String.valueOf(this.getId()) + "\n");
                        Thread.sleep(2000);
                        System.out.printf("Thread №%d finished writing to the file\n", this.getId());
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        locker.unlock();
                    }
                }

            };
            threadList[i].start();
        }

        for (Thread thread : threadList){
            thread.join();
        }

        writer.close();
    }

}
