package com.week3;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTreeLevelNodeCount_06 {
    static class Node {
        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.height = 1;
            left = right = null;
        }
    }

    private static int countNodesAtLevel(Node root, int k) {
        if (root == null || k < 1) {
            return 0;
        }

        if (k == 1) {
            return 1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty() && level < k) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;
        }

        return queue.size();
    }

    // Utility method to create a balanced AVL tree for testing
    private static Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

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

    private static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private static int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private static int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    public static void main(String[] args) {
        // Example AVL tree construction
        Node root = null;
        root = insert(root, 10);
        root = insert(root, 20);
        root = insert(root, 30);
        root = insert(root, 40);
        root = insert(root, 50);
        root = insert(root, 25);

        int k = 3; // Level to count nodes
        System.out.println("Number of nodes at level " + k + ": " +
                countNodesAtLevel(root, k));
    }
}