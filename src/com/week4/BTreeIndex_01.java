package com.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BTreeIndex_01 {
    static class BTreeNode {
        List<Integer> keys;
        List<BTreeNode> children;
        boolean isLeaf;
        int t; // minimum degree

        BTreeNode(int t, boolean isLeaf) {
            this.t = t;
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }

    static class BTree {
        BTreeNode root;
        final int t; // minimum degree

        BTree(int t) {
            this.t = t;
            root = null;
        }

        private void splitChild(BTreeNode parent, int index, BTreeNode child) {
            BTreeNode newNode = new BTreeNode(child.t, child.isLeaf);
            parent.children.add(index + 1, newNode);
            parent.keys.add(index, child.keys.get(t - 1));

            for (int j = 0; j < t - 1; j++) {
                newNode.keys.add(child.keys.get(j + t));
            }

            if (!child.isLeaf) {
                for (int j = 0; j < t; j++) {
                    newNode.children.add(child.children.get(j + t));
                }
                child.children.subList(t, child.children.size()).clear();
            }

            child.keys.subList(t - 1, child.keys.size()).clear();
        }

        public void insert(int key) {
            if (root == null) {
                root = new BTreeNode(t, true);
                root.keys.add(key);
                return;
            }

            if (root.keys.size() == 2 * t - 1) {
                BTreeNode newRoot = new BTreeNode(t, false);
                newRoot.children.add(root);
                root = newRoot;
                splitChild(newRoot, 0, newRoot.children.get(0));
            }
            insertNonFull(root, key);
        }

        private void insertNonFull(BTreeNode node, int key) {
            int i = node.keys.size() - 1;

            if (node.isLeaf) {
                while (i >= 0 && key < node.keys.get(i)) {
                    i--;
                }
                node.keys.add(i + 1, key);
            } else {
                while (i >= 0 && key < node.keys.get(i)) {
                    i--;
                }
                i++;

                if (node.children.get(i).keys.size() == 2 * t - 1) {
                    splitChild(node, i, node.children.get(i));
                    if (key > node.keys.get(i)) {
                        i++;
                    }
                }
                insertNonFull(node.children.get(i), key);
            }
        }

        public List<Integer> rangeQuery(int start, int end) {
            List<Integer> result = new ArrayList<>();
            rangeQueryUtil(root, start, end, result);
            return result;
        }

        private void rangeQueryUtil(BTreeNode node, int start, int end, List<Integer> result) {
            if (node == null)
                return;

            for (int i = 0; i < node.keys.size(); i++) {
                if (!node.isLeaf) {
                    rangeQueryUtil(node.children.get(i), start, end, result);
                }

                int key = node.keys.get(i);
                if (key >= start && key <= end) {
                    result.add(key);
                }
            }

            if (!node.isLeaf) {
                rangeQueryUtil(node.children.get(node.children.size() - 1), start, end, result);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read degree of B-tree
        int t = scanner.nextInt();

        // Read number of keys
        int n = scanner.nextInt();

        // Create B-tree
        BTree tree = new BTree(t);

        // Insert keys into B-tree
        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            tree.insert(key);
        }

        // Read number of range queries
        int q = scanner.nextInt();

        // Perform range queries and output results
        for (int i = 0; i < q; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            List<Integer> result = tree.rangeQuery(l, r);
            for (int key : result) {
                System.out.print(key + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}