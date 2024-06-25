package com.infosys.dao;

import com.infosys.pojo.Book;

public class BookDAO {
	private Book[] books;
	private int bookCount;

	public BookDAO() {
		this.books = new Book[10];
		this.bookCount = 0;
	}

	public void addBook(Book book) {
		if (bookCount == books.length) {
			Book[] newBooks = new Book[books.length * 2];
			for (int i = 0; i < books.length; i++) {
				newBooks[i] = books[i];
			}
			books = newBooks;
		}
		books[bookCount++] = book;
	}

	public Book getBookById(Long bookId) {
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getBookId().equals(bookId)) {
				return books[i];
			}
		}
		return null;
	}

	public boolean updateBook(Book updatedBook) {
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getBookId().equals(updatedBook.getBookId())) {
				books[i].setBookName(updatedBook.getBookName());
				books[i].setAuthorName(updatedBook.getAuthorName());
				books[i].setDescription(updatedBook.getDescription());
				return true;
			}
		}
		return false;
	}

	public boolean deleteBook(Long bookId) {
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getBookId().equals(bookId)) {
				books[i] = books[--bookCount];
				books[bookCount] = null;
				return true;
			}
		}
		return false;
	}
}


