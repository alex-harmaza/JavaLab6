package by.training.epam.lab6.task3.countDownLatch;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Aliaksandr_Harmaza on 10/13/2016.
 */
public class Human extends Thread {

    private String name;
    private EnglishGroup group;


    public Human(String name, EnglishGroup group){
        this.name = name;
        this.group = group;
    }


    public String getHumanName(){
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s goes to join the English group.\n", name);
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000 + 1));
            System.out.printf("%s came to join the English group.\n", name);
            group.enroll(this);
        }
        catch (InterruptedException ex){
            System.out.printf("%s changed his mind to learn English.\n", name);
        }
    }
}
