package com.week4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNoteCheck_04 {

    public static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> wordCount = new HashMap<>();

        // Count the occurrences of each word in the magazine
        for (String word : magazine) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Check if we can form the note using the words in the magazine
        for (String word : note) {
            if (!wordCount.containsKey(word) || wordCount.get(word) == 0) {
                System.out.println("No");
                return;
            }
            wordCount.put(word, wordCount.get(word) - 1);
        }

        System.out.println("Yes");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of words in the magazine and the note
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        // Read the words in the magazine
        String[] magazine = scanner.nextLine().split(" ");

        // Read the words in the note
        String[] note = scanner.nextLine().split(" ");

        // Check if the note can be formed using the magazine
        checkMagazine(magazine, note);

        scanner.close();
    }
}
