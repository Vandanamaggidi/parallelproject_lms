package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.BooksBorrowed;

public interface UserDao {

	boolean request(int bookId, int id);

	BookDetails borrow(int bookId);

	boolean returnBook(int bookId, int id, String status);

	LinkedList<BookIssue> bookHistoryDetails(int id);

	List<BooksBorrowed> borrowedBook(int id);
}
