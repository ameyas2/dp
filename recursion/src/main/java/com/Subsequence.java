package com;

public class Subsequence {
    public static void main(String[] args) {
        /*System.out.println(maxSum(new int[]{5, 4, 100, 3, 2, 101, 1} , -1, 0, ""));
        System.out.println(maxSum(new int[]{5, 4, 100, 3, 2, 101, 1} , -1, 0, new Integer[8][8]));*/
        //System.out.println(maxSum(new int[]{ 5, 4, 100, 3, 2, 101, 1 }));
        //System.out.println(maxSumProductSubsequence(new int[]{ 4, -1, -3, 3 }, new int[]{ -4, 0, 5 }, 4, 3, 0, new Integer[5][4][10]));
        /*System.out.println(subsequenceSum(new int[]{3000, 2000, 1000, 3, 10}, 5, 2, new Integer[10][10]));
        System.out.println(subsequenceSum(new int[]{3000, 2000, 1000, 3, 10}, 2));*/
        System.out.println(subsequencesSumCount(new int[] {84, 87, 73}, 100, 3, new Integer[4][101]));
        System.out.println(subsequencesSumCount(new int[] {25, 13, 40}, 50, 3, new Integer[4][51]));
    }

    // https://www.geeksforgeeks.org/count-of-subsequences-in-an-array-with-sum-less-than-or-equal-to-x/
    public static int subsequencesSumCount(int[] arr, int sum, int n, Integer[][] dp) {
        if(n == 0 && sum >= 0) {
            return 1;
        }

        if(sum < 0)
            return 0;

        if(dp[n][sum] != null)
            return dp[n][sum];

        if(sum >= arr[n - 1]) {
            int include = subsequencesSumCount(arr, sum - arr[n - 1], n - 1, dp);
            int exclude = subsequencesSumCount(arr, sum, n - 1, dp);
            return dp[n][sum] = include + exclude;
        } else {
            return dp[n][sum] = subsequencesSumCount(arr, sum, n - 1, dp);
        }
    }

    // https://www.geeksforgeeks.org/maximum-subsequence-sum-such-that-no-three-are-consecutive-in-o1-space/

    public static int subsequenceSum(int[] arr, int k) { // not working
        int n = arr.length;
        int[][] dp = new int[n + 1][k + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                if(i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(j < k) {
                    int include = arr[i - 1] + dp[i - 1][j];
                    int exclude = dp[i - 1][k];
                    dp[i][j] = Math.max(include, exclude);
                } else {
                    dp[i][j] = dp[i - 1][k];
                }
            }
        }

        return dp[n][k];
    }


    public static int subsequenceSum(int[] arr, int n, int k, Integer[][] dp) {
        if(n == 0) {
            return 0;
        }

        if(dp[n][k] != null)
            return dp[n][k];

        if(k > 0) {
            int include = arr[n - 1] + subsequenceSum(arr, n - 1, k - 1, dp);
            int exclude = subsequenceSum(arr, n - 1, 2, dp);
            return Math.max(include, exclude);
        } else {
            return subsequenceSum(arr, n - 1, 2, dp);
        }
    }

    // https://www.geeksforgeeks.org/maximise-sum-of-product-of-pairs-by-choosing-subsequence-of-same-length-from-given-arrays/
    public static int maxSumProductSubsequence(int[] a, int[] b, int n, int m, int taken, Integer[][][] tab) {
        if((n == 0 || m == 0 ) && taken == 0)
            return -99999999;

        if((n == 0 || m == 0 ) && taken != 0)
            return 0;

        if(tab[n][m][taken == 0 ? 0 : 1] != null)
            return tab[n][m][taken == 0 ? 0 : 1];

        int includeAB = a[n - 1] * b[m - 1] + maxSumProductSubsequence(a, b, n - 1, m - 1, taken + 1, tab);
        int includeA =  maxSumProductSubsequence(a, b, n - 1, m, taken, tab);
        int includeB =  maxSumProductSubsequence(a, b, n, m - 1, taken, tab);

        return tab[n][m][taken == 0 ? 0 : 1] = Math.max(includeAB, Math.max(includeA, includeB));
    }

    // https://www.geeksforgeeks.org/maximum-sum-decreasing-subsequence/

    public static int maxSum(int[] arr) {
        int n = arr.length;
        int[] tab = new int[n + 1];

        for(int curr = 1; curr < n; curr++) {
            tab[curr] = arr[curr];
        }

        for(int curr = 1; curr < n; curr++) {
            for(int prev = 0; prev < curr; prev++) {
                if(arr[curr] < arr[prev] && tab[curr] < tab[prev] + arr[curr])
                    tab[curr] = tab[prev] + arr[curr];
            }
        }

        int max = Integer.MIN_VALUE;
        for(int curr = 1; curr < n; curr++) {
            max = Math.max(max, tab[curr]);
        }

        return max;
    }

    public static int maxSum(int[] arr, int prev, int curr, Integer[][] cache) {
        if(curr == arr.length) {
            return 0;
        }

        if(prev > -1 && cache[curr][prev] != null)
            return cache[curr][prev];

        if(prev == -1 || arr[prev] > arr[curr]) {
            int include = maxSum(arr, curr, curr + 1, cache) + arr[curr];
            int exclude = maxSum(arr, prev, curr + 1, cache);
            return Math.max(include, exclude);
        } else {
            return maxSum(arr, prev, curr + 1, cache);
        }
    }

    public static int maxSum(int[] arr, int prev, int curr, String res) {
        if(curr == arr.length) {
            System.out.println(res);
            return 0;
        }

        if(prev == -1 || arr[prev] > arr[curr]) {
            int include = maxSum(arr, curr, curr + 1, res + " " + arr[curr]) + arr[curr];
            int exclude = maxSum(arr, prev, curr + 1, res);
            return Math.max(include, exclude);
        } else {
            return maxSum(arr, prev, curr + 1, res);
        }
    }
}
