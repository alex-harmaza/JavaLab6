package by.training.epam.lab6.task3.cyclicBarrier;


public class FerryBoat implements Runnable {

    //Метод выполняется, когда количество машин,
    //захвативших барьер, станет равно пяти
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("FerryBoat ferried cars");
        } catch (InterruptedException e) {
            System.out.println("The ferry broke");
        }
    }

}
