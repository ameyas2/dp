package com;

public class Stairs {
    public static void main(String[] args) {
        //System.out.println(jumps(6, ""));
        //System.out.println(jumps(100, new Long[101]));
        //System.out.println(jumps(new int[]{1,100,1,1,1,100,1,1,100,1}, 9, new Integer[11]));
        //System.out.println(stairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        //System.out.println(stairsOptimized(new int[]{1,100,1,1,1,100,1,1,100,1}));
        //System.out.println(jump(3, ""));
        /*System.out.println(stairs(6, ""));
        System.out.println(stairs(6, 4, ""));*/
        /*System.out.println(stairs(6, new Integer[7]));
        System.out.println(stairs(6, 4, new Integer[7]));*/
        //countStairsWithJumps(new int[]{3,3,0,2,2,3});
        //System.out.println(minStairsWithJumps(new int[]{3,2,4,2,0,2,3,1,2,2}));
        /*System.out.println(jumps(new int[]{16, 19, 10, 12, 18 }, 4));
        System.out.println(jumps(new int[]{2, 5, 3, 1, 7, 3, 4 }, 6));*/
        /*System.out.println(jumpStones(new int[]{10, 30, 40, 50, 20}, 3, 5, "", new Integer[6]));
        System.out.println(jumpStones(new int[]{10, 20, 10}, 1, 3, "", new Integer[4]));*/
    }

    // https://www.geeksforgeeks.org/minimum-cost-incurred-before-the-geek-reaches-stone-n/
    public static int jumpStones(int[] heights, int k, int n, String res, Integer[] cache) {
        if(n == 1) {
            return Math.abs(heights[0] - heights[1]);
        }

        if(n <= 0) return 0;

        if(cache[n] != null)
            return cache[n];

        int minCost = 1000000000;
        for(int i = 1; i <= k; i++) {
            int diff = n - i <= 0 ? 0 : Math.abs(heights[n - 1] - heights[n - 1 - i]);
            int cost = jumpStones(heights, k, n - i, res + " " + diff, cache) + diff;
            minCost = cache[n] = Math.min(minCost, cost);
        }

        return minCost;
    }

    public static int jumps(int[] arr, int n) {
        if(n == 0) return 0 ;
        if(n == 1) return 1;

        int one = jumps(arr, n - 1);
        int two = jumps(arr, n - 2);

        return Math.min(one, two) + arr[n];
    }



    /** https://www.youtube.com/watch?v=Zobz9BXpwYE&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=5 */
    public static Integer[] minStairsWithJumps(int[] jumps) {
        int n = jumps.length;
        Integer[] jumpCount = new Integer[n + 1];
        jumpCount[n] = 0;

        for(int i = n - 1; i >=0; i--) {
            if(jumps[i] > 0) {
                Integer min = Integer.MAX_VALUE;
                for (int j = 1; j + i <= n && j <= jumps[i]; j++) {
                    if(jumpCount[i + j] != null)
                        min = Math.min(min, jumpCount[j + i]);
                }
                jumpCount[i] = 1 + min;
            }
        }
        Utils.print(jumpCount);
        return jumpCount;
    }



    /** https://www.youtube.com/watch?v=uNqoQ0sNZCM&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=3 */
    public static int[] countStairsWithJumps(int[] jumps) {
        int n = jumps.length;
        int[] jumpCount = new int[n + 1];
        jumpCount[n] = 1;

        for(int i = n - 1; i >=0; i--) {
            for(int j = 1; j + i <= n && j <= jumps[i] ; j++) {
                jumpCount[i] += jumpCount[i + j];
            }
        }
        Utils.print(jumpCount);
        return jumpCount;
    }

    /** https://www.youtube.com/watch?v=A6mOASLl2Dg&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=2 */
    public static int stairs(int n, int k, Integer[] tab) {
        if(n == 0) {
            return 1;
        }

        if(n < 0)
            return 0;

        if(tab[n] != null)
            return tab[n];

        int totalCount = 0;
        for(int i = 1; i <= k; i++) {
            int count = stairs(n - i, k, tab);
            totalCount += count;
        }
        return tab[n] = totalCount;
    }

    public static int stairs(int n, Integer[] tab) {
        if(n == 0) {
            return 1;
        }

        if(n < 0)
            return 0;

        int s1 = stairs(n - 1, tab);
        int s2 = stairs(n - 2, tab);
        int s3 = stairs(n - 3, tab);
        int totalCount = s1 + s2 + s3;
        return tab[n] = totalCount;
    }

    public static int stairs(int n, int k, String res) {
        if(n == 0) {
            System.out.println(res);
            return 1;
        }

        if(n < 0)
            return 0;

        int totalCount = 0;
        for(int i = 1; i <= k; i++) {
            int count = stairs(n - i, k, res + " " + i);
            totalCount += count;
        }
        return totalCount;
    }

    public static int stairs(int n, String res) {
        if(n == 0) {
            System.out.println(res);
            return 1;
        }

        if(n < 0)
            return 0;

        int s1 = stairs(n - 1, res + " 1");
        int s2 = stairs(n - 2, res + " 2");
        int s3 = stairs(n - 3, res + " 3");
        int totalCount = s1 + s2 + s3;
        return totalCount;
    }
    /** https://www.youtube.com/watch?v=A6mOASLl2Dg&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=2 */


    public static int jump(int n, String res) {
        if(n < 0)
            return 0;

        if(n <= 1) {
            System.out.println(res + ", " + n);
            return n;
        }

        return jump(n - 1, res + ", 1") + jump(n - 2, res + ", 2");
    }

    public static int stairsOptimized(int[] cost) {
        int prev1 = cost[0];
        int prev2 = cost[0];
        int curr = 0;
        for(int i = 2; i < cost.length; i++) {
            curr = cost[i] + Math.min(prev1, prev2);
            prev1 = prev2;
            prev2 = curr;
        }

        return curr;
    }

    public static int stairs(int[] cost) {
        int[] cache = new int[cost.length + 1];
        cache[0] = cost[0];
        cache[1] = cost[1];

        for(int i = 2; i < cost.length; i++) {
            cache[i] = cost[i] + Math.min(cache[i - 1], cache[i - 2]);
        }
        return cache[cost.length - 1];
    }

    public static int jumps(int[] cost, int n, Integer[] cache) {
        if(n == 0)
            return cost[0];
        if(n == 1)
            return cost[1];

        if(cache[n] != null)
            return cache[n];

        int oneStep = jumps(cost, n - 1, cache);
        int twoStep = jumps(cost, n - 2, cache);
        return cache[n] = Math.min(oneStep, twoStep) + cost[n];
    }

    public static long jumps(int n, Long[] cache) {
        if(n == 0) {
            return 1l;
        }

        if(n < 0) {
            return 0;
        }

        if(cache[n] != null) {
            return cache[n];
        }

        return cache[n] = jumps(n - 1, cache) + jumps(n - 2, cache);
    }

    public static int jumps(int n, String res) {
        if(n == 0) {
            System.out.println(res);
            return 1;
        }

        if(n < 0) {
            return 0;
        }

        return jumps(n - 1, res + "1 ") + jumps(n - 2, res + "2 ");
    }
}
