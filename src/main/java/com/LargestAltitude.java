package com;

public class LargestAltitude {
    public static int largestAltitude(int[] gain) {
        int[] res = new int[gain.length + 1];
        res[0] = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] + gain[i - 1];
        }
        for(int i = 0; i < res.length; i++) {
            if(res[i] > max)
                max = res[i];
        }

        return max;
    }
}
