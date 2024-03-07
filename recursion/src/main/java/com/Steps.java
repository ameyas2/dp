package com;

public class Steps {
    public static void main(String[] args) {
        System.out.println(minSteps(10, new Integer[11]));
        System.out.println(minSteps(10));
    }

    // https://www.geeksforgeeks.org/minimum-steps-minimize-n-per-given-condition/

    public static int minSteps(int n) {
        int[] tab = new int[n + 1];
        tab[1] = 0;
        for(int i = 2; i <= n; i++) {
            int min = tab[i - 1];
            if(i % 2 == 0) {
                min = Math.min(min, tab[i / 2]);
            }
            if(i % 3 == 0) {
                min = Math.min(min, tab[i / 3]);
            }
            tab[i] = min + 1;
        }

        return tab[n];
    }


    public static int minSteps(int n, Integer[] cache) {
        if(n == 1)
            return 0;

        if(cache[n] != null)
            return cache[n];

        int steps = minSteps(n - 1,cache);
        if(n % 2 == 0)
            steps = Math.min(steps, minSteps(n / 2,cache));
        else if(n % 3 == 0)
            steps = Math.min(steps, minSteps(n / 3,cache));

        return cache[n] = steps + 1;
    }
}
