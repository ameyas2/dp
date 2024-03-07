package com;

import java.util.Map;

public class Stocks {
    public static void main(String[] args) {
        //System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        /*System.out.println(maxProfit(new int[]{7,1,5,3,6,4}, 0, true, ""));
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}, 0, 1, 3, new Integer[7][3][4]));*/

        System.out.println(stocks(new int[]{3, 3, 5, 0, 0, 3, 1, 4}, 0, 1, 2, ""));
    }

    public static int stocks(int[] arr, int index, int buyAllowed, int transactions, String res) {
        if(index == arr.length) {
            System.out.println(res);
            return 0;
        }

        if(buyAllowed == 1 && transactions > 0) {
            int buy = stocks(arr, index + 1, 0, transactions, res + " " + arr[index]) - arr[index];
            int notBuy = stocks(arr, index + 1, 1, transactions, res);
            return Math.max(buy, notBuy);
        } else {
            int sell = stocks(arr, index + 1, 1, transactions - 1, res + " " + arr[index]) + arr[index];
            int notSell = stocks(arr, index + 1, 0, transactions + 1, res);
            return Math.max(sell, notSell);
        }
    }


    // https://www.geeksforgeeks.org/best-time-to-buy-and-sell-stock/

    public static int profit(int[] prices, int index, int canBuy) {
        if(index == prices.length) return 0;

        if(canBuy == 1) {
            int buy = -prices[index] + profit(prices, index + 1, 0);
            int notBuy = profit(prices, index + 1, 1);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + profit(prices, index + 1, 1);
            int notSell = profit(prices, index + 1, 0);
            return Math.max(sell, notSell);
        }
    }


    // https://www.youtube.com/watch?v=nGJmxkUJQGs&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
    public static int maxProfit(int[] prices, int index, int canBuy, int cap, Integer[][][] tab) {
        if(index == prices.length || cap == 0)
            return 0;

        if(tab[index][canBuy][cap] != null){
            return tab[index][canBuy][cap];
        }

        if(canBuy == 1) {
            int buy = -prices[index] + maxProfit(prices, index + 1, 0, cap, tab);
            int notBuy = maxProfit(prices, index + 1, 1, cap, tab);
            return tab[index][canBuy][cap] = Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfit(prices, index + 1, 1, cap - 1, tab);
            int notSell = maxProfit(prices, index + 1, 0, cap, tab);
            return tab[index][canBuy][cap] = Math.max(sell, notSell);
        }
    }


    public static int maxProfit(int[] prices, int index, boolean canBuy, String res) {
        if(index == prices.length) {
            //System.out.println(res);
            return 0;
        }

        if(canBuy) {
            int buy = -prices[index] + maxProfit(prices, index + 1, false, res + " " + prices[index]);
            int notBuy = maxProfit(prices, index + 1, true, res);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfit(prices, index + 1, true, res + " " + prices[index]);
            int notSell = maxProfit(prices, index + 1, false, res);
            return Math.max(sell, notSell);
        }
    }


    // https://www.youtube.com/watch?v=excAOvwF_Wk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=36
    public static int maxProfit(int[] prices) {
        int min = prices[0], profit = 0;

        for (int i = 1; i < prices.length; i++) {
            int cost = prices[i] - min;
            profit = Math.max(profit, cost);
            System.out.println("Processing : " + prices[i] + " " + min + " profit : " + profit);
            min = Math.min(min, prices[i]);

        }

        return profit;
    }


}
