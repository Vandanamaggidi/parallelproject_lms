package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class AdminUserServiceImplements implements AdminUserService{

	private AdminUserDao dao = LibraryFactory.getAdminUserDao();
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
	public List<BookDetails> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookDetails> searchBookByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return dao.searchBookByAuthor(authorName);
	}

	@Override
	public List<BookDetails> searchBookById(int bookId) {
		// TODO Auto-generated method stub
		return dao.searchBookById(bookId);
	}

	@Override
	public List<BookDetails> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(email, password, newPassword, role);
	}

}
