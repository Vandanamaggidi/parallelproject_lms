package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dao.AdminDao;
import com.capgemini.librarymanagementsystem.dao.UserDao;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;

public class UserServiceImplements implements UserService {

	private UserDao dao = LibraryFactory.getUserDao();

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return dao.registerUser(user);
	}

	@Override
	public User authUser(String email, String password) {
		// TODO Auto-generated method stub
		return dao.authUser(email, password);
	}

	@Override
	public Request bookRequest(User user, BookDetails bookDetails) {
		// TODO Auto-generated method stub
		return dao.bookRequest(user, bookDetails);
	}

	@Override
	public Request bookReturn(User user, BookDetails bookDetails) {
		// TODO Auto-generated method stub
		return dao.bookReturn(user, bookDetails);
	}

	@Override
	public LinkedList<BookDetails> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<BookDetails> searchBookByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return dao.searchBookByAuthor(authorName);
	}

	@Override
	public List<BookDetails> searchBookBycategory(String bookCategory) {
		// TODO Auto-generated method stub
		return dao.searchBookBycategory(bookCategory);
	}

	@Override
	public LinkedList<BookDetails> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

}
