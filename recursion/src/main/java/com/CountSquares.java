package com;

public class CountSquares {
    public static void main(String[] args) {
        //int[][] mat = {{0,1,1,1}, {1,1,1,1}, {0,1,1,1}};
        int[][] mat = {{1,0,1}, {1,1,0}, {1,1,0}};
        System.out.println(countSquares(mat));
    }

    public static int countSquares(int[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];

        int sum = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                    //sum += dp[i][j];
                    continue;
                }

                if(matrix[i][j] == 0) {
                    dp[i][j] = 0;
                    continue;
                }


                dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]),dp[i][j - 1]);
                //sum += dp[i][j];
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
    }
}

/*if(matrix[i - 1][j] == 1 && matrix[i][j - 1] == 1 && matrix[i - 1][j - 1] == 1 && matrix[i][j] == 1)
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = matrix[i][j];*/