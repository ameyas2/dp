package com;

public class UglyNumber {
    public static long uglyNumber(int n) {
        long[] arr = new long[n + 1];
        long mul2 = 1, mul3 = 1, mul5 = 1;
        int j2 = 1, j3 = 1, j5 = 1;
        arr[1] = 1;

        for(int i = 2; i <= n; i++) {
            mul2 = 2 * arr[j2];
            mul3 = 3 * arr[j3];
            mul5 = 5 * arr[j5];
            long min = 0;
            if(mul2 <= mul3 && mul2 <= mul5) {
                min = mul2;
                j2++;
                if(mul2 == mul3) {
                    j3++;
                }
                if(mul2 == mul5) {
                    j5++;
                }
            } else if(mul3 <= mul2 && mul3 <= mul5) {
                min = mul3;
                j3++;
                if(mul2 == mul3) {
                    j2++;
                }
                if(mul3 == mul5) {
                    j5++;
                }
            } else {
                min = mul5;
                j5++;
                if(mul5 == mul3) {
                    j3++;
                }
                if(mul2 == mul5) {
                    j2++;
                }
            }
            arr[i] = min;
        }
        return arr[n];
    }
}
