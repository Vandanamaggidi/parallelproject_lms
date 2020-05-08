package com.capgemini.librarymanagementsystem_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystem_spring.dao.AdminDao;
import com.capgemini.librarymanagementsystem_spring.dto.BookDetails;
import com.capgemini.librarymanagementsystem_spring.dto.BookIssue;
import com.capgemini.librarymanagementsystem_spring.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_spring.dto.User;

@Service
public class AdminServiceImplements implements AdminService{

	@Autowired
	private AdminDao dao;
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
