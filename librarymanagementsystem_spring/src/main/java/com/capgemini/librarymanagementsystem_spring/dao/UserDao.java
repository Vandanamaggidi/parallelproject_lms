 package com.capgemini.librarymanagementsystem_spring.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_spring.dto.BooksBorrowed;

public interface UserDao {

	
	boolean request(int bookId, int id);

	boolean returnBook(int bookId, int id, String status);
	
	LinkedList<Integer> bookHistoryDetails(int id);
	
	List<BooksBorrowed> borrowedBook(int id);
	
}
