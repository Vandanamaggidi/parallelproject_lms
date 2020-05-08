package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;
import com.capgemini.librarymanagementsystem_hibernate.exception.LMSException;

public class AdminUserDaoImplements implements AdminUserDao{

	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;
	@Override
	public boolean registerUser(User user) {
		
		boolean isRegistered = false;
		try {

			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			isRegistered = true;
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			// return false;
		}
		return isRegistered;
	}

	@Override
	public User authUser(String email, String password) {
		
		try {

			manager = factory.createEntityManager();
			String jpql = "select u from User u where u.email=:email and u.password=:password";
			TypedQuery<User> query = manager.createQuery(jpql, User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			User bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} 
	}

	@Override
	public List<BookDetails> searchBookByTitle(String bookName) {
		
		try {

			manager = factory.createEntityManager();
			String jpql = "select b from BookDetails b where b.bookName=:bookName";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookName", bookName);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} 
	}

	@Override
	public List<BookDetails> searchBookByAuthor(String authorName) {
		
		try {

			manager = factory.createEntityManager();
			String jpql = "select b from BookDetails b where b.authorName=:authorName";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("authorName", authorName);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} 
	}

	@Override
	public List<BookDetails> searchBookById(int bookId) {
		
		try {

			manager = factory.createEntityManager();
			String jpql = "select b from Bookdetails b where b.bookId=:bookId";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookId", bookId);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} 
	}

	@Override
	public List<BookDetails> getBooksInfo() {
		manager = factory.createEntityManager();
		String jpql = "select b from Bookdetails b";
		TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
		List<BookDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
	

		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from User u where u.id=:id and u.role=:role and u.password=:password";
			TypedQuery<User> query = manager.createQuery(jpql, User.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			User rs = query.getSingleResult();
			if (rs != null) {
				User record = manager.find(User.class, id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;
			} else {
				throw new LMSException("User doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}
	}

}
