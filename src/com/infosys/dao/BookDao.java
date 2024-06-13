package com.infosys.dao;

import com.infosys.pojo.Book;

public class BookDao {
    
    private Book[] books;
    private int bookCount;
    
    // Default constructor with default size 100
    public BookDao() {
        int size = 100; // Set default size to 100
        books = new Book[size]; // Initialize the books array with the default size
        bookCount = 0; // Initialize book count to 0
    }
    
    // Constructor with specified size
    public BookDao(int size) {
        books = new Book[size]; // Initialize the books array with the specified size
        bookCount = 0; // Initialize book count to 0
    }
    
    // Method to add a book to the array
    public void addBook(Book book) {
        if (bookCount < books.length) { // Check if there is space in the array
            books[bookCount] = book; // Add the book to the array
            bookCount++; // Increment the book count
        } else {
            System.out.println("Array is full. Cannot add more books."); // Print a message if the array is full
        }
    }
    
    // Method to get a book by its ID
    public Book getBook(int bookId) {
        for (Book book : books) { // Iterate over the books array
            if (book != null && book.getBookId() == bookId) { // Check if the book is not null and the ID matches
                return book; // Return the book if found
            }
        }
        return null; // Return null if the book is not found
    }

    // Method to get all books
    public Book[] getAllBooks() {
        return books; // Return the array of books
    }
    
    // Method to update a book
    public void updateBook(Book oldBook, Book newBook) {
        for (int i = 0; i < bookCount; i++) { // Iterate over the books array
            if (books[i].getBookId() == oldBook.getBookId()) { // Check if the ID matches the old book's ID
                books[i] = newBook; // Update the book with the new book
                break; // Exit the loop after updating
            }
        }
    }
    
    // Method to delete a book
    public void deleteBook(Book book) {
        for (int i = 0; i < bookCount; i++) { // Iterate over the books array
            if (books[i].getBookId() == book.getBookId()) { // Check if the ID matches
                for (int j = i; j < bookCount - 1; j++) { // Shift the books to the left to fill the gap
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null; // Clear the last book element
                bookCount--; // Decrement the book count
                break; // Exit the loop after deleting
            }
        }
    }
}

