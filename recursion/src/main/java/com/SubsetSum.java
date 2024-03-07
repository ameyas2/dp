package com;

import java.util.Arrays;

public class SubsetSum {
    public static void main(String[] args) {
        /*System.out.println(subsetSum(new int[]{3, 34, 4, 12, 5, 2}, 9, 6));
        System.out.println(subsetSumMem(new int[]{3, 34, 4, 12, 5, 2}, 9, 6, new Boolean[6 + 1][ 9 + 1]));
        System.out.println(subsetSumTab(new int[]{3, 34, 4, 12, 5, 2}, 9, 6));*/
        //System.out.println(subsetSum(new int[]{2,3,9,7,8,10}, 11, 6, ""));
        //System.out.println(subsetSumTab(new int[]{2,3,9,7,8,10}, 11));
        //System.out.println(minSubsetSumDifference(new int[]{2,3,9,7,8,10}));
       /* System.out.println(bitwiseOR(new int[]{5, 1, 3, 4, 2}, 5, ""));
        System.out.println(bitwiseOR(new int[]{2, 6, 2, 8, 4, 5}, 6, ""));*/

        //System.out.println(sumK(new int[]{1,1,1}, 2, 3, ""));

        //subsetDiff(new int[]{5,2,6,4}, 3);

        //System.out.println(subsetSumMax(new int[]{2, 3, 2, 3, 3, 4}, -1, 0));
        //System.out.println("test");
        /*System.out.println(maxNum(new int[]{25, 10, 5}, 30, 3, new Integer[4][31]));
        System.out.println(maxNum(new int[]{9, 6, 4, 3}, 11, 4, new Integer[5][12]));

        System.out.println(maxNum(new int[]{25, 10, 5}, 30));
        System.out.println(maxNum(new int[]{9, 6, 4, 3}, 11));*/

        //System.out.println(sumOfNines(156));
        System.out.println(subsetSumAddSub(new int[]{1,1,1,1,1}, 0, 3, 5, ""));
    }

    public static int subsetSumAddSub(int[] arr, int sum, int k, int n, String res) {
        if(n == 0) {
            if(sum == k) {
                System.out.println(res + " " + sum);
                return 1;
            } else return 0;
        }

        int sub = subsetSumAddSub(arr, sum - arr[n - 1], k, n - 1, res + " -" + arr[n - 1]);
        int add = subsetSumAddSub(arr, sum + arr[n - 1], k, n - 1, res + " +" + arr[n - 1]);
        return add + sub;
    }

    // https://www.geeksforgeeks.org/check-if-an-array-can-be-split-into-3-subsequences-of-equal-sum-or-not/
    public static int sumOfNines(int n) {
        int[] arr = new int[n / 9];
        int sum = 9;
        for(int i = 0; sum <= n; i++, sum += 10) {
            arr[i] = sum;
        }
        arr = Arrays.stream(arr).filter(num -> num != 0).toArray();
        System.out.println(sumOfNines(arr, n));
        return sumOfNines(arr, n, arr.length, new Integer[arr.length + 1][n + 1]);
    }

    public static int sumOfNines(int[] arr, int sum, int n, Integer[][] dp) {
        if(sum == 0) {
            return 0;
        }

        if(sum < 0 || sum > 0 && n == 0)
            return 99999;

        if(dp[n][sum] != null)
            return dp[n][sum];

        if(sum >= arr[n - 1]) {
            int include = sumOfNines(arr, sum - arr[n - 1], n, dp) + 1;
            int exclude = sumOfNines(arr, sum, n - 1, dp);
            return Math.min(include, exclude);
        } else {
            return sumOfNines(arr, sum, n - 1, dp);
        }
    }

