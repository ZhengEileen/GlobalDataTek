package com.infosys.pojo;

public class User {

    private String userName;
    private int userId;
    private String emailId;
    private String password;
    private Book[] newBooks;
    private Book[] favorite;
    private Book[] completed;

    // Default constructor
    public User() {
        this.newBooks = new Book[10]; // Initialize newBooks array with default size 10
        this.favorite = new Book[10]; // Initialize favorite array with default size 10
        this.completed = new Book[10]; // Initialize completed array with default size 10
    }

    // Parameterized constructor
    public User(String userName, int userId, String emailId, String password, Book[] newBooks, Book[] favorite, Book[] completed) {
        this.userName = userName; // Set userName
        this.userId = userId; // Set userId
        this.emailId = emailId; // Set emailId
        this.password = password; // Set password
        this.newBooks = new Book[10]; // Initialize newBooks array with default size 10
        this.favorite = new Book[10]; // Initialize favorite array with default size 10
        this.completed = new Book[10]; // Initialize completed array with default size 10
    }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter for userId
    public int getUserId() {
        return userId;
    }

    // Setter for userId
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter for emailId
    public String getEmailId() {
        return emailId;
    }

    // Setter for emailId
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for newBooks
    public Book[] getNewBooks() {
        return newBooks;
    }

    // Setter for newBooks
    public void setNewBooks(Book[] newBooks) {
        this.newBooks = newBooks;
    }

    // Getter for favorite
    public Book[] getFavorite() {
        return favorite;
    }

    // Setter for favorite
    public void setFavorite(Book[] favorite) {
        this.favorite = favorite;
    }

    // Getter for completed
    public Book[] getCompleted() {
        return completed;
    }

    // Setter for completed
    public void setCompleted(Book[] completed) {
        this.completed = completed;
    }
}
