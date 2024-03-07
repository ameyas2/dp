package com;

import java.util.Arrays;

public class TilingProblem {
    public static void main(String[] args) {
        //System.out.println(tiling(100));
        //long[] cache = new long[201];
        //Arrays.fill(cache, -1);
        //System.out.println(tiling(200, cache));
        //System.out.println(tiling(7, 4));
        /*System.out.println(tiles(4, new Integer[5]));
        System.out.println(tiles(4));*/

        System.out.println(tiling2(2));
    }

    // https://www.geeksforgeeks.org/count-ways-to-tile-an-n-length-board-using-tiles-of-specified-dimensions/
    public static int tiling2(int n) {
        if(n <= 1)
            return n;

        return 2 * tiling2(n - 1) + tiling2(n - 2);
    }

    // https://www.geeksforgeeks.org/tiling-problem/
    public static int tiles(int n) {
        int[] tab = new int[n + 1];

        tab[0] = 0;
        tab[1] = 1;
        tab[2] = 2;

        for(int i = 3; i <= n; i++) {
            tab[i] = tab[i - 1] + tab[i - 2];
        }

        return tab[n];
    }

    public static int tiles(int n, Integer[] cache) {
        if(n <= 2) {
            return n;
        }

        if(cache[n] != null)
            return cache[n];

        return cache[n] = tiles(n - 1, cache) + tiles(n - 2, cache);
    }


    public static int tiling(int n, int m) {
        if(n <= 0)
            return 0;
        if(n < m)
            return 1;

        return tiling(n - 1, m) + tiling(n - m, m);
    }

    public static int tiling(int n) {
        if(n <= 2)
            return n;
        return tiling(n - 1) + tiling(n - 2);
    }

    public static long tiling(int n, long[] cache) {
        if(n <= 2)
            return n;
        if(cache[n] != -1)
            return cache[n];
        cache[n] = tiling(n - 1, cache) + tiling(n - 2, cache);
        return cache[n];
    }
}
