package com;

public class Matrix {
    public static void main(String[] args) {
        System.out.println(maxSum(new int[][]{{10, 10, 100, 30}, {80, 50, 10, 50}}, 4,4,5, ""));
    }

    public static int maxSum(int[][] mat, int row, int col, int k, String res) {
        if(row == 0 || col == 0 || k == 0)
            return 0;

        int includeRow = mat[row - 1][col - 1] + maxSum(mat, row - 1, col, k - 1, res + " " + mat[row - 1][col - 1]);
        int includeCol = mat[row - 1][col - 1] + maxSum(mat, row, col - 1, k - 1, res + " " + mat[row - 1][col - 1]);
        int excludeRow = maxSum(mat, row - 1, col, k, res);
        int excludeCol = maxSum(mat, row, col - 1, k, res);

        return Math.max(includeRow, Math.max(includeCol, Math.max(excludeCol, excludeRow)));
    }
}
