package org.test2;

import java.util.*;

public class Main {
    static ArrayList<String> saddlePoint = new ArrayList<>();
    static int[][] matrix = new int[3][3];

    public static void main(String[] args) {
        fillMatrix(matrix);
        getPotentialValueSaddlePoint();

        if(saddlePoint.isEmpty()){
            System.out.println("Седловых точек нет.");
        } else {
            for (String point : saddlePoint){
                System.out.println(point);
            }
        }

        System.out.println(Arrays.deepToString(matrix));
    }
    public static void fillMatrix(int[][] ints){
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = getRandomNubmer();
            }
        }
    }
    public static int getRandomNubmer(){
        return (int) (Math.random() * 10);
    }
    public static int getMinValue(int[] smallest){
        int min = smallest[0];
        for (int num : smallest){
            if (num < min){
                min = num;
            }
        }
        return min;
    }
    public static int getMaxValue(int[] biggest){
        int max = biggest[0];
        for(int num : biggest){
            if (num > max){
                max = num;
            }
        }
        return max;
    }
    public static boolean isMaxValueColoumn(int[] numCol, int value){
        for (int num : numCol){
            if(getMaxValue(numCol) > value){
                return false;
            }
        }
        return true;
    }
    public static int[] getColoumn(int indexX){
        int[] col = new int[matrix.length];
        if (indexX > matrix.length - 1){
            return col;
        }
        for (int i = 0; i < matrix.length; i++) {
            col[i] = matrix[i][indexX];
        }
        return col;
    }
    public static ArrayList<Integer> getListIndexMinValue(int[] ints, int value){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == value){
                integerArrayList.add(i);
            }
        }
        return integerArrayList;
    }
    //проверка на то, чтобы в данном столбце не было одинаковых значений, т.к. значение седловой должно быть строко больше, а не равно
    public static boolean isNoMoreSuchValuesColoumn(int[] col, int value){
        int countValue = 0;
        for (int i : col){
            if (i == value){
                countValue++;
            }
        }
        if (countValue > 1){
            return false;
        } else {
            return true;
        }
    }
    public static void getPotentialValueSaddlePoint(){
        // седловых точек может быть несколько
        ArrayList<Integer> points = new ArrayList<>();
        int numLine = -1;
        for (int[] ints : matrix) {
            ++numLine;
            int minValue = getMinValue(ints);
            points = getListIndexMinValue(ints, minValue);
            for (Integer ValueIndex : points) {
                if ((isMaxValueColoumn((getColoumn(ValueIndex)), minValue)) && (isNoMoreSuchValuesColoumn(getColoumn(ValueIndex), minValue))) {
                    saddlePoint.add("Значение: " + String.valueOf(minValue) + "; индекс строки: " + String.valueOf(numLine)
                            + ", индекс стролбца: " + String.valueOf(ValueIndex));
                }
            }
        }
    }
}