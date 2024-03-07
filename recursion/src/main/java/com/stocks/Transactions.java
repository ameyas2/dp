package com.stocks;
//https://www.youtube.com/watch?v=nGJmxkUJQGs&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
public class Transactions {
    public static void main(String[] args) {
        System.out.println("Profit : " + profit(new int[]{7,1,5,3,6,4}, 0, true, ""));
        System.out.println("Profit : " + profit(new int[]{7,1,5,3,6,4}, 0, 1, new Integer[6][2]));
        System.out.println("Profit Cap : " + profit(new int[]{7,1,5,3,6,4}, 0, 1, 1));
        System.out.println("Profit Cap : " + profit(new int[]{7,1,5,3,6,4}, 0, 1, 2, new Integer[7][3][3]));
        System.out.println("Profit cooldown: " + profitCoolDown(new int[]{4,9,0,4,10}, 0, 1, new Integer[6][3]));
        System.out.println("Profit fee: " + profitFee(new int[]{1,3,2,8,4,9}, 2, 0, 1, new Integer[7][3]));
    }

    // https://www.youtube.com/watch?v=k4eK-vEmnKg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=41
    public static int profitFee(int[] prices, int fee, int i, int buy, Integer[][] cache) {
        if(i == prices.length)
            return 0;

        if(cache[i][buy] != null)
            return cache[i][buy];

        int profit = 0;
        if(buy == 1) {
            int canBuy = -prices[i] + profit(prices, i + 1, 0, cache);
            int notBuy = profit(prices, i + 1, 1, cache);
            profit = Math.max(canBuy, notBuy);
        } else {
            int canSell = prices[i] - fee + profit(prices, i + 2, 1, cache);
            int notSell = profit(prices, i + 1, 0, cache);
            profit = Math.max(canSell, notSell);
        }
        return cache[i][buy] = profit;
    }

    // https://www.youtube.com/watch?v=IGIe46xw3YY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=40
    public static int profitCoolDown(int[] prices, int i, int buy, Integer[][] cache) {
        if(i == prices.length)
            return 0;

        if(cache[i][buy] != null)
            return cache[i][buy];

        int profit = 0;
        if(buy == 1) {
            int canBuy = -prices[i] + profit(prices, i + 1, 0, cache);
            int notBuy = profit(prices, i + 1, 1, cache);
            profit = Math.max(canBuy, notBuy);
        } else {
            int canSell = prices[i] + profit(prices, i + 2, 1, cache);
            int notSell = profit(prices, i + 1, 0, cache);
            profit = Math.max(canSell, notSell);
        }
        return cache[i][buy] = profit;
    }

    public static int profit(int[] prices, int i, int buy, int cap, Integer[][][] cache) {
        if(i == prices.length)
            return 0;
        if(cap == 0)
            return 0;

        if(cache[i][buy][cap] != null)
            return cache[i][buy][cap];

        int profit = 0;
        if(buy == 1) {
            int canBuy = -prices[i] + profit(prices, i + 1, 0, cap);
            int notBuy = profit(prices, i + 1, 1, cap);
            profit = Math.max(canBuy, notBuy);
        } else {
            int canSell = prices[i] + profit(prices, i + 1, 1, cap - 1);
            int notSell = profit(prices, i + 1, 0, cap);
            profit = Math.max(canSell, notSell);
        }
        return cache[i][buy][cap] = profit;
    }

    public static int profit(int[] prices, int i, int buy, int cap) {
        if(i == prices.length)
            return 0;
        if(cap == 0)
            return 0;

        if(buy == 1) {
            int canBuy = -prices[i] + profit(prices, i + 1, 0, cap);
            int notBuy = profit(prices, i + 1, 1, cap);
            return Math.max(canBuy, notBuy);
        } else {
            int canSell = prices[i] + profit(prices, i + 1, 1, cap - 1);
            int notSell = profit(prices, i + 1, 0, cap);
            return Math.max(canSell, notSell);
        }
    }


    /*public static int profit(int[] price) {
        int n = price.length;
        int[][] tab = new int[n + 1][3];

        
    }*/

    public static int profit(int[] price, int i, int buy, Integer[][] cache) {
        if(i == price.length) {
            return 0;
        }

        if(cache[i][buy] != null)
            return cache[i][buy];

        int profit = 0;
        if(buy == 1) {
            int canBuy = -price[i] + profit(price, i + 1, 0, cache);
            int notBuy = profit(price, i + 1, 1, cache);
            profit = Math.max(canBuy, notBuy);
        } else {
            int canSell = price[i] + profit(price, i + 1, 1, cache);
            int notSell = profit(price, i + 1, 0, cache);
            profit = Math.max(canSell, notSell);
        }
        return cache[i][buy] = profit;
    }

    public static int profit(int[] price, int i, boolean buy, String res) {
        if(i == price.length) {
            //System.out.println(res);
            return 0;
        }

        int profit = 0;
        if(buy) {
            int canBuy = -price[i] + profit(price, i + 1, false, res + " buy " + price[i]);
            int notBuy = profit(price, i + 1, true, res);
            profit = Math.max(canBuy, notBuy);
        } else {
            int canSell = price[i] + profit(price, i + 1, true, res + " sell " + price[i]);
            int notSell = profit(price, i + 1, false, res);
            profit = Math.max(canSell, notSell);
        }
        return profit;
    }
}
