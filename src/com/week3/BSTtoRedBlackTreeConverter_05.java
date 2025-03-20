package com.week3;

import java.util.Scanner;

public class BSTtoRedBlackTreeConverter_05 {
    static class Node {
        int data;
        Node left, right, parent;
        boolean isRed;

        Node(int data) {
            this.data = data;
            this.isRed = true; // New nodes are always red
            left = right = parent = null;
        }
    }

    private Node root;

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != null)
            x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == null)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        x.right = y;
        y.parent = x;
    }

    private void fixViolation(Node node) {
        Node parent = null;
        Node grandParent = null;

        while (node != root && node.isRed && node.parent.isRed) {
            parent = node.parent;
            grandParent = parent.parent;

            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                if (uncle != null && uncle.isRed) {
                    grandParent.isRed = true;
                    parent.isRed = false;
                    uncle.isRed = false;
                    node = grandParent;
                } else {
                    if (node == parent.right) {
                        leftRotate(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rightRotate(grandParent);
                    boolean temp = parent.isRed;
                    parent.isRed = grandParent.isRed;
                    grandParent.isRed = temp;
                    node = parent;
                }
            } else {
                Node uncle = grandParent.left;

                if (uncle != null && uncle.isRed) {
                    grandParent.isRed = true;
                    parent.isRed = false;
                    uncle.isRed = false;
                    node = grandParent;
                } else {
                    if (node == parent.left) {
                        rightRotate(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    leftRotate(grandParent);
                    boolean temp = parent.isRed;
                    parent.isRed = grandParent.isRed;
                    grandParent.isRed = temp;
                    node = parent;
                }
            }
        }
        root.isRed = false;
    }

    private void insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
            root.isRed = false;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (data < current.data)
                current = current.left;
            else
                current = current.right;
        }

        newNode.parent = parent;
        if (data < parent.data)
            parent.left = newNode;
        else
            parent.right = newNode;

        fixViolation(newNode);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        BSTtoRedBlackTreeConverter_05 tree = new BSTtoRedBlackTreeConverter_05();
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        for (String s : input) {
            tree.insert(Integer.parseInt(s));
        }

        tree.inorderTraversal(tree.root);
        scanner.close();
    }
}