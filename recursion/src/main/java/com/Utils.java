package com;

import java.util.Arrays;

public class Utils {

    public static <T> void  print(T[][] arr){
        for(int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static void  print(int[][] arr){
        for(int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static <T> void  print(T[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void  print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
}
