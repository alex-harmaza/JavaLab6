package by.training.epam.lab6.task3.phaser;

import java.util.concurrent.Phaser;

/**
 * Created by Aliaksandr_Harmaza on 10/14/2016.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Car[] cars = new Car[10];
        Phaser phaser = new Phaser(cars.length + 3);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(i, (int)(Math.random() * 100 + 50), phaser,  500000);
            cars[i].start();
            Thread.sleep(100);
        }

        while (phaser.getRegisteredParties() > 3) {
            Thread.sleep(100);
        }

        Thread.sleep(100);
        System.out.println("На старт!");
        phaser.arriveAndDeregister();

        Thread.sleep(100);
        System.out.println("Внимание!");
        phaser.arriveAndDeregister();

        Thread.sleep(100);
        System.out.println("Марш!");
        phaser.arriveAndDeregister();

        for (Car car : cars){
            car.join();
        }
    }
}
