package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDaoImplements;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssue;
import com.capgemini.librarymanagementsystem_hibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;

public class AdminServiceImplements implements AdminService{

	private AdminDao dao = new AdminDaoImplements();
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
