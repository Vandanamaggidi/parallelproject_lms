package com.capgemini.librarymanagementsystem_spring.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_spring.dto.BookDetails;
import com.capgemini.librarymanagementsystem_spring.dto.BookIssue;
import com.capgemini.librarymanagementsystem_spring.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_spring.dto.User;


public interface AdminDao {

	
	boolean addBook(BookDetails bookDetail);

	boolean removeBook(int bookId);

	boolean updateBook(BookDetails book);

	boolean bookIssue(int bookId, int id);
	
	List<RequestDetails> showRequests();

	List<BookIssue> showIssuedBooks();
	
	 List<User> showUsers();
}
