package com;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;

public class Fibonacci {
    public static void main(String[] args) {
        //System.out.println(fib(200, new long[210]));
        //System.out.println(fib2(0));
        //System.out.println(fibo(8, new Long[9]));
        //System.out.println(fibOptimized(9));
        alternate(9);
        alternate(15);
    }



    // https://www.geeksforgeeks.org/alternate-fibonacci-numbers/
    public static void alternate(int n) {
        if(n <= 1) {
            System.out.print(0 + " " + 1);
        }

        boolean print = true;
        StringBuilder string = new StringBuilder();
        string.append(0 + " ");
        int prev = 0;
        int prev2 = 1;
        int curr = 0;
        for(int i = 1; i < n; i++) {
            curr = prev + prev2;
            if(print) {
                string.append(curr + " ");
                print = false;
            } else {
                print = true;
            }
            prev2 = prev;
            prev = curr;
        }

        System.out.println(string);
    }

    public static int fibOptimized(int n) {
        if(n <= 1)
            return n;

        int left = 0;
        int right = 1;
        int cur = 0;
        for(int i = 2; i <= n; i++){
            cur = left + right;
            left = right;
            right = cur;
        }
        return cur;
    }

    public static int fibTab(int n) {
        if(n <= 1)
            return n;

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i <= n; i++)
            arr[i] = arr[i - 1] + arr[i - 2];

        return arr[n];
    }

    public static long fibo(int n, Long[] cache) {
        if(n <= 1)
            return n;

        if(cache[n] != null)
            return cache[n];

        return cache[n] = fibo(n - 1, cache) + fibo(n - 2, cache);
    }

    public static long fib(int n) {
        if(n <= 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static long fib(int n, long[] cache) {
        if(n <= 2)
            return 1;
        if(cache[n] > 0)
            return cache[n];

        cache[n] = fib(n - 1, cache) + fib(n - 2, cache);
        return cache[n];
    }

    public static int fib2(int n) {
        int[] arr = new int[n + 1];
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;

        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    public static BigInteger fibBig(int n) {
        BigInteger[] arr = new BigInteger[n];
        arr[0] = arr[1] = BigInteger.ONE;
        for(int i = 2; i < n; i++) {
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }

        return arr[n - 1];
    }

    public static int fib3(int n) {
        int[] arr = new int[n + 1];
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;

        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;

        for(int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }

        return arr[n];
    }
}
