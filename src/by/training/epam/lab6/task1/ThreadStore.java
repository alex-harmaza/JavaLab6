package by.training.epam.lab6.task1;

import java.io.Closeable;
import java.util.concurrent.*;


public class ThreadStore implements Closeable{

    private ThreadPoolExecutor threadPool;


    public ThreadStore(int threadNumber){
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadNumber);
    }


    public SquareMatrix multiplyMatrices(SquareMatrix first, SquareMatrix second)
            throws ExecutionException, InterruptedException {

        if (first.getSize() != second.getSize()){
            throw new IllegalArgumentException("The order is not the same");
        }

        FutureTask<Double>[][] threadResults = new FutureTask[first.getSize()][second.getSize()];
        for (int i = 0; i < first.getSize(); i++){
            for (int j = 0; j < second.getSize(); j++){
                threadResults[i][j] = new FutureTask<>(new RowMultiplication(first.getRow(i), second.getColumn(j)));
                threadPool.execute(threadResults[i][j]);
            }
        }

        SquareMatrix composition = new SquareMatrix(threadResults.length);
        for (int i = 0; i < threadResults.length; i++){
            for (int j = 0; j < threadResults.length; j++){
                composition.setElement(i, j, threadResults[i][j].get());
            }
        }

        return composition;
    }

    @Override
    public void close() {
        threadPool.shutdown();
    }


    private class RowMultiplication implements Callable<Double> {

        private double[] r1;
        private double[] r2;


        public RowMultiplication(double[] r1, double[] r2){
            this.r1 = r1;
            this.r2 = r2;
        }


        @Override
        public Double call() throws Exception {
            double sum = 0;
            for (int i = 0; i < r1.length; i++){
                sum += r1[i] * r2[i];
            }
            return sum;
        }
    }
}
