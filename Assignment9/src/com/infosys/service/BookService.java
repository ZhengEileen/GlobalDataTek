package com.infosys.service;

import com.infosys.dao.BookDAO;
import com.infosys.exception.InvalidInputException;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

import java.util.Scanner;

public class BookService {
	private Scanner scanner;
	private BookDAO bookDAO;

	public BookService() {
		this.scanner = new Scanner(System.in);
		this.bookDAO = new BookDAO();
	}

	public Book getBookDetails(User user, Long bookId) {
		if (user == null || bookId == null) {
			throw new InvalidInputException("Invalid input: User and book ID are required.");
		}

		Book[] allBooks = getAllBooks(user);
		for (Book book : allBooks) {
			if (book != null && book.getBookId().equals(bookId)) {
				return book;
			}
		}
		return null;
	}

	private Book[] getAllBooks(User user) {
		int totalLength = user.getNewBooks().length + user.getFavourite().length + user.getCompleted().length;
		Book[] allBooks = new Book[totalLength];
		int currentIndex = 0;

		for (Book book : user.getNewBooks()) {
			if (book != null) {
				allBooks[currentIndex++] = book;
			}
		}
		for (Book book : user.getFavourite()) {
			if (book != null) {
				allBooks[currentIndex++] = book;
			}
		}
		for (Book book : user.getCompleted()) {
			if (book != null) {
				allBooks[currentIndex++] = book;
			}
		}

		Book[] result = new Book[currentIndex];
		for (int i = 0; i < currentIndex; i++) {
			result[i] = allBooks[i];
		}

		return result;
	}

	public void printBooks(Book[] books) {
		if (books == null || books.length == 0) {
			System.out.println("No books found.");
			return;
		}
		for (Book book : books) {
			if (book != null) {
				System.out.println("Book ID: " + book.getBookId());
				System.out.println("Name: " + book.getBookName());
				System.out.println("Author: " + book.getAuthorName());
				System.out.println("Description: " + book.getDescription());
				System.out.println();
			}
		}
	}

	public void selectBookById(User user) {
		System.out.print("Enter book ID: ");
		String bookIdStr = scanner.nextLine();
		Long bookId = parseBookId(bookIdStr);
		if (bookId != null) {
			Book book = getBookDetails(user, bookId);
			if (book != null) {
				System.out.println("Book Details: ");
				System.out.println("Name: " + book.getBookName());
				System.out.println("Author: " + book.getAuthorName());
				System.out.println("Description: " + book.getDescription());
			} else {
				System.out.println("Book not found.");
			}
		}
	}

	private Long parseBookId(String bookIdStr) {
		try {
			return Long.valueOf(bookIdStr);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid book ID.");
			return null;
		}
	}
}

