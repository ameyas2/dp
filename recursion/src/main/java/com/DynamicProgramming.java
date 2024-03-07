package com;

public class DynamicProgramming {
    public static void main(String[] args) {
        //System.out.println(knapsack(new int[] {1,2,3,5}, new int[] {2, 6, 5, 10}, 6, 4));
        //System.out.println(knapsackCache(new int[] {1,2,3,5}, new int[] {2, 6, 5, 10}, 6, 4));
        /*System.out.println(knapsackCache(new int[] {1,2,3,5}, new int[] {2, 6, 5, 10}, 4, 4));
        System.out.println(subsetSum(new int[] {1,2,3,5}, 6, 4));
        System.out.println(subsetSum(new int[] {1,2,3,5}, 40, 4));
        System.out.println(subsetSum(new int[] {1,2,3,5}, 6, 4, new boolean[4 + 1][6 + 1]));*/
        System.out.println(knapsackTabular(new int[] {1,2,3,5}, new int[] {2, 6, 5, 10}, 6, 4));
    }




    public static int knapsack(int[] wt, int[] val, int capacity, int items) {
        /*if no items are available or the capacity is zero(knapsack is full)
        then return value as 0*/
        if(capacity == 0 || items == 0)
            return 0;

        /*if last items weight is lees than capacity, add it to knapsack then reduce the capacity based on the weight of the item and reduce the total items
        if last item is not added to knapsack then reduce the total items and
        return the max value of sum of value of all items or except the last item either of the above conditions*/
        if(wt[items - 1] <= capacity) {
            return Math.max(val[items - 1] + knapsack(wt, val, capacity - wt[items - 1], items - 1), knapsack(wt, val, capacity, items - 1));
        } else {
            return knapsack(wt, val, capacity, items - 1);
        }
    }

    public static int knapsackCache(int[] wt, int[] val, int capacity, int items) {
        int[][] cache = new int[items + 1][capacity + 1];
        return knapsack(wt, val, capacity, items, cache);
    }

    public static int knapsack(int[] wt, int[] val, int capacity, int items, int[][] cache) {
        if(capacity == 0 || items == 0)
            return 0;
        if(cache[items][capacity] > 0)
            return cache[items][capacity];

        if(wt[items - 1] <= capacity) {
            cache[items][capacity] = Math.max(val[items - 1] + knapsack(wt, val, capacity - wt[items - 1], items - 1, cache), knapsack(wt, val, capacity, items - 1, cache));
        } else {
            cache[items][capacity] = knapsack(wt, val, capacity, items - 1, cache);
        }
        return cache[items][capacity];
    }

    public static int knapsackTabular(int[] wt, int[] val, int capacity, int items) {
        int[][] t = new int[items + 1][capacity + 1];

        for(int row = 1; row <= items; row++) {
            for(int col = 1; col <= capacity; col++) {
                if(wt[row -1] <= col) {
                    t[row][col] = Math.max(t[row - 1][col], val[row - 1] + t[row - 1][col - wt[row  - 1]]);
                } else {
                    t[row][col] = t[row - 1][col];
                }
            }
        }

        return t[items][capacity];
    }

    public static boolean subsetSum(int arr[], int sum, int count) {
        if(sum > 0 && count == 0)
            return false;

        if(sum == 0)
            return true;

        if(arr[count - 1] <= sum) {
            return subsetSum(arr, sum - arr[count - 1], count - 1) || subsetSum(arr, sum, count - 1);
        } else {
            return subsetSum(arr, sum, count - 1);
        }
    }

    public static boolean subsetSum(int[] arr, int sum, int count, boolean[][] cache) {
        if(sum > 0 && count == 0)
            return false;

        if(sum == 0)
            return true;

        if(cache[count][sum] == true)
            return cache[count][sum];

        if(arr[count - 1] <= sum) {
            cache[count][sum] = subsetSum(arr, sum - arr[count - 1], count - 1) || subsetSum(arr, sum, count - 1);
        } else {
            cache[count][sum] = subsetSum(arr, sum, count - 1);
        }

        return cache[count][sum];
    }


}
