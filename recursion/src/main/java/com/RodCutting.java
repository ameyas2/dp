package com;

import java.util.List;

public class RodCutting {
    public static void main(String[] args) {
        /*System.out.println(rodCutting(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8));
        System.out.println(rodCutting(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8, new Integer[9][9]));
        System.out.println(rodCuttingTab(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8));*/

        //System.out.println(rodCutting(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8, ""));
        //System.out.println(rodCutting(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8, new Integer[9][9], ""));
        //System.out.println(rodCuttingTab(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8));
        //System.out.println(evenRodCutting(6, 4, ""));
        System.out.println(rodCutting(new int[]{3, 10, 11}, 3, 17, new Integer[4][18]));
        System.out.println(rodCutting(new int[]{3, 10, 11}, 17));
    }

    public static int rodCutting(int[] arr, int size) {
        int n = arr.length;
        int[][] dp = new int[n + 1][size + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= size; j++) {
                if(i == 0 || j == 0)
                    continue;
                if(j >= arr[i - 1]) {
                    int in = dp[i][j - arr[i - 1]] + 1;
                    int ex = dp[i - 1][j];
                    dp[i][j] = Math.max(in, ex);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][size];

    }


    public static int rodCutting(int[] arr, int n, int size, Integer[][] dp) {
        if(size == 0) {
            return 0;
        }

        if(size < 0 || size > 0 && n == 0)
            return -99999;

        if(dp[n][size] != null)
            return dp[n][size];

        if(size >= arr[n - 1]) {
            int include = rodCutting(arr, n, size - arr[n - 1], dp) + 1;
            int exclude = rodCutting(arr, n - 1, size, dp);
            return dp[n][size] = Math.max(include, exclude);
        } else {
            return dp[n][size] = rodCutting(arr, n - 1, size, dp);
        }

    }

    public static int evenRodCutting(int n, int k, String res) {
        if(n == 0) {
            System.out.println(res);
            return 1;
        }

        if(n < 0 || n > 0 && k == 0)
            return 0;

        if(n >= k) {
            int include = evenRodCutting(n - k, k, res + " " + k);
            int exclude = evenRodCutting(n, k - 2, res);
            return include + exclude;
        } else {
            return evenRodCutting(n, k - 2, res);
        }
    }

    public static int rodCuttingTab(int[] len, int[] price, int length) {
        int n = len.length;
        Integer[][] tab = new Integer[n + 1][length + 1];

        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < length + 1; j++) {
                if (i == 0 || j == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= len[i - 1]) {
                    int include = price[i - 1] + tab[i][j - len[i - 1]];
                    int exclude = tab[i - 1][j];
                    tab[i][j] = Math.max(include, exclude);
                } else
                    tab[i][j] = tab[i - 1][j];
            }
        }

        return tab[n][length];
    }

    public static int rodCutting(int[] len, int[] price, int n, int length, Integer[][] cache, String res) {
        if(n == 0 || length == 0) {
            if(length == 0)
                System.out.println(res);
            return 0;
        }

        if(cache[n][length] != null) {
            return cache[n][length];
        }

        if(length >= len[n - 1]) {
            int include = price[n - 1] + rodCutting(len, price, n, length - len[n - 1], cache, res + " " + len[n - 1] + "(" + price[n - 1] +")");
            int exclude = rodCutting(len, price, n - 1, length, cache, res);
            return cache[n][length] = Math.max(include, exclude);
        } else {
            return cache[n][length] = rodCutting(len, price, n - 1, length, cache, res);
        }
    }

    public static int rodCutting(int[] len, int[] price, int n, int length, String res) {
        if(n == 0 || length == 0) {
            if(length == 0)
                System.out.println(res);
            return 0;
        }

        if(length >= len[n - 1]) {
            int include = price[n - 1] + rodCutting(len, price, n, length - len[n - 1], res + " " + len[n - 1] + "(" + price[n - 1] +")");
            int exclude = rodCutting(len, price, n - 1, length, res);
            return Math.max(include, exclude);
        } else {
            return rodCutting(len, price, n - 1, length, res);
        }
    }

    public static int rodCuttingTab(int[] len, int[] price, int n, int l) {
        int[][] dp = new int[n + 1][l + 1];
        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= n; col++) {
                if(row == 0 || col == 0) {
                    dp[row][col] = 0;
                    continue;
                }

                if(col >= len[row - 1]) {
                    int include = price[row - 1] + dp[row][col - len[row - 1]];
                    int exclude = dp[row - 1][col];
                    dp[row][col] = Math.max(include, exclude);
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }

        return dp[n][l];
    }

    public static int rodCutting(int[] len, int[] price, int n, int l, Integer[][] dp) {
        if(l == 0 || n == 0) return 0;
        if(dp[n][l] != null) return dp[n][l];

        if(l >= len[n - 1]) {
            int include = price[n - 1] + rodCutting(len, price, n, l - len[n - 1], dp);
            int exclude = rodCutting(len, price, n - 1, l, dp);
            return dp[n][l] = Math.max(include, exclude);
        } else return dp[n][l] = rodCutting(len, price, n - 1, l, dp);
    }

    public static int rodCutting(int[] len, int[] price, int n, int l) {
        if(l == 0 || n == 0)
            return 0;

        if(l >= len[n -1]) {
            int include = price[n - 1] + rodCutting(len, price, n, l - len[n - 1]);
            int exclude = rodCutting(len, price, n - 1, l);
            return Math.max(include, exclude);
        } else return rodCutting(len, price, n - 1, l);
    }
}
