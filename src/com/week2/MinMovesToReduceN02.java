package com.week2;

import java.util.Scanner;

public class MinMovesToReduceN02 {
    public static int getMinMoves(int n) {
        if (n <= 1) return n;

        // Option 1: Decrease by 1
        int moves = 1 + getMinMoves(n - 1);

        // Option 2: Try factorization
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int max = Math.max(i, n / i);
                moves = Math.min(moves, 1 + getMinMoves(max));
            }
        }

        return moves;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();

        for (int i = 0; i < q; i++) {
            int n = scanner.nextInt();
            System.out.println(getMinMoves(n));
        }

        scanner.close();
    }
}