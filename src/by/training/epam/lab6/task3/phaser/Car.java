package by.training.epam.lab6.task3.phaser;

import java.util.concurrent.Phaser;

/**
 * Created by Aliaksandr_Harmaza on 10/14/2016.
 */
public class Car  extends Thread{

    private int carNumber;
    private int carSpeed;
    private Phaser phaser;
    private int trackLength;


    public Car(int carNumber, int carSpeed, Phaser phaser, int trackLength) {
        this.carNumber = carNumber;
        this.carSpeed = carSpeed;
        this.phaser = phaser;
        this.trackLength = trackLength;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", carNumber);
            phaser.arriveAndDeregister();
            phaser.awaitAdvance(0);
            Thread.sleep(trackLength / carSpeed);
            System.out.printf("Автомобиль №%d финишировал!\n", carNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
