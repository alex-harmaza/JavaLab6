package by.training.epam.lab6.task3.countDownLatch;


/**
 * Пример из жизни: человек захотел записаться на курсы английского языка.
 * Он пришел в компанию, которая формирует группу из N человек.
 * Группа постепенно заполняется людьми и когда наберется необходимое
 * количество людей - начнутся курсы по языку
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        String[] names = new String[]{"Ivan", "Alex", "Yana", "Julia", "Dima", "Olga"};
        EnglishGroup group = new EnglishGroup(names.length);

        for (String name : names){
            new Human(name, group).start();
        }

        group.expectFillingGroup();
    }

}
