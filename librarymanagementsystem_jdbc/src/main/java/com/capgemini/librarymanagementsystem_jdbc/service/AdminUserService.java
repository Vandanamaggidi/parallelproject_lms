package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;

public interface AdminUserService {

	boolean registerUser(User user);

	User authUser(String email, String password);
	
	List<BookDetails> searchBookByTitle(String bookName);

	List<BookDetails> searchBookByAuthor(String authorName);

	List<BookDetails> searchBookById(int bookId);

	List<BookDetails> getBooksInfo();
	
	boolean updatePassword(String email, String password, String newPassword, String role);
}
