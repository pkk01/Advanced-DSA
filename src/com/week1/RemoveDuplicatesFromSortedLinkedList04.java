package com.week1;

import java.util.Scanner;

public class RemoveDuplicatesFromSortedLinkedList04 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode removeDuplicates(ListNode head) {
        // Handle empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        while (current.next != null) {
            if (current.val == current.next.val) {
                // Skip duplicate node
                current.next = current.next.next;
            } else {
                // Move to next node
                current = current.next;
            }
        }

        return head;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static ListNode createList(Scanner scanner, int n) {
        if (n <= 0) return null;

        ListNode head = new ListNode(scanner.nextInt());
        ListNode current = head;

        for (int i = 1; i < n; i++) {
            current.next = new ListNode(scanner.nextInt());
            current = current.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // number of test cases

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(); // number of elements in the list
            ListNode head = createList(scanner, n);
            head = removeDuplicates(head);
            printList(head);
        }

        scanner.close();
    }
}