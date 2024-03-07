package com;

public class LongestSubsequnce {
    public static void main(String[] args) {
       /* System.out.println(lsdiff(new int[]{10, 9, 4, 5, 4, 8, 6}, 7));
        System.out.println(lsdiff(new int[]{1, 2, 3, 2, 3, 7, 2, 1}, 7));*/
        //System.out.println(minDeletionSortedSequence(new int[]{30, 40, 2, 5, 1, 7, 45, 50, 8}));
        //System.out.println(longestSubsequence(new int[]{10, 9, 4, 5, 4, 8, 6}, -1, 0, ""));
        System.out.println(lisEven(new int[]{10, 22, 9, 33, 21, 50, 41, 60}, -1, 0, new Integer[10][10]));
    }

    // https://www.geeksforgeeks.org/length-of-longest-increasing-absolute-even-subsequence/

    public static int lisEven(int[] arr, int prev, int curr, Integer[][] tab) {
        if(curr == arr.length) {
            return 0;
        }

        if(prev > -1 && tab[curr][prev] != null)
            return tab[curr][prev];

        if(prev == -1 || (arr[prev] < arr[curr] && (arr[curr] - arr[prev]) % 2 == 0)) {
            int include = lisEven(arr, curr, curr + 1, tab) + 1;
            int exclude = lisEven(arr, prev, curr + 1, tab);
            int max = Math.max(include, exclude);
            if(prev > -1)
                tab[curr][prev] = max;
            return max;
        } else {
            return tab[curr][prev] = lisEven(arr, prev, curr + 1, tab);
        }
    }

    public static int longestSubsequence(int[] arr, int prev, int curr, String res) {
        if(curr == arr.length) {
            System.out.println(res);
            return 1;
        }

        if(prev == -1 || Math.abs(arr[curr] - arr[prev]) == 1) {
            int include = longestSubsequence(arr, curr, curr + 1, res + " " + arr[curr]);
            int exclude = longestSubsequence(arr, prev, curr + 1, res);
            return Math.max(include, exclude);
        } else {
            return longestSubsequence(arr, prev, curr + 1, res);
        }
    }



    // https://www.geeksforgeeks.org/minimum-number-deletions-make-sorted-sequence/
    public static int minDeletionSortedSequence(int[] arr) {
        return arr.length - lis(arr, -1, 0, new Integer[10][10]);
    }



    public static int lis(int[] arr, int prev, int curr, Integer[][] cache) {
        if(curr == arr.length) {
            return 0;
        }

        if(prev != -1 && cache[curr][prev] != null) {
            return cache[curr][prev];
        }

        if(prev == -1 || arr[prev] < arr[curr]) {
            int include = lis(arr, curr, curr + 1, cache) + 1;
            int exclude = lis(arr, prev, curr + 1, cache);
            int max = Math.max(include, exclude);
            if(prev > -1) {
                cache[curr][prev] = max;
            }
            return max;
        } else {
            return cache[curr][prev] = lis(arr, prev, curr + 1, cache);
        }
    }

    public static int lsdiff01(int[] arr, int index, int prev) {
        if(index > arr.length)
            return 0;

        int len = lsdiff01(arr, index + 1, prev);
        int diff = Math.abs(arr[index] - arr[index - 1]);
        if(prev == -1 || diff == 0 || diff == 1) {
            len = Math.max(len, lsdiff01(arr, index + 1, index));
        }
        return len;
    }

    public static int lsdiff(int[] arr, int n) {
        if(n < 0)
            return 0;

        if(n > 1 && Math.abs(arr[n - 1] - arr[n - 2]) == 1) {
            int inc = 1 + lsdiff(arr, n - 1);
            int exc = lsdiff(arr, n - 1);
            return Math.max(inc, exc);
        } else
            return lsdiff(arr, n - 1);
    }


}
