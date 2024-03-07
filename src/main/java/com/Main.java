package com;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Log4j2
public class Main {
    public static void main(String[] args) {

        /*log.info(fib(6, new long[7]));
        log.info(fib(7, new long[8]));
        log.info(fib(80, new long[81]));*/
        /*log.info(fibTab(6));
        log.info(fibTab(7));
        log.info(fibTab(80));*/
        /*int n = 18;
        Map<String, Long> cache = new HashMap<>();
        log.info(gridTraveller(n, n, cache));*/
        //log.info(canSum(7, new int[] {5, 3, 4, 7}));
        //log.info(canSum(3000, new int[] {2, 4}));
        /*log.info(howSum(300, new int[] {5, 3, 4, 7}, new HashMap<>()));
        log.info(howSum(7, new int[] {2, 4}, new HashMap<>()));*/
        //log.info(canSumTab(7,new int[] {5,3,4}));
        /*log.info(UglyNumber.uglyNumber(7));
        log.info(UglyNumber.uglyNumber(10));
        log.info(UglyNumber.uglyNumber(15));
        log.info(UglyNumber.uglyNumber(150));*/
        /*int[][] mine = { {1, 3, 1, 5},
                {2, 2, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2}};
        System.out.println(GoldMine.goldMine(mine));*/

        /*int[] coins = {2, 3, 5};
        int change = 7;
        System.out.println(CoinChange.coinChange(coins, change));*/

        //System.out.println(SubsetSum.subsetSum(new int[] {2,3,7,8,10}, 11));
        //System.out.println(LargestAltitude.largestAltitude(new int[] {-4,-3,-2,-1,4,3,2}));
        flipAndInvertImage(new int[][] {{1,1,0,0}, {1,0,0,1}, {0,1,1,1}, {1,0,1,0}});
    }

    public static int[][] flipAndInvertImage(int[][] image) {
        int rowsLength = image.length;
        int colLength = image[0].length;

        int[][] res = new int[rowsLength][colLength];
        for(int row = 0; row < rowsLength; row++) {
            for (int col = colLength - 1, j = 0; col >= 0; col--, j++) {
                res[row][j] = image[row][col];
            }
        }

        for(int row = 0; row < rowsLength; row++) {
            for(int col = 0; col < rowsLength; col++) {
                if(res[row][col] == 0)
                    res[row][col] = 1;
                else if(res[row][col] == 1)
                    res[row][col] = 0;
            }
        }

        return res;
    }

    public static long fib(int n, long[] cache) {
        if(cache[n] != 0)
            return cache[n];
        if(n <= 2)
            return 1;
        cache[n] = fib(n - 1, cache) + fib(n - 2, cache);
        return cache[n];
    }

    public static long gridTraveller(int row, int col, Map<String, Long> cache) {
        String key = row+","+col;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        if(row == 0 || col == 0)
            return 0;
        if(row == 1 && col == 1)
            return 1;
        cache.put(key, gridTraveller(row - 1, col, cache) + gridTraveller(row, col - 1, cache));
        return cache.get(key);
    }

    public static boolean canSum(int target, int[] arr) {
        if(target == 0)
            return true;
        if(target < 0)
            return false;
        for(int i = 0; i < arr.length; i++) {
            if(canSum(target - arr[i], arr) == true)
                return true;
        }
        return false;
    }

    public static LinkedList<Integer> howSum(int target, int[] arr, Map<Integer, LinkedList<Integer>> cache) {
        if(cache.containsKey(target))
            return cache.get(target);
        if(target == 0)
            return new LinkedList<>();
        if(target < 0)
            return null;
        for(int i = 0; i < arr.length; i++) {
            LinkedList<Integer> result = howSum(target - arr[i], arr, cache);
            if(result != null) {
                result.add(arr[i]);
                cache.put(target, result);
                return result;
            }
        }
        return null;
    }

    public static long fibTab(int n) {
        long[] tab = new long[n + 1];
        tab[1] = 1;
        for(int i = 2; i <= n; i++) {
            tab[i] = tab[i - 1] + tab[i - 2];
        }
        return tab[n];
    }

    public static long gridTravellerTab(int row, int col) {
        long[][] tab = new long[row + 1][col + 1];
        tab[1][1] = 1;
        for(int i = 0; i <= row; i++) {
            for(int j = 0; j <= col; j++) {
                if(i + 1 <= row)
                    tab[i + 1][j] += tab[i][j];
                if(j + 1 <= col)
                    tab[i][j + 1] += tab[i][j];
            }
        }
        return tab[row][col];
    }

    public static boolean canSumTab(int target, int[] arr) {
        boolean[] table = new boolean[target + 1];
        table[0] = true;
        for (int i = 0; i <= target; i++) {
            if(table[i] == true) {
                for(int j = 0; j < arr.length; j++) {
                    if(arr[j] + i <= target)
                        table[arr[j] + i] = true;
                }
            }
        }
        return table[target];
    }

    public static void printArr(int[] arr) {
        for (int col = 0; col < arr.length; col++) {
            System.out.print(arr[col] + " ");
        }
        System.out.println();
    }


}
