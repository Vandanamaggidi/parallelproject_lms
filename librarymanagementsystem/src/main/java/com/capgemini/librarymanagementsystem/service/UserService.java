package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface UserService {

	boolean registerUser(User user);

	User authUser(String email, String password);

	public Request bookRequest(User user, BookDetails bookDetails);

	public Request bookReturn(User user, BookDetails bookDetails);

	List<BookDetails> searchBookByTitle(String bookName);

	List<BookDetails> searchBookByAuthor(String authorName);

	List<BookDetails> searchBookBycategory(String bookCategory);

	List<BookDetails> getBooksInfo();
	

}
