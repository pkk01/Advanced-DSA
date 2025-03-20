package com.week1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringQueryCounter02 {
    public static int[] countQueryStrings(List<String> inputStrings, List<String> queryStrings) {
        Map<String, Integer> queryCountMap = new HashMap<>();
        for (String inputString : inputStrings) {
            for (String queryString : queryStrings) {
                int count = queryCountMap.getOrDefault(queryString, 0);
                count += countSubstring(inputString, queryString);
                queryCountMap.put(queryString, count);
            }
        }

        int[] result = new int[queryStrings.size()];
        for (int i = 0; i < queryStrings.size(); i++) {
            result[i] = queryCountMap.get(queryStrings.get(i));
        }
        return result;
    }

    private static int countSubstring(String inputString, String queryString) {
        int count = 0;
        int index = 0;
        while ((index = inputString.indexOf(queryString, index)) != -1) {
            count++;
            index += queryString.length();
        }
        return count;
    }

    public static void main(String[] args) {
        List<String> inputStrings = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        List<String> queryStrings = List.of("apple", "banana", "orange");

        int[] result = countQueryStrings(inputStrings, queryStrings);

        for (int i = 0; i < queryStrings.size(); i++) {
            System.out.println(queryStrings.get(i) + " occurs " + result[i] + " times.");
        }
    }
}