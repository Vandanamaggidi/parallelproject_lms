package com.capgemini.librarymanagementsystem_jdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserServiceImplements;

public class AdminUserServiceTest {

	private AdminUserService service= new AdminUserServiceImplements();
	
	@Test
	public void testRegisterUser() {
		User user = new User();
		user.setId(54321);
		user.setFirstName("chintu");
		user.setLastName("qwerty");
		user.setEmail("qwerty@gmail.com");
		user.setPassword("qwerty");
		user.setMobileNo(987654321);
		user.setRole("student");
		boolean result = service.registerUser(user);
		Assertions.assertTrue(result);
	}
	
	
	@Test
	public void testRegisterUsers() {
		User user = new User();
		user.setId(54321);
		user.setFirstName("chintu");
		user.setLastName("qwerty");
		user.setEmail("qwerty@gmail.com");
		user.setPassword("qwerty");
		user.setMobileNo(987654321);
		user.setRole("student");
		boolean result = service.registerUser(user);
		Assertions.assertFalse(result);
	}
	
	@Test
	public void  testLoginUser() {
		User result = service.authUser("dhana@gmail.com", "Dhana23@");
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void  testLoginUsers() {
		User result = service.authUser("dhana@gmail.com", "Dhana23@");
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void testBookByTitle() {
		List<BookDetails> result = service.searchBookByTitle("sql");
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	public void testBookByTitles() {
		List<BookDetails> result = service.searchBookByTitle("sql");
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void testBookByAuthor() {
		List<BookDetails> result = service.searchBookByAuthor("BenForta");
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void testBookByAuthors() {
		List<BookDetails> result = service.searchBookByAuthor("BenForta");
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	public void testBookById() {
		List<BookDetails> result = service.searchBookById(12);
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	public void testBookByIds() {
		List<BookDetails> result = service.searchBookById(12);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void testBooksInfo() {
		List<BookDetails> result = service.getBooksInfo();
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	public void testBooksInfos() {
		List<BookDetails> result = service.getBooksInfo();
		Assertions.assertNotNull(result);
	}
	
	
}
