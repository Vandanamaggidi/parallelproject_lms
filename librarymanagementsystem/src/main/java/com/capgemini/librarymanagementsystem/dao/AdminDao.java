package com.capgemini.librarymanagementsystem.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface AdminDao {

	boolean registerAdmin(Admin admin);

	Admin authAdmin(String email, String password);

	boolean addBook(BookDetails bookDetails);

	boolean removeBook(int bookId);

	List<BookDetails> searchBookBycategory(String bookCategory);

	List<BookDetails> searchBookByTitle(String bookName);

	List<BookDetails> searchBookByAuthor(String bookAuthor);

	List<BookDetails> getBooksInfo();

	List<User> showUsers();

	List<Request> showRequests();

	boolean bookIssue(User user, BookDetails bookDetails);

	boolean isBookReceived(User user, BookDetails bookDetails);

	boolean updateBook(BookDetails book);

}
