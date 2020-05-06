package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssue;
import com.capgemini.librarymanagementsystem_hibernate.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_hibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.User;
import com.capgemini.librarymanagementsystem_hibernate.exception.LMSException;

public class UserDaoImplements implements UserDao {

	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean registerUser(User user) {

		boolean isRegistered = false;
		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
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

			factory = Persistence.createEntityManagerFactory("TestPersistence");
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
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean request(int bookId, int id) {

		int count = 0;
		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookDetails b where b.bookId=:bookId";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bId", bookId);
			List rs = query.getResultList();
			if (rs != null) {
				String jpql1 = "select b from BooksBorrowed b where b.id=:id and b.bookId=:bookId";
				TypedQuery<BooksBorrowed> query1 = (TypedQuery<BooksBorrowed>) manager.createQuery(jpql1,
						BooksBorrowed.class);
				//
				query1.setParameter("Id", id);
				query1.setParameter("bId", bookId);
				List rs1 = query1.getResultList();
				if (rs1.isEmpty() || rs1 == null) {
					String jpql2 = "select b from BookIssue b where b.id=:id";
					TypedQuery<BookIssue> query2 = (TypedQuery<BookIssue>) manager.createQuery(jpql2, BookIssue.class);
					query2.setParameter("id", id);
					List<BookIssue> rs2 = query2.getResultList();
					for (BookIssue p : rs2) {
						noOfBooks = count++;
					}
					if (noOfBooks < 3) {
						Query bookName = manager
								.createQuery("select b.bookName from Bookdetails b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from User u where u.id=:id");
						email.setParameter("id", id);
						List userEmail = email.getResultList();
						transaction.begin();
						RequestDetails request = new RequestDetails();
						//
						request.setId(id);
						request.setBookId(bookId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;

					} else {
						throw new LMSException("You have crossed the book limit");
					}
				} else {
					throw new LMSException("You have already borrowed the requested book");
				}
			} else {
				throw new LMSException("The book with requested id is not present");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public boolean addBook(BookDetails bookDetail) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(bookDetail);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public boolean removeBook(int bookId) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookDetails record = manager.find(BookDetails.class, bookId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean updateBook(BookDetails book) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookDetails record = manager.find(BookDetails.class, book.getBookId());
			record.setBookName(book.getBookName());
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public boolean bookIssue(int bookId, int id) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from bookDetails b where b.bookId=:bookId";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bId", bookId);
			BookDetails rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select r from RequestDetails r where r.uId=:uId and r.bookId=:bookId";
				TypedQuery<RequestDetails> query1 = manager.createQuery(jpql1, RequestDetails.class);
				//
				query1.setParameter("id", id);
				query1.setParameter("bookId", bookId);
				List<RequestDetails> rs1 = query1.getResultList();
				if (!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssue issueBook = new BookIssue();
					issueBook.setId(id);
					issueBook.setBookId(bookId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if (!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager
								.createQuery("select b.bookName from BookDetails b where b.bookId=:bookId");
						bookName.setParameter("bId", bookId);
						List book = bookName.getResultList();
						BooksBorrowed borrowedBooks = new BooksBorrowed();
						//
						borrowedBooks.setId(id);
						borrowedBooks.setBookId(bookId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					} else {
						throw new LMSException("Book Not issued");
					}
				} else {
					throw new LMSException("The respective user have not placed any request");
				}
			} else {
				throw new LMSException("There is no book exist with bookId" + bookId);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public List<BookDetails> searchBookByTitle(String bookName) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookDetails b where b.bookName=:bookName";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookName", bookName);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookDetails> searchBookByAuthor(String authorName) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookDetails b where b.authorName=:authorName";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("authorName", authorName);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public List<BookDetails> searchBookById(int bookId) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Bookdetails b where b.bookId=:bookId";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookId", bookId);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookDetails> getBooksInfo() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from Bookdetails b";
		TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
		List<BookDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

	@Override
	public boolean returnBook(int bookId, int id, String status) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookDetails b where b.bookId=:bookId";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookId", bookId);
			BookDetails rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select b from BookIssue b where b.bookId=:bookId and b.id=:id ";
				TypedQuery<BookIssue> query1 = manager.createQuery(jpql1, BookIssue.class);
				query1.setParameter("bookId", bookId);
				//
				query1.setParameter("id", id);
				BookIssue rs1 = query1.getSingleResult();
				if (rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000 * 60 * 60 * 24));
					if (daysBetween > 7.0) {
						// transaction.begin();
						float fine = daysBetween * 5;
						System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
						if (status == "yes") {
							transaction.begin();
							manager.remove(rs1);
							transaction.commit();
							transaction.begin();
							String jpql3 = "select b from booksBorrowed b  where b.bookId=:bookId and b.id=:id";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bookId", bookId);
							query3.setParameter("id", id);
							BooksBorrowed bbb = (BooksBorrowed) query3.getSingleResult();
							// int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from RequestDetails r where r.bookId=:bookId and r.id=:id";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bookId", bookId);
							query4.setParameter("id", id);
							RequestDetails rdb = (RequestDetails) query4.getSingleResult();
							// int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						} else {
							throw new LMSException("The User has to pay fine for delaying book return");
						}
					} else {
						transaction.begin();

						manager.remove(rs1);
						transaction.commit();

						transaction.begin();
						String jpql3 = "select b from BooksBorrowed b  where b.bookId=:bId and b.id=:uid";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bookId", bookId);
						//
						query3.setParameter("id", id);
						BooksBorrowed bbb = (BooksBorrowed) query3.getSingleResult();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from RequestDetails r where r.bookId=:bookId and r.uId=:uId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bookId", bookId);
						//
						query4.setParameter("id", id);
						RequestDetails rdb = (RequestDetails) query4.getSingleResult();
						// int rdb_Id = rdb.getId();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				} else {
					throw new LMSException("This respective user hasn't borrowed any book");
				}
			} else {
				throw new LMSException("book doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public LinkedList<Integer> bookHistoryDetails(int id) {

		int count = 0;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssue b";
		TypedQuery<BookIssue> query = manager.createQuery(jpql, BookIssue.class);
		List<BookIssue> recordList = query.getResultList();
		for (BookIssue p : recordList) {
			noOfBooks = count++;
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(noOfBooks);
		manager.close();
		factory.close();
		//
		return list;

	}

	@Override
	public List<BooksBorrowed> borrowedBook(int id) {

		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BooksBorrowed b where b.uId=:uId";
			TypedQuery<BooksBorrowed> query = manager.createQuery(jpql, BooksBorrowed.class);
			//
			query.setParameter("uId", id);
			List<BooksBorrowed> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<RequestDetails> showRequests() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestDetails r";
		TypedQuery<RequestDetails> query = manager.createQuery(jpql, RequestDetails.class);
		List<RequestDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookIssue> showIssuedBooks() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssue b";
		TypedQuery<BookIssue> query = manager.createQuery(jpql, BookIssue.class);
		List<BookIssue> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<User> showUsers() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select u from User u";
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		List<User> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
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
		} finally {
			manager.close();
			factory.close();
		}
	}
}
