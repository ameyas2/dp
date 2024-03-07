package com;

public class LongestRepeatedSubsequence {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("");
        System.out.println(lrs("aabb", 4, 4, sb));
        System.out.println(sb);
    }

    public static int lrs(String a, int n, int m, StringBuilder sb) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n - 1) == a.charAt(m - 1) && n != m) {
            sb.append(a.charAt(n - 1));
            return 1 + lrs(a, n - 1, m - 1, sb);
        } else return Math.max(lrs(a, n, m - 1, sb), lrs(a, n - 1, m, sb));
    }
}
