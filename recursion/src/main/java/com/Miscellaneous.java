package com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Miscellaneous {
    public static void main(String[] args) {
        /*System.out.println(move(1,3,4, new Integer[2]));
        System.out.println(move(9,5,1, new Integer[10]));*/
        /*System.out.println(maxBreakSum(12, new Integer[13]));
        System.out.println(maxBreakSum(12));*/

        //System.out.println(minNoTaskCount(new int[]{ 0, 1, 3, 2 }, 0, 4, new Integer[5][4]));
        /*System.out.println(minReduceCost(2,2, -1, -1, new Integer[3][3]));
        System.out.println(minReduceCost(53,16, -1, -1, new Integer[100][100]));*/

        /*System.out.println(reduce(25, new Integer[30]));
        System.out.println(reduce(10, new Integer[20]));
        System.out.println(reduce(25));
        System.out.println(reduce(10));*/

        //System.out.println(tip(new int[]{ 1, 2, 3, 4, 5 }, new int[]{ 5, 4, 3, 2, 1 }, 3, 3, 5, new Integer[10][10][10]));

        //System.out.println(reduce2(10, ""));
        System.out.println(maxBillboardRevenue(20, new int[]{6,8,12,14,16}, new int[]{5,8,5,3,1}, 3));
    }

    // https://www.youtube.com/watch?v=SiGqwJOvflE
    // type of LIS problem

    public static int maxBillboardRevenue(int miles, int[] b, int[] v, int dist) {
        int[] dp = new int[miles + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < b.length; i++)
            map.put(b[i], v[i]);

        dp[0] = 0;
        for(int i = 1; i <= miles; i++) {
            if(!map.containsKey(i)) {
                dp[i] = dp[i - 1];
            } else {
                int exclude = dp[i - 1];
                int include = dp[i - dist - 1] + map.get(i);
                dp[i] = Math.max(include, exclude);
            }
        }

        return dp[miles];
    }

    public static int reduce2(int n, String res) {
        if(n == 0) {
            System.out.println(res);
            return 0;
        }

        if(n < 0)
            return 9999999;

        int divideBy3 = 9999999, divideBy2 = 9999999, sub1 = 9999999;
        if(n % 3 == 0) {
            divideBy3 = reduce2(n / 3, res + " n/3");
        } else if(n % 2 == 0) {
            divideBy2 = reduce2(n / 2, res + " n/2");
        } else {
            sub1 = reduce2(n - 1, res + " n-1");
        }

        return Math.min(divideBy3, Math.min(divideBy2, sub1)) + 1;
    }

    // https://www.geeksforgeeks.org/maximum-tip-calculator-2/
    public static int tip(int[] a, int[] b, int x, int y, int n, Integer[][][] dp) {
        if(n == 0) return 0;

        if(dp[n][x][y] != null)
            return dp[n][x][y];

        if(x <= 0) {
            return dp[n][x][y] = b[n - 1] + tip(a, b, x, y - 1, n - 1, dp);
        }

        if(y <= 0) {
            return dp[n][x][y] = a[n - 1] + tip(a, b, x - 1, y, n - 1, dp);
        }

        return dp[n][x][y] = Math.max(b[n - 1] + tip(a, b, x, y - 1, n - 1, dp),
                a[n - 1] + tip(a, b, x - 1, y, n - 1, dp));
    }

    // https://www.geeksforgeeks.org/minimize-operations-to-reduce-n-to-2-by-repeatedly-reducing-by-3-or-dividing-by-5/

    public static int reduce(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 999999);

        dp[2] = 0;

        for(int i = 3; i <= n; i++) {
            int mul5 = 99999999;
            if(i % 5 == 0)
                mul5 = dp[i / 5];
            int sub3 = dp[i - 3];
            dp[i] = Math.min(mul5, sub3) + 1;
        }

        return dp[n];
    }

    public static int reduce(int n, Integer[] cache) {
        if(n == 2)
            return 0;

        if(n < 2)
            return 99999999;

        if(cache[n] != null)
            return cache[n];

        int mul5 = 99999999;
        if(n % 5 == 0) {
            mul5 = reduce(n / 5, cache);
        }

        int sub3 = reduce(n - 3, cache);
        return cache[n] = Math.min(mul5, sub3) + 1;
    }
    public static int minReduceCost(int a, int b, int prevA, int prevB, Integer[][] cache) {
        if(a == prevA && b == prevB) {
            return Integer.MAX_VALUE;
        }

        if(a == 0 && b == 0) {
            return 0;
        }

        if(cache[a][b] != null)
            return cache[a][b];

        int ans1 = minReduceCost(a / 2, b, a, b, cache);
        if(ans1 != Integer.MAX_VALUE)
            ans1 += 1;

        int ans2 = minReduceCost(a, b / 2, a, b, cache);
        if(ans2 != Integer.MAX_VALUE)
            ans2 += 1;

        int ans3 = minReduceCost((int)Math.sqrt(a * b), (int)Math.sqrt(a * b), a, b, cache);
        if(ans3 != Integer.MAX_VALUE)
            ans3 += 2;

        return cache[a][b] = Math.min(ans1, Math.min(ans2, ans3));
    }

    // https://www.geeksforgeeks.org/minimum-number-of-days-in-which-no-task-is-performed/s
    public static int minNoTaskCount(int[] arr, int last, int n, Integer[][] cache) {
        if(n == 0)
            return 0;

        if(cache[n][last] != null)
            return cache[n][last];

        if(arr[n - 1] == 0) {
            return cache[n][last] = 1 + minNoTaskCount(arr, 0, n - 1, cache);
        } else if(arr[n - 1] == 1) {
            if(last == 2) {
                return cache[n][last] = 1 + minNoTaskCount(arr, 0, n - 1, cache);
            } else {
                return cache[n][last] = minNoTaskCount(arr, 2, n - 1, cache);
            }
        } else if(arr[n - 1] == 2) {
            if(last == 1) {
                return cache[n][last] = 1 + minNoTaskCount(arr, 0, n - 1, cache);
            } else {
                return cache[n][last] = minNoTaskCount(arr, 1, n - 1, cache);
            }
        } else {
            if(last == 1)
                return cache[n][last] = minNoTaskCount(arr, 2, n - 1, cache);
            else if(last == 2)
                return cache[n][last] = minNoTaskCount(arr, 1, n - 1, cache);
            else
                return cache[n][last] = Math.min(minNoTaskCount(arr, 2, n - 1, cache), minNoTaskCount(arr, 1, n - 1, cache));
        }
    }

    // https://www.geeksforgeeks.org/recursively-break-number-3-parts-get-maximum-sum/
    public static int maxBreakSum(int n) {
        int[] tab = new int[n + 1];

        tab[0] = 0;
        tab[1] = 1;

        for(int i = 2; i <= n; i++) {
            tab[i] = Math.max((tab[i / 2]) + (tab[i / 3]) + (tab[i / 4]), i);
        }

        return tab[n];
    }


    public static int maxBreakSum(int n, Integer[] cache) {
        if(n == 0 || n == 1)
            return n;

        if(cache[n] != null)
            return cache[n];

        return cache[n] = Math.max(maxBreakSum(n / 2, cache) + maxBreakSum(n / 3, cache) + maxBreakSum(n / 4, cache), n);
    }

    // https://www.geeksforgeeks.org/minimum-cost-to-reach-a-point-n-from-0-with-two-different-operations-allowed/
    public static int move(int n, int p, int q, Integer[] cache) {
        if(n == 0) return 0;

        if(cache[n] != null) return cache[n];

        int mp = move(n - 1, p, q, cache) + p;
        int mq = n % 2 == 0 ? move(n / 2, p, q, cache) + q : Integer.MAX_VALUE;

        return cache[n] = Math.min(mp, mq);
    }
}
