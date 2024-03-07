package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        //System.out.println(subsetSumMax(new int[]{2, 3, 2, 3, 3, 4}, -1, 0, new Integer[7][7]));
        /*System.out.println(subsetCount(new int[]{1, 2, 3, 3}, 6, 4, new Integer[5][7]));
        System.out.println(subsetCount(new int[]{1, 1, 1, 1}, 1, 4, new Integer[5][2]));*/

        /*System.out.println(subsetSumDiffCount(new int[]{1, 1, 2, 3}, 1));
        System.out.println(subsetSumDiffCount(new int[]{1, 6, 11, 5}, 1));*/

        /*System.out.println(sum(8, 2, new Integer[9][3]));
        System.out.println(sum(8, 2));*/

        //System.out.println(smallSubset(new int[]{2, 4, 5, 6, 7, 8}, 16, 6, new ArrayList<>()));

        //System.out.println(subsetProductCount(new int[]{1, 1, 2, 2, 3, 4}, 4, 6, 1, new Integer[7][100]));

        System.out.println(splitArray(new int[]{1, 2, 3, 4, 5}, 5, 5, ""));
    }

    public static int splitArray(int[] arr, int k, int n, String res) {
        if(k == 0 || (k > 0 && n == 0)) {
            System.out.println(res);
            return 1;
        }

        if(k < 0)
            return 0;

        if(k >= arr[n - 1]) {
            int include = splitArray(arr, k - arr[n - 1], n - 1, res + " " + arr[n - 1]);
            int exclude = splitArray(arr, k, n - 1, res);
            return include + exclude;
        } else {
            return splitArray(arr, k, n - 1, res);
        }
    }

    // https://www.geeksforgeeks.org/count-subsets-in-an-array-having-product-k/

    public static int subsetProductCount(int[] arr, int k, int n, int prod, Integer[][] dp) {
        if(n < 1) {
            return prod == k ? 1 : 0;
        }

        if(dp[n][prod] != null)
            return dp[n][prod];

        int include = subsetProductCount(arr, k / arr[n - 1], n - 1, prod * arr[n - 1], dp);
        int exclude = subsetProductCount(arr, k, n - 1, prod, dp);
        return dp[n][prod] = include + exclude;
    }

    /*public static int subsetProductCount(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n + 1][1000];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j < )
        }
    }*/

    public static int smallSubset(int[] arr, int k, int n, List<List<Integer>> list) {
        if(k == 0) {
            System.out.println(list);
            return list.size();
        }

        if(k < 0  || k > 0 && n == 0)
            return 9999999;

        if(arr[n - 1] <= k) {
            //list.add(arr[n - 1]);
            int include = smallSubset(arr, k - arr[n - 1], n - 1, list);
            list.remove(list.size() -  1);
            int exclude = smallSubset(arr, k, n - 1, list);
            return Math.min(include, exclude);
        } else {
            return smallSubset(arr, k, n - 1, list);
        }
    }

    // https://www.geeksforgeeks.org/ways-to-sum-to-n-using-natural-numbers-up-to-k-with-repetitions-allowed/

    public static int sum(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                if(i == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if(i - j < 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = dp[i - j][j] + dp[i][j - 1];
            }
        }

        return dp[n][k];
    }

    public static int sum(int n, int k, Integer[][] dp) {
        if(n == 0)
            return 1;

        if(n < 0 || k <= 0)
            return 0;

        if(dp[n][k] != null)
            return dp[n][k];

        return sum(n - k, k, dp) + sum(n, k - 1, dp);
    }

    // https://www.geeksforgeeks.org/count-ways-to-split-array-into-two-subsets-having-difference-between-their-sum-equal-to-k/
    public static int subsetSumDiffCount(int[] arr, int diff) {
        int total = Arrays.stream(arr).sum();
        int sum2 = (total - diff) / 2;
        int s1 = sum2 + diff;

        //return subsetSumDiffCount(arr, s1, arr.length, new Integer[arr.length + 1][s1 + 1]);
        return subsetSumDiffCountTab(arr, s1);
    }

    public static int subsetSumDiffCountTab(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if(i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(j >= arr[i - 1]) {
                    int include = dp[i - 1][j - arr[i - 1]];
                    int exclude = dp[i - 1][j];
                    dp[i][j] = include + exclude;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public static int subsetSumDiffCount(int[] arr, int sum, int n, Integer[][] cache) {
        if(sum == 0)
            return 1;

        if(sum < 0 || sum > 0 && n == 0)
            return 0;

        if(cache[n][sum] != null)
            return cache[n][sum];

        if(sum >= arr[n - 1]) {
            int include = subsetSumDiffCount(arr, sum - arr[n - 1], n - 1, cache);
            int exclude = subsetSumDiffCount(arr, sum, n - 1, cache);
            return include + exclude;
        } else {
            return subsetSumDiffCount(arr, sum, n - 1, cache);
        }
    }
    
    //https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x-set-2/
    public static int subsetCount(int[] arr, int x, int n, Integer[][] cache) {
        if(x == 0) return 1;

        if(x < 0 || (x > 0 && n == 0))
            return 0;

        if(cache[n][x] != null)
            return cache[n][x];

        if(x >= arr[n - 1]) {
            int include = subsetCount(arr, x - arr[n - 1], n - 1, cache);
            int exclude = subsetCount(arr, x, n - 1, cache);
            return cache[n][x] = include + exclude;
        } else {
            return cache[n][x] = subsetCount(arr, x, n - 1, cache);
        }
    }

    // https://www.geeksforgeeks.org/maximum-sum-of-subset-having-no-consecutive-elements/

    public static int subsetSumMax(int[] arr, int prev, int curr, Integer[][] tab) {
        if(curr == arr.length)
            return 0;

        if(prev > -1 && tab[curr][prev] != null)
            return tab[curr][prev];

        if(prev == -1 || arr[prev] + 1 != arr[curr]) {
            int include = arr[curr] + subsetSumMax(arr, curr, curr + 1, tab);
            int exclude = subsetSumMax(arr, prev, curr + 1, tab);
            int max = Math.max(include, exclude);
            if(prev > -1)
                tab[curr][prev] = max;
            return max;
        } else {
            return tab[curr][prev] = subsetSumMax(arr, prev, curr + 1, tab);
        }
    }


}
