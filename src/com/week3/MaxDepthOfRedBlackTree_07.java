package com.week3;

public class MaxDepthOfRedBlackTree_07 {
    static class Node {
        int data;
        Node left, right;
        boolean isRed;

        Node(int data) {
            this.data = data;
            this.isRed = true;
            left = right = null;
        }
    }

    private Node root;

    private int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }

    private Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        } else {
            return root;
        }

        // Fix Red Black Tree violations
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        return root;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.isRed;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }

    private void flipColors(Node h) {
        h.isRed = true;
        h.left.isRed = false;
        h.right.isRed = false;
    }

    public void insert(int data) {
        root = insert(root, data);
        root.isRed = false;  // Root must always be black
    }

    public int getMaxDepth() {
        return maxDepth(root);
    }

    public static void main(String[] args) {
        MaxDepthOfRedBlackTree_07 tree = new MaxDepthOfRedBlackTree_07();

        // Example: Insert values
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("Maximum depth of the Red-Black Tree: " +
                tree.getMaxDepth());
    }
}