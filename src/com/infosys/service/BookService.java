package com.infosys.service;

import java.util.Random;
import java.util.Scanner;
import com.infosys.dao.BookDao;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

public class BookService {

    private Scanner userInput;
    private BookDao dao;
    private User currentUser;
    private boolean[] usedIds;
    private int size;

    // Default constructor
    public BookService() {
        int size = 100; // Default size
        this.userInput = new Scanner(System.in);
        this.dao = new BookDao(size);
        this.size = size;
        this.usedIds = new boolean[size];
    }

    // Constructor with specified size
    public BookService(Scanner userInput, int size) {
        this.userInput = userInput;
        this.dao = new BookDao(size);
        this.size = size;
        this.usedIds = new boolean[size];
    }

    // Constructor with currentUser but default size
    public BookService(Scanner userInput, User currentUser) {
        this.userInput = userInput;
        int size = 100; // Default size
        this.dao = new BookDao(size);
        this.size = size;
        this.usedIds = new boolean[size];
        this.currentUser = currentUser;
    }

    // Constructor with specified size and currentUser
    public BookService(Scanner userInput, int size, User currentUser) {
        this.userInput = userInput;
        this.dao = new BookDao(size);
        this.size = size;
        this.usedIds = new boolean[size];
        this.currentUser = currentUser;
    }

    // Method to set the current user
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // Method to get the number of books
    public int numberBooks() {
        System.out.println("How many books does the bookstore own in total?");
        int number = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer
        return number;
    }

    // Method to generate a unique book ID
    public int generateBookId() {
        Random randomNum = new Random();
        int bookId = randomNum.nextInt(size);
        while (usedIds[bookId]) { // Check if the ID is already used
            bookId = randomNum.nextInt(size);
        }
        usedIds[bookId] = true; // Mark the ID as used
        return bookId;
    }

    // Method to show all books
    public void showAllBooks() {
        Book[] books = dao.getAllBooks();
        for (Book book : books) {
            if (book != null) {
                System.out.println("ID: " + book.getBookId() + " " + book.getBookName());
            }
        }
    }

    // Method to add a new book
    public void addBook() {
        System.out.println("Would you like to add a new book to the system?");
        System.out.println("Type \"yes\" if you want to.");
        System.out.println("Type \"finish\" if you have finished adding your books.");
        String userDecision = userInput.nextLine();

        while (!userDecision.equalsIgnoreCase("finish")) {
            System.out.println("Please type the name of the book below, adding one book at a time.");
            String newBookName = userInput.nextLine();

            System.out.println("Please type the author name below.");
            String newAuthorName = userInput.nextLine();

            System.out.println("Please type the book's description below.");
            String description = userInput.nextLine();

            int bookId = generateBookId();

            Book newBook = new Book(newBookName, newAuthorName, description, bookId);

            dao.addBook(newBook); // Add the new book to the DAO

            System.out.println("Would you like to continue adding books to the system?");
            System.out.println("Type \"yes\" if you want to.");
            System.out.println("Type \"finish\" if you have finished adding your books.");
            userDecision = userInput.nextLine();
        }
    }

    // Method to delete a book
    public void deleteBook() {
        System.out.println("Here are the books available in the system:");
        showAllBooks();
        System.out.println("Please type the ID of the book you want to delete.");
        System.out.println("Type \"finish\" if you have finished deleting books.");
        String userDecision = userInput.nextLine();

        while (!userDecision.equalsIgnoreCase("finish")) {
            int bookId;
            if (isNumeric(userDecision)) { // Check if the input is numeric
                bookId = Integer.parseInt(userDecision);
                Book bookToDelete = dao.getBook(bookId);

                if (bookToDelete != null) {
                    dao.deleteBook(bookToDelete); // Delete the book from the DAO
                    System.out.println("Book with ID " + bookId + " has been deleted.");
                } else {
                    System.out.println("Book with ID " + bookId + " not found.");
                }
            } else {
                System.out.println("Invalid ID. Please enter a valid book ID.");
            }

            System.out.println("Would you like to continue deleting books from the system?");
            System.out.println("Please type the ID of the book you want to delete.");
            System.out.println("Type \"finish\" if you have finished deleting books.");
            userDecision = userInput.nextLine();
        }
    }

