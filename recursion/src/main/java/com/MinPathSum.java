package com;

public class MinPathSum {
    public static void main(String[] args) {
        /*System.out.println(minPathSum(new int[][]{{ 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 }}, 2, 2));
        System.out.println(minPathSum(new int[][]{{ 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 }}, 2, 2, new Integer[3][3]));*/
        //System.out.println(triangleMinPath(new int[][]{{1}, {2,3}, {3,6,7}, {8,9,6,10}}, 0, 0, ""));
        //System.out.println(triangleMinPath(new int[][]{{1}, {2,3}, {3,6,7}, {8,9,6,10}}));
        //System.out.println(triangleMinPathOptimized(new int[][]{{1}, {2,3}, {3,6,7}, {8,9,6,10}}));
        System.out.println(minCostPath(new int[][]{{1,2,10,4}, {100,3,2,1}, {1,1,20,2}, {1,2,2,1}}));
    }

    // https://www.youtube.com/watch?v=N_aJ5qQbYA0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=13
    public static int minCostPath(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] tab = new int[n][m];

        for(int i = 0; i < n; i++) {
            tab[i][m - 1] = arr[i][m - 1];
        }

        for(int col = m - 2; col >= 0; col--) {
            for(int row = 0; row < n; row++) {
                int up = Integer.MAX_VALUE;
                int down = Integer.MAX_VALUE;
                int right = tab[row][col + 1];

                if(row > 0)
                    up = tab[row - 1][col + 1];

                if(row < n - 1)
                    down = tab[row + 1][col + 1];


                tab[row][col] = arr[row][col] + Math.min(Math.min(up, down), right);
            }
        }

        Utils.print(tab);
        System.out.println();
        Utils.print(arr);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < tab[0].length; i++) {
            if (min > tab[i][0])
                min = tab[i][0];
        }

        return min;
    }

    // https://www.youtube.com/watch?v=SrP-PiLSYC0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=12
    public static int triangleMinPathOptimized(int[][] arr) {
        int n = arr.length;
        int[] below = new int[n];
        int[] curr = new int[n - 1];

        for (int i = 0; i < arr[n - 1].length; i++)
            below[i] = arr[n - 1][i];

        for(int row = n - 1; row > 0; row--) {
            for(int col = 0; col < arr[row].length - 1; col++) {
                int down = arr[row - 1][col] + below[col];
                int downRight = arr[row - 1][col] + below[col + 1];
                curr[col] = Math.min(down, downRight);
            }
            below = curr;
            curr = new int[curr.length - 1];
        }

        return below[0];
    }

    public static int triangleMinPath(int[][] arr) {
        int n = arr.length;
        int[][] tab = new int[n + 1][n + 1];

        for(int i = 0; i < arr[n - 1].length; i++)
            tab[n][i] = arr[n - 1][i];

        for(int row = n - 1; row >= 0; row--) {
            for(int col = 0; col < arr[row].length - 1; col++) {
                int down = arr[row - 1][col] + tab[row + 1][col];
                int downRight = arr[row - 1][col] + tab[row + 1][col + 1];
                tab[row][col] = Math.min(down, downRight);
            }
        }

        return tab[1][0];
    }

    public static int triangleMinPath(int[][] arr, int row, int col, Integer[][] tab) {
        if(row == arr.length - 1) {
            return arr[row][col];
        }

        if(tab[row][col] != null)
            return tab[row][col];

        int down = arr[row][col] + triangleMinPath(arr, row + 1, col, tab);
        int downRight = arr[row][col] + triangleMinPath(arr, row + 1, col + 1, tab);
        int min = Math.min(down, downRight);
        return tab[row][col] = min;
    }

    public static int triangleMinPath(int[][] arr, int row, int col, String res) {
        if(row == arr.length - 1) {
            //System.out.println(res);
            return arr[row][col];
        }

        /*if(row > arr.length || col > arr[0].length)
            return Integer.MAX_VALUE;*/

        int down = arr[row][col] + triangleMinPath(arr, row + 1, col, res + "D");
        int downRight = arr[row][col] + triangleMinPath(arr, row + 1, col + 1, res + "dR");
        int min = Math.min(down, downRight);
        return min;
    }

    public static int minPathSum(int[][] arr, int n, int m, Integer[][] dp) {
        if(n < 0 || m < 0)
            return Integer.MAX_VALUE;

        if(n == 0 && m == 0)
            return arr[n][m];

        if(dp[n][m] != null)
            return dp[n][m];

        int up = minPathSum(arr, n - 1, m);
        int upLeft = minPathSum(arr, n - 1, m - 1);
        int left = minPathSum(arr, n, m - 1);

        return dp[n][m] = arr[n][m] + min(up, left, upLeft);
    }

    public static int minPathSum(int[][] arr, int n, int m) {
        if(n < 0 || m < 0)
            return Integer.MAX_VALUE;

        if(n == 0 && m == 0)
            return arr[n][m];

        int up = minPathSum(arr, n - 1, m);
        int upLeft = minPathSum(arr, n - 1, m - 1);
        int left = minPathSum(arr, n, m - 1);
        return arr[n][m] + min(up, left, upLeft);
    }

    public static int min(int a, int b, int c){
        if(a < b && a < c)
            return a;
        else if(b < a && b < c)
            return b;
        else return c;
    }
}
