package com;

public class StairsNew {
    public static void main(String[] args) {
        System.out.println(stairsJump(3));
        System.out.println(stairsJump(10, new Integer[11]));
    }

    public static int stairsJump(int stairs, Integer[] tab) {
        if(stairs == 1 || stairs == 0)
            return 1;

        if(tab[stairs] != null) {
            return tab[stairs];
        }

        return tab[stairs] = stairsJump(stairs - 1) + stairsJump(stairs - 2);
    }
    public static int stairsJump(int stairs) {
        if(stairs == 1 || stairs == 0)
            return 1;

        int one = stairsJump(stairs - 1);
        int two = stairsJump(stairs - 2);
        return one + two;
    }
}
