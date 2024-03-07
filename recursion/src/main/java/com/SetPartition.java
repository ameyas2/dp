package com;

public class SetPartition {
    public static void main(String[] args) {
        System.out.println(setPartition(new int[]{1,2,3,4}, 0, "", ""));
    }

    public static int setPartition(int[] arr, int n, String inc, String exc) {
        if(n == arr.length) {
            System.out.println(inc + " : " + exc);
            return 1;
        }

        int include = setPartition(arr, n + 1, inc + " " + arr[n], exc);
        int exclude = setPartition(arr, n + 1, inc, exc + " " + arr[n]);
        return include + exclude;
    }
}
