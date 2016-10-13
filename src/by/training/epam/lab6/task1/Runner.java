package by.training.epam.lab6.task1;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by alexh on 10.10.2016.
 */
public class Runner {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        try {
            System.out.print("Enter the order matrices: ");
            int order = Integer.parseInt(console.nextLine().trim());

            if (order < 0){
                throw new NumberFormatException();
            }

            System.out.println("Enter the first matrix:\n");
            SquareMatrix firstMatrix = getMatrixFromConsole(console, order);

            System.out.println("\nEnter the second matrix:\n");
            SquareMatrix secondMatrix = getMatrixFromConsole(console, order);

            try (ThreadStore store = new ThreadStore(order * order / 2)) {
                System.out.println("\nResult:\n\n" + store.multiplyMatrices(firstMatrix, secondMatrix));
            }
        }
        catch (NumberFormatException ex){
            System.out.println("Error: incorrect input value");
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }

    private static SquareMatrix getMatrixFromConsole(Scanner scanner, int order){
        SquareMatrix result = new SquareMatrix(order);
        for (int i = 0; i < order; i++){
            for (int j = 0; j < order; j++){
                System.out.print(String.format("element[%d, %d] = ", i, j));
                result.setElement(i, j, Double.parseDouble(scanner.nextLine().trim()));
            }
        }
        return result;
    }
}
