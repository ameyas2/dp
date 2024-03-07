package com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grids {
    public static void main(String[] args) {
        int n = 100;
        //System.out.println(countPaths(n, 0, 0, ""));
        //System.out.println(countPaths(n, 0, 0, "", new Long[n + 1][n + 1]));
        //System.out.println(countPaths(3));
        /*System.out.println(minCostPath(new int[][]{{2,8,4,1,6,4,2},
                {6,0,9,5,3,8,5},
                {1,4,3,4,0,6,5},
                {6,4,7,2,4,6,1},
                {1,0,3,7,1,2,7},
                {1,5,3,2,3,0,9},
                {2,2,5,1,9,8,2 }}, new Integer[8][8], 0, 0, new ArrayList<>(),0));*/
        System.out.println(gridPathCount(5,5, new Integer[6][6]));
        System.out.println(gridPathCount(3,3, new Integer[6][6]));
        System.out.println(gridPathCount(5,5));
        System.out.println(gridPathCount(3,3));


    }

    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-traverse-a-matrix/

    public static int gridPathCount(int n, int m) {
        int[][] tab = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(i == 1 || j ==1) {
                    tab[i][j] = 1;
                } else {
                    tab[i][j] = tab[i - 1][j] + tab[i][j - 1];
                }
            }
        }

        return tab[n][m];
    }

    public static int gridPathCount(int n, int m, Integer[][] cache) {
        if(n == 1 & m == 1) return 1;

        if(n < 1 || m < 1) return 0;

        if(cache[n][m] != null) return cache[n][m];

        return cache[n][m] = gridPathCount(n - 1, m, cache) + gridPathCount(n, m - 1, cache);
    }

    /** https://www.youtube.com/watch?v=BzTIOsC0xWM&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=7  */
    public static int minCostPath(int[][] grid, Integer[][] tab, int row, int col, List<String> res, int sum) {
        int n = grid.length;
        int m = grid[0].length;
        if(row == n - 1 & col == m - 1) {
            res.add("[" + row + " " + col +"]" + "[" + grid[row][col] + "] " + (sum + grid[row][col]));
            System.out.println(res);
            return grid[row][col];
        }

        if(row >= n || col >= m)
            return Integer.MAX_VALUE;

        if(tab[row][col] != null) {
            res.add("[" + row + " " + col +"]" + "[" + grid[row][col] + "] ");
            return tab[row][col];
        }

        res.add("[" + row + " " + col +"]" + "[" + grid[row][col] + "] ");
        int down = minCostPath(grid, tab,row + 1, col, res, sum + grid[row][col]);
        res.remove(res.size() - 1);
        int right = minCostPath(grid, tab, row, col + 1, res, sum + grid[row][col]);
        return tab[row][col] = grid[row][col] + Math.min(down, right);

    }

    public static int minCostPath(int[][] grid, int row, int col, String res, int sum) {
        int n = grid.length;
        int m = grid[0].length;
        if(row == n - 1 & col == m - 1) {
            System.out.println(res + "[" + grid[row][col] + "]" + (sum + grid[row][col]));
            return grid[row][col];
        }

        if(row >= n || col >= m)
            return Integer.MAX_VALUE;

        int down = minCostPath(grid, row + 1, col, res + "D[" + grid[row][col] + "]", sum + grid[row][col]);
        int right = minCostPath(grid, row, col + 1, res + "R[" + grid[row][col] + "]", sum + grid[row][col]);
        return grid[row][col] + Math.min(down, right);
    }

    public static Long countPaths(int n) {
        long[][] tab = new long[n][n];
        tab[0][0] = 1l;
        tab[0][1] = 1l;
        tab[1][0] = 1l;

        long up = 1;
        long left = 1;
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++) {
                if(row == 0 && col == 0)
                    continue;
                if(row - 1 >= 0)
                    up = tab[row - 1][col];
                if(col - 1 >= 0)
                    left = tab[row][col - 1];

                tab[row][col] =  up + left;
            }
        }

        return tab[n - 1][n - 1];
    }

    public static Long countPaths(int n, int row, int col, String directions, Long[][] tab) {
        if(row == n - 1 && col == n - 1) {
            //System.out.println(directions);
            return 1l;
        }

        if(row >= n || col >= n)
            return 0l;

        if(tab[row][col] != null)
            return tab[row][col];

        Long down = countPaths(n, row + 1, col, directions + "D", tab);
        Long right = countPaths(n, row, col + 1, directions + "R", tab);

        return tab[row][col] = down + right;
    }

    public static int countPaths(int n, int row, int col, String directions) {
        if(row == n - 1 && col == n - 1) {
            //System.out.println(directions);
            return 1;
        }

        if(row >= n || col >= n)
            return 0;

        int down = countPaths(n, row + 1, col, directions + "D");
        int right = countPaths(n, row, col + 1, directions + "R");
        return down + right;
    }
}
