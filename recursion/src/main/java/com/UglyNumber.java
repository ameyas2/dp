package com;

public class UglyNumber {
    public static void main(String[] args) {
        //System.out.println(uglyNumber(20));
        //System.out.println(uglyNumber2(20));
        System.out.println(uglyNumber3(20));
    }

    public static int uglyNumber3(int n) {
        int arr[] = new int[n];
        arr[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for(int i = 1; i < n; i++) {
            int mul2 = arr[i2] * 2;
            int mul3 = arr[i3] * 3;
            int mul5 = arr[i5] * 5;

            int min = Math.min(mul2, Math.min(mul5, mul3));
            if(min == mul2)
                i2++;
            if(min == mul3)
                i3++;
            if(min == mul5)
                i5++;
            arr[i] = min;
        }

        return arr[n - 1];
    }

    public static int uglyNumber2(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int mul2 = 1, mul3 = 1, mul5 = 1;
        for(int i = 1; i < n; i++) {
            mul2 = arr[i2] * 2;
            mul3 = arr[i3] * 3;
            mul5 = arr[i5] * 5;

            arr[i] = Math.min(Math.min(mul2, mul3), mul5);

            if(arr[i] == mul2) {
                i2++;
            }
            if(arr[i] == mul3) {
                i3++;
            }
            if(arr[i] == mul5) {
                i5++;
            }
        }

        return arr[n - 1];
    }

    public static int uglyNumber(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int mul2 = 1, mul3 = 1, mul5 = 1;
        for(int i = 1; i < n; i++) {
            mul2 = arr[i2] * 2;
            mul3 = arr[i3] * 3;
            mul5 = arr[i5] * 5;

            if(mul2 <= mul3 && mul2 <= mul5) {
                arr[i] = mul2;
                i2++;
                if(mul3 == mul2)
                    i3++;
                if(mul5 == mul2)
                    i5++;
            } else if(mul3 <= mul2 && mul3 <= mul5) {
                arr[i] = mul3;
                i3++;
                if(mul3 == mul2)
                    i2++;
                if(mul5 == mul3)
                    i5++;
            } else if(mul5 <= mul2 && mul5 <= mul2) {
                arr[i] = mul5;
                i5++;
                if(mul5 == mul2)
                    i2++;
                if(mul5 == mul3)
                    i3++;
            }
        }

        return arr[n - 1];
    }


}
