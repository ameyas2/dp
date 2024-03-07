package com;

public class PaintFence {
    public static void main(String[] args) {
        System.out.println(paint(5, 3));
    }

    public static int paint(int n, int k) {
        if(n <= 0)
            return 0;
        if(n == 1)
            return k;

        int first = paint(n - 1, k) * (k - 1);
        int sec = (k - 1) * paint(n - 2, k);
        return first + sec;
    }
}
