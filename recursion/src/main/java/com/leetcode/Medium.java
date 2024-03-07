package com.leetcode;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        /*int[][] arr = {{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}};
        int[][] arr2 = {{-1}};
        System.out.println(Arrays.toString(findBall(arr2)));*/
        System.out.println(minCostTickets(new int[]{2,5}, new int[]{1,4,25}));
    }

    public static int minCostTickets(int[] days, int[] costs) {
        return minCostTickets(days, costs, 0, 0);
    }

    public static int minCostTickets(int[] days, int[] costs, int index, int day) {
        if(index == costs.length || day > days[index])
            return 0;

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < costs.length; i++) {
            int cost = costs[i] + minCostTickets(days, costs, i + 1, day + days[i]);
            min = Math.min(min, cost);
        }
        return min;
    }

    public static int[] findBall(int[][] arr) {
        int n = arr[0].length;
        int[] res = new int[n];

        for(int col = 0; col < n; col++) {
            res[col] = ballFall(arr, 0, col);
        }

        return res;
    }
    // https://leetcode.com/problems/where-will-the-ball-fall/
    public static int ballFall(int[][] arr, int row, int col) {
        if(row == arr.length) {
            return col;
        }

        if(arr[row][col] == 1 && col < arr[0].length - 1 && arr[row][col] == arr[row][col + 1])
            return ballFall(arr, row + 1, col + 1);
        else if(arr[row][col] == -1 && col > 0 && arr[row][col] == arr[row][col - 1])
            return ballFall(arr, row + 1, col - 1);
        else return -1;
    }
}