    // Method to add a book to the "New" list
    public void addNew() {
        System.out.println("The following are the books we have so far:");
        showAllBooks();

        Book[] newBooks = currentUser.getNewBooks();

        System.out.println("Please type the ID number of the book you want to add to your NEW list.");
        System.out.println("Type \"-1\" if you have finished adding your books.");

        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        while (bookId != -1) {
            Book newBook = dao.getBook(bookId);
            if (newBook != null) {
                for (int i = 0; i < newBooks.length; i++) {
                    if (newBooks[i] == null) {
                        newBooks[i] = newBook; // Add the book to the "New" list
                        System.out.println("No. " + bookId + " added successfully!");
                        break;
                    }
                }
            } else {
                System.out.println("Book not found.");
            }

            System.out.println("The following are the books we have so far:");
            showAllBooks();

            System.out.println("Please type the ID number of the book you want to add to your NEW list.");
            System.out.println("Type \"-1\" if you have finished adding your books.");

            bookId = userInput.nextInt();
            userInput.nextLine(); // Clear the buffer
        }
    }

    // Method to delete a book from the "New" list
    public void deleteNew() {
        isNew(); // Display the "New" list

        Book[] newBooks = currentUser.getNewBooks();

        System.out.println("Please type the ID number of the book you want to delete from your NEW list.");
        System.out.println("Type \"-1\" if you have finished deleting your books.");

        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        while (bookId != -1) {
            boolean found = false;
            for (int i = 0; i < newBooks.length; i++) {
                if (newBooks[i] != null && newBooks[i].getBookId() == bookId) {
                    // Shift all the books to the left to fill the gap
                    for (int j = i; j < newBooks.length - 1; j++) {
                        newBooks[j] = newBooks[j + 1];
                    }
                    newBooks[newBooks.length - 1] = null; // Clear the last book element
                    found = true;
                    System.out.println("No. " + bookId + " deleted successfully!");
                    break;
                }
            }
            if (!found) {
                System.out.println("Book not found.");
            }

            isNew(); // Display the updated "New" list

            System.out.println("Please type the ID number of the book you want to delete from your NEW list.");
            System.out.println("Type \"-1\" if you have finished deleting your books.");

            bookId = userInput.nextInt();
            userInput.nextLine(); // Clear the buffer
        }
    }

    // Method to add a book to the "Favorite" list
    public void addFavorite() {
        System.out.println("The following are the books we have so far:");
        showAllBooks();

        Book[] favoriteBooks = currentUser.getFavorite();

        System.out.println("Please type the ID number of the book you want to add to your FAVORITE list.");
        System.out.println("Type \"-1\" if you have finished adding your books.");

        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        while (bookId != -1) {
            Book favoriteBook = dao.getBook(bookId);
            if (favoriteBook != null) {
                for (int i = 0; i < favoriteBooks.length; i++) {
                    if (favoriteBooks[i] == null) {
                        favoriteBooks[i] = favoriteBook; // Add the book to the "Favorite" list
                        System.out.println("No. " + bookId + " added successfully to your FAVORITE list!");
                        break;
                    }
                }
            } else {
                System.out.println("Book not found.");
            }

            System.out.println("The following are the books we have so far:");
            showAllBooks();

            System.out.println("Please type the ID number of the book you want to add to your FAVORITE list.");
            System.out.println("Type \"-1\" if you have finished adding your books.");

            bookId = userInput.nextInt();
            userInput.nextLine(); // Clear the buffer
        }
    }

    // Method to delete a book from the "Favorite" list
    public void deleteFavorite() {
        isFavorite(); // Display the "Favorite" list

        Book[] favoriteBooks = currentUser.getFavorite();

        System.out.println("Please type the ID number of the book you want to delete from your FAVORITE list.");
        System.out.println("Type \"-1\" if you have finished deleting your books.");

        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        while (bookId != -1) {
            boolean found = false;
            for (int i = 0; i < favoriteBooks.length; i++) {
                if (favoriteBooks[i] != null && favoriteBooks[i].getBookId() == bookId) {
                    // Shift all the books to the left to fill the gap
                    for (int j = i; j < favoriteBooks.length - 1; j++) {
                        favoriteBooks[j] = favoriteBooks[j + 1];
                    }
                    favoriteBooks[favoriteBooks.length - 1] = null; // Clear the last book element
                    found = true;
                    System.out.println("No. " + bookId + " deleted successfully from your FAVORITE list!");
                    break;
                }
            }
            if (!found) {
                System.out.println("Book not found.");
            }

            isFavorite(); // Display the updated "Favorite" list

            System.out.println("Please type the ID number of the book you want to delete from your FAVORITE list.");
            System.out.println("Type \"-1\" if you have finished deleting your books.");

            bookId = userInput.nextInt();
            userInput.nextLine(); // Clear the buffer
        }
    }

