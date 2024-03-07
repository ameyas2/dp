package com;

public class Consecutive {
    public static void main(String[] args) {
        System.out.println(noConsecutiveO(5));
        System.out.println(noConsecutiveO(3));

        System.out.println(noConsecutiveO1(5));
        System.out.println(noConsecutiveO1(3));
    }

    // https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
    public static int noConsecutiveO(int n) {
        int[] z = new int[n];
        int[] o = new int[n];

        z[0] = 1;
        o[0] = 1;

        for(int i = 1; i < n; i++) {
            o[i] = o[i - 1] + z[i - 1];
            z[i] = o[i - 1];
        }

        return o[n - 1] + z[n - 1];
    }

    public static int noConsecutiveO1(int n) {
        int z = 1;
        int o = 1;

        for(int i = 1; i < n; i++) {
            int temp = o;
            o = o + z;
            z = temp;
        }

        return o + z;
    }
}
