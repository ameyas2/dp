package com;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Stack;

@Log4j2
public class RecursionMain {
    public static void main(String[] args) {
        int[][] cache = new int[11][11];
        for(int i = 0; i < 11; i++)
            Arrays.fill(cache[i], -1);
        //printN(10);
        /*Stack<Integer> stack = new Stack<>();
        stack.add(13);
        stack.add(4);
        stack.add(45);
        stack.add(65);
        stack.add(100);
        stack.add(3);
        System.out.println(stack);
        processStack(stack);
        System.out.println(stack);*/
        //System.out.println(numSum(10));
        //System.out.println(binarySearch(new int[]{5, 4, 8, 10, 30, 45, 50, 60, 90}, 7, (9 + 0) / 2, 0, 9));
        //System.out.println(fib(1000, new long[10001]));
        //mergeSort();
        //System.out.println(knapsack(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50, 3));
        //System.out.println(knapsackTabular(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5, 3));
        //System.out.println(countSubsetSumTabular(new int[]{2,3,5,1,6,4}, 6, 6));
        //System.out.println(coinChange(new int[] {1,2,3}, 5));
        //System.out.println(lcs("abcdghrtm", "abedlhytl", 9, 9, new int[9][9]));
        //System.out.println(eggDropping(3,5));
        System.out.println(eggDropping(3, 5, cache));
    }

    public static int eggDropping(int e, int f) {
        if(e == 1)
            return f;

        if(f <= 1)
            return f;

        int min = Integer.MAX_VALUE;
        for(int k = 1; k < f; k++) {
            int temp = 1 + Math.max(eggDropping(e - 1, k - 1), eggDropping(e, f - k));
            min = Math.min(min, temp);
        }
        return min;
    }

    public static int eggDropping(int e, int f, int[][] cache) {
        if(f <= 1)
            return f;
        if(e == 1)
            return e;

        if(cache[e][f] != -1)
            return cache[e][f];

        int min = Integer.MAX_VALUE;
        for(int k = 1; k < f; k++) {
            int low = cache[e - 1][k - 1] != -1 ? cache[e - 1][k - 1] : eggDropping(e - 1, k - 1);
            int high = cache[e][f - k] != -1 ? cache[e][f - k] : eggDropping(e, f - k);

            int temp = 1 + Math.max(low, high);
            min = Math.min(min, temp);
        }
        return min;
    }

    public static int lcs(String x, String y, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(x.charAt(n - 1) == y.charAt(m - 1)) {
            return 1 + lcs(x, y, n - 1, m - 1);
        } else {
            return Math.max(lcs(x, y, n - 1, m), lcs(x, y, n, m - 1));
        }
    }

    public static int lcs(String x, String y, int n, int m, int[][] cache) {
        if(n == 0 || m == 0)
            return 0;

        if(cache[n - 1][m - 1] >= 0)
            return cache[n - 1][m - 1];

        if(x.charAt(n - 1) == y.charAt(m - 1)) {
            cache[n - 1][m - 1] = 1 + lcs(x, y, n - 1, m - 1);
        } else {
            cache[n - 1][m - 1] = Math.max(lcs(x, y, n - 1, m), lcs(x, y, n, m - 1));
        }

        return cache[n - 1][m - 1];
    }

    /*public static int lcsTabular(String x, String y, int m, int n) {
        int[][] t = new int[m + 1][n + 1];
        for(int row = 1; row <= m; row++) {

        }
    }*/


    public static int coinChange(int[] coins, int sum) {
        int[][] t = new int[coins.length + 1][sum + 1];
        Arrays.fill(t[0], 0);
        for(int row = 0; row < coins.length + 1; row++) {
            t[row][0] = 1;
        }

        for(int row = 1; row <= coins.length; row++) {
            for(int col = 1; col <= sum; col++) {
                if(coins[row - 1] <= col) {
                    t[row][col] = t[row - 1][col] + t[row][col - coins[row - 1]];
                } else {
                    t[row][col] = t[row - 1][col];
                }
            }
        }

        return t[coins.length][sum];
    }

    public static int countSubsetSumTabular(int[] arr, int sum, int count) {
        int t[][] = new int[count + 1][sum + 1];
        for(int row = 0; row < t.length; row++) {
            t[row][0] = 1;
        }

        for(int row = 1; row <= count; row++) {
            for(int col = 1; col <= sum; col++) {
                if(arr[row - 1] <= col) {
                    t[row][col] = t[row - 1][col] + t[row - 1][col - arr[row - 1]];
                } else {
                    t[row][col] = t[row -1][col];
                }
            }
        }

        return t[count][sum];
    }

    public static boolean subsetSumTabular(int[] arr, int sum, int count) {
        boolean t[][] = new boolean[count + 1][sum + 1];
        for(int row = 0; row < t.length; row++) {
            t[row][0] = true;
        }

        for(int row = 1; row <= count; row++) {
            for(int col = 1; col <= sum; col++) {
                if(arr[row - 1] <= col) {
                    t[row][col] = t[row - 1][col] || t[row - 1][col - arr[row - 1]];
                } else {
                    t[row][col] = t[row - 1][col];
                }
            }
        }

        return t[count][sum];
    }

