package com;

public class Loop {
    public static void main(String[] args) {
        System.out.println(diceSum(3, new Integer[4]));
        System.out.println(diceSum(6, new Integer[7]));
    }

    // https://www.geeksforgeeks.org/count-ways-to-obtain-given-sum-by-repeated-throws-of-a-dice/
    public static int diceSum(int n, Integer[] dp) {
        if(n == 0) {
            return 1;
        }

        if(n < 0) {
            return 0;
        }

        if(dp[n] != null)
            return dp[n];

        int sum = 0;
        for(int i = 1; i <= 6; i++) {
            sum += diceSum(n - i, dp);
        }

        return dp[n] = sum;
    }
}
