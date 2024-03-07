package com;

public class CoinChange {
    public static int coinChange(int[] arr, int change) {
        int[] res = new int[change + 1];
        res[0] = 1;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 1; j <= change; j++) {
                int index = j - arr[i];
                if(index < 0) {
                    continue;
                }

                res[j] = res[j] + res[index];
            }
        }
        Main.printArr(res);
        return res[change];
    }
}
