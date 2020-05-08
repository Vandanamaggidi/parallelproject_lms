package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class AdminServiceImplements implements AdminService{

	private AdminDao dao = LibraryFactory.getAdminDao();
	
	@Override
	public boolean addBook(BookDetails bookDetail) {
		// TODO Auto-generated method stub
		return dao.addBook(bookDetail);
	}

	@Override
	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookDetails book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean bookIssue(int bookId, int id) {
		// TODO Auto-generated method stub
		return dao.bookIssue(bookId, id);
	}

	@Override
	public List<RequestDetails> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	@Override
	public List<BookIssue> showIssuedBooks() {
		// TODO Auto-generated method stub
		return dao.showIssuedBooks();
	}

	@Override
	public List<User> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

	

}
