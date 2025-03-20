package com.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TowerConstruction04 {
    public static List<List<Integer>> constructTower(int n, int[] disks) {
        List<List<Integer>> result = new ArrayList<>(n);
        PriorityQueue<Integer> availableDisks = new PriorityQueue<>((a, b) -> b - a);
        int maxSeen = n;

        // Initialize result list with empty lists
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }

        // Process each day
        for (int day = 0; day < n; day++) {
            availableDisks.offer(disks[day]);

            // Check if we can place disks
            while (!availableDisks.isEmpty() && availableDisks.peek() == maxSeen) {
                result.get(day).add(availableDisks.poll());
                maxSeen--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] disks = new int[n];

        for (int i = 0; i < n; i++) {
            disks[i] = scanner.nextInt();
        }

        List<List<Integer>> result = constructTower(n, disks);
        for (List<Integer> day : result) {
            if (!day.isEmpty()) {
                for (int disk : day) {
                    System.out.print(disk + " ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}