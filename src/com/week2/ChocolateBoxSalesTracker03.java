package com.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ChocolateBoxSalesTracker03 {
    public static List<Long> solve(int n, long[] c) {
        Stack<Long> stack = new Stack<>();
        List<Long> sales = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (c[i] == 0) {
                // Sell box from top of stack
                if (!stack.isEmpty()) {
                    sales.add(stack.pop());
                }
            } else {
                // Add new box to stack
                stack.push(c[i]);
            }
        }

        return sales;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] c = new long[n];

        for (int i = 0; i < n; i++) {
            c[i] = scanner.nextLong();
        }

        List<Long> result = solve(n, c);
        for (Long chocolates : result) {
            System.out.print(chocolates + " ");
        }

        scanner.close();
    }
}