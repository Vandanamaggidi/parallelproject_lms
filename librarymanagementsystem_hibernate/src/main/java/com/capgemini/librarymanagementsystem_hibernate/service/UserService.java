package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssue;
import com.capgemini.librarymanagementsystem_hibernate.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_hibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;

public interface UserService {

	boolean registerUser(User user);

	User authUser(String email, String password);

	boolean request(int bookId, int id);
	// BookDetails borrow(int bookId);

	boolean addBook(BookDetails bookDetail);

	boolean removeBook(int bookId);

	boolean updateBook(BookDetails book);

	boolean bookIssue(int bookId, int id);

	List<BookDetails> searchBookByTitle(String bookName);

	List<BookDetails> searchBookByAuthor(String authorName);

	List<BookDetails> searchBookById(int bookId);

	List<BookDetails> getBooksInfo();

	boolean returnBook(int bookId, int id, String status);

	LinkedList<Integer> bookHistoryDetails(int id);

	List<BooksBorrowed> borrowedBook(int id);

	List<RequestDetails> showRequests();

	List<BookIssue> showIssuedBooks();

	List<User> showUsers();

	boolean updatePassword(int id, String password, String newPassword, String role);

}