    public static boolean subsetSum(int[] arr, int sum, int count, boolean[][] cache) {
        if(count == 0 && sum > 0)
            return false;

        if(sum == 0)
            return true;

        if(cache[count - 1][sum - 1] == true)
            return cache[count - 1][sum - 1];

        if(arr[count - 1] <= sum) {
            System.out.println(arr[count - 1] + " " + sum);
            cache[count - 1][sum - 1] = subsetSum(arr, sum - arr[count - 1], count - 1, cache) || subsetSum(arr, sum, count - 1, cache);
        } else {
            cache[count - 1][sum - 1] = subsetSum(arr, sum, count - 1, cache);
        }
        return cache[count - 1][sum - 1];

        /*if(count == 0 && sum > 0)
            return false;

        if(sum == 0)
            return true;

        if(arr[count - 1] <= sum) {
            System.out.println(arr[count - 1] + " " + sum);
            return subsetSum(arr, sum - arr[count - 1], count - 1) || subsetSum(arr, sum, count - 1);
        } else {
            return subsetSum(arr, sum, count - 1);
        }*/
    }

    public static int knapsackTabular(int[] wt, int[] val, int capacity, int items) {
        int[][] t = new int[items + 1][capacity + 1];
        for(int row = 0; row < items + 1; row++) {
            for(int col = 0; col < capacity + 1; col++) {
                t[row][col] = 0;
            }
        }

        for(int item = 1; item < items + 1; item++) {
            for(int col = 1; col < capacity + 1; col++) {
                t[item][col] = wt[item - 1] <= col ? Math.max(val[item - 1] + t[item - 1][capacity - wt[item - 1]], t[item - 1][capacity]) : t[item - 1][capacity];
            }
        }
        return t[items][capacity];
    }

    public static int knapsack(int[] wt, int[] val, int capacity, int items) {
        int[][] cache = new int[items][capacity];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        return knapsack(wt, val, capacity, items, cache);
    }

    public static int knapsack(int[] wt, int[] val, int capacity, int items, int[][] cache) {
        /*if no items are available or the capacity is zero(knapsack is full)
        then return value as 0*/
        if(items == 0 || capacity == 0)
            return 0;
        if(cache[items - 1][capacity - 1] != -1)
            return cache[items - 1][capacity - 1];
        /*if last items weight is lees than capacity, add it to knapsack then reduce the capacity based on the weight of the item and reduce the total items
        if last item is not added to knapsack then reduce the total items and
        return the max value of sum of value of all items or except the last item either of the above conditions*/
        if(wt[items - 1] <= capacity) {
            cache[items - 1][capacity - 1] = Math.max(knapsack(wt, val, capacity - wt[items - 1], items - 1) + val[items - 1], knapsack(wt, val, capacity, items - 1));
        } else {
            /*if last items weight is more than capacity, skip it and reduce the total items
            return the value of sum of other items*/
            cache[items - 1][capacity - 1] = knapsack(wt, val, capacity, items - 1);
        }
        return cache[items - 1][capacity - 1];
        /*if(items == 0 || capacity == 0)
            return 0;

        if(wt[items - 1] <= capacity) {
            return Math.max(knapsack(wt, val, capacity - wt[items - 1], items - 1) + val[items - 1], knapsack(wt, val, capacity, items - 1));
        } else {
            return knapsack(wt, val, capacity, items - 1);
        }*/
    }

    public static void mergeSort() {
        int[] arr = {44, 78, 3, 72, 10, 2, 67, 55, 89, 13, 20};
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + "");
        }
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] data, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while(i <= mid && j <= end) {
            if(data[i] <= data[j]) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = data[i++];
        }

        while (j <= end) {
            temp[k++] = data[j++];
        }

        for(int index = start; index <= end; index++) {
            data[index] = temp[index - start];
        }
    }

    public static long fib(int length, long[] cache) {
        if(length <= 1)
            return 1;
        if(cache[length] > 0)
            return cache[length];
        cache[length] = fib(length - 1, cache) + fib(length - 2, cache);
        return cache[length];
    }

    public static int binarySearch(int[] arr, int target, int m, int l, int r) {
        if(l >= r)
            return -1;
        if(arr[m] == target)
            return m;
        else if(arr[m] < target)
            l = m + 1;
        else if(arr[m] > target)
            r = m - 1;

        return binarySearch(arr, target, (l + r) / 2, l, r);
    }

    public static int numSum(int num) {
        if(num == 0)
            return 0;
        return numSum(num - 1) + num;
    }

    public static String decimalToBinary(Integer num) {
        if(num == 0)
            return "";
        int div = num / 2;
        return decimalToBinary(div) + num % 2;
    }

    public static boolean palindrome(String data) {
        if(data.length() <= 1)
            return true;
        if(data.charAt(0) == data.charAt(data.length() - 1))
            return false;
        return palindrome(data.substring(1, data.length() - 1));
    }

    public static String reverseString(String data) {
        if(data.length() <= 0)
            return "";

        return reverseString(data.substring(1)) + data.charAt(0);
    }

    public static void printN(int n) {
        if(n <= 0)
            return;
        printN(n - 1);
         
    }

    public static void processStack(Stack<Integer> stack) {
        if(stack.isEmpty())
            return;
        int num = stack.pop();
        System.out.println("Popping " + num);
        processStack(stack);
        sortedInsert(stack, num);
    }

    public static void sortedInsert(Stack<Integer> stack, Integer val) {
        if(stack.isEmpty() || stack.peek() < val) {
            stack.push(val);
        } else {
            int temp = stack.pop();
            sortedInsert(stack, val);
            stack.push(temp);
        }
    }
}
