package com;

public class Jumps {
    public static void main(String[] args) {
        //System.out.println(jumpEnergy(new int[]{30, 10, 60, 10, 60, 50}, 5, "", new Integer[6]));
        //System.out.println(jumpEnergyTab(new int[]{30, 10, 60, 10, 60, 50}, 5));
        //System.out.println(jumpEnergyK(new int[]{30, 10, 60, 10, 60, 50}, 5));
        //System.out.println(jumpEnergyK(new int[]{30, 10, 60, 10, 60, 50}, 5, new Integer[6], 2));
        //System.out.println(steps(5,3, "", new Integer[6][4]));
        //System.out.println(score(20, ""));

        System.out.println(indexJumps(new int[]{2,3,5,7,9}, 0, new Integer[10]));
        System.out.println(indexJumps(new int[]{2, 2, 1, 2, 3, 3}, 0, new Integer[10]));
    }

    public static int indexJumps(int[] arr, int n, Integer[] dp) {
        if(n >= arr.length) {
            return 0;
        }

        if(dp[n] != null)
            return dp[n];


        int include = arr[n] + indexJumps(arr, arr[n] + n, dp);
        int exclude = indexJumps(arr, n + 1, dp);

        return dp[n] = Math.max(include, exclude);
    }

    // https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/

    public static int score(int n, String res) {
        if(n == 0) {
            System.out.println(res);
            return 1;
        }

        if(n < 0) return 0;

        int ten = score(n - 10, res + " " + 10);
        int five = score(n - 5, res + " " + 5);
        int three = score(n - 3, res + " " + 3);

        return ten + five + three;
    }

    // https://www.geeksforgeeks.org/number-of-ways-to-reach-nth-floor-by-taking-at-most-k-leaps/

    public static int steps(int n, int k, String res, Integer[][] cache) {
        if(n == 0) {
            System.out.println(res);
            return 1;
        }

        if(n < 0) return 0;

        if(cache[n][k] != null)
            return cache[n][k];

        int steps = 0;
        for(int i = 1; i <= k; i++) {
            if(n >= i )
                steps += steps(n - i, k, res + " " + i, cache);
        }

        return steps;
    }

    public static int jumpEnergyK(int[] arr, int n, Integer[] cache, int k) {
        if(n <= 0) {
            return 0;
        }

        if(cache[n] != null)
            return cache[n];

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++) {
            if(n - i >=  0) {
                int left = Math.abs(arr[n] - arr[n - i]);
                int one = jumpEnergyK(arr, n - i, cache, k) + left;
                min = Math.min(min, one);
            }
        }

        return cache[n] = min;
    }

    public static int jumpEnergyK(int[] arr, int n) {
        Integer[] tab = new Integer[n + 1];
        tab[0] = 0;

        for(int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                int prev = tab[i - 1] + Math.abs(arr[i] - arr[i - j]);
                min = Math.min(min, prev);
            }
            //int right = i > 1 ? tab[i - 2] + Math.abs(arr[i] - arr[i - 2]) : Integer.MAX_VALUE;
            tab[i] = min;
        }

        return tab[n];
    }

    public static int jumpEnergyTab(int[] arr, int n) {
        Integer[] tab = new Integer[n + 1];
        tab[0] = 0;

        for(int i = 1; i <= n; i++) {
            int left = tab[i - 1] + Math.abs(arr[i] - arr[i - 1]);
            int right = i > 1 ? tab[i - 2] + Math.abs(arr[i] - arr[i - 2]) : Integer.MAX_VALUE;
            tab[i] = Math.min(left, right);
        }

        return tab[n];
    }

    public static int jumpEnergy(int[] arr, int n, String res, Integer[] cache) {
        if(n <= 0) {
            System.out.println(res);
            return 0;
        }

        int two = Integer.MAX_VALUE;

        if(cache[n] != null)
            return cache[n];

        int left = Math.abs(arr[n] - arr[n - 1]);
        int one = jumpEnergy(arr, n - 1, res + ", " + arr[n], cache) + left;

        if(n > 1) {
            int right = Math.abs(arr[n] - arr[n - 2]);
            two = jumpEnergy(arr, n - 2, res + ", " + arr[n], cache) + right;
        }
        return cache[n] = Math.min(one, two);
    }
}
