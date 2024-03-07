package com;

public class NoAdjacentSum {
    public static void main(String[] args) {
        /*System.out.println(maxSum(new int[]{1,2,4}, 3, ""));
        System.out.println(maxSum(new int[]{2,1,4,9,1,6}, 6, ""));

        System.out.println(maxSum(new int[]{1,2,4}, 3, "", new Integer[4]));
        System.out.println(maxSum(new int[]{2,1,4,9,1,6}, 6, "", new Integer[7]));

        System.out.println(maxSum(new int[]{1,2,4}, 3));
        System.out.println(maxSum(new int[]{2,1,4,9,1,6}, 6));*/

        //System.out.println(sum(new int[]{9,9,8,2}, 4, ""));
        /*System.out.println(paintHouseNonAdjacentColor(new int[][]{{14, 2, 11}, {11, 14, 5}, {14, 3, 10}}));
        System.out.println(paintHouseNonAdjacentMultiColor(new int[][]{{14, 2, 11}, {11, 14, 5}, {14, 3, 10}}));*/

        System.out.println(maxSumTab(new int[]{2,1,4,9,1,6}, 6, new Integer[7]));
        System.out.println(maxSumTab(new int[]{5,10,10,100,5,6}, 6, new Integer[7]));

        System.out.println(maxSumTab(new int[]{2,1,4,9,1,6}));
        System.out.println(maxSumTab(new int[]{5,10,10,100,5,6}));
    }

    public static int maxSumTab(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[0] = arr[0];
        dp[1] = arr[1];

        for(int i = 2; i <= n; i++) {
            int include = arr[i - 1] + dp[i - 2];
            int exclude = dp[i - 1];
            dp[i] = Math.max(include, exclude);
        }

        return dp[n];
    }

    public static int maxSumTab(int[] arr, int n, Integer[] dp) {
        if(n <= 0)
            return 0;

        if(dp[n] != null)
            return dp[n];

        int include = maxSumTab(arr, n - 2, dp) + arr[n - 1];
        int exclude = maxSumTab(arr, n - 1, dp);
        return dp[n] = Math.max(include, exclude);
    }

    // https://www.geeksforgeeks.org/minimize-cost-of-painting-n-houses-such-that-adjacent-houses-have-different-colors/


    public static int paintHouseNonAdjacentColor(int[][] costs)  {
        int n = costs.length;

        int[][] dp = new int[n][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for(int i = 1; i < n; i++) {
            // choose red
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];

            // choose green
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];

            // choose blue
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];
        }

        return Math.min(dp[2][0], Math.min(dp[2][1], dp[2][2]));
    }

    // https://www.youtube.com/watch?v=jGywRalvoRw&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=24
    public static int paintHouseNonAdjacentMultiColor(int[][] costs)  {
        int n = costs.length;
        int m = costs[0].length;

        int[][] dp = new int[n][m];

        for(int i = 0; i < m; i++)
            dp[0][i] = costs[0][i];

        for(int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < m; k++) {
                    if(k != j) {
                        min = Math.min(min, dp[i - 1][k]);
                    }
                }
                dp[i][j] = min  + costs[i][j];
            }
           /* // choose red
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];

            // choose green
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];

            // choose blue
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];*/
        }

        return Math.min(dp[2][0], Math.min(dp[2][1], dp[2][2]));
    }

    public static int sum(int[] arr, int n, String res) {
        if(n <= 0) {
            System.out.println(res);
            return 0;
        }

        int include = arr[n - 1] + sum(arr, n - 2, res + " " + arr[n - 1]);
        int exclude = sum(arr, n - 1, res);
        return Math.max(include, exclude);
    }


    public static int maxSum(int[] arr, int n) {
        Integer[] tab = new Integer[n + 1];
        tab[0] = 0;
        tab[1] = arr[0];

        for(int i = 2; i <= arr.length; i++) {
            int include = arr[i - 1] + tab[i - 2];
            int exclude = tab[i - 1];
            tab[i] = Math.max(include, exclude);
        }

        return tab[n];
    }

    public static int maxSum(int[] arr, int n, Integer[] cache) {
        if(n <=  0) {
            return 0;
        }

        if(cache[n] != null)
            return cache[n];

        int include = arr[n - 1] + maxSum(arr, n - 2, cache);
        int exclude = maxSum(arr, n - 1, cache);
        return cache[n] = Math.max(include, exclude);
    }

    public static int maxSum(int[] arr, int n, String res) {
        if(n <= 0) {
            if(n == 0)
                System.out.println(res);
            return 0;
        }

        int include = arr[n - 1] + maxSum(arr, n - 2, res + " " + arr[n - 1]);
        int exclude = maxSum(arr, n - 1, res);
        return Math.max(include, exclude);
    }
}