    public static int sumOfNines(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(i == 0) {
                    dp[i][j] = 99999;
                    continue;
                }

                if(j >= arr[i - 1]) {
                    int include = dp[i][j - arr[i - 1]] + 1;
                    int exclude = dp[i - 1][j];
                    dp[i][j] =  Math.min(include, exclude);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[n][sum];
    }

    public static int maxNum(int[] arr, int v) {
        int n = arr.length;
        int[][] tab = new int[n + 1][v + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= v; j++) {
                if(j == 0 || i == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= arr[i - 1]) {
                    int include = tab[i][v - arr[i - 1]] + 1; //  maxNum(arr, v - arr[n - 1], n, tab) + 1;
                    int exclude = tab[i - 1][j];//maxNum(arr, v, n - 1, tab);
                    return tab[i][j] = Math.max(include, exclude);
                } else {
                    return tab[i][j] = tab[i - 1][j]; //maxNum(arr, v, n - 1, tab);
                }
            }
        }

        return tab[n][v];
    }

    public static int maxNum(int[] arr, int v, int n, Integer[][] tab) {
        if(v == 0)
            return 0;
        if(v < 0 || v > 0 & n == 0)
            return -1;

        if(tab[n][v] != null)
            return tab[n][v];

        if(v >= arr[n - 1]) {
            int include = maxNum(arr, v - arr[n - 1], n, tab) + 1;
            int exclude = maxNum(arr, v, n - 1, tab);
            return tab[n][v] = Math.max(include, exclude);
        } else {
            return tab[n][v] = maxNum(arr, v, n - 1, tab);
        }
    }

    // https://www.geeksforgeeks.org/count-of-subsets-with-given-difference/
    public static void subsetDiff(int[] arr, int diff) {
        int sum = Arrays.stream(arr).sum();
        int partitionSum = (sum - diff) / 2;
        System.out.println(subsetDiff(arr, partitionSum, arr.length, new Integer[arr.length + 1][partitionSum + 1]));
        System.out.println(subsetDiffTab(arr, partitionSum));
    }


