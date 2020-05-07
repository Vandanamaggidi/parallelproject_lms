package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookDetails implements Serializable {

	private int bookId = (int)Math.random();;
	private String bookName;
	private String bookCategory;
	private String authorName;
	private String publisherName;


	public BookDetails(int bookId, String bookName, String bookCategory, String authorName, String publisherName) {
		
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookCategory = bookCategory;
		this.publisherName = publisherName;
	}

	public BookDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@Override
	public String toString() {
		return String.format("%-10s %-10s %-13s %-15s %s", bookId, bookName, bookCategory, authorName, publisherName);
	}

}
