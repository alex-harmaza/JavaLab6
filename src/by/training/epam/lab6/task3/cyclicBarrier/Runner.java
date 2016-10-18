package by.training.epam.lab6.task3.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 *  Существует паромная переправа.
 *  Паром может переправлять одновременно по 5 автомобилей.
 *  Чтобы не гонять паром лишний раз, нужно отправлять его,
 *  когда у переправы соберется минимум 5 автомобилей.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier ferry = new CyclicBarrier(5, new FerryBoat());
        Car[] cars = new Car[20];

        for (int i = 0; i < cars.length; i++){
            cars[i] = new Car(i, ferry);
            cars[i].start();
        }

        for (Car car : cars){
            car.join();
        }
    }

}
