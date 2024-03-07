package com;

public class EggDropping {
    public static void main(String[] args) {
        //System.out.println(eggs(2, 100));
        System.out.println(eggsMem(2, 100, new Integer[3][101]));
        System.out.println(eggsMemOpt(2, 100, new Integer[3][101]));
    }

    public static int eggsMemOpt(int e, int f, Integer[][] dp) {
        if(e <= 1 || f <= 1)
            return f;

        if(dp[e][f] != null)
            return dp[e][f];

        int min = Integer.MAX_VALUE;

        for(int k = 1; k <= f; k++) {
            int low = dp[e - 1][k - 1] != null ? dp[e - 1][k - 1] : eggsMemOpt(e - 1, k - 1, dp);
            int high = dp[e][f - k] != null ? dp[e][f - k] : eggsMemOpt(e, f - k, dp);
            int worstCase = 1 + Math.max(low, high);
            min = Math.min(min, worstCase);
        }

        return dp[e][f] = min;
    }

    public static int eggsMem(int e, int f, Integer[][] dp) {
        if(e <= 1 || f <= 1)
            return f;

        if(dp[e][f] != null)
            return dp[e][f];

        int min = Integer.MAX_VALUE;

        for(int k = 1; k <= f; k++) {
            int worstCase = 1 + Math.max(eggsMem(e - 1, k - 1, dp), eggsMem(e, f - k, dp));
            min = Math.min(min, worstCase);
        }

        return dp[e][f] = min;
    }

    public static int eggs(int e, int f) {
        if(e <= 1 || f <= 1)
            return f;
        int min = Integer.MAX_VALUE;

        for(int k = 1; k <= f; k++) {
            int worstCase = 1 + Math.max(eggs(e - 1, f - 1), eggs(e, f - 1));
            min = Math.min(min, worstCase);
        }

        return min;
    }
}
