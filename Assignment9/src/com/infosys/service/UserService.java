package com.infosys.service;

import com.infosys.dao.UserDAO;
import com.infosys.exception.InvalidInputException;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAO();
	}

	public void registerUser(String username, Long userId, String emailId, String password) {
		if (username == null || username.isEmpty() || userId == null || emailId == null || emailId.isEmpty()
				|| password == null || password.isEmpty()) {
			throw new InvalidInputException("Invalid input: All fields are required.");
		}
		User newUser = new User(username, userId, emailId, password);
		userDAO.addUser(newUser);
	}

	public boolean loginUser(String username, String password) {
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			throw new InvalidInputException("Invalid input: Username and password are required.");
		}
		return userDAO.verifyUser(username, password);
	}

	public User getUserDetails(String username) {
		if (username == null || username.isEmpty()) {
			throw new InvalidInputException("Invalid input: Username is required.");
		}
		return userDAO.getUserByUsername(username);
	}

	public User getUserById(Long userId) {
		if (userId == null) {
			throw new InvalidInputException("Invalid input: User ID is required.");
		}
		return userDAO.getUserById(userId);
	}

	public void updateUser(User user) {
		if (user == null || user.getUserName() == null || user.getUserName().isEmpty() || user.getEmailId() == null
				|| user.getEmailId().isEmpty()) {
			throw new InvalidInputException("Invalid input: All fields are required.");
		}
		userDAO.updateUser(user);
	}

	public void deleteUser(String username) {
		if (username == null || username.isEmpty()) {
			throw new InvalidInputException("Invalid input: Username is required.");
		}
		userDAO.deleteUser(username);
	}

	public void viewAllBooks(User user, BookService bookService) {
		if (user == null) {
			throw new InvalidInputException("Invalid input: User is required.");
		}
		System.out.println("New Books: ");
		bookService.printBooks(user.getNewBooks());

		System.out.println("Favourite Books: ");
		bookService.printBooks(user.getFavourite());

		System.out.println("Completed Books: ");
		bookService.printBooks(user.getCompleted());
	}

	public boolean markBookAsCompleted(User user, Long bookId) {
		if (user == null || bookId == null) {
			throw new InvalidInputException("Invalid input: User and book ID are required.");
		}
		for (int i = 0; i < user.getNewBooks().length; i++) {
			Book book = user.getNewBooks()[i];
			if (book != null && book.getBookId().equals(bookId)) {

				user.getNewBooks()[i] = null;

				Book[] completedBooks = user.getCompleted();
				boolean addedToCompleted = false;
				for (int j = 0; j < completedBooks.length; j++) {
					if (completedBooks[j] == null) {

						completedBooks[j] = book;
						addedToCompleted = true;
						break;
					}
				}

				if (!addedToCompleted) {

					Book[] newCompletedBooks = new Book[completedBooks.length * 2];
					for (int j = 0; j < completedBooks.length; j++) {
						newCompletedBooks[j] = completedBooks[j];
					}
					newCompletedBooks[completedBooks.length] = book;
					user.setCompleted(newCompletedBooks);
				}
				return true;
			}
		}
		return false;
	}
}
