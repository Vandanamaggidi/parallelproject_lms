package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.AdminDao;
import com.capgemini.librarymanagementsystem.dao.AdminDaoImplements;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class AdminDaoTest {

	private AdminDao dao=new AdminDaoImplements();
	BookDetails info=new BookDetails();
	
	@Test
	public void testaddBook() {
		info.setBookId(12345);
		info.setBookName("sql");
		info.setAuthorName("Ben forta");
		info.setBookCategory("programming");
		info.setPublisherName("google");
		boolean status=dao.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin() {
		Admin admin=new Admin();
		admin.setAdminId(12345);
		admin.setName("usha");
		admin.setMobileNo(994920634);
		admin.setEmail("usha@gmail.com");
		admin.setPassword("Usha@123");
		boolean status=dao.registerAdmin(admin);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook() {
		info.setBookId(12345);
		info.setBookName("sql");
		info.setAuthorName("BenForta");
		info.setBookCategory("programming");
		info.setPublisherName("google");
		boolean status=dao.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook() {
		info.setBookId(11111);
		info.setBookName("jdbc");
		info.setAuthorName("ayan");
		info.setBookCategory("aptitude");
		info.setPublisherName("sk");
		boolean status=dao.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		Admin status = dao.authAdmin("van@gmail.com", "Van23@");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle() {
		List<BookDetails> info = dao.searchBookByTitle("sql");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor() {
		List<BookDetails> info = dao.searchBookByAuthor("BenForta");
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
	public void testShowStudents() {
		List<User> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowRequests() {
		List<Request> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testaddBook1() {
		info.setBookId(4567);
		info.setBookName("servlet");
		info.setAuthorName("GiridharSir");
		info.setBookCategory("java");
		info.setPublisherName("servers");
		boolean status=dao.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin1() {
		Admin adn=new Admin();
		adn.setAdminId(12345);
		adn.setName("chary");
		adn.setMobileNo(728598698);
		adn.setEmail("chary@gmail.com");
		adn.setPassword("Chary@123");
		boolean status=dao.registerAdmin(adn);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook1() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setAuthorName("jamesgosling");
		info.setBookCategory("java");
		info.setPublisherName("sunmicrosystems");
		boolean status=dao.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook1() {
		info.setBookId(11111);
		info.setBookName("javajdbc");
		info.setAuthorName("rknarayan");
		info.setBookCategory("aptitude");
		info.setPublisherName("skpublications");
		boolean status=dao.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		Admin status = dao.authAdmin("van@gmail.com", "van23@");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		List<BookDetails> info = dao.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		List<BookDetails> info = dao.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		List<BookDetails> info = dao.searchBookBycategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		List<BookDetails> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowStudents1() {
		List<User> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowRequests1() {
		List<Request> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}
	
}
