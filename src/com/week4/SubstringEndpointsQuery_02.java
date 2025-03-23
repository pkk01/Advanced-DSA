package com.week4;

import java.util.Scanner;

public class SubstringEndpointsQuery_02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the length of the string
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        // Read the string
        String s = scanner.nextLine();

        // Read the number of queries
        int q = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        // Preprocess the string to store positions of each character
        int[][] positions = new int[26][n + 1];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            positions[c - 'a'][i + 1] = 1;
        }

        // Compute prefix sums for each character
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++) {
                positions[i][j] += positions[i][j - 1];
            }
        }

        // Process each query
        for (int i = 0; i < q; i++) {
            String query = scanner.nextLine();
            char x = query.charAt(0);
            char y = query.charAt(2);

            int count = 0;
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == x) {
                    count += positions[y - 'a'][n] - positions[y - 'a'][j + 1];
                } else if (s.charAt(j) == y) {
                    count += positions[x - 'a'][n] - positions[x - 'a'][j + 1];
                }
            }

            System.out.println(count);
        }

        scanner.close();
    }
}
