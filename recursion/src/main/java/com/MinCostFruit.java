package com;

public class MinCostFruit {
    public static void main(String[] args) {
        System.out.println(minCost(new int[]{20, 10, 4, 50, 100}, 5, 5));
        System.out.println(minCost(new int[]{20, 10, 4, 50, 100}, 5, 5, new Integer[6][6]));
        System.out.println(minCostTab(new int[]{20, 10, 4, 50, 100}, 5, 5));
    }

    public static int minCostTab(int[] cost, int n, int w) {

        int[][] dp = new int[n + 1][w + 1];

        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= n; col++) {
                if(col == 0) {
                    dp[row][col] = 0;
                    continue;
                }

                if(row == 0) {
                    dp[row][col] = Integer.MAX_VALUE;
                    continue;
                }

                if(col >= row) {
                    int include = cost[row - 1] + dp[row][col - row];
                    int exclude = dp[row - 1][col];
                    dp[row][col] = Math.min(include, exclude);
                } else dp[row][col] = dp[row - 1][col];
            }
        }

        return dp[n][w];
    }

    public static int minCost(int[] cost, int n, int w, Integer[][] dp) {

        if(n == 0)
            return Integer.MAX_VALUE;

        if(w == 0)
            return 0;

        if(dp[n][w] != null)
            return dp[w][n];

        if(w >= n) {
            int include = cost[n - 1] + minCost(cost, n, w - n);
            int exclude = minCost(cost, n - 1, w);
            return dp[w][n] = Math.min(include, exclude);
        } else return dp[w][n] = minCost(cost, n - 1, w);
    }

    public static int minCost(int[] cost, int n, int w) {

        if(n == 0)
            return Integer.MAX_VALUE;

        if(w == 0)
            return 0;

        if(w >= n) {
            int include = cost[n - 1] + minCost(cost, n, w - n);
            int exclude = minCost(cost, n - 1, w);
            return Math.min(include, exclude);
        } else return minCost(cost, n - 1, w);
    }
}
