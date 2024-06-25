package com.infosys.pojo;

public class Book {
	private String bookName;
	private String authorName;
	private String description;
	private Long bookId;

	public Book(String bookName, String authorName, String description, Long bookId) {
		this.bookName = bookName;
		this.authorName = authorName;
		this.description = description;
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
}
