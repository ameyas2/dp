package com;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetSum {
    public static boolean subsetSum(int[] nums, int sum) {
        boolean[][] tab = new boolean[nums.length + 1][sum + 1];
        Arrays.stream(tab).forEach(resRow -> Arrays.fill(resRow, false));
        for(int i = 0; i < tab.length; i++) {
            tab[i][0] = true;
        }
        for(int row = 1; row <= nums.length; row++) {
            for(int col = 1; col <= sum; col++) {
                int res = col - nums[row - 1];
                if(col < nums[row - 1]) {
                    tab[row][col] = tab[row - 1][col];
                    continue;
                }
                boolean upper = tab[row - 1][col];
                boolean upperLeft = tab[row - 1][res];

                tab[row][col] = upper ? upper : upperLeft;
            }
        }

        List<Integer> numbers = new LinkedList<>();

        for(int row = tab.length - 1; row > 0; ) {
            for(int col = tab[0].length - 1; col > 0; ) {
                if(tab[row][col]) {
                    if(tab[row - 1][col]) {
                        row--;
                    } else if(tab[row - 1][col - nums[row - 1]]) {
                        numbers.add(nums[row - 1]);
                        col = col - nums[row - 1];
                    }
                } else
                    row--;
            }
        }
        System.out.println(numbers);
        return tab[nums.length][sum];

    }
}
