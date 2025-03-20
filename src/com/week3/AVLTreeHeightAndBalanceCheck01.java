package com.week3;

public class AVLTreeHeightAndBalanceCheck01 {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    private static class HeightBalancePair {
        int height;
        boolean isBalanced;

        HeightBalancePair(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static HeightBalancePair checkHeightAndBalance(Node root) {
        if (root == null) {
            return new HeightBalancePair(0, true);
        }

        // Get height and balance info from left and right subtrees
        HeightBalancePair left = checkHeightAndBalance(root.left);
        HeightBalancePair right = checkHeightAndBalance(root.right);

        // Calculate current node's height
        int height = Math.max(left.height, right.height) + 1;

        // Check if current subtree is balanced
        boolean isBalanced = left.isBalanced && right.isBalanced &&
                Math.abs(left.height - right.height) <= 1;

        return new HeightBalancePair(height, isBalanced);
    }

    public static int getHeight(Node root) {
        return checkHeightAndBalance(root).height;
    }

    public static boolean isBalanced(Node root) {
        return checkHeightAndBalance(root).isBalanced;
    }

    public static void main(String[] args) {
        // Example usage
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);

        System.out.println("Height: " + getHeight(root));
        System.out.println("Is Balanced: " + isBalanced(root));
    }
}