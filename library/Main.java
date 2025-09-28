package library;

import model.User;              // Represents a user of the library
import service.LibraryService;  // Service class to manage library operations
import java.util.Scanner;       // Scanner for user input

public class Main {
    private static Scanner scanner = new Scanner(System.in);       // Scanner for reading user input
    private static LibraryService library = new LibraryService();  // Library service instance to manage books and users
    private static User currentUser = null;                        // Current user of the library

    public static void main(String[] args) {
        showMainMenu();      // Display the main menu and handle user choices
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Admin Functions");
            System.out.println("2. User Functions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Handle user choice for main menu
            switch (choice) {
                case 1:
                    showAdminMenu();
                    break;
                case 2:
                    showUserMenu();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Handle user choice for admin menu
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Method to display user menu and handle user actions
    private static void showUserMenu() {
        if (currentUser == null) {
            System.out.print("\nEnter your name: ");
            String name = scanner.nextLine();
            currentUser = new User(name);
            library.setCurrentUser(currentUser);
        }

        // Display user menu options and handle user actions
        while (true) {
            System.out.println("\n===== User Menu (" + currentUser.getName() + ") =====");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Borrowing History");
            System.out.println("4. View Available Books");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Handle user choice for user menu
            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    currentUser.displayHistory();
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Method to add a new book to the library
    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        library.addBook(title, author, isbn);
        System.out.println("Book added successfully!");
    }

    // Method to remove a book from the library by ISBN
    private static void removeBook() {
        System.out.println("\n--- Remove Book ---");
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
        System.out.println("Book removed successfully!");
    }

    // Method to borrow a book from the library
    private static void borrowBook() {
        System.out.println("\n--- Borrow Book ---");
        System.out.print("Enter ISBN of book to borrow: ");
        String isbn = scanner.nextLine();
        library.requestBorrow(isbn);
        library.processBorrow();
    }

    // Method to return a borrowed book to the library
    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn);
        library.processReturn();
    }
}