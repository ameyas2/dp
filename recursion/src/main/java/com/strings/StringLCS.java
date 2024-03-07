package com.strings;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

public class StringLCS {
    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAB";
        /*String s1 = RandomStringUtils.randomAlphabetic(1000);
        String s2 = RandomStringUtils.randomAlphabetic(1000);*/
        /*System.out.println(s1);
        System.out.println(s2);*/
        //System.out.println(lcs(s1, s2, s1.length(), s2.length(), ""));
        //System.out.println(lcSubstring(s1, s2, s1.length(), s2.length(), new Integer[s1.length() + 1][s2.length() + 1]));
        //System.out.println(lcsStr(s1, s2));
        System.out.println(palindromicLCS("agbcba"));
    }

    // https://www.youtube.com/watch?v=hbTaCmQGqLg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=29
    public static int repeatingSS(String a, String b, int la, int lb, Integer[][] tab) {
        if(la == a.length() || lb == b.length())
            return 0;

        if(tab[la][lb] != null)
            return tab[la][lb];

        if(a.charAt(la - 1) == b.charAt(lb - 2) && la != lb)
            return tab[la][lb] = 1 + repeatingSS(a, b, la - 1, lb - 1, tab);
        else {
            int al = repeatingSS(a, b, la, lb - 1, tab);
            int bl = repeatingSS(a, b, la - 1, lb, tab);
            return tab[la][lb] = Math.max(al, bl);
        }
    }

    // https://www.youtube.com/watch?v=wuOOOATz_IA&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=26
    public static String palindromicLCS(String a) {
        return lcsStr(a, StringUtils.reverse(a));
    }

    // https://www.youtube.com/watch?v=x5hQvnUcjiM&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=23

    public static String lcsStr(String a, String b) {
        Integer[][] tab = lcsMat(a, b);
        List<String> str = new ArrayList<>();

        for(int row = tab.length - 1, col = tab[0].length - 1; row > 0 && col > 0;) {
            if(tab[row][col] == tab[row - 1][col])
                row--;
            else if(tab[row][col] == tab[row][col - 1])
                col--;
            else {
                str.add(Character.toString(a.charAt(row - 1)));
                row--;
                col--;
            }
        }

        return str.toString();
    }

    public static Integer[][] lcsMat(String a, String b) {
        int la = a.length();
        int lb = b.length();
        Integer[][] tab = new Integer[la + 1][lb + 1];

        for(int row = 0; row <= la; row++) {
            for(int col = 0; col <= la; col++) {
                if(row == 0 || col == 0) {
                    tab[row][col] = 0;
                    continue;
                }

                if(a.charAt(row - 1) == b.charAt(col - 1)) {
                    tab[row][col] = tab[row - 1][col - 1] + 1;
                } else {
                    tab[row][col] = Math.max(tab[row - 1][col], tab[row][col - 1]);
                }
            }
        }

        return tab;
    }

    // https://www.youtube.com/watch?v=HrybPYpOvz0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=22
    public static int lcSubstring(String a, String b, int la, int lb, Integer[][] cache) {
        if(la == 0 || lb == 0)
            return 0;

        if(cache[la][lb] != null)
            return cache[la][lb];

        if(a.charAt(la - 1) == b.charAt(lb - 1)) {
            return cache[la][lb] = 1 + lcSubstring(a, b, la - 1, lb - 1, cache);
        } else {
            return 0;
        }
    }

    // https://www.youtube.com/watch?v=hR3s9rGlMTU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=21

    public static int lcs(String a, String b) {
        int la = a.length();
        int lb = b.length();
        Integer[][] tab = new Integer[la + 1][lb + 1];

        for(int row = 0; row <= la; row++) {
            for(int col = 0; col <= la; col++) {
                if(row == 0 || col == 0) {
                    tab[row][col] = 0;
                    continue;
                }

                if(a.charAt(row - 1) == b.charAt(col - 1)) {
                    tab[row][col] = tab[row - 1][col - 1] + 1;
                } else {
                    tab[row][col] = Math.max(tab[row - 1][col], tab[row][col - 1]);
                }
            }
        }

        return tab[la][lb];
    }

    // https://www.youtube.com/watch?v=g_hIx4yn9zg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=20

    public static int lcs(String a, String b, int la, int lb, Integer[][] cache) {
        if(la == 0 || lb == 0) {
            return 0;
        }

        if(cache[la][lb] != null)
            return cache[la][lb];

        if(a.charAt(la - 1) == b.charAt(lb - 1)) {
            return cache[la][lb] = 1 + lcs(a, b, la - 1, lb - 1, cache);
        } else {
            int al = lcs(a, b, la, lb - 1, cache);
            int bl = lcs(a, b, la - 1, lb, cache);
            return cache[la][lb] = Math.max(al, bl);
        }
    }

    // https://www.youtube.com/watch?v=4Urd0a0BNng&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=19

    public static int lcs(String a, String b, int la, int lb, String res) {
        if(la == 0 || lb == 0) {
            System.out.println(res);
            return 0;
        }

        if(a.charAt(la - 1) == b.charAt(lb - 1)) {
            return 1 + lcs(a, b, la - 1, lb - 1, res + ", " + a.charAt(la - 1));
        } else {
            int ac = lcs(a, b, la - 1, lb, res);
            int bc = lcs(a, b, la, lb - 1, res);
            return Math.max(ac, bc);
        }
    }
}
