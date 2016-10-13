package by.training.epam.lab6.task3.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Aliaksandr_Harmaza on 10/13/2016.
 */
public class Car extends Thread {

    private int carNumber;
    private CyclicBarrier barrier;


    public Car(int carNumber, CyclicBarrier barrier) {
        this.carNumber = carNumber;
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try {
            System.out.printf("Expectant with №%d arrived at the ferry.\n", carNumber);
            //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
            //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
            barrier.await();
            System.out.printf("Expectant with №%d continued movement.\n", carNumber);
        } catch (BrokenBarrierException | InterruptedException e) {
            System.out.println("Something went wrong");
        }
    }

}
