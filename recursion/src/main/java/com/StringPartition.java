package com;

import java.util.HashSet;
import java.util.Set;

public class StringPartition {
    public static void main(String[] args) {
        System.out.println(stringPart("acbadbaada"));
    }

    public static int stringPart(String s) {
        Set<Character> set = new HashSet<>();
        int[] prefix = new int[s.length()];
        int[] suffix = new int[s.length()];
        int goodSplit = 0;

        set.add(s.charAt(0));
        prefix[0] = 1;
        for(int i = 1; i < s.length(); i++) {
            if(set.contains(s.charAt(i))) {
                prefix[i] = prefix[i - 1];
            } else {
                prefix[i] = prefix[i - 1] + 1;
                set.add(s.charAt(i));
            }
        }

        set.clear();
        set.add(s.charAt(s.length() - 1));
        suffix[s.length() - 1] = 1;
        for(int i = s.length() - 2; i >= 0; i--) {
            if(set.contains(s.charAt(i))) {
                suffix[i] = suffix[i + 1];
            } else {
                suffix[i] = suffix[i + 1] + 1;
                set.add(s.charAt(i));
            }
        }

        for(int i = 0; i < suffix.length - 1; i++) {
            if(prefix[i] == suffix[i + 1])
                goodSplit++;
        }

        return goodSplit;
    }

    public static void stringPart(String s, int i, int j) {
        if(i > j) return;
        for(int k = i; k < j; k++) {
            System.out.println(s.substring(i, k + 1) + " " + s.substring(k + 1, j));
            stringPart(s, i, k);
            stringPart(s, k + 1, j);
        }
    }
}
