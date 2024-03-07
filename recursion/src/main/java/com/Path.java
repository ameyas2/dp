package com;

import java.util.Arrays;

public class Path {
    public static void main(String[] args) {
        //System.out.println(minSumPath(new int[][]{{1, 2, 3}, { 4, 5, 6}, { 7, 8, 9}}));
        /*System.out.println(path(new int[][]{ { 2, 3, 1 },
                { 6, 1, 9 },
                { 8, 2, 3 } }, 3, 3, 7, ""));*/
        //System.out.println(maxTrianglePathSum(new int[][]{{ 1 }, { 2, 1 }, { 3, 3, 2 }}));
        /*System.out.println(maxAvgSum(new int[][]{{1, 2, 3},
                {6, 5, 4},
                {7, 3, 9}}, 3, 3, ""));*/

       /* System.out.println(pathCount(3,3, ""));
        System.out.println(pathCount(3,3, new Integer[4][4]));
        System.out.println(pathCount(3,3));*/

        /*System.out.println(path2(new int[][]{ { 1, 1, 1, 1 },
                { -1, 1, -1, -1 },
                { -1, 1, -1, 1 },
                { -1, -1, 1, 1 }
        }, 4, 4, 3, "", new Boolean[5][5][4]));*/

        //System.out.println(maxSumPath(new int[][]{{2, 10, 8}, {8, 8, 8}, {0, 1, 0}}, 2, 2, 2, new Integer[3][3][3]));

        /*System.out.println(pathX(new int[][]{ { 1, 1, 1, 1 },
                { -1, 1, -1, -1 },
                { -1, 1, -1, 1 },
                { -1, -1, 1, 1 }
        }, 3, 3, 3, "", new Boolean[10][10][10]));*/

        /*int[][] x = { { 1, 2, 1 }, { 3, 4, 5 }, { 1, 1, 1 } };
        int[][] y = { { 2, 3, 4 }, { 1, 3, 1 }, { 2, 1, 1 } };
        int[][] z = { { 1, 8, 9 }, { 1, 4, 5 }, { 6, 5, 4 } };
        System.out.println(pathCost(x,y,z, 2,2, new Integer[3][3]));*/

        //System.out.println(minCost(new int[]{5, 1, 2, 10, 100}, 1, ""));

        //System.out.println(pathProduct(new int[][]{{1, -2, 1}, {1, -2, 1}, {3, -4, 1}}, 2, 2, ""));
        int[][] tab = new int[][]{{3, 2, 1},
            {6, 5, 4},
            {7, 8, 9}};
        maxPathSum(tab, 2, 2, tab);
        System.out.println();
    }

    public static int maxPathSum(int[][] mat, int i, int j, int[][] tab) {
        if(i == 0 && j == 0)
            return mat[0][0];

        if(i == 0) {
            System.out.println("values " + i + " " + j);
            System.out.println("adding " + i + " " + (j - 1) + " " + mat[i][j - 1]);
            return mat[i][j - 1];
        }

        if(j == 0) {
            System.out.println("values " + i + " " + j);
            System.out.println("adding " + (i - 1) + " " + j + " " + mat[i - 1][j]);
            return mat[i - 1][j];
        }

        System.out.println("adding " + i + " " + (j)  + " " + mat[i][j]);
        return mat[i][j] = mat[i][j] + Math.max(maxPathSum(mat, i - 1, j, tab), maxPathSum(mat, i, j - 1, tab));
    }

    // https://www.geeksforgeeks.org/maximum-non-negative-product-of-a-path-from-top-left-to-bottom-right-of-given-matrix/ this is not a correct solution
    public static int pathProduct(int[][] mat, int row, int col, String res) {
        if(row == 0 && col == 0) {
            System.out.println(res + " " + mat[row][col]);
            return mat[row][col];
        }

        if(row < 0 || col < 0)
            return 1;

        int up = pathProduct(mat, row - 1, col, res + " " + mat[row][col])  * mat[row][col];
        int left = pathProduct(mat, row, col - 1, res + " " + mat[row][col])  * mat[row][col];

        return Math.max(up, left);
    }

    public static int minCost(int[] arr, int n, String res) {
        if(n > arr.length || n < 0) {
            System.out.println(res);
            return 99999999;
        }


        int forward = minCost(arr, n + 2, res + " " + arr[n - 1]);
        int backward = minCost(arr, n - 1, res + " " + arr[n - 1]);

        return Math.min(forward, backward) + arr[n - 1];
    }

    // https://www.geeksforgeeks.org/minimize-cost-to-reach-bottom-right-from-top-left-corner-of-matrix-with-given-separate-cost-for-each-move/

    public static int pathCost(int[][] x, int[][] y, int[][] z, int i, int j, Integer[][] cache) {
        if(i == 0 && j == 0) return 0;

        if(cache[i][j] != null)
            return cache[i][j];

        int ans = Integer.MAX_VALUE;

        if(j > 0) {
            ans = Math.min(ans, pathCost(x,y,z, i, j - 1, cache) + x[i][j]);
        }

        if(i > 0) {
            ans = Math.min(ans, pathCost(x,y,z, i - 1, j, cache) + y[i][j]);
        }

        if(i > 0 && j > 0) {
            ans = Math.min(ans, pathCost(x,y,z, i - 1, j - 1, cache) + z[i][j]);
        }

        return cache[i][j] = ans;
    }


