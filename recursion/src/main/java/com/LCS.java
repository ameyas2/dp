package com;

public class LCS {
    public static void main(String[] args) {
        System.out.println(lcs("abcdgh", "abedghr", 6, 7));
        System.out.println(lcs("abcdlh", "abedghr", 6, 7));

        System.out.println(lcsMem("abcdgh", "abedghr", 6, 7, new Integer[7][8]));
        System.out.println(lcsMem("abcdlh", "abedghr", 6, 7, new Integer[7][8]));

        System.out.println(lcsTab("abcdgh", "abedghr", 6, 7));
        System.out.println(lcsTab("abcdlh", "abedghr", 6, 7));
    }

    public static int lcsTab(String a, String b, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }

    public static int lcsMem(String a, String b, int n, int m, Integer[][] dp) {
        if(n == 0 || m == 0)
            return 0;

        if(dp[n][m] != null)
            return dp[n][m];

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            dp[n][m] = 1 + lcs(a, b, n - 1, m - 1);
        } else {
            dp[n][m] = Math.max(lcs(a, b, n - 1, m), lcs(a, b, n, m - 1));
        }

        return dp[n][m];
    }

    public static int lcs(String a, String b, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            return 1 + lcs(a, b, n - 1, m - 1);
        } else {
            return Math.max(lcs(a, b, n - 1, m), lcs(a, b, n, m - 1));
        }
    }
}
