package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.UserDao;
import com.capgemini.librarymanagementsystem.dao.UserDaoImplememts;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.User;

public class UserDaoTest {

	private UserDao dao=new UserDaoImplememts();
	BookDetails info=new BookDetails();
	
	@Test
	public void testRegisterStudent() {
		User info=new User();
		info.setUserId(1234);
		info.setUserName("dhana");
		info.setMobileNo(987456321);
		info.setEmail("dhana@gmail.com");
		info.setPassword("Dan23@");
		boolean status=dao.registerUser(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateUser() {
		User status = dao.authUser("van@gmail.com", "Van23@");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle() {
		List<BookDetails> info = dao.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor() {
		List<BookDetails> info = dao.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory() {
		List<BookDetails> info = dao.searchBookBycategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo() {
		List<BookDetails> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	
	@Test
	public void testRegisterStudent1() {
		User info=new User();
		info.setUserId(1234);
		info.setUserName("dhana");
		info.setMobileNo(987456321);
		info.setEmail("dhana@gmail.com");
		info.setPassword("Dan23@");
		boolean status=dao.registerUser(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testAuthenticateUser1() {
		User status = dao.authUser("van@gmail.com", "Van23@");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		List<BookDetails> info = dao.searchBookByTitle("sql");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		List<BookDetails> info = dao.searchBookByAuthor("BenForta");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		List<BookDetails> info = dao.searchBookBycategory("maths");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		List<BookDetails> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	
}
