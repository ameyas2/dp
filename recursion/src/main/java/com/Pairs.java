package com;

public class Pairs {
    public static void main(String[] args) {
        System.out.println(pairs(3, new Integer[4]));
    }

    public static int pairs(int n, Integer[] cache) {
        if(n == 0 || n == 1)
            return 1;

        if(cache[n] != null)
            return cache[n];

        return cache[n] = pairs(n - 1, cache) + (n - 1) * pairs(n - 2, cache);
    }
}
