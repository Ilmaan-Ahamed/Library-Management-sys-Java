package data;

import model.Book;     // Represents a book in the library

// Binary Search Tree Node class to represent each node in the book inventory
public class BSTNode {
    Book book;
    BSTNode left, right;

    // Constructor to initialize a BSTNode with a book
    public BSTNode(Book book) {
        this.book = book;
        this.left = this.right = null;
    }
}