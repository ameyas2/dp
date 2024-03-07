package com;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(maxSubstring("abcbab", 0, 5, new Integer[7][7]));
    }


    // https://www.geeksforgeeks.org/length-of-longest-palindrome-substring/

    public static int maxSubstring(String str) { // not working
        int n = str.length();
        int[][] tab = new int[n + 1][n + 1];

        int l = 0, r = n;
        for(; l <= r; l++) {
            for(; r >= l; r--) {
                if(l == r) {
                    tab[l][r] = 1;
                }

                if(str.charAt(l) == str.charAt(r))
                    tab[l][r] = 2 + tab[l - 1][r + 1];
                else {
                    tab[l][r] = Math.max(tab[l - 1][r], tab[l][r + 1]);
                }
            }
        }

        return tab[n][n];
    }

    public static int maxSubstring(String str, int l, int r, Integer[][] tab) {
        if(l > r) {
            return 0;
        }

        if(l == r) {
            return 1;
        }

        if(tab[l][r] != null)
            return tab[l][r];

        if(str.charAt(l) == str.charAt(r))
            return tab[l][r] = 2 + maxSubstring(str, l + 1, r - 1, tab);

        return tab[l][r] = Math.max(maxSubstring(str, l + 1, r, tab), maxSubstring(str, l, r - 1, tab));
    }
}
