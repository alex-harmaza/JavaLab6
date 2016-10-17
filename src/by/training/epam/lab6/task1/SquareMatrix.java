package by.training.epam.lab6.task1;


/**
 * Created by alexh on 10.10.2016.
 */
public class SquareMatrix {

    private double[][] matrix;


    public SquareMatrix(int size){
        if (size < 2){
            throw new IllegalArgumentException("The size can't be negative");
        }
        matrix = new double[size][size];
    }


    public int getSize(){
        return matrix.length;
    }

    public double getElement(int x, int y){
        return matrix[x][y];
    }

    public void setElement(int x, int y, double value){
        matrix[x][y] = value;
    }

    public double[] getRow(int x){
        return matrix[x];
    }

    public void setRow(int x, double[] row){
        if (row.length != matrix.length){
            throw new IllegalArgumentException("Row is not the same size");
        }
        matrix[x] = row;
    }

    public double[] getColumn(int index){
        double[] column = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++){
            column[i] = matrix[i][index];
        }
        return column;
    }

    public void setColumn(int index, double[] column){
        if (column.length != matrix.length){
            throw new IllegalArgumentException("Column is not the same size");
        }
        for (int i = 0; i < matrix.length; i++){
            matrix[i][index] = column[i];
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int maxValueLength = 0;

        for (double[] row : matrix){
            for (double e : row){
                int valueLength = String.format("%.3f", e).length();
                if (maxValueLength < valueLength){
                    maxValueLength = valueLength;
                }
            }
        }

        String format = "%-" + (maxValueLength + 4) + ".3f";

        for (double[] row : matrix){
            builder.append((builder.length() == 0) ? "" : "\n");
            for (int i = 0; i < row.length; i++){
                builder.append(String.format(format, row[i]));
            }
        }
        return builder.toString();
    }
}
