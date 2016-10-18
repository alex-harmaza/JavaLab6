package by.training.epam.lab6.task3.phaser;

import java.util.concurrent.Phaser;

/**
 * Гонки автомобилей.
 * Сначала все автомобили подъежают к стартовой линии
 * ожидают друг друга, пока все не соберутся.
 * Когда все соберутся, то производится отсчет
 * и все машины стартуют.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Car[] cars = new Car[10];

        //Создаем объекта класса Phaser на количество фаз
        //равное количеству авто + 3 фазы (На старт, внимание, марш)
        Phaser phaser = new Phaser(cars.length + 3);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(i, (int)(Math.random() * 100 + 50), phaser,  500000);
            cars[i].start();
            Thread.sleep(100);
        }

        //Ждем, пока все машины не доедут до стартовой линии
        while (phaser.getRegisteredParties() > 3) {
            Thread.sleep(100);
        }

        Thread.sleep(200);
        System.out.println("At the start!");
        phaser.arriveAndDeregister();

        Thread.sleep(200);
        System.out.println("Attention!");
        phaser.arriveAndDeregister();

        Thread.sleep(200);
        System.out.println("Go!");
        phaser.arriveAndDeregister();

        for (Car car : cars){
            car.join();
        }
    }
}
