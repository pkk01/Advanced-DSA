package com.week1;

public class ArrayReverse01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] reversedArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversedArr[i] = arr[arr.length - 1 - i];
        }
        for (int j : reversedArr) {
            System.out.println(j);
        }
    }
}
