package com;

public class Segments {
    public static void main(String[] args) {
        /*System.out.println(segments(17, 1,2,3));
        System.out.println(segment(7, 5,2,5, new Integer[8]));*/

        System.out.println(sumWay(4, new Integer[5]));
        System.out.println(sumWay(4));
    }

    public static int sumWay(int n) {
        int[] dp = new int[n + 1];

        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = 2;

        for(int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
        }

        return dp[n];
    }

    public static int sumWay(int n, Integer[] cache) {
        if(n == 0) return 1;
        if(n < 0) return 0;

        if(cache[n] != null)
            return cache[n];

        return sumWay(n - 1, cache) + sumWay(n - 3, cache) + sumWay(n - 4, cache);
    }

    // https://www.geeksforgeeks.org/maximum-number-segments-lengths-b-c/

    public static int segments(int n, int a, int b, int c) {
        int[] dp = new int[n + 1];

        for(int i = 0; i < n; i++) {
            if(i + a <= n) {
                dp[i + a] = Math.max(dp[i] + 1, dp[i + a]);
            }

            if(i + b <= n) {
                dp[i + b] = Math.max(dp[i] + 1, dp[i + b]);
            }

            if(i + c <= n) {
                dp[i + c] = Math.max(dp[i] + 1, dp[i + c]);
            }
        }

        return dp[n];
    }

    public static int segment(int n, int a, int b, int c, Integer[] cache) {
         if(n == 0) return 0;

         if(cache[n] != null)
             return cache[n];

         int max = 0;
         if(n >= a)
             max = Math.max(max, segment(n - a,a,b,c, cache) + 1);

        if(n >= b)
            max = Math.max(max, segment(n - b,a,b,c, cache) + 1);

        if(n >= c)
            max = Math.max(max, segment(n - c,a,b,c, cache) + 1);

        return cache[n] = max;
    }
}
