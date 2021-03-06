package com.capgemini.librarymanagementsystem.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class AdminDaoImplements implements AdminDao {

	@Override
	public boolean registerAdmin(Admin admin) {

		for (Admin ad : LibraryDB.admins) {
			if (ad.getEmail().equals(admin.getEmail())) {
				return false;
			}
		}
		LibraryDB.admins.add(admin);
		return true;

	}

	@Override
	public Admin authAdmin(String email, String password) {

		//Admin admins = new Admin();
		for (Admin admin : LibraryDB.admins) {
		if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
			return admin;
		}
		}

		throw new LMSException("Invalid credentials");
	}

	@Override
	public boolean addBook(BookDetails bookDetails) {

		for (BookDetails b : LibraryDB.bookDetails) {
			if (b.getBookId() == bookDetails.getBookId()) {
				return false;
			}
		}
		LibraryDB.bookDetails.add(bookDetails);
		return true;

	}

	@Override
	public boolean removeBook(int bookId) {

		boolean removeStatus = false;
		for (int i = 0; i <= LibraryDB.bookDetails.size() - 1; i++) {
			BookDetails retrievedBook = LibraryDB.bookDetails.get(i);
			int retrievedId = retrievedBook.getBookId();
			if (bookId == retrievedId) {
				removeStatus = true;
				LibraryDB.bookDetails.remove(i);
				break;
			}
		}
		return removeStatus;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean updateBook(BookDetails book) {

		for (int i = 0; i <= LibraryDB.bookDetails.size() - 1; i++) {
			BookDetails retrievedBook = LibraryDB.bookDetails.get(i);
			if (retrievedBook.getBookId() == book.getBookId()) {
				retrievedBook.setAuthorName(book.getBookName());
				retrievedBook.setAuthorName(book.getAuthorName());
				retrievedBook.setBookCategory(book.getBookCategory());
				return true;
			}

			else {
				throw new LMSException("Invalid Book");
			}
		}
		throw new LMSException("Book not updated");

	}

	@Override
	public List<BookDetails> searchBookBycategory(String bookCategory) {

		LinkedList<BookDetails> searchList = new LinkedList<BookDetails>();
		for (int i = 0; i <= LibraryDB.bookDetails.size() - 1; i++) {
			BookDetails retrievedBook = LibraryDB.bookDetails.get(i);
			String retrievedCategory = retrievedBook.getBookCategory();
			if (bookCategory.equals(retrievedCategory)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}

	}

	@Override
	public LinkedList<BookDetails> searchBookByTitle(String bookName) {

		LinkedList<BookDetails> searchList = new LinkedList<BookDetails>();
		for (int i = 0; i <= LibraryDB.bookDetails.size() - 1; i++) {
			BookDetails retrievedBook = LibraryDB.bookDetails.get(i);
			String retrievedBookName = retrievedBook.getBookName();
			if (bookName.equals(retrievedBookName)) {
				searchList.add(retrievedBook);
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}
	}

	@Override
	public LinkedList<BookDetails> searchBookByAuthor(String bookAuthor) {

		LinkedList<BookDetails> searchList = new LinkedList<BookDetails>();
		for (int i = 0; i <= LibraryDB.bookDetails.size() - 1; i++) {
			BookDetails retrievedBook = LibraryDB.bookDetails.get(i);
			String retrievedBookAuthor = retrievedBook.getAuthorName();
			if (bookAuthor.equals(retrievedBookAuthor)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}

	}

	@Override
	public List<BookDetails> getBooksInfo() {

		return LibraryDB.bookDetails;

	}

	@Override
	public List<User> showUsers() {

		List<User> usersList = new LinkedList<User>();
		for (User userBean : LibraryDB.users) {

			userBean.getUserId();
			userBean.getUserName();
			userBean.getEmail();
			userBean.getNoOfBooksBorrowed();
			usersList.add(userBean);

		}
		return usersList;
	}

	@Override
	public List<Request> showRequests() {

		List<Request> info = new LinkedList<Request>();
		for (Request requestInfo : LibraryDB.REQUEST) {
			requestInfo.getBookDetails();
			requestInfo.getUserInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			info.add(requestInfo);
		}
		return info;
	}

	@Override
	public boolean bookIssue(User user, BookDetails bookDetails) {

		boolean isValid = false;

		Request requestInfo = new Request();

		int noOfBooksBorrowed = user.getNoOfBooksBorrowed();
		for (Request info : LibraryDB.REQUEST) {
			if (info.getUserInfo().getUserId() == user.getUserId()) {
				if (info.getBookDetails().getBookId() == bookDetails.getBookId()) {
					requestInfo = info;

					isValid = true;

				}
			}
		}

		if (isValid) {
			for (BookDetails info2 : LibraryDB.bookDetails) {
				if (info2.getBookId() == bookDetails.getBookId()) {
					bookDetails = info2;
				}
			}

			for (User userInfo : LibraryDB.users) {
				if (userInfo.getUserId() == user.getUserId()) {
					user = userInfo;
					noOfBooksBorrowed = user.getNoOfBooksBorrowed();

				}
			}

			if (noOfBooksBorrowed < 3) {

				boolean isRemoved = LibraryDB.bookDetails.remove(bookDetails);
				if (isRemoved) {

					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					user.setNoOfBooksBorrowed(noOfBooksBorrowed);
					// DataBase.REQUESTDB.remove(requestInfo);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new LMSException("Book can't be borrowed");
				}

			} else {
				throw new LMSException("Student Exceeds maximum limit");
			}

		} else {
			throw new LMSException("Book data or Student data is incorrect");
		}
	}

	@Override
	public boolean isBookReceived(User user, BookDetails bookDetails) {

		boolean isValid = false;
		Request requestInfo1 = new Request();
		for (Request requestInfo : LibraryDB.REQUEST) {

			if (requestInfo.getBookDetails().getBookId() == bookDetails.getBookId()
					&& requestInfo.getUserInfo().getUserId() == user.getUserId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			bookDetails.setAuthorName(requestInfo1.getBookDetails().getAuthorName());
			bookDetails.setBookName(requestInfo1.getBookDetails().getBookName());
			LibraryDB.bookDetails.add(bookDetails);
			LibraryDB.REQUEST.remove(requestInfo1);

			for (User userInfo2 : LibraryDB.users) {
				if (userInfo2.getUserId() == user.getUserId()) {
					user = userInfo2;
				}
			}
			int noOfBooksBorrowed = user.getNoOfBooksBorrowed();
			noOfBooksBorrowed--;
			user.setNoOfBooksBorrowed(noOfBooksBorrowed);
			return true;
		}
		return false;
	}

}
