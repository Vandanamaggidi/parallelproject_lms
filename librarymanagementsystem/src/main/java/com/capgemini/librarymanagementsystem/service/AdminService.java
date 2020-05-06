package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface AdminService {

	boolean registerAdmin(Admin admin);

	Admin authAdmin(String email, String password);

	boolean addBook(BookDetails bookDetails);

	boolean removeBook(int bookId);

	boolean updateBook(BookDetails book);

	List<BookDetails> searchBookBycategory(String bookCategory);

	LinkedList<BookDetails> searchBookByTitle(String bookName);

	LinkedList<BookDetails> searchBookByAuthor(String bookAuthor);

	LinkedList<BookDetails> getBooksInfo();

	List<User> showUsers();

	List<Request> showRequests();

	boolean bookIssue(User user, BookDetails bookDetails);

	boolean isBookReceived(User user, BookDetails bookDetails);
}