    // Method to add a book to the "Completed" list
    public void addCompleted() {
        System.out.println("The following are the books we have so far:");
        showAllBooks();

        Book[] completed = currentUser.getCompleted();

        System.out.println("Please type the ID number of the book you want to add to your COMPLETED list.");
        System.out.println("Type \"-1\" if you have finished adding your books.");

        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        while (bookId != -1) {
            Book completedBook = dao.getBook(bookId);
            if (completedBook != null) {
                for (int i = 0; i < completed.length; i++) {
                    if (completed[i] == null) {
                        completed[i] = completedBook; // Add the book to the "Completed" list
                        System.out.println("No. " + bookId + " added successfully to your COMPLETED list!");
                        break;
                    }
                }
            } else {
                System.out.println("Book not found.");
            }

            System.out.println("The following are the books we have so far:");
            showAllBooks();

            System.out.println("Please type the ID number of the book you want to add to your COMPLETED list.");
            System.out.println("Type \"-1\" if you have finished adding your books.");

            bookId = userInput.nextInt();
            userInput.nextLine(); // Clear the buffer
        }
    }

    // Method to delete a book from the "Completed" list
    public void deleteCompleted() {
        isCompleted(); // Display the "Completed" list

        Book[] completedBooks = currentUser.getCompleted();

        System.out.println("Please type the ID number of the book you want to delete from your COMPLETED list.");
        System.out.println("Type \"-1\" if you have finished deleting your books.");

        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        while (bookId != -1) {
            boolean found = false;
            for (int i = 0; i < completedBooks.length; i++) {
                if (completedBooks[i] != null && completedBooks[i].getBookId() == bookId) {
                    // Shift all the books to the left to fill the gap
                    for (int j = i; j < completedBooks.length - 1; j++) {
                        completedBooks[j] = completedBooks[j + 1];
                    }
                    completedBooks[completedBooks.length - 1] = null; // Clear the last book element
                    found = true;
                    System.out.println("No. " + bookId + " deleted successfully from your COMPLETED list!");
                    break;
                }
            }
            if (!found) {
                System.out.println("Book not found in your COMPLETED list.");
            }

            isCompleted(); // Display the updated "Completed" list

            System.out.println("Please type the ID number of the book you want to delete from your COMPLETED list.");
            System.out.println("Type \"-1\" if you have finished deleting your books.");

            bookId = userInput.nextInt();
            userInput.nextLine(); // Clear the buffer
        }
    }

    // Method to display the "New" list
    public void isNew() {
        Book[] newBooks = currentUser.getNewBooks();
        System.out.println("Here are the books in your NEW list:");
        for (Book book : newBooks) {
            if (book != null) {
                System.out.println("Book ID: " + book.getBookId() + " Book Name: " + book.getBookName());
            }
        }
    }

    // Method to display the "Favorite" list
    public void isFavorite() {
        Book[] favoriteBooks = currentUser.getFavorite();
        System.out.println("Here are the books in your FAVORITE list:");
        for (Book book : favoriteBooks) {
            if (book != null) {
                System.out.println("Book ID: " + book.getBookId() + " Book Name: " + book.getBookName());
            }
        }
    }

    // Method to display the "Completed" list
    public void isCompleted() {
        Book[] completedBooks = currentUser.getCompleted();
        System.out.println("Here are the books in your COMPLETED list:");
        for (Book book : completedBooks) {
            if (book != null) {
                System.out.println("Book ID: " + book.getBookId() + " Book Name: " + book.getBookName());
            }
        }
    }

    // Method to select a book by its ID
    public Book selectBookById() {
        System.out.println("Please enter the ID of the book you wish to select:");
        int bookId = userInput.nextInt();
        userInput.nextLine(); // Clear the buffer

        Book book = dao.getBook(bookId);
        if (book != null) {
            System.out.println("Book Details: ");
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Title: " + book.getBookName());
            System.out.println("Author: " + book.getAuthorName());
            System.out.println("Description: " + book.getDescription());
        } else {
            System.out.println("Sorry, no book found with ID " + bookId + ".");
        }

        return book;
    }

    // Method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


