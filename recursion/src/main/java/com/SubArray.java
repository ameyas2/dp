package com;

public class SubArray {
    public static void main(String[] args) {
        /*System.out.println(minSubArray(new int[]{3,1,7,1,2}, 5, 11, new Integer[6][12]));
        System.out.println(minSubArray(new int[]{2, 3, 5, 4, 1}, 5, 11,  new Integer[6][12]));
*/
        System.out.println(minSubArrayProd(new int[]{0, 1, -2, -3, -4}, 5, 1, "", new Integer[6][100]));
        System.out.println(minSubArrayProd(new int[]{-1, -2, 0, 1, 2}, 5, 1, "", new Integer[6][100]));
    }

    public static int minSubArrayProd(int[] arr, int n, int prod, String res, Integer[][] dp) {
        if(n == 0) {
            System.out.println(res);
            return prod > 0 ? 0 : 999999;
        }


       /* if(n == 0 && prod < 0) {
            return 99999;
        }
*/
       /* if(dp[n][k] != null)
            return dp[n][k];*/

        int include = minSubArrayProd(arr, n - 1, prod * arr[n - 1], res + " " + arr[n - 1], dp) + 1;
        int exclude = minSubArrayProd(arr, n - 1, prod, res, dp);
        return Math.min(include, exclude);
    }

    // https://www.geeksforgeeks.org/smallest-subarray-from-a-given-array-with-sum-greater-than-or-equal-to-k-set-2/

    public static int minSubArray(int[] arr, int n, int k, Integer[][] dp) {
        if(k <= 0) {
            return 0;
        }

        if(k > 0 && n == 0)
            return 9999999;

        if(dp[n][k] != null)
            return dp[n][k];

        int include = minSubArray(arr, n - 1, k - arr[n - 1], dp) + 1;
        int exclude = minSubArray(arr, n - 1, k, dp);
        return dp[n][k] = Math.min(include, exclude);
    }

}
