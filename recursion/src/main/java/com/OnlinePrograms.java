package com;

import java.util.*;

public class OnlinePrograms {
    public static void main(String[] args) {
        //int[] arr = countBits(18);
        //System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        //System.out.println(maxProfit(new int[]{7,6,4,3,1}));
        //System.out.println(getMaximumGenerated(7));
        //System.out.println(climbStairsBottomUp(4));
        //System.out.println(isSubsequence("abc", "ahbgdc"));
        //System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        //System.out.println(getKth(12, 15, 2));
        System.out.println(stoneGame(new int[]{5,3,4,5}));
    }

    public List<Integer> lexicalOrder(int n) {
        int mul = 0;
        for(int i = 0;;) {
            
        }
    }

    public static boolean stoneGame(int[] piles) {
        int sum = 0;
        for (int pile : piles) {
            sum += pile;
        }
        return stoneGame(piles, 0, piles.length - 1, new Integer[piles.length + 1][piles.length + 1]) > sum / 2;
    }

    public static int stoneGame(int[] piles, int l, int r, Integer[][] dp) {
        if(l > r)
            return 0;

        if(dp[l][r] != null)
            return dp[l][r];

        boolean even = (r - l) % 2 == 0 ? true : false;
        int left = even ? piles[l] : 0;
        int right = even ? piles[r] : 0;

        return dp[l][r] = Math.max(stoneGame(piles, l + 1, r, dp) + left, stoneGame(piles, l, r - 1, dp) + right);
    }


    public static int getKth(int lo, int hi, int k) {
        int result = 0;
        Map<Integer, Set<Integer>> map = new TreeMap<>();
        for(int i = lo; i <= hi; i++) {
            int pv = powerValue(i);
            //System.out.println("pv " + pv);
            if(map.containsKey(pv)) {
                map.get(pv).add(i);
            } else {
                TreeSet<Integer> set = new TreeSet<>();
                set.add(i);
                map.put(pv, set);
            }
        }


        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if(entry.getValue().size() >= k){
                for (Integer integer : entry.getValue()) {
                    if(k == 1) {
                        return integer;
                    } else {
                        k--;
                    }
                }
            } else {
                k -= entry.getValue().size();
            }
        }
        return result;
    }

    public static int powerValue(int x) {
        int value = 0;
        while (true) {
            if(x == 1)
                return value;
            if(x % 2 == 0) {
                x = x / 2;
            } else {
                x = 3 * x + 1;
            }
            value++;
        }
    }


    public static int maxSubArray(int[] nums) {
        int[] sums = new int[nums.length];
        int max = nums[0], num = nums[0];
        sums[0] = nums[0];

        for(int i = 1; i < nums.length; i++) {
            num = Math.max(nums[i], nums[i] + sums[i - 1]);
            sums[i] = num;
            max = Math.max(num, max);
        }
        return max;
    }

    public static boolean isSubsequence(String s, String t) {
        /*if(s.length() == 0)
            return true;
        if(s.length() > 0 && t.length() == 0)
            return false;

        if(s.charAt(s.length() - 1) == t.charAt(t.length() - 1))
            return isSubsequence(s.substring(0, s.length() - 1), t.substring(0, t.length() - 1));
        else
            return isSubsequence(s, t.substring(0, t.length() - 1));*/
        if(s.equals(""))
            return true;

        int index = 0;
        for(int i = 0; i < t.length(); i++) {
            if(t.charAt(i) == s.charAt(index))
                index++;
            if(index == s.length())
                return true;
        }

        return false;
    }

    public static int climbStairsBottomUp(int n) {
        int[] cache = new int[n + 1];
        cache[0] = cache[1] = 1;
        for(int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }

    public static int climbStairs(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return climbStairs(n, cache);
    }

    public static int climbStairs(int n, int[] cache) {
        if(n < 0)
            return 0;
        if(n <= 1)
            return 1;
        if(cache[n] > -1)
            return cache[n];
        cache[n] = climbStairs(n - 1, cache) + climbStairs(n - 2, cache);
        return cache[n];
    }

    public static int getMaximumGenerated(int n) {
        int[] nums = new int[n + 10];
        int max = 1;

        nums[0] = 0;
        nums[1] = 1;

        for(int i = 1; i <= n; i++) {
            int firstIndex = 2 * i;
            int secondIndex = (2 * i) + 1;

            if(2 <= firstIndex && firstIndex <= n) {
                int val = nums[i];
                if(max < val)
                    max = val;
                nums[firstIndex] = val;
            }

            if(2 <= secondIndex && secondIndex <= n) {
                int val = nums[i] + nums[i + 1];
                if(max < val)
                    max = val;
                nums[secondIndex] = val;
            }
        }

        return max;
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int min = prices[0];

        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < min)
                min = prices[i];
            else if(prices[i] > min)
                profit = Math.max(profit, prices[i] - min);
        }

        return profit;
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];

        if(n >= 1)
            res[1] = 1;

        for(int i = 2, count = 1; i <= n; count++) {
            int range = (int)Math.pow(2, count);
            for(int k = 0; k < range && i <= n; k++) {
                res[i++] = res[k] + 1;
            }
        }

        return res;
    }


}
