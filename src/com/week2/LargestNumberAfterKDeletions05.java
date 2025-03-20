package com.week2;

import java.util.Scanner;

public class LargestNumberAfterKDeletions05 {
    public static String findLargestNumber(String n, int k) {
        int len = n.length();
        if (k >= len) return "0";

        StringBuilder result = new StringBuilder();
        int remaining = len - k;  // digits to keep
        int start = 0;

        while (remaining > 0) {
            // Find largest digit in the valid range
            int maxIndex = start;
            for (int i = start; i <= len - remaining; i++) {
                if (n.charAt(i) > n.charAt(maxIndex)) {
                    maxIndex = i;
                }
            }

            // Add the largest digit found
            result.append(n.charAt(maxIndex));
            start = maxIndex + 1;
            remaining--;
        }

        // Remove leading zeros if any
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.isEmpty() ? "0" : result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.next();
        int k = scanner.nextInt();

        System.out.println(findLargestNumber(n, k));
        scanner.close();
    }
}