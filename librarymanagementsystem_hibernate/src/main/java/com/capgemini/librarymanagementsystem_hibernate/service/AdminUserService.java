package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;

public interface AdminUserService {

	boolean registerUser(User user);

	User authUser(String email, String password);
	
	List<BookDetails> searchBookByTitle(String bookName);

	List<BookDetails> searchBookByAuthor(String authorName);

	List<BookDetails> searchBookById(int bookId);

	List<BookDetails> getBooksInfo();
	
	boolean updatePassword(int id, String password, String newPassword, String role);
}
