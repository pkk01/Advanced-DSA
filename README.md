# 🌳 Advanced Tree Data Structures
> High-performance implementations of AVL and Red-Black trees in Java

## 📚 Contents
1. [Features](#-features)
2. [Implementation](#-implementation)
3. [Usage](#-usage)
4. [Performance](#-performance)
5. [Setup](#-setup)

## ✨ Features

- **AVL Trees**
    - Self-balancing insertion
    - Level-wise node counting
    - Inorder traversal

- **Red-Black Trees**
    - BST to Red-Black conversion
    - Maximum depth calculation
    - Color property validation

## 🔧 Implementation

### AVL Tree Level Count
```java
Node root = new Node(20);
root = insert(root, 10);
root = insert(root, 30);
int count = countNodesAtLevel(root, 2); // Count nodes at level 2