package service;

import model.Book;              // Represents a book in the library  
import model.User;              // Represents a user of the library
import data.BookInventory;      // Represents the book inventory using a binary search tree
import java.util.Queue;         // Represents a queue for borrow requests
import java.util.LinkedList;    // Represents a linked list for borrow requests
import java.util.Stack;

// LibraryService class to manage library operations
public class LibraryService {
    private BookInventory inventory;      // Represents the book inventory
    private Queue<Book> borrowRequests;   // Represents a queue for borrow requests
    private Stack<Book> returnStack;      // Represents a stack for book returns
    private User currentUser;

    // Constructor to initialize the library service
    public LibraryService() {
        inventory = new BookInventory();
        borrowRequests = new LinkedList<>();
        returnStack = new Stack<>();
    }

    // Method to set the current user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Method to get the current user
    public void addBook(String title, String author, String isbn) {
        inventory.insert(new Book(title, author, isbn));
    }

    // Method to remove a book by ISBN
    public void removeBook(String isbn) {
        inventory.delete(isbn);
    }

    // Method to display all books in the library sorted by ISBN
    public void displayBooks() {
        System.out.println("Library Books (Sorted by ISBN):");
        inventory.displayInOrder();
    }

    // Method to search for a book by ISBN
    public void requestBorrow(String isbn) {
        Book book = inventory.search(isbn);
        if (book != null && book.isAvailable()) {
            borrowRequests.add(book);
            book.setAvailable(false);
            System.out.println("Borrow request added to queue for: " + book.getTitle());
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    // Method to process borrow requests
    public void processBorrow() {
        if (!borrowRequests.isEmpty()) {
            Book book = borrowRequests.poll();
            if (currentUser != null) {
                currentUser.addToHistory(book);
            }
            System.out.println("Book issued: " + book.getTitle());
        } else {
            System.out.println("No pending borrow requests.");
        }
    }

    // Method to return a book by ISBN
    public void returnBook(String isbn) {
        Book book = inventory.search(isbn);
        if (book != null && !book.isAvailable()) {
            returnStack.push(book);
            book.setAvailable(true);
            if (currentUser != null) {
                currentUser.removeFromHistory(isbn);
            }
            System.out.println("Book returned: " + book.getTitle());
        } else {
            System.out.println("Invalid return request.");
        }
    }

    // Method to process returns from the return stack
    public void processReturn() {
        if (!returnStack.isEmpty()) {
            Book book = returnStack.pop();
            System.out.println("Processed return for: " + book.getTitle());
        } else {
            System.out.println("No books to process in return stack.");
        }
    }
}