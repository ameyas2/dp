package com;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decode("2263", 4));

        System.out.println(decode("231011", 6));    }

    public static int decode(String s, int n) {
        if(n == 0)
            return 1;

        if(n < 1)
            return 0;


        int num = Integer.parseInt(String.valueOf(s.charAt(n - 1)));
        int one = num == 0 ? 0 : decode(s, n - 1);
        int two = 0;
        if(n > 1) {
            String digits = s.substring(n - 2, n);
            num = Integer.parseInt(digits);
            if (num <= 26 && n > 0 && digits.charAt(0) != '0')
                two = decode(s, n - 2);

        }
        return one + two;
    }
}
