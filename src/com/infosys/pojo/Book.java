package com.infosys.pojo;

public class Book {

    private String bookName;
    private String authorName;
    private String description;
    private int bookId;

    // Default constructor
    public Book() {}

    // Parameterized constructor
    public Book(String bookName, String authorName, String description, int bookId) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.bookId = bookId;
    }

    // Getter for bookName
    public String getBookName() {
        return bookName;
    }

    // Setter for bookName
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    // Getter for authorName
    public String getAuthorName() {
        return authorName;
    }

    // Setter for authorName
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for bookId
    public int getBookId() {
        return bookId;
    }

    // Setter for bookId
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}

