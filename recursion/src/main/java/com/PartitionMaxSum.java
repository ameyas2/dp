package com;

import java.util.Arrays;

public class PartitionMaxSum {
    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{1,15,7,9}, 0, 2, ""));
    }



    public static int maxSum(int[] arr, int index, int k, String res) {
        if(index >= arr.length) {
            //System.out.println(res);
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int len = 0;
        int total = 0;
        for(int i = index; i < Math.min(arr.length, index + k); i++) {
            len++;
            max = Math.max(max, arr[i]);
            System.out.println(i + " " + arr[i] + " len " + len + " max " + max);
            int sum = len * max + maxSum(arr, i + 1, k, res + " " + arr[i]);
            total = Math.max(total, sum);
            System.out.println("Total " + total);
        }
        return total;
    }
}
