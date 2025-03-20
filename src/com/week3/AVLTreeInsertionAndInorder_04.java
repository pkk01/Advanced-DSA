package com.week3;

import java.util.Scanner;

public class AVLTreeInsertionAndInorder_04 {
    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.height = 1;
            this.left = this.right = null;
        }
    }

    private static int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private static int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private static Node insert(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else
            return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private static void inorderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            inorderTraversal(node.left, sb);
            sb.append(node.data).append(" ");
            inorderTraversal(node.right, sb);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node root = null;

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            root = insert(root, value);

            StringBuilder sb = new StringBuilder();
            inorderTraversal(root, sb);
            System.out.println(sb.toString().trim());
        }
        scanner.close();
    }
}