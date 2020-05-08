package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BooksBorrowed;

public interface UserService {

	boolean request(int bookId, int id);
	// BookDetails borrow(int bookId);

	boolean returnBook(int bookId, int id, String status);

	LinkedList<Integer> bookHistoryDetails(int id);

	List<BooksBorrowed> borrowedBook(int id);

}
