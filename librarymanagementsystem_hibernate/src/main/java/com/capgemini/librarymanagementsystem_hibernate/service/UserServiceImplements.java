package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dao.UserDao;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssue;
import com.capgemini.librarymanagementsystem_hibernate.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_hibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;
import com.capgemini.librarymanagementsystem_hibernate.factory.LibraryFactory;

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
	public boolean request(int bookId, int id) {
		// TODO Auto-generated method stub
		return dao.request(bookId, id);
	}

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

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(id, password, newPassword, role);
	}

}
