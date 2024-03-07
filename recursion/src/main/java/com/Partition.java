package com;

public class Partition {
    public static void main(String[] args) {
        partition(0, 3);
    }

    public static void partition2(int index, int n) {
        if(index == n)
            return;

        for(int i = index; i < n; i++) {
            System.out.println("i " + i + " index " + index);
            partition2(index + 1, n);
        }
    }

    public static void partition(int index, int n) {
        if(index == n)
            return;

        for(int i = 0; i < n; i++) {
            System.out.println("i " + i + " index " + index);
            partition(index + 1, n);
        }
    }
}
