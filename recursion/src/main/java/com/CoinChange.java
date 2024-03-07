package com;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        ///System.out.println(coinChange(new int[]{1, 2, 3}, 1000, 3));
        /*int n = 10001;
        int[][] cache = new int[n][n];
        for(int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);
        //System.out.println(coinChange(new int[]{1, 2, 3}, 10000, 3, cache));
        System.out.println(coinChange(new int[]{1, 2, 3}, 5));*/
        //System.out.println(change(new int[]{1,2,5}, 11, 3, ""));
        //System.out.println(change(new int[]{1,2,5}, 11, 3, "", new Integer[4][12]));
        //System.out.println(coinChangeCountTab(new int[]{1,2,5}, 11));
        System.out.println(coinChangeMin(new int[]{6,1,2,5}, 11, 4, 0, ""));
    }



    public static int coinChangeMin(int[] coins, int sum, int n, int count, String res) {
        if(sum == 0) {
            System.out.println(res + " count : " + count);
            return count;
        }

        if(sum < 0 || (sum > 0 && n == 0))
            return Integer.MAX_VALUE;

        if(sum >= coins[n - 1]) {
            int include = coinChangeMin(coins, sum - coins[n - 1], n, count + 1, res + ", " + coins[n - 1]);
            int exclude = coinChangeMin(coins, sum, n - 1, count, res);
            return Math.min(include, exclude);
        } else {
            return coinChangeMin(coins, sum, n - 1, count, res);
        }
    }

    public static int coinChangeCountTab(int[] coins, int sum) {
        int n = coins.length;
        Integer[][] tab = new Integer[n + 1][sum + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    tab[i][j] = 1;
                    continue;
                }

                if(i == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= coins[i - 1]) {
                    tab[i][j] = tab[i][j - coins[i - 1]] + tab[i - 1][j];
                } else {
                    tab[i][j] =  tab[i - 1][j];
                }
            }
        }
        Utils.print(tab);
        return tab[n][sum];
    }

    public static int change(int[] coins, int target, int n, String res, Integer[][] cache) {
        if(target == 0) {
            System.out.println(res);
            return 0;
        }

        if(target < 0 || (target > 0 && n == 0))
            return Integer.MAX_VALUE;

        if(cache[n][target] != null)
            return cache[n][target];

        if(target >= coins[n - 1]) {
            int include = change(coins, target - coins[n - 1], n, res + " " + coins[n - 1]);
            int exclude = change(coins, target, n - 1, res);
            return cache[n][target] = 1 + Math.min(include, exclude);
        } else
            return cache[n][target] = change(coins, target, n - 1, res);
    }

    public static int change(int[] coins, int target, int n, String res) {
        if(target == 0) {
            System.out.println(res);
            return 0;
        }

        if(target < 0 || (target > 0 && n == 0))
            return Integer.MAX_VALUE;

        if(target >= coins[n - 1]) {
            int include = change(coins, target - coins[n - 1], n, res + " " + coins[n - 1]);
            int exclude = change(coins, target, n - 1, res);
            return 1 + Math.min(include, exclude);
        } else
            return change(coins, target, n - 1, res);
    }



    public static int coinChange(int[] coins, int target, int n) {
        if(target == 0)
            return 1;
        if(target < 0 || (n <= 0 && target > 0))
            return 0;

        if(coins[n - 1] <= target) {
            return coinChange(coins, target - coins[n - 1], n) + coinChange(coins, target, n - 1);
        } else {
            return coinChange(coins, target, n - 1);
        }
    }

    public static int coinChange(int[] coins, int target, int n, int[][] cache) {
        if(target == 0)
            return 1;
        if(target < 0 || (n <= 0 && target > 0))
            return 0;
        if(cache[n][target] != -1)
            return cache[n][target];

        if(coins[n - 1] <= target) {
            cache[n][target] = coinChange(coins, target - coins[n - 1], n, cache) + coinChange(coins, target, n - 1, cache);
        } else {
            cache[n][target] = coinChange(coins, target, n - 1, cache);
        }

        return cache[n][target];
    }

    public static int coinChange(int[] coins, int target) {
        int[][] t = new int[coins.length + 1][target + 1];
        for(int i = 0; i < t.length; i++)
            t[i][0] = 1;
        for(int row = 1; row < t.length; row++){
            for(int col = 1; col < t[row].length; col++){
                if(coins[row - 1] <= col) {
                    t[row][col] = t[row - 1][col] + t[row][col - coins[row - 1]];
                } else {
                    t[row][col] = t[row - 1][col];
                }
            }
        }

        return t[coins.length][target];
    }
}
