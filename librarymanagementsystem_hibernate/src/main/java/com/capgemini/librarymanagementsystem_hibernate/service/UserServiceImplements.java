package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dao.UserDao;
import com.capgemini.librarymanagementsystem_hibernate.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_hibernate.factory.LibraryFactory;

public class UserServiceImplements implements UserService {

	private UserDao dao = LibraryFactory.getUserDao();


	@Override
	public boolean returnBook(int bookId, int id, String status) {
		// TODO Auto-generated method stub
		return dao.returnBook(bookId, id, status);
	}

	@Override
	public LinkedList<Integer> bookHistoryDetails(int id) {
		// TODO Auto-generated method stub
		return dao.bookHistoryDetails(id);
	}

	@Override
	public List<BooksBorrowed> borrowedBook(int id) {
		// TODO Auto-generated method stub
		return dao.borrowedBook(id);
	}

	@Override
	public boolean request(int bookId, int id) {
		// TODO Auto-generated method stub
		return dao.request(bookId, id);
	}

}
