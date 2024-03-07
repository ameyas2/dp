package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSubSequence {
    public static void main(String[] args) {
        //System.out.println(maxSubsequence(new int[]{3, 100, 4, 5, 150, 6}, -1, 0, new Integer[7][7]));
        //System.out.println(maxSubsequence(new int[]{3, 100, 4, 5, 150, 6}));
        System.out.println(maxSumIncreasingSubsequence(new int[]{1, 101, 2, 3, 100, 4, 5}, -1, 0, new ArrayList<>()));
    }

    //https://www.geeksforgeeks.org/printing-maximum-sum-increasing-subsequence/

    public static int maxSumIncreasingSubsequence(int[] arr, int prev, int curr, List<Integer> nums) {
        if(curr == arr.length) {
            System.out.println(nums);
            return 0;
        }

        if(prev == -1 || arr[prev] < arr[curr]) {
            nums.add(arr[curr]);
            int include = maxSumIncreasingSubsequence(arr, curr, curr + 1, nums) + arr[curr];
            nums.remove(nums.size() - 1);
            int exclude = maxSumIncreasingSubsequence(arr, prev, curr + 1, nums);
            return Math.max(include, exclude);
        } else {
            return maxSumIncreasingSubsequence(arr, prev, curr + 1, nums);
        }
    }

    // https://www.geeksforgeeks.org/maximum-product-increasing-subsequence/

    public static int maxSubsequence(int[] arr) {
        int n = arr.length;
        int[] tab = new int[n];

        for(int i = 0; i < tab.length; i++)
            tab[i] = arr[i];



        for(int curr = 1; curr < n; curr++) {
            for(int prev = 0; prev < curr; prev++) {
                if(arr[curr] > arr[prev] && tab[curr] < tab[prev] * arr[curr]) {
                    tab[curr] = tab[prev] * arr[curr];
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < tab.length; i++)
            max = Math.max(max, tab[i]);

        return max;
    }

    public static int maxSubsequence(int[] arr, int prev, int curr, Integer[][] cache) {
        if(curr == arr.length) return 1;

        if(prev > -1 && cache[curr][prev] != null)
            return cache[curr][prev];

        if(prev == -1 || arr[curr] > arr[prev]) {
            int include = maxSubsequence(arr, curr, curr + 1, cache) * arr[curr];
            int exclude = maxSubsequence(arr, prev, curr + 1, cache);
            return cache[curr][prev] = Math.max(include, exclude);
        } else {
            return cache[curr][prev] = maxSubsequence(arr, prev, curr + 1, cache);
        }
    }
}
