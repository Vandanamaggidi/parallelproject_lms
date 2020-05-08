package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookDetails implements Serializable{

	private int bookId;
	private String bookName;
	private String authorName;
	private String publisherName;
	private int copies;
	private String bookCategory;
	
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	//private Date dateAdded;
	public int getBookId() {
		return bookId;
	}
	public void setId(int bookId) {
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
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-13s %-15s %s", bookId, bookName, authorName, bookCategory, publisherName);
	}

	
}
