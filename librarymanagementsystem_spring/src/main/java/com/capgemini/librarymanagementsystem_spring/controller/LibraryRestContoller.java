package com.capgemini.librarymanagementsystem_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystem_spring.dto.BookDetails;
import com.capgemini.librarymanagementsystem_spring.dto.BookIssue;
import com.capgemini.librarymanagementsystem_spring.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_spring.dto.LibraryResponse;
import com.capgemini.librarymanagementsystem_spring.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_spring.dto.User;
import com.capgemini.librarymanagementsystem_spring.service.AdminService;
import com.capgemini.librarymanagementsystem_spring.service.AdminUserService;
import com.capgemini.librarymanagementsystem_spring.service.UserService;

@RestController
public class LibraryRestContoller {

	@Autowired
	private UserService service;
	@Autowired
	private AdminService service1;
	@Autowired
	private AdminUserService service3 ;

	@PostMapping(path = "/addUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public LibraryResponse addUser(@RequestBody User bean) {
		boolean isAdded = service3.registerUser(bean);
		LibraryResponse response = new LibraryResponse();
		if (isAdded) {
			response.setMessage("record inserted");
		} else {
			response.setError(true);
			response.setMessage("unable to add");
		}
		return response;
	}

	@PostMapping(path = "/addBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse addBook(@RequestBody BookDetails bean) {
		boolean isBookAdded = service1.addBook(bean);
		LibraryResponse response = new LibraryResponse();
		if (isBookAdded) {
			response.setMessage("Book added succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be added");
		}
		return response;

	}

	@PutMapping(path = "/updateBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse updateBook(@RequestBody BookDetails bean) {
		boolean isBookUpdated = service1.updateBook(bean);
		LibraryResponse response = new LibraryResponse();
		if (isBookUpdated) {
			response.setMessage("Book updated succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be updated");
		}
		return response;

	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse authentication(String email, String password) {
		User userLogin = service3.authUser(email, password);
		LibraryResponse response = new LibraryResponse();
		if (userLogin != null) {
			response.setMessage("Login succesfully");
		} else {
			response.setError(true);
			response.setMessage("Invalid credentials,Please try again");
		}
	 	return response;
 	}

	@DeleteMapping(path = "/removeBook/{bookId} ", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse deleteBook(@PathVariable(name = "bookId") int bookId) {
		boolean isBookDeleted = service1.removeBook(bookId);
		LibraryResponse response = new LibraryResponse();
		if (isBookDeleted) {
			response.setMessage("Book deleted succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book not deleted");
		}
		return response;
	}

	@GetMapping(path = "/booksInfo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse getBookInfo() {
		List<BookDetails> getInfo = service3.getBooksInfo();
		LibraryResponse response = new LibraryResponse();
		if (getInfo != null && !getInfo.isEmpty()) {
			response.setMessage("Books found");
			response.setBook(getInfo);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/booksByName", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse getBookByName(String bookName) {
		List<BookDetails> bean = service3.searchBookByTitle(bookName);
		LibraryResponse response = new LibraryResponse();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/booksByAuthor", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse getBookByAuthor(String authorName) {
		List<BookDetails> bean = service3.searchBookByAuthor(authorName);
		LibraryResponse response = new LibraryResponse();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/booksById", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse getBookById(int bookId) {
		List<BookDetails> bean = service3.searchBookById(bookId);
		LibraryResponse response = new LibraryResponse();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@PostMapping(path = "/bookIssue", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse issueBook(int id, int bookId) {
		boolean isBookIssued = service1.bookIssue(id, bookId);
		LibraryResponse response = new LibraryResponse();
	 	if (isBookIssued) {
			response.setMessage("Book issued succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be issued");
		}
		return response;
	}

	@PostMapping(path = "/returnBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse returnBook(int bookId, int id, String status) {
		boolean isBookReturned = service.returnBook(bookId, id, status);
		LibraryResponse response = new LibraryResponse();
		if (isBookReturned) {
			response.setMessage("Book returned succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be returned");
		}
		return response;
	}

	@PostMapping(path = "/requestBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse requestBook( int bookId, int id) {
		boolean isBookRequested = service.request(bookId, id);
		LibraryResponse response = new LibraryResponse();
		if (isBookRequested) {
			response.setMessage("Book requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be requested");
		}
		return response;
	}

	@GetMapping(path = "/showRequests", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse showRequests() {
		List<RequestDetails> detailList = service1.showRequests();
		LibraryResponse response = new LibraryResponse();

		if (detailList != null && !detailList.isEmpty()) {
			response.setDetails(detailList);
		} else {
			response.setError(true);
			response.setMessage("They are no requests");
		}
		return response;
	}

	@GetMapping(path = "/showIssuedBooks", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse showIssuedBooks() {
		List<BookIssue> issueList = service1.showIssuedBooks();
		LibraryResponse response = new LibraryResponse();

		if (issueList != null && !issueList.isEmpty()) {
			response.setIssue(issueList);
		} else {
			response.setError(true);
			response.setMessage("No Books are Issued");
		}
		return response;
	}

	@GetMapping(path = "/showUsers", produces = { MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse showUsers() {
		List<User> usersList = service1.showUsers();
		LibraryResponse response = new LibraryResponse();

		if (usersList != null && !usersList.isEmpty()) {
			response.setUsers(usersList);
		} else {
			response.setError(true);
			response.setMessage("They are no Users");
		}
		return response;
	}

	@PutMapping(path = "/updatePassword", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse updatePassord(int id, String password, String newPassword, String role) {
		boolean isUpdated = service3.updatePassword(id, password, newPassword, role);
		LibraryResponse response = new LibraryResponse();

		if (isUpdated) {
			response.setMessage("Password updated successfully");
		} else {
			response.setError(true);
			response.setMessage("Password is not updated");
		}
		return response;
	}

	@GetMapping(path = "/borrowedBooks", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse getBorrowedBooks(int id) {
		List<BooksBorrowed> borrowed = service.borrowedBook(id);
		LibraryResponse response = new LibraryResponse();

		if (borrowed != null && !borrowed.isEmpty()) {
			response.setBorrowed(borrowed);
		} else {
			response.setError(true);
			response.setMessage("There are no borrowed  books");
		}
		return response;
	}

}
