package com.capgemini.librarymanagementsystem_hibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_hibernate.dao.UserDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.UserDaoImplements;
import com.capgemini.librarymanagementsystem_hibernate.dto.BooksBorrowed;

public class UserDaoTest {

	private UserDao dao = new UserDaoImplements();
	
	@Test
	public void testRequestBook() {
		boolean result = dao.request(54321, 54321);
		Assertions.assertTrue(result);
	}
	@Test
	public void testReturnBook() {
		boolean result = dao.returnBook(54321, 54321, "yes");
		Assertions.assertTrue(result);
	}
	@Test
	public void testBorrowedBook() {
		List<BooksBorrowed> info = dao.borrowedBook(54321);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testRequest1() {
		boolean result = dao.request(54321, 54321);
		Assertions.assertNotNull(result);
	}
	@Test
	public void testReturnBooks() {
		boolean result = dao.returnBook(54321, 54321, "yes");
		Assertions.assertNotNull(result);
	}
	@Test
	public void testBorrowedBook1() {
		List<BooksBorrowed> result = dao.borrowedBook(54321);
		Assertions.assertNotNull(result);
	}
	
}
