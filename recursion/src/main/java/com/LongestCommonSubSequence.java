package com;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        /*String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        int n = s1.length();
        int m = s2.length();*/
        /*System.out.println(lcs(s1, s2, n, m));
        System.out.println(lcs(s1, s2, n, m, new Integer[n + 1][m + 1]));
        System.out.println(lcsTab(s1, s2, n, m));*/
        //System.out.println(lcs(s1, s2, n, m, "", new Integer[n + 1][m + 1]));
        //System.out.println(longestPalindromicSubsequence("agbcba"));
        /*System.out.println(vowelSubsequence("geeksforgeeks", "feroeeks", 13,8, new Integer[14][9]));
        System.out.println(vowelSubsequence("geeksforgeeks", "feroeeks"));*/
        //System.out.println(lics(new int[]{3, 10, 3, 11, 4, 5, 6, 7, 8, 12}, 10));

        //System.out.println(lcsSum(new int[]{ 9, 11, 2, 1, 6, 2, 7 }, new int[]{ 1, 2, 6, 9, 2, 3, 11, 7 }, 18, 7, 8, new Integer[10][10][20]));
        //System.out.println(lcSubString("ABCD", "ACDBDCD", 4, 7));

        //System.out.println(lcs(new int[]{3, 5, 1, 8}, new int[]{3, 3, 5, 3, 8}, 4, 5, new Integer[5][6]));
        System.out.println(minCostRearrange(new int[]{10, 5, 2, 4, 10, 5}, new int[]{5, 1, 2, 10, 4}, 3));
    }

    // https://www.geeksforgeeks.org/minimum-cost-required-to-rearrange-a-given-array-to-make-it-equal-to-another-given-array/
    public static int minCostRearrange(int[] a, int[] b, int c) {
        //int cost = minCostRearrange(a, b, a.length, b.length, new Integer[a.length + 1][b.length + 1]);
        int cost = minCostRearrange(a, b);
        return (Math.min(a.length, b.length) - cost) * c;
    }

    public static int minCostRearrange(int[] a, int[] b, int n, int m, Integer[][] dp) {
        if(n == 0 || m == 0)
            return 0;

        if(dp[n][m] != null)
            return dp[n][m];

        if(a[n - 1] == b[m - 1])
            return minCostRearrange(a, b, n - 1, m - 1, dp) + 1;

        int first = minCostRearrange(a, b, n - 1, m, dp);
        int sec = minCostRearrange(a, b, n, m - 1, dp);

        return dp[n][m] = Math.max(first, sec);
    }

    public static int minCostRearrange(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        int[][] tab = new int[n + 1][m + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(i == 0 || j == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(a[i - 1] == b[j - 1]) {
                    tab[i][j] = tab[i - 1][j - 1] + 1;
                } else {
                    tab[i][j] = Math.max(tab[i - 1][j], tab[i][j - 1]);
                }
            }
        }

        return tab[n][m];
    }


    public static int lcs(int[] arr1, int arr2[], int i, int j, Integer[][] tab) {
        if(i == 0 || j == 0) {
            return 0;
        }

        if(tab[i][j] != null)
            return tab[i][j];

        if(arr1[i - 1] == arr2[j - 1])
            return lcs(arr1, arr2, i - 1, j - 1, tab) + 1;

        return Math.max(lcs(arr1, arr2, i - 1, j, tab), lcs(arr1, arr2, i, j - 1, tab));
    }

    public static int lcSubString(String a, String b, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n - 1) == b.charAt(m - 1))
            return 1 + lcSubString(a, b, n - 1, m - 1);
        else
            return lcSubString(a, b, n - 1, m);

    }

    // https://www.geeksforgeeks.org/length-of-longest-common-subsequence-with-given-sum-k/

    public static int lcsSum(int[] a, int[] b, int k, int i, int j, Integer[][][] cache) {
        if(k == 0)
            return 0;

        if(k < 0) return -99999999;

        if(i == 0 || j == 0) {
            return k == 0 ? 0 : -99999999;
        }

        if(cache[i][j][k] != null)
            return cache[i][j][k];

        if(a[i - 1] == b[j - 1]) {
            int include = lcsSum(a, b, k - a[i - 1], i - 1, j - 1, cache);
            int exclude = lcsSum(a, b, k, i - 1, j - 1, cache);
            return cache[i][j][k] = Math.max(1 + include, exclude);
        } else {
            return cache[i][j][k] = Math.max(lcsSum(a, b, k, i - 1, j, cache), lcsSum(a, b, k, i, j - 1, cache));
        }
    }

    public static int lics(int[] arr, int n) {
        if(n == 0) return 0;

        if(arr[n - 1] > arr[n - 2] && arr[n - 1] - arr[n - 2] == 1) {
            int include = lics(arr, n - 1) + 1;
            int exclude = lics(arr, n - 1);
            return include + exclude;
        } else {
            return lics(arr, n - 1);
        }
    }



    // https://www.geeksforgeeks.org/length-of-longest-common-subsequence-containing-vowels/
    public static int vowelSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] tab = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1) && isVowel(s2.charAt(j - 1))) {
                    tab[i][j] = tab[i - 1][j - 1] + 1;
                } else {
                    tab[i][j] = Math.max(tab[i][j - 1], tab[i - 1][j]);
                }
            }
        }

        return tab[n][m];
    }

    public static int vowelSubsequence(String s1, String s2, int n, int m, Integer[][] cache) {
        if(n == 0 || m == 0) return 0;

        if(cache[n][m] != null) return cache[n][m];

        if(s1.charAt(n - 1) == s2.charAt(m - 1) && isVowel(s2.charAt(m - 1))) {
            return vowelSubsequence(s1, s2, n - 1, m - 1,cache) + 1;
        }

        return Math.max(vowelSubsequence(s1, s2, n - 1, m, cache), vowelSubsequence(s1, s2, n, m - 1,cache));
    }

    public static boolean isVowel(char ch) {
        return ch == 'u' || ch == 'o' || ch == 'i' || ch == 'e' || ch == 'a';
    }

    public static int longestPalindromicSubsequence(String s) {
        return lcs(s, reverse(s), s.length(), s.length(), "");
    }

    public static String reverse(String s) {
        StringBuilder builder = new StringBuilder("");
        for(int i = s.length() - 1; i >= 0; i--){
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }

    public static int lcs(String s, String b, int n, int m, String res, Integer[][] cache) {
        if(n == 0 || m == 0) {
            if(n == 0 && m == 0)
                System.out.println(res);
            return 0;
        }

        if(cache[n][m] != null) {
            return cache[n][m];
        }

        if(s.charAt(n - 1) == b.charAt(m - 1)) {
            return cache[n][m] = 1 + lcs(s, b, n - 1, m - 1, res + "," + s.charAt(n - 1));
        } else {
            int left = lcs(s, b, n - 1, m, res);
            int right = lcs(s, b, n, m - 1, res);
            return cache[n][m] = Math.max(left, right);
        }
    }

    public static int lcs(String s, String b, int n, int m, String res) {
        if(n == 0 || m == 0)
            return 0;

        if(s.charAt(n - 1) == b.charAt(m - 1)) {
            System.out.println(res + "," + s.charAt(n - 1));
            return 1 + lcs(s, b, n - 1, m - 1, res + "," + s.charAt(n - 1));
        } else {
            int left = lcs(s, b, n - 1, m, res);
            int right = lcs(s, b, n, m - 1, res);
            return Math.max(left, right);
        }
    }

    public static int lcsTab(String a, String b, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= m; col++) {
                if(row == 0 || col == 0) {
                    dp[row][col] = 0;
                    continue;
                }
                if(a.charAt(row - 1) == b.charAt(col - 1)) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                } else
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
            }
        }

        return dp[n][m];
    }

    public static int lcs(String a, String b, int n, int m, Integer[][] dp) {
        if(n == 0 || m == 0)
            return 0;

        if(dp[n][m] != null)
            return dp[n][m];

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            return dp[n][m] = 1 + lcs(a, b, n - 1, m - 1);
        }
        else return dp[n][m] = Math.max(lcs(a, b, n - 1, m), lcs(a, b, n, m - 1));
    }

    public static int lcs(String a, String b, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            return 1 + lcs(a, b, n - 1, m - 1);
        }
        else return Math.max(lcs(a, b, n - 1, m), lcs(a, b, n, m - 1));
    }
}
