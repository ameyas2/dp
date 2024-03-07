package com;

public class Tasks {
    public static void main(String[] args) {
        System.out.println(tasks(new int[]{3, 6, 8, 7, 6}, new int[]{1, 5, 4, 5, 3}, 5, new Integer[6]));
        System.out.println(tasks(new int[]{3, 6, 8, 7, 6}, new int[]{1, 5, 4, 5, 3}));
    }

    // https://www.geeksforgeeks.org/dynamic-programming-high-effort-vs-low-effort-tasks-problem/
    public static int tasks(int[] high, int[] low) {
        int n = high.length;
        int[] tab = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            int highTask = i >= 2 ? high[i - 1] + tab[i - 2] : high[i - 1];
            int lowTask = low[i - 1] + tab[i - 1];
            tab[i] = Math.max(highTask, lowTask);
        }

        return tab[n];
    }

    public static int tasks(int[] high, int[] low, int n, Integer[] cache) {
        if(n <= 0) {
            return 0;
        }

        if(cache[n] != null) {
            return cache[n];
        }

        int highTask = high[n - 1] + tasks(high, low, n - 2, cache);
        int lowTask = low[n - 1] + tasks(high, low, n - 1, cache);
        return cache[n] = Math.max(lowTask, highTask);
    }
}
