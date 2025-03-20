package com.week2;

import java.util.Scanner;
import java.util.Stack;

public class MaximizeTopElementOfStack01 {
    public static long maximizeTop(int n, long k, long[] arr) {
        // If k is odd and n=1, we can't get any element at top
        if (n == 1 && k % 2 == 1) {
            return -1;
        }

        // If k >= 2*n, we can rearrange elements optimally
        if (k >= 2 * n) {
            long max = arr[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(max, arr[i]);
            }
            return max;
        }

        // If k >= n, we can get the maximum element in first n elements
        if (k >= n) {
            long max = arr[0];
            for (int i = 1; i < k && i < n; i++) {
                max = Math.max(max, arr[i]);
            }
            return max;
        }

        // If k < n, we can only access first k+1 elements
        long max = arr[0];
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long k = scanner.nextLong();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }

        System.out.println(maximizeTop(n, k, arr));
        scanner.close();
    }
}