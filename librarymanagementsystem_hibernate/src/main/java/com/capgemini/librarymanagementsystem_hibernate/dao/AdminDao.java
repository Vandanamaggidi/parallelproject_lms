package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssue;
import com.capgemini.librarymanagementsystem_hibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;


public interface AdminDao {

	
	boolean addBook(BookDetails bookDetail);

	boolean removeBook(int bookId);

	boolean updateBook(BookDetails book);

	boolean bookIssue(int bookId, int id);
	
	List<RequestDetails> showRequests();

	List<BookIssue> showIssuedBooks();
	
	 List<User> showUsers();
}
