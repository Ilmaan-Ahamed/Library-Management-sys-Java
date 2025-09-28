package model;

public class Book {
    // Fields
    private String title;         // Stores book title
    private String author;        // Stores book author
    private String isbn;          // Stores book ISBN
    private boolean available;    // Track availability

    // Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true; // Default to available
    }

    // Getters and Setters
    public String getTitle() { return title; } 
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // Override equals method to compare books by ISBN
    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ")";
    }
}