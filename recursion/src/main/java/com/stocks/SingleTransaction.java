package com.stocks;

// https://www.youtube.com/watch?v=excAOvwF_Wk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=36
public class SingleTransaction {
    public static void main(String[] args) {
        System.out.println(profit(new int[]{7,1,5,3,6,4}));
        System.out.println(profit2(new int[]{7,1,5,3,6,4}));
    }

    public static int profit2(int[] price) {
        int min = price[0];
        int maxProfit = Integer.MIN_VALUE;

        for(int i = 1; i < price.length; i++) {
            int profit = price[i] - min;
            maxProfit = Math.max(maxProfit, profit);
            min = Math.min(min, price[i]);
        }

        return maxProfit;
    }

    public static int profit(int[] price) {
        int min = price[0];
        int maxProfit = Integer.MIN_VALUE;

        for(int i = 1; i < price.length; i++) {
            if(price[i] < min) {
                min = price[i];
                continue;
            } else {
                int profit = price[i] - min;
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}
