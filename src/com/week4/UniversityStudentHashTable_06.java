package com.week4;

import java.util.LinkedList;

public class UniversityStudentHashTable_06 {
    static class Student {
        int id;
        String name;

        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class HashTable {
        private final int SIZE = 100;
        private LinkedList<Student>[] table;

        @SuppressWarnings("unchecked")
        public HashTable() {
            table = new LinkedList[SIZE];
            for (int i = 0; i < SIZE; i++) {
                table[i] = new LinkedList<>();
            }
        }

        private int hash(int id) {
            return id % SIZE;
        }

        public void insert(int id, String name) {
            int index = hash(id);
            for (Student student : table[index]) {
                if (student.id == id) {
                    student.name = name; // Update existing student
                    return;
                }
            }
            table[index].add(new Student(id, name)); // Insert new student
        }

        public String search(int id) {
            int index = hash(id);
            for (Student student : table[index]) {
                if (student.id == id) {
                    return student.name;
                }
            }
            return null; // Student not found
        }
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        // Insert students
        hashTable.insert(101, "Alice");
        hashTable.insert(102, "Bob");
        hashTable.insert(103, "Charlie");

        // Search for students
        System.out.println(hashTable.search(101)); // Output: Alice
        System.out.println(hashTable.search(104)); // Output: null
        System.out.println(hashTable.search(102)); // Output: Bob
    }
}
