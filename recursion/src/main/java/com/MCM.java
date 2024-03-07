package com;

import java.util.Map;

public class MCM {
    public static void main(String[] args) {
        System.out.println(mcm(new int[]{10,20,30,40,50}, 1, 4));
    }

    public static int mcm(int[] arr, int i, int j) {
        if(i == j)
            return 0;

        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
            int a = arr[i  - 1] * arr[k] * arr[j];
            int total = a + mcm(arr, i, k) + mcm(arr, k + 1, j);
            min = Math.min(min, total);
        }
        return min;
    }
}
