package com;

public class Knapsack {
    public static void main(String[] args) {
       /* System.out.println(knapsack(new int[]{1,3,4,5}, new int[]{1,4,5,7}, 7, 4, ""));
        System.out.println(knapsack2(new int[]{1,3,4,5}, new int[]{1,4,5,7}, 7, 4, "", new Integer[5][8]));*/
        //System.out.println(knapsack(new int[]{1,3,4,5}, new int[]{1,4,5,7}, 7));
        //knapsackPrint(new int[]{1,3,4,5}, new int[]{1,4,5,7}, 7);

        //System.out.println(diceSum(3, 6, ""));
        System.out.println(extendedKnapsack(new int[]{2, 7, 1, 5, 3}, new int[]{2, 5, 2, 3, 4}, 8,2,5, new Integer[6][9][3]));
        System.out.println(extendedKnapsack(new int[]{2, 7, 1, 5, 3}, new int[]{2, 5, 2, 3, 4}, 1,2,5, new Integer[6][2][3]));
    }

    public static int extendedKnapsack(int[] p, int[] w, int capacity, int k, int n, Integer[][][] dp) {
        if(k == 0 || n == 0 || capacity <= 0) {
            return 0;
        }

        if(dp[n][capacity][k] != null)
            return dp[n][capacity][k];

        if(capacity >= w[n - 1]) {
            int include = p[n - 1] + extendedKnapsack(p, w, capacity - w[n - 1], k - 1, n - 1, dp);
            int exclude = extendedKnapsack(p, w, capacity, k, n - 1, dp);
            return Math.max(include, exclude);
        } else {
            return extendedKnapsack(p, w, capacity, k, n - 1, dp);
        }
    }

    // https://www.geeksforgeeks.org/count-ways-to-obtain-given-sum-by-repeated-throws-of-a-dice/ this version does not consider permutations
    public static int diceSum(int sum, int num, String res) {
        if(sum == 0) {
            System.out.println(res);
            return 1;
        }

        if(sum < 0 || (sum > 0 && num == 0))
            return 0;

        if(sum >= num) {
            int include = diceSum(sum - num, num, res + " " + num);
            int exclude = diceSum(sum, num - 1, res);
            return include + exclude;
        } else {
            return diceSum(sum, num - 1, res);
        }
    }

    public static void knapsackPrint(int[] wt, int[] val, int capacity) {
        int n = wt.length;

        int[][] tab = new int[n + 1][capacity + 1];

        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= capacity; col++) {
                if(row == 0 || col == 0) {
                    tab[row][col] = 0;
                    continue;
                }

                if(col >= wt[row - 1]) {
                    int include = tab[row - 1][col - wt[row - 1]] + val[row - 1];
                    int exclude = tab[row - 1][col];
                    tab[row][col] = Math.max(include, exclude);
                } else {
                    tab[row][col] = tab[row - 1][col];
                }
            }
        }

        System.out.println();
    }

    public static int knapsack(int[] wt, int[] val, int capacity) {
        int n = wt.length;
        Integer[][] tab = new Integer[n + 1][capacity + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= capacity; j++) {
                if(i == 0 || j == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= wt[i - 1]) {
                    int include = val[i - 1] + tab[i - 1][j - wt[i - 1]];
                    int exclude = tab[i - 1][j];
                    tab[i][j] = Math.max(include, exclude);
                } else
                    tab[i][j] = tab[i - 1][j];
            }
        }
        Utils.print(tab);
        return tab[n][capacity];
    }

    public static int knapsack2(int[] wt, int[] val, int capacity, int n, String res, Integer[][] cache) {
        if(n == 0 || capacity == 0) {
            if(capacity == 0) {
                System.out.println(res);
            }
            return 0;
        }

        if(cache[n][capacity] != null)
            return cache[n][capacity];

        if(capacity >= wt[n - 1]) {
            int include = val[n - 1] + knapsack2(wt, val, capacity - wt[n - 1], n - 1, res + " " + wt[n - 1], cache);
            int exclude = knapsack2(wt, val, capacity, n - 1, res, cache);
            return cache[n][capacity] = Math.max(include, exclude);
        } else
            return cache[n][capacity] = knapsack2(wt, val, capacity, n - 1, res, cache);
    }

    public static int knapsack(int[] wt, int[] val, int capacity, int n, String res) {
        if(capacity == 0 || n == 0) {
            if(capacity == 0)
                System.out.println(res);
            return 0;
        }

        if(capacity >= wt[n - 1]) {
            int include = val[n - 1] + knapsack(wt, val, capacity - wt[n - 1], n - 1, res + " " + wt[n - 1]);
            int exclude = knapsack(wt, val, capacity, n - 1, res);
            return Math.max(include, exclude);
        } else
            return knapsack(wt, val, capacity, n - 1, res);
    }
}
