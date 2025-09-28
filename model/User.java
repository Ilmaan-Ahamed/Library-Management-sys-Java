package model;

import java.util.LinkedList;

public class User {
    private String name;                        // Stores user name
    private LinkedList<Book> borrowingHistory;  // Stores borrowing history as a linked list

    
    // Constructor to initialize user with a name and an empty borrowing history
    public User(String name) {
        this.name = name;
        // Initialize borrowing history as an empty linked list
        this.borrowingHistory = new LinkedList<>();  
        
    }

    // Methods to manage borrowing history
    public void addToHistory(Book book) {
        borrowingHistory.addFirst(book);
    }

    // Method to remove a book from borrowing history by ISBN
    public void removeFromHistory(String isbn) {
        borrowingHistory.removeIf(book -> book.getIsbn().equals(isbn));
    }

    // Method to display the borrowing history of the user
    public void displayHistory() {
        System.out.println("Borrowing history for " + name + ":");
        borrowingHistory.forEach(System.out::println);
    }

    // Getters
    public String getName() { return name; }
}