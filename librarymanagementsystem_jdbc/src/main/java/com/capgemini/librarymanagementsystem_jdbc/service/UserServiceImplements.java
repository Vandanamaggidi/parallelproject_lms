package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class UserServiceImplements implements UserService {

	private UserDao dao = LibraryFactory.getUserDao();

	@Override
	public boolean request(int bookId, int id) {
		// TODO Auto-generated method stub
		return dao.request(bookId, id);
	}

	@Override
	public BookDetails borrow(int bookId) {
		// TODO Auto-generated method stub
		return dao.borrow(bookId);
	}

	@Override
	public boolean returnBook(int bookId, int id, String status) {
		// TODO Auto-generated method stub
		return dao.returnBook(bookId, id, status);
	}

	@Override
	public LinkedList<BookIssue> bookHistoryDetails(int uId) {
		// TODO Auto-generated method stub
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<BooksBorrowed> borrowedBook(int id) {
		// TODO Auto-generated method stub
		return dao.borrowedBook(id);
	}


}
