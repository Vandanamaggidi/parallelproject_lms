package com.capgemini.librarymanagementsystem.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface UserDao {

	boolean registerUser(User user);

	User authUser(String email, String password);

	public Request bookRequest(User user, BookDetails bookDetails);

	public Request bookReturn(User user, BookDetails bookDetails);

	List<BookDetails> searchBookBycategory(String bookCategory);

	LinkedList<BookDetails> searchBookByTitle(String bookName);

	LinkedList<BookDetails> searchBookByAuthor(String authorName);

	LinkedList<BookDetails> getBooksInfo();

}