    public static int subsetDiffTab(int[] arr, int sum) {
        int n = arr.length;
        int[][] tab = new int[n + 1][sum + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    tab[i][j] = 1;
                    continue;
                }

                if(i == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= arr[i - 1]) {
                    int include = tab[i - 1][j - arr[i - 1]];
                    int exclude = tab[i - 1][j];
                    tab[i][j] = include + exclude;
                } else {
                    tab[i][j] = tab[i - 1][j];
                }
            }
        }

        return tab[n][sum];
    }

    public static int subsetDiff(int[] arr, int sum, int n, Integer[][] cache) {
        if(sum == 0) {
            return 1;
        }

        if(sum < 0 || sum > 0 && n == 0) {
            return 0;
        }

        if(cache[n][sum] != null)
            return cache[n][sum];

        if(sum >= arr[n - 1]) {
            int include = subsetDiff(arr, sum - arr[n - 1], n - 1, cache);
            int exclude = subsetDiff(arr, sum, n - 1, cache);
            return cache[n][sum] = include + exclude;
        } else {
            return cache[n][sum] = subsetDiff(arr, sum, n - 1, cache);
        }
    }

    public static boolean sumK(int[] arr, int k, int n, String res) {
        if(k == 0) {
            System.out.println(res);
            return true;
        }
        if(k < 0 || k > 0 && n == 0) return false;

        int rep = (arr[n - 1] * -1);
        boolean replace = sumK(arr, k - rep, n - 1, res + " " + rep);

        int add = arr[n - 1] + n - 1;
        boolean addIndex = sumK(arr, k - add, n - 1, res + " " + add);

        int sub = arr[n - 1] - n - 1;
        boolean subIndex = sumK(arr, k - sub, n - 1, res + " " + sub);

        return replace || addIndex || subIndex;
    }

    public static int bitwiseOR(int[] arr, int n, String res) {
        if(n == 0) {
            System.out.println(res);
            return 0;
        }

        int include = bitwiseOR(arr, n - 1, res + " " + arr[n - 1]) | arr[n - 1];
        int exclude = bitwiseOR(arr, n - 1, res);
        return Math.max(include, exclude);
    }

    public static int minSubsetSumDifference(int[] arr) {
        int total = Arrays.stream(arr).sum();

        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= total; i++) {
            if(subsetSumTab(arr, i,arr.length)) {

                int diff = total - i;
                int subDiff = Math.abs(diff - i);
                if(min > subDiff)
                    min = subDiff;
                System.out.println("num : " + i + " total " + total + " subDiff " + subDiff + " min " + min);
            }
        }

        return min;
    }
     

    public static int subsetSumTab(int[] arr, int sum) {
        int n = arr.length;
        Integer[][] tab = new Integer[n + 1][sum + 1];

        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < sum + 1; j++) {
                if(j == 0) {
                    tab[i][j] = 1;
                    continue;
                }

                if(i == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= arr[i - 1]) {
                    int include = tab[i - 1][j - arr[i - 1]];
                    int exclude = tab[i - 1][j];
                    tab[i][j] = include + exclude;
                } else
                    tab[i][j] = tab[i - 1][j];
            }
        }

        Utils.print(tab);
        return tab[n][sum];
    }

    public static int subsetSum(int[] arr, int sum, int n, String res, Integer[][] cache) {
        if(sum == 0) {
            System.out.println(res);
            return 1;
        }

        if(sum < 0 || sum > 0 && n == 0)
            return 0;

        if(cache[n][sum] != null)
            return cache[n][sum];

        if(sum >= arr[n - 1]) {
            int include = subsetSum(arr, sum - arr[n - 1], n - 1, res + "," + arr[n - 1], cache);
            int exclude = subsetSum(arr, sum, n - 1, res, cache);
            return include + exclude;
        } else
            return subsetSum(arr, sum, n - 1, res, cache);
    }

    public static int subsetSum(int[] arr, int sum, int n, String res) {
        if(sum == 0) {
            System.out.println(res);
            return 1;
        }

        if(sum < 0 || sum > 0 && n == 0)
            return 0;

        if(sum >= arr[n - 1]) {
            int include = subsetSum(arr, sum - arr[n - 1], n - 1, res + "," + arr[n - 1]);
            int exclude = subsetSum(arr, sum, n - 1, res);
            return include + exclude;
        } else
            return subsetSum(arr, sum, n - 1, res);
    }

    public static boolean subsetSumTab(int[] arr, int sum, int n) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= sum; col++) {
                if(col == 0) {
                    dp[row][col] = true;
                    continue;
                }
                if(row == 0) {
                    dp[row][col] = false;
                    continue;
                }
                if(col >= arr[row - 1]) {
                    dp[row][col] = dp[row - 1][col - arr[row - 1]] || dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }

        return dp[n][sum];
    }

    public static boolean subsetSumMem(int[] arr, int sum, int n, Boolean dp[][]) {
        if(sum == 0) return true;
        if(sum < 0 || sum > 0 && n == 0) return false;
        if(dp[n][sum] != null) return dp[n][sum];

        if(sum >= arr[n - 1]) {
            boolean include = subsetSum(arr, sum - arr[n - 1], n - 1);
            boolean exclude = subsetSum(arr, sum, n - 1);
            return dp[n][sum] = include || exclude;
        } else {
            return dp[n][sum] = subsetSum(arr, sum, n - 1);
        }
    }

    public static boolean subsetSum(int[] arr, int sum, int n) {
        if(sum == 0) return true;
        if(sum < 0 || sum > 0 && n == 0) return false;

        if(sum >= arr[n - 1]) {
            boolean include = subsetSum(arr, sum - arr[n - 1], n - 1);
            boolean exclude = subsetSum(arr, sum, n - 1);
            return include || exclude;
        } else {
            return subsetSum(arr, sum, n - 1);
        }
    }
}
