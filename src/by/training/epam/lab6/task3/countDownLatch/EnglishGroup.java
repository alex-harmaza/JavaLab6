package by.training.epam.lab6.task3.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Aliaksandr_Harmaza on 10/13/2016.
 */
public class EnglishGroup {

    //Счетчик свободных мест в группе
    private CountDownLatch latch;


    public EnglishGroup(int groupSize){
        latch = new CountDownLatch(groupSize);
    }


    public void enroll(Human human) throws InterruptedException{
        try {
            System.out.printf("%s recorded in the group.\n", human.getHumanName());
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 4000 + 1));
            System.out.printf("%s joined the group. It remains to %d seats.\n",
                    human.getHumanName(), latch.getCount() - 1);
        }
        finally {
            latch.countDown();
        }
    }


    public void expectFillingGroup() throws InterruptedException {
        latch.await();  //Когда счетчик будет равен нулю, блокировка снимется
        System.out.printf("Group filled.\n");
    }

}