    // https://www.geeksforgeeks.org/find-if-path-from-top-left-to-bottom-right-with-sum-x-exists-in-given-matrix/
    public static boolean pathX(int[][] mat, int x, int row, int col, String res, Boolean[][][] cache) {
        if(row == 0 && col == 0) {
            int num = x - mat[row][col];
            System.out.println(res + " " + num);
            return num == 0;
        }

        if(row < 0 || col < 0 || x < 0)
            return false;

        if(cache[row][col][x] != null)
            return cache[row][col][x];

        boolean up = pathX(mat, x - mat[row][col], row - 1, col, res + " UP ", cache);
        boolean left = pathX(mat, x - mat[row][col], row, col - 1, res + " LEFT ", cache);

        return cache[row][col][x] = up || left;
    }


    // https://www.geeksforgeeks.org/maximum-path-sum-when-at-most-k-elements-can-be-picked-from-a-row/

    public static int maxSumPath(int[][] mat, int row, int col, int k, Integer[][][] cache) {
        if(row == 0 && col == 0) {
            //System.out.println(res);
            return mat[row][col];
        }

        if(row < 0 || col < 0)
            return 0;

        if(cache[row][col][k] != null)
            return cache[row][col][k];

        int up = maxSumPath(mat, row - 1, col, k, cache);

        int left = k >= 1 ? maxSumPath(mat, row, col - 1, k - 1, cache) : 0;

        return cache[row][col][k] = Math.max(up, left) + mat[row][col];
    }

    // https://www.geeksforgeeks.org/find-if-path-from-top-left-to-bottom-right-with-sum-x-exists-in-given-matrix/
    public static boolean path2(int[][] mat, int row, int col, int x, String res, Boolean[][][] cache) {
        if(row == 1 && col == 1) {
            System.out.println(res + " " + (x - mat[row - 1][col - 1]));
            return x - mat[row - 1][col - 1] == 0 ? true : false;
        }

        if(x < 0 || row < 1 || col < 1)
            return false;

        //if(cache[row][col][x] != null) return cache[row][col][x];

        boolean up = path2(mat, row - 1, col, x - mat[row - 1][col - 1], res + " " + row + "," + col, cache);
        boolean left = path2(mat, row, col - 1, x - mat[row - 1][col - 1], res + " " + row + "," + col, cache);

        return  up || left;
    }

    // https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/

    public static int pathCount(int row, int col) {
        int[][] tab = new int[row + 1][col + 1];

        for(int r = 1; r <= row; r++) {
            for(int c = 1; c <= col; c++) {
                if(r == 1 || c == 1) {
                    tab[r][c] = 1;
                    continue;
                }

                tab[r][c] = tab[r - 1][c] + tab[r][c - 1];
            }
        }


        return tab[row][col];
    }

    public static int pathCount(int row, int col, Integer[][] cache) {
        if(row == 1 && col == 1) {
            return 1;
        }

        if(row <= 0 || col <= 0)
            return 0;

        if(cache[row][col] != null)
            return cache[row][col];

        int up = pathCount(row - 1, col, cache);
        int left = pathCount(row, col - 1, cache);

        return cache[row][col] = up + left;
    }

    public static int pathCount(int row, int col, String res) {
        if(row == 1 && col == 1) {
            System.out.println(res);
            return 1;
        }

        if(row <= 0 || col <= 0)
            return 0;

        int up = pathCount(row - 1, col, res + " up");
        int left = pathCount(row, col - 1, res + " left");

        return up + left;
    }

    public static int maxAvgSum(int[][] arr, int row, int col, String res) {
        if(row == 0 && col == 0) {
            System.out.println(res + " " + arr[row][col]);
            return arr[row][col];
        }

        if(row == 0 || col == 0) {
            return 0;
        }

        int up = maxAvgSum(arr, row - 1, col, res + " " + arr[row - 1][col - 1]);
        int left = maxAvgSum(arr, row, col - 1,  res + " " + arr[row - 1][col - 1]);

        return Math.max(up, left) + arr[row - 1][col - 1];
    }

    public static int maxTrianglePathSum(int[][] arr) {
        int n = arr.length;
        int m = arr[n - 1].length;


        int[][] tab = new int[n][m];

        for(int i = 0; i < m; i++)
            tab[n - 1][i] = arr[n - 1][i];

        return tab[0][0];
    }


    public static int path(int[][] arr, int row, int col, int energy, String res) {
        if(row == 1 && col == 1) {
            System.out.println(res);
            return 0;
        }

        if(energy <= 0)
            return -1;

        if(row < 0 || col < 0)
            return -1;

        int up = path(arr, row - 1, col, energy - arr[row - 1][col - 1], res + " " + arr[row - 1][col - 1]);
        int left = path(arr, row, col - 1, energy - arr[row - 1][col - 1], res + " " + arr[row - 1][col - 1]);
        int diag = path(arr, row - 1, col - 1, energy - arr[row - 1][col - 1], res + " " + arr[row - 1][col - 1]);

        return Math.max(up, Math.max(left, diag)) + arr[row - 1][col - 1];

    }

    // https://www.geeksforgeeks.org/minimum-sum-falling-path-in-a-nxn-grid/
    public static int minSumPath(int[][] mat) {
        int n = mat.length;
        int m = mat.length;
        int[][] tab = new int[n][m];

        for(int i = 0; i < m; i++)
            tab[n - 1][i] = mat[n - 1][i];

        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j < m; j++) {
                int dl = j > 0 ? tab[i + 1][j - 1] : Integer.MAX_VALUE;
                int d = tab[i + 1][j];
                int dr = j < m - 1 ? tab[i + 1][j + 1] : Integer.MAX_VALUE;

                tab[i][j] = Math.min(dl, Math.min(d, dr)) + mat[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++)
            min = Math.min(min, tab[0][i]);
        return min;
    }
}
