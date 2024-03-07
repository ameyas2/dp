package com;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        System.out.println(unboundedKnapsack(new int[]{6, 18}, new int[]{2, 3}, 50, 2, new Integer[10][100]));
        System.out.println(unboundedKnapsack(new int[]{6, 18}, new int[]{2, 3}, 50));
    }

    // https://www.geeksforgeeks.org/unbounded-knapsack-repetition-of-items-allowed-set-2/

    public static int unboundedKnapsack(int[] val, int[] wt, int capacity) {
        int n = wt.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= capacity; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(j >= wt[i - 1]) {
                    int include = val[i - 1] + dp[i][j - wt[i - 1]];
                    int exclude = dp[i - 1][j];
                    dp[i][j] = Math.max(include, exclude);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[n][capacity];
    }

    public static int unboundedKnapsack(int[] val, int[] wt, int capacity, int n, Integer[][] dp) {
        if(n == 0 || capacity == 0)
            return 0;

        if(dp[n][capacity] != null)
            return dp[n][capacity];

        if(capacity >= wt[n - 1]) {
            int include = val[n - 1] + unboundedKnapsack(val, wt, capacity - wt[n - 1], n, dp);
            int exclude = unboundedKnapsack(val, wt, capacity, n - 1, dp);
            return dp[n][capacity] = Math.max(include, exclude);
        } else {
            return dp[n][capacity] = unboundedKnapsack(val, wt, capacity, n - 1, dp);
        }
    }
}
