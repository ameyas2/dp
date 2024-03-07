package com;

public class GoldMine {
    public static long goldMine(int[][] mine) {
        int rows = mine.length;
        int cols = mine[0].length;
        int[][] result = new int[rows][cols];
        int max = 0;

        for(int row = 0; row < rows; row++) {
            result[row][cols - 1] = mine[row][cols - 1];
        }
        for(int row = 0; row < rows; row++) {
            for(int col = cols - 2; col >= 0; col--) {

                int upRight = row - 1 >= 0 ? result[row - 1][col + 1] : -1;
                int right = result[row][col + 1];
                int downRight = row + 1 < rows ? result[row + 1][col + 1] : -1;

                result[row][col] = mine[row][col] + max(upRight, downRight, right);
            }
        }
        printArr(mine);
        printArr(result);

        for(int i = 0; i < rows; i++) {
            if(result[i][0] > max)
                max = result[i][0];
        }
        return max;
    }

    public static int max(int a, int b, int c) {
        if(a > b && a > c)
            return a;
        else if(b > a && b > c)
            return b;
        else return c;
    }

    public static void printArr(int[][] arr) {
        for(int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
