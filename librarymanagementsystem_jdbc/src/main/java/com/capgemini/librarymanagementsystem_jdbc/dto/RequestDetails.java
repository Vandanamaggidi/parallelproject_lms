package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class RequestDetails implements Serializable{

	
	private int id;
	private String fullName;
	private int bookId;
	private String bookName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %s", id, fullName, bookId, bookName); 
	}
	
}
