package com.week3;

public class RedBlackTreeValidation02 {
    static class Node {
        int data;
        Node left, right;
        boolean isRed;  // true for red, false for black

        Node(int data, boolean isRed) {
            this.data = data;
            this.left = this.right = null;
            this.isRed = isRed;
        }
    }

    private static boolean isValidRedBlackTree(Node root) {
        if (root == null) {
            return true;
        }

        // Property 1: Root must be black
        if (root.isRed) {
            return false;
        }

        // Check BST property and other Red-Black properties
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) &&
                checkProperties(root) != -1;
    }

    private static boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data <= min || node.data >= max) {
            return false;
        }

        return isBST(node.left, min, node.data) &&
                isBST(node.right, node.data, max);
    }

    private static int checkProperties(Node node) {
        if (node == null) {
            return 0;
        }

        int leftBlackHeight = checkProperties(node.left);
        if (leftBlackHeight == -1) return -1;

        int rightBlackHeight = checkProperties(node.right);
        if (rightBlackHeight == -1) return -1;

        if (node.isRed) {
            if ((node.left != null && node.left.isRed) ||
                    (node.right != null && node.right.isRed)) {
                return -1;
            }
        }

        if (leftBlackHeight != rightBlackHeight) {
            return -1;
        }

        return (node.isRed ? 0 : 1) + leftBlackHeight;
    }

    public static void main(String[] args) {
        // Corrected example with BST property
        Node root = new Node(20, false);    // Black root
        root.left = new Node(10, true);     // Red node
        root.right = new Node(30, true);    // Red node
        root.left.left = new Node(5, false); // Black node
        root.left.right = new Node(15, false); // Black node
        root.right.left = new Node(25, false); // Black node
        root.right.right = new Node(35, false); // Black node

        System.out.println(isValidRedBlackTree(root) ?
                "Valid Red-Black Tree" : "Invalid Red-Black Tree");
    }
}