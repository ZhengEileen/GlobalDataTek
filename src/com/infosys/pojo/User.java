package com.infosys.pojo;

public class User {
<<<<<<< HEAD
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String role;
	
	public User() {}
	
	public User(String firstName, String lastName, String userName, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}	
=======

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
>>>>>>> 889d62ab8b34eaf3a3c91ab47d7270522fcc4c8d
