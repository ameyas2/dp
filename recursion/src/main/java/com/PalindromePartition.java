package com;

public class PalindromePartition {
    public static void main(String[] args) {
        /*System.out.println(partition("level", 0, 4) - 1);
        System.out.println(partition("bababcbadcede", 0, 12) - 1);
        System.out.println(partition("abc", 0, 3) - 1);*/
        //palindromePartition("aabb", 0, "");
        System.out.println(palindromePartitioning2("aabb", 0, "", new Integer[5]));
    }

    public static int palindromePartitioning2(String str, int index, String res, Integer[] tab) {
        if(index >= str.length()) {
            System.out.println(res);
            return 1;
        }

        if(tab[index] != null)
            return tab[index];

        int count = 0;
        for(int i = index; i < str.length(); i++) {
            String sub = str.substring(index, i + 1);
            if(isPalindrome(sub, 0, sub.length() - 1))
                count = 1 + palindromePartitioning2(str, i + 1, res + " " + sub, tab);
        }
        return tab[index] = count;
    }

    public static int palindromePartitioning(String s, int index, String res, Integer[][] tab) {
        if(index >= s.length()) {
            System.out.println("Partitioned : " + res);
            return 1;
        }
        
        int count = 0;
        for(int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            System.out.println("Checking : " + sub);
            if(isPalindrome(sub, 0, sub.length() - 1)) {
                count = 1 + palindromePartitioning(s, i + 1, res + ", " + sub);
            }
        }

        return count;
    }

    public static int palindromePartitioning(String s, int index, String res) {
        if(index >= s.length()) {
            System.out.println("Partitioned : " + res);
            return 1;
        }

        int count = 0;
        for(int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            System.out.println("Checking : " + sub);
            if(isPalindrome(sub, 0, sub.length() - 1)) {
                 count = 1 + palindromePartitioning(s, i + 1, res + ", " + sub);
            }
        }

        return count;
    }

    public static void palindromePartition(String s, int index, String res) {
        if(index == s.length()) {
            System.out.println(res);
            return;
        }

        for(int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if(isPalindrome(sub, 0, sub.length())) {
                palindromePartition(s, i + 1, res + ", " + sub);
            }
        }
    }


    public static int partition(String s, int i, int j) {
        if(i >= j)
            return 0;

        int min = Integer.MAX_VALUE;

        for(int k = i; k < j; k++) {
            if(isPalindrome(s, i, k)) {
                int temp = partition(s, k + 1, j) + 1;
                min = Math.min(min, temp);
            }
        }
        return min;
    }

    public static boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

}
